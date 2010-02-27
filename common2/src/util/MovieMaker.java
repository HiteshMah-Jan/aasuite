/*
 * MovieMaker.java
 *
 * Created on Feb 28, 2008, 1:33:08 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author Entokwaa
 */
import java.io.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import javax.media.control.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.datasink.*;
import javax.media.format.VideoFormat;
import javax.media.util.ImageToBuffer;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * This class creates QuickTime movies (.mov) or AVIs (.avi) out of a list of JPEG files or out
 * of an array of java.awt.Image objects.
 */
public class MovieMaker implements ControllerListener, DataSinkListener {

    private final Object waitSync = new Object();
    private final Object waitFileSync = new Object();
    private final Processor processor;
    private final MediaLocator outML;

    private boolean stateTransitionOK = true;
    private boolean fileDone = false;
    private boolean fileSuccess = true;

    public static File[] getFiles(Vector<String> vec) {
        File[] files = new File[vec.size()];
        for (int i = 0; i < vec.size(); i++) {
            files[i] = new File(vec.get(i));
        }
        return files;
    }

    public MovieMaker(int width, int height, int frameRate, File outputFile, File[] jpegFiles) throws Exception {
        this(outputFile, new ImageDataSource(width, height, frameRate, jpegFiles));
    }

    public MovieMaker(int width, int height, int frameRate, File outputFile, Image[] images) throws Exception {
        this(outputFile, new ImageDataSource(width, height, frameRate, images));
    }

    private MovieMaker(File outputFile, ImageDataSource ids) throws Exception {
        this.processor = createProcessor(outputFile, ids);
        this.outML = new MediaLocator(outputFile.toURL());
    }

    private Processor createProcessor(File outputFile, ImageDataSource ids) throws Exception {
        System.out.println("- create processor for the image datasource ...");
        Processor p = Manager.createProcessor(ids);

        p.addControllerListener(this);

        // Put the Processor into configured state so we can set
        // some processing options on the processor.
        p.configure();
        if (!waitForState(p, p.Configured)) {
            throw new IOException("Failed to configure the processor.");
        }

        if (outputFile.getName().toLowerCase().endsWith(".mov")) {
            // Set the output content descriptor to QuickTime.
            p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.QUICKTIME));
        } else if (outputFile.getName().toLowerCase().endsWith(".avi")) {
            // Set the output content descriptor to AVI (MSVIDEO).
            p.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.MSVIDEO));
        } else {
            throw new IllegalArgumentException("unsupported file extension: " + outputFile.getName());
        }
        return p;
    }

    public static void mergeVideoAndAudio(String filename, File file) {
        new Merge(filename, file);
    }

    public void makeMovie() throws Exception {
        // Query for the processor for supported formats.
        // Then set it on the processor.
        TrackControl[] tcs = processor.getTrackControls();
        Format[] f = tcs[0].getSupportedFormats();
        if (f == null || f.length == 0) {
            throw new Exception("The mux does not support the input format: " + tcs[0].getFormat());
        }

        tcs[0].setFormat(f[0]);

        System.out.println("Setting the track format to: " + f[0]);

        // We are done with programming the processor. Let's just
        // realize it.
        processor.realize();
        if (!waitForState(processor, Processor.Realized)) {
            throw new Exception("Failed to realize the processor.");
        }

        // Now, we'll need to create a DataSink.
        DataSink dsink = createDataSink(processor, outML);
        if (dsink == null) {
            throw new Exception("Failed to create a DataSink for the given output MediaLocator: " + outML);
        }

        dsink.addDataSinkListener(this);
        fileDone = false;

        System.out.println("start processing...");

        // OK, we can now start the actual transcoding.
        processor.start();
        dsink.start();

        // Wait for EndOfStream event.
        waitForFileDone();

        // Cleanup.
        dsink.close();
        processor.removeControllerListener(this);

        System.out.println("...done processing.");
    }

    /**
     * Create the DataSink.
     */
    private static DataSink createDataSink(Processor p, MediaLocator outML) throws IOException, NoDataSinkException {
        DataSource ds = p.getDataOutput();

        if (ds == null) {
            throw new IOException("processor does not have an output DataSource");
        }

        System.out.println("- create DataSink for: " + outML);
        DataSink dsink = Manager.createDataSink(ds, outML);
        dsink.open();

        return dsink;
    }

    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     */
    private boolean waitForState(Processor p, int state) throws InterruptedException {
        synchronized (waitSync) {
            while (p.getState() < state && stateTransitionOK) {
                waitSync.wait();
            }
        }
        return stateTransitionOK;
    }

    /**
     * Controller Listener.
     */
    public void controllerUpdate(ControllerEvent evt) {
        if (evt instanceof ConfigureCompleteEvent || evt instanceof RealizeCompleteEvent || evt instanceof PrefetchCompleteEvent) {
            synchronized (waitSync) {
                stateTransitionOK = true;
                waitSync.notifyAll();
            }
        } else if (evt instanceof ResourceUnavailableEvent) {
            synchronized (waitSync) {
                stateTransitionOK = false;
                waitSync.notifyAll();
            }
        } else if (evt instanceof EndOfMediaEvent) {
            evt.getSourceController().stop();
            evt.getSourceController().close();
        }
    }

    /**
     * Block until file writing is done.
     */
    private boolean waitForFileDone() throws InterruptedException {
        synchronized (waitFileSync) {
            while (!fileDone) {
                waitFileSync.wait();
            }
        }
        return fileSuccess;
    }

    /**
     * Event handler for the file writer.
     */
    public void dataSinkUpdate(DataSinkEvent evt) {
        if (evt instanceof EndOfStreamEvent) {
            synchronized (waitFileSync) {
                fileDone = true;
                waitFileSync.notifyAll();
            }
        } else if (evt instanceof DataSinkErrorEvent) {
            synchronized (waitFileSync) {
                fileDone = true;
                fileSuccess = false;
                waitFileSync.notifyAll();
            }
        }
    }

    static TargetDataLine targetDataLine;

    public static void writeAudioToFile(String filename) {
        try {
            stopAudioRecord();
            File audioFile = new File(constants.Constants.ROOT_FOLDER+"tmp/MicRecording.wav");
            audioFile.renameTo(new File(constants.Constants.ROOT_FOLDER+"tmp/"+filename+".wav"));
        } catch (Exception ex) {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }

    public static void stopAudioRecord() {
        if (targetDataLine != null) {
            targetDataLine.stop();
            targetDataLine.close();
        }
    }

    public static void startAudioRecording() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    // Set the main audio capture parameters
                    float sampleRate = 8000.0F;
                    int sampleSizeInBits = 8;
                    int channels = 1;
                    boolean signed = true;
                    boolean bigEndian = false;
                    AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

                    // Get a TargetDataLine from the appropriate mixer
                    DataLine.Info dataLineInfo = new javax.sound.sampled.DataLine.Info(TargetDataLine.class, audioFormat);
                    targetDataLine = (javax.sound.sampled.TargetDataLine) AudioSystem.getLine(dataLineInfo);

                    // Prepare the line for use
                    targetDataLine.open(audioFormat);
                    targetDataLine.start();

                    // Build an input stream from the line
                    AudioInputStream audioInStream = new javax.sound.sampled.AudioInputStream(targetDataLine);

                    // Create the output file
                    java.io.File outputFile = new java.io.File(constants.Constants.ROOT_FOLDER+"tmp/MicRecording.wav");

                    // ...
                    // Start acquisition from mic
                    AudioFileFormat.Type targetFileFormatType = javax.sound.sampled.AudioFileFormat.Type.WAVE;
                    AudioSystem.write(audioInStream, targetFileFormatType, outputFile);
                } catch (Exception ex) {
                    Logger.getLogger("global").log(Level.SEVERE, null, ex);
                }
            }
        });
        t.start();
    }

    /**
     * how to run:
     * java MovieMaker -w <width> -h <height> -f <frame rate per sec.> -o <output file> <input JPEG file 1> <input JPEG file 2> ...
     *
     * example:
     * java MovieMaker -w 320 -h 240 -f 1 -o temp/out.mov temp/foo1.jpg temp/foo2.jpg
     *
     * NOTE - the images are not scaled to the specified height and width; they are cropped to the specified size instead.
     * However, there seems to be problems even with cropping. Best results come from
     * having your images match the passed-in size args.
     * It would be nice if someone could add auto-scaling of jpeg images to this class.
     */
    public static void main(String[] args) {
        mergeVideoAndAudio("1204205017059", new File("d:/test.mov"));
    }

///////////////////////////////////////////////
    //
    // Inner classes.
    ///////////////////////////////////////////////
    /**
     * A DataSource to read from a list of JPEG image files or
     * java.awt.Images, and
     * turn that into a stream of JMF buffers.
     * The DataSource is not seekable or positionable.
     */
    private static class ImageDataSource extends PullBufferDataSource {

        private final Time durTime;

        private final PullBufferStream[] streams = new JpegSourceStream[1];

        /**
         * Constructor for creating movies out of jpegs
         */
        ImageDataSource(int width, int height, int frameRate, File[] jpegFiles) {
            streams[0] = new JpegSourceStream(width, height, frameRate, jpegFiles);
            this.durTime = new Time(jpegFiles.length / frameRate);
        }

        /**
         * Constructor for creating movies out of Images
         * NOTE - this is all done IN MEMORY, so you'd better have enough
         */
        ImageDataSource(int width, int height, int frameRate, Image[] images) {
            streams[0] = new AWTImageSourceStream(width, height, frameRate, images);
            this.durTime = new Time(images.length / frameRate);
        }

        public void setLocator(MediaLocator source) {
        }

        public MediaLocator getLocator() {
            return null;
        }

        /**
         * Content type is of RAW since we are sending buffers of video
         * frames without a container format.
         */
        public String getContentType() {
            return ContentDescriptor.RAW;
        }

        public void connect() {
        }

        public void disconnect() {
        }

        public void start() {
        }

        public void stop() {
        }

        /**
         * Return the ImageSourceStreams.
         */
        public PullBufferStream[] getStreams() {
            return streams;
        }

        public Time getDuration() {
            return durTime;
        }

        public Object[] getControls() {
            return new Object[0];
        }

        public Object getControl(String type) {
            return null;
        }
    }

/**
     * The jpeg-based source stream to go along with ImageDataSource.
     */
    private static class JpegSourceStream implements PullBufferStream {

        private final File[] jpegFiles;
        private final int width;
        private final int height;
        private final VideoFormat videoFormat;

        private int nextImage = 0; // index of the next image to be read.
        private boolean ended = false;
        // Bug fix from Forums - next one line
        long seqNo = 0;

        public JpegSourceStream(int width, int height, int frameRate, File[] jpegFiles) {
            this.width = width;
            this.height = height;
            this.jpegFiles = jpegFiles;

            this.videoFormat = new VideoFormat(VideoFormat.JPEG, new Dimension(width, height), Format.NOT_SPECIFIED, Format.byteArray, (float) frameRate);
        }

        /**
         * We should never need to block assuming data are read from files.
         */
        public boolean willReadBlock() {
            return false;
        }

        /**
         * This is called from the Processor to read a frame worth
         * of video data.
         */
        public void read(final Buffer buf) {
            try {
                // Check if we've finished all the frames.
                if (nextImage >= jpegFiles.length) {
                    // We are done. Set EndOfMedia.
                    System.out.println("Done reading all images.");
                    buf.setEOM(true);
                    buf.setOffset(0);
                    buf.setLength(0);
                    ended = true;
                    return;
                }

                File imageFile = jpegFiles[nextImage];
                nextImage++;

                System.out.println(" - reading image file: " + imageFile);

                // Open a random access file for the next image.
                RandomAccessFile raFile = new RandomAccessFile(imageFile, "r");

                byte[] data = (byte[]) buf.getData();

                // Check to see the given buffer is big enough for the frame.
                if (data == null || data.length < raFile.length()) {
                    // allocate larger buffer
                    data = new byte[(int) raFile.length()];
                    buf.setData(data);
                }

                // Read the entire JPEG image from the file.
                raFile.readFully(data, 0, (int) raFile.length());

                System.out.println(" read " + raFile.length() + " bytes.");

                // Bug fix for AVI files from Forums ( next 3 lines).
                long time = (long) (seqNo * (1000 / videoFormat.getFrameRate()) * 1000000);
                buf.setTimeStamp(time);
                buf.setSequenceNumber(seqNo++);

                buf.setOffset(0);
                buf.setLength((int) raFile.length());
                buf.setFormat(videoFormat);
                buf.setFlags(buf.getFlags() | buf.FLAG_KEY_FRAME);

                // Close the random access file.
                raFile.close();
            } catch (Exception e) {
                // it's important to print the stack trace here because the
                // sun class that calls this method silently ignores
                // any IOExceptions that get thrown
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        /**
         * Return the format of each video frame. That will be JPEG.
         */
        public Format getFormat() {
            return videoFormat;
        }

        public ContentDescriptor getContentDescriptor() {
            return new ContentDescriptor(ContentDescriptor.RAW);
        }

        public long getContentLength() {
            return LENGTH_UNKNOWN;
        }

        public boolean endOfStream() {
            return ended;
        }

        public Object[] getControls() {
            return new Object[0];
        }

        public Object getControl(String type) {
            return null;
        }
    }

/**
     * The java.awt.Image-based source stream to go along with ImageDataSource.
     * Not sure yet if this class works.
     */
    private static class AWTImageSourceStream implements PullBufferStream {

        private final Image[] images;
        private final int width;
        private final int height;
        private final VideoFormat videoFormat;

        private int nextImage = 0; // index of the next image to be read.
        private boolean ended = false;
        // Bug fix from Forums - next one line
        private long seqNo = 0;

        public AWTImageSourceStream(int width, int height, int frameRate, Image[] images) {
            this.width = width;
            this.height = height;
            this.images = images;

            // not sure if this is correct, especially the VideoFormat value
            this.videoFormat = new VideoFormat(VideoFormat.RGB, new Dimension(width, height), Format.NOT_SPECIFIED, Format.byteArray, (float) frameRate);
        }

        /**
         * We should never need to block assuming data are read from files.
         */
        public boolean willReadBlock() {
            return false;
        }

        /**
         * This is called from the Processor to read a frame worth
         * of video data.
         */
        public void read(final Buffer buf) throws IOException {
            try {
                // Check if we've finished all the frames.
                if (nextImage >= images.length) {
                    // We are done. Set EndOfMedia.
                    System.out.println("Done reading all images.");
                    buf.setEOM(true);
                    buf.setOffset(0);
                    buf.setLength(0);
                    ended = true;
                    return;
                }

                Image image = images[nextImage];
                nextImage++;

                // Open a random access file for the next image.
                //RandomAccessFile raFile = new RandomAccessFile(imageFile, "r");
                Buffer myBuffer = ImageToBuffer.createBuffer(image, videoFormat.getFrameRate());

                buf.copy(myBuffer);

                // Bug fix for AVI files from Forums ( next 3 lines).
                long time = (long) (seqNo * (1000 / videoFormat.getFrameRate()) * 1000000);
                buf.setTimeStamp(time);
                buf.setSequenceNumber(seqNo++);

                //buf.setOffset(0);
                //buf.setLength((int)raFile.length());
                //buf.setFormat(videoFormat);
                //buf.setFlags(buf.getFlags() | buf.FLAG_KEY_FRAME);
            } catch (Exception e) {
                // it's important to print the stack trace here because the
                // sun class that calls this method silently ignores
                // any Exceptions that get thrown
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        /**
         * Return the format of each video frame.
         */
        public Format getFormat() {
            return videoFormat;
        }

        public ContentDescriptor getContentDescriptor() {
            return new ContentDescriptor(ContentDescriptor.RAW);
        }

        public long getContentLength() {
            return LENGTH_UNKNOWN;
        }

        public boolean endOfStream() {
            return ended;
        }

        public Object[] getControls() {
            return new Object[0];
        }

        public Object getControl(String type) {
            return null;
        }
    }
}

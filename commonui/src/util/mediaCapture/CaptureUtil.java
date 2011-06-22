/*
 * CaptureUtil.java
 * 
 * Created on Feb 28, 2008, 7:50:11 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.mediaCapture;

/**
 *
 * @author Entokwaa
 */
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.*;
import javax.media.control.*;

import util.Log;

import java.util.Vector;
import java.awt.*;
import com.sun.media.ui.PlayerWindow;

public class CaptureUtil  {

    public static DataSource getCaptureDS(VideoFormat vf, AudioFormat af) {
	DataSource dsVideo = null;
	DataSource dsAudio = null;
	DataSource ds = null;

	// Create a capture DataSource for the video
	// If there is no video capture device, then exit with null
	if (vf != null) {
	    dsVideo = createDataSource(vf);
	    if (dsVideo == null)
		return null;
	}
	if (af != null) {
	    dsAudio = createDataSource(af);
	}

	// Create the monitoring datasource wrapper
	if (dsVideo != null) {
	    dsVideo = new MonitorCDS(dsVideo);
	    if (dsAudio == null)
		return dsVideo;
	    ds = dsVideo;
	} else if (dsAudio != null) {
	    return dsAudio;
	} else
	    return null;

	// Merge the data sources, if both audio and video are available
	try {
	    ds = Manager.createMergingDataSource(new DataSource [] {
		dsAudio, dsVideo
	    });
	} catch (IncompatibleSourceException ise) {
	    return null;
	}

	return ds;
    }

    static DataSource createDataSource(Format format) {
	DataSource ds;
	Vector devices;
	CaptureDeviceInfo cdi;
	MediaLocator ml;

	// Find devices for format
	devices = CaptureDeviceManager.getDeviceList(format);
	if (devices.size() < 1) {
	    Log.out("! No Devices for ",format);
	    return null;
	}
	// Pick the first device
	cdi = (CaptureDeviceInfo) devices.elementAt(0);

	ml = cdi.getLocator();

	try {
	    ds = Manager.createDataSource(ml);
	    ds.connect();
	    if (ds instanceof CaptureDevice) {
		setCaptureFormat((CaptureDevice) ds, format);
	    }
	} catch (Exception e) {
	    System.err.println(e);
	    return null;
	}
	return ds;
    }

    static void setCaptureFormat(CaptureDevice cdev, Format format) {
	FormatControl [] fcs = cdev.getFormatControls();
	if (fcs.length < 1)
	    return;
	FormatControl fc = fcs[0];
	Format [] formats = fc.getSupportedFormats();

	for (int i = 0; i < formats.length; i++) {
	    if (formats[i].matches(format)) {
		format = formats[i].intersects(format);
		Log.out("Setting format ",format);
		fc.setFormat(format);
		break;
	    }
	}
    }
}

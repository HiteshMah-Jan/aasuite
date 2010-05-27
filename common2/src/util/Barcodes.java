/*
 * Barcodes.java
 * 
 * Created on May 10, 2008, 9:18:20 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author Entokwaa
 */
import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.BarcodeEANSUPP;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.BarcodePostnet;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
/**
 * List with different Barcode types.
 */
public class Barcodes {
	/**
	 * List with different Barcode types.
	 * @param args no arguments needed
	 */
	public static void main(String[] args) {
        Log.out("Barcodes");
        
        // step 1: creation of a document-object
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        
        try {
            
            // step 2: creation of the writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/barcodes.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add content to the document
            PdfContentByte cb = writer.getDirectContent();
            
            Barcode code128 = new Barcode128();
            code128.setCode("4710088633880");
            Image image128 = code128.createImageWithBarcode(cb, null, null);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.getDefaultCell().setFixedHeight(70);
            
            table.addCell("CODE 128");
            table.addCell(new Phrase(new Chunk(image128, 0, 0)));
            
            document.add(table);
        }
        catch (Exception de) {
            de.printStackTrace();
        }
        
        // step 5: we close the document
        document.close();
	}
}

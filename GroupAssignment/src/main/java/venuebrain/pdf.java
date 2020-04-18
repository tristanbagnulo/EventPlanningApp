package venuebrain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.io.FileOutputStream;

        

/**
 *
 * @author Shai C
 */
public class pdf {
    
    public static void testPDF(){
    
       try{
       Document document = new Document();
       PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

       
       document.add(new Paragraph("Please Print please please test test"));
       
       document.open();
       Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
       Chunk chunk = new Chunk("Hello World", font);

       document.add(chunk);
       document.close();
       }catch(Exception e){
           System.out.println(e);
       }
       System.out.println("itext executed");
    }
}

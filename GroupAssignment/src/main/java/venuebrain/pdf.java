package venuebrain;

import java.io.FileOutputStream;
import org.w3c.dom.Document;

/**
 *
 * @author Shai C
 */
public class pdf {
   
    
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

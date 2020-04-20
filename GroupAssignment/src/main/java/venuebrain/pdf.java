package venuebrain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import javafx.scene.control.SelectionMode;
import java.io.FileOutputStream;

        

/**
 *
 * @author Shai C
 */
public class pdf {
   
    //plan
    //Get the details from the event selected.
        //Refer to the Event selected
        //
    //Print them onto the PDF file.
    
    public static void invitePDF(String eventName, String location, Guest guest){
       try{
       Document document = new Document();
       PdfWriter.getInstance(document, new FileOutputStream(eventName + " invitation for " + guest.getAccessCode() + ".pdf"));

       document.open();
       
      
       Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
       Chunk chunk = new Chunk("Dear " + guest.getFName(), font);
       document.add(chunk);
       
       document.add(new Paragraph("You are cordially invited to " + eventName + " located in " + location + ". We hope you can make it!"));

       document.close();
       }catch(Exception e){
           System.out.println(e);
       }
       System.out.println("PDF generated");
    }
    
}

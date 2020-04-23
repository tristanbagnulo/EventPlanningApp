package venuebrain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 


//Add images to PDFs instructions here https://www.tutorialspoint.com/itext/itext_adding_image_to_pdf.htm

import javafx.scene.control.SelectionMode;
import java.io.FileOutputStream;

/**
 *
 * @author Shai C
 */
public class pdf {
   
    //plan
    //Get the details from the event selected.
        //Neil recommends eventTable.getSelectionModel().getSelectedItem());
        //Refer to the Event selected within the table on the page
        //
    //Print them onto the PDF file.
    public static void testPDF(){
       try{
       //Instantiate the document object
        Document document = new Document();
       //Name the pdf file
       PdfWriter.getInstance(document, new FileOutputStream("Invitation.pdf"));
       
       //Open the document
       document.open();
       //Add text in a "Paragraph". 
       document.add(new Paragraph("Tristan Testing PDF"));
      
       //_____________Adding an image: pending impor of Image, ImageData  and ImageDataFactors
       
    //Create an ImageData object
       String imageFile = "C:\\Users\\Tristan\\OneDrive\\UNSW\\UNSW 2020, T1\\INFS2605\\Group Project\\youreInvited.jpeg";
       ImageData data = ImageDataFactory.create(imageFile);
       Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
       Chunk chunk = new Chunk("Hello World", font);
       
       //Creating an Image object
       Image image = new Image(data);
       
       //Adding image to the document
       document.add(image);
       
       //Closing the document
       document.close();
       
           System.out.println("Image added");
       
           
        
        //________________________

       document.add(chunk);
       document.close();
       }catch(Exception e){
           System.out.println(e);
       }
       System.out.println("itext executed");
    }
    
}

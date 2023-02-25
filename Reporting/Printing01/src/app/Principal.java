//NO SIRVE AUN PARA THERMAL PRINTER

package app;

import java.awt.print.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

public class Principal {
	static Logger mlog = Logger.getLogger("app.Principal");

	public static void main(String[] args) throws FileNotFoundException, PrintException  {
		// TODO Auto-generated method stub
		mlog.info("starting");
		String datos = "001 2 coca cola 5232\n 002 1 pepsi 451";
		
		FileInputStream textStream;
		textStream = new FileInputStream("repository.dat");
		
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		
		   aset.add(MediaSizeName.ISO_A8);
		   //aset.add(new Copies(1));
		//aset.add(MediaSize.ISO.A8);

		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc mydoc = new SimpleDoc(textStream, flavor, null);

		   PrintService[] services = PrintServiceLookup.lookupPrintServices(
		                flavor, aset);
		   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

		   if(services.length == 0) {
		       if(defaultService == null) {
		             //no printer found

		       } else {
		            //print using default
		            DocPrintJob job = defaultService.createPrintJob();
		            job.print(mydoc, aset);

		       }

		    } else {

		       //built in UI for printing you may not use this
		       PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);


		        if (service != null)
		        {
		           DocPrintJob job = service.createPrintJob();
		           job.print(mydoc, aset);
		        }

		    }
		
		
		
		
		
		
		
		
		/*DocFlavor flavor = new DocFlavor("text/plain", "java.io.InputStream");
		   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		   aset.add(MediaSize.ISO.A8);
		   PrintService[] pservices =
		                 PrintServiceLookup.lookupPrintServices(flavor, aset);
		   if (pservices.length > 0) {
		       DocPrintJob pj = pservices[0].createPrintJob();
		       try {
		           //FileInputStream fis = new FileInputStream("test.ps");
		           Doc doc = new SimpleDoc(datos, flavor, null);
		           pj.print(doc, aset);
		        } catch (PrintException e) {
		        }
		   }*/
		
		
		
		
		
		
		
		
		
		
		
		/*try{
		Paper paper = new Paper();
		paper.setSize(2.32 * 72, 2 * 72);
		
		PageFormat pf = new PageFormat();
		
		Book book = new Book();
		Printable printable =  book.getPrintable(0);

		
		PrinterJob pj =  new PrinterJob();
		
		
		
		DocFlavor docFlavor = new DocFlavor("text/plain", "java.io.InputStream");
		
		SimpleDoc simpleDoc = new SimpleDoc(datos, docFlavor, null);
		
		PrintServiceLookup pslookup = new PrintServiceLookup();
		
		}
		
		catch(Exception ex){System.out.println(ex.getMessage());}*/

	}

}

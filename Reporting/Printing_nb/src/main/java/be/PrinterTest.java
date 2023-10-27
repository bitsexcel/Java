package app;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class PrinterTest {
	static Logger myLog = Logger.getLogger("app.PrinterTest");
  public static void main (String [] args) throws PrintException, IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Enter the name of the printer: ");
    String printerName = "POS-58";
    //String printerName = bufferedReader.readLine();
    //System.out.print("Enter a short message of what you would like to print here: ");
    //String printerMessage = "PRINTER MESSAGE: " + bufferedReader.readLine();
    String printerMessage = "PRINTER MESSAGE:" ;
    boolean printerCheck = false;
    DocPrintJob job = null;
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    System.out.println("Number of print services: " + printServices.length);
    for (PrintService printer : printServices) {
        System.out.println("Printer: " + printer.getName());
        if (printer.getName().contains(printerName)) {
            InputStream inputStream = new ByteArrayInputStream(printerMessage.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(inputStream, flavor, null);
            job = printer.createPrintJob();
            job.print(doc, null);
            myLog.info("printed");
            printerCheck = true;
        }
    }
    if (printerCheck == false) {
        System.out.println("The printer you were searching for could not be found.");
    }
  }
}
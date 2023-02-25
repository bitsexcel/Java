package app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;

public class New1 {
	static Logger myLog = Logger.getLogger("app.New1");

	public static void main(String[] args) {

		try {
			myLog.info("starting program");
			String s = "Hello my friend \n";

			// byte[] by = s.getBytes();
			InputStream is = new ByteArrayInputStream(s.getBytes());
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			// PrintService pservice = PrintServiceLookup.lookupDefaultPrintService();
			// myLog.info(pservice.getName());
			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
			PrintService printer = printServices[0];
			myLog.info(printer.getName());
			if (printer.getName().contains("POS-58")) {
				// DocPrintJob job = pservice.createPrintJob();
				Doc doc = new SimpleDoc(is, flavor, null);
				DocPrintJob job = printer.createPrintJob();

				job.print(doc, null);
			}

		} catch (PrintException e) {
			e.printStackTrace();
		}
	}
}
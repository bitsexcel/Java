package app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class MyPrintTest {

	public void executePrint(String sample) {
		// data for printing
		String data = sample;

		try {
			// locate printer
			PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

			System.out.println("Printer online: " + printService);

			// create a print job
			DocPrintJob job = printService.createPrintJob();

			// define the format of print document
			InputStream is = new ByteArrayInputStream(data.getBytes());
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

			// print the data
			Doc doc = new SimpleDoc(is, flavor, null);
			job.print(doc, null);

			is.close();
			System.out.println("Printing Done!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new MyPrintTest().executePrint("testing Printing \n ");
	}
}

import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;


public class PrintDriver
{
	public static void print(String fileName)
	{
		try
		{
			PrintService service = PrintServiceLookup.lookupDefaultPrintService ();
			//Find all services compatible with document
			PrintService[] services = 
				PrintServiceLookup.lookupPrintServices (DocFlavor.INPUT_STREAM.AUTOSENSE , null);
			
			//Set up print attributes
			PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet ();
			//Have user select a service
			PrintService ps = ServiceUI.printDialog (null, 50, 50,
					services, service, null, attributes);

			SimpleDoc sd;

			//Check is print service was chosen
			if (ps != null)
			{
				try
				{
					//Set up file info
					InputStream in = new FileInputStream(fileName);
					
					sd = new SimpleDoc (in, ps.getSupportedDocFlavors () [13],
							new HashDocAttributeSet ());
					//Create the print job in service exists
					ps.createPrintJob ().print (sd, attributes);
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,
							"Failed to find a printer that supports file format.\n" +
							"Please ensure a compatible printer is conected and powered on.",
							"Error.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

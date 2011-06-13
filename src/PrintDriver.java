
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

/**
 * The PrintDriver Class.
 * <p>
 * This class is used to handle printing of documents.
 * It holds a single method that create a request to the
 * user specified printer to print a given file.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : April 11, 2011
 * @see HighScoreSate
 * @see MenuState
 */
public class PrintDriver
{
	private PrintDriver()
	{ /* Suppress Default Constructor */ }
	
	/**
	 * This method is called statically by the Program else
	 * where when the user opts to print out content from the
	 * game. It will open an interface that will prompt the user
	 * to select a printer (the system default printer will be
	 * selected as the default option) that is compatible with
	 * the file output format for this program. It is declared as
	 * static because after the user has selected their printer
	 * of choice and the print request was sent, this Class's
	 * functionality is no longer necessary, and it performs no
	 * further tasks, and is therefore never required to use more
	 * than this sole method.
	 * 
	 * @param fileName The path to the file to be printed.
	 */
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
			JOptionPane.showMessageDialog(null,
					"Failed to find a printer that supports file format.\n" +
					"Please ensure a compatible printer is conected and powered on.",
					"Error.", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

package DefaultPackage;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import LD.BasesDeDatos;
import LP.Principal;


public class clsMain 
{

	public static void main(String[] args)
	{
		BasesDeDatos.initBD("DeustoAirlinesBD");
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		    {
		        if ("Nimbus".equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e)
		{
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}	
		
		
		Principal objPrincipal = new Principal();
		objPrincipal.setVisible(true);
		
	}

}

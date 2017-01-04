package LP;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConsultaIngresos extends JInternalFrame
{
	private JPanel contentPane;
	
	
	public ConsultaIngresos()
	{
		createAndShowGUI();
		
	}
	
	
	public void createAndShowGUI()
	{
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	}
}

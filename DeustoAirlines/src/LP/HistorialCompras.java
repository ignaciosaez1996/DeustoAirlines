package LP;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class HistorialCompras extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	String correo; 
	
	
	public HistorialCompras(String correo)
	{
		this.correo = correo;
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

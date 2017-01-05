package LP;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class HistorialCompras extends JInternalFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	String correo; 
	private JTable table_1;
	
	
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 70, 638, 129);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.setBounds(300, 230, 117, 38);
		contentPane.add(btnNewButton);
	}
}

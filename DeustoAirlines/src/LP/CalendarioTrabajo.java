package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


public class CalendarioTrabajo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList listaTrabajadores;
	private JScrollPane scrollTrabajadores;
	private JTable table;
	private JScrollPane scrollTabla;
	
	public CalendarioTrabajo()
	{
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Calendario de Trabajo");
		setBounds(70, 10, 950, 650);
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}

}

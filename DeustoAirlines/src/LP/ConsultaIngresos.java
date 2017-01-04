package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ConsultaIngresos extends JInternalFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	
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
	
	table = new JTable();
	table.setBounds(57, 24, 419, 165);
	contentPane.add(table);
	
	JButton btnNewButton = new JButton("Cargar Tabla");
	btnNewButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				String query = "select * from billete";
				PreparedStatement pat = connection.prepareStatement(query);
				ResultSet rs = pat.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				
			
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	});
	btnNewButton.setBounds(296, 11, 247, 46);
	contentPane.add(btnNewButton);
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setTitle("Consulta de ingresos");
	setBounds(70, 10, 950, 650);
	
	}
}

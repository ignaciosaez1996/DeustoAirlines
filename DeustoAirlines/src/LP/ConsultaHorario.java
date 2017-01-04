package LP;

import javax.swing.JInternalFrame;
import static COMUN.Definiciones.CMD_BTN_CANCELAR;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaHorario extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;




	JPanel contentPane;

	private JTable table;
	private JButton btnCancelar;
	
	Connection connection = null;

	
	public ConsultaHorario()
	{
		createAndShowGUI();
		connection = BasesDeDatos.getConnection();
		//setLocationRelativeTo(null);
	}
	
	private void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Consultar horario");
		this.setBounds(260, 30, 802, 597);
		this.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 78, 706, 367);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable ( int rowIndez, int colIndex)
			{
				return false;
			}
			
		};
		scrollPane.setViewportView(table);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(296, 492, 102, 30);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Cargar Tabla");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					String query = "select * from vuelo";
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(223, 11, 247, 46);
		contentPane.add(btnNewButton);
	}


	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		 {	
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
		} 
	}
	
	
}

	
	
	


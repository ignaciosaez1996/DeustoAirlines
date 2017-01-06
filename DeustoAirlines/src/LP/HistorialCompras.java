package LP;

import static COMUN.Definiciones.CMD_BTN_ACEPTAR;
import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_ELEGIRTRABAJADOR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.GestorCliente;

public class HistorialCompras extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String correo; 
	private JList list;
	Connection connection;
	
	
	public HistorialCompras(String correo)
	{
		connection = BasesDeDatos.getConnection();
		this.correo = correo;
		createAndShowGUI();
		llenarTabla();
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
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.setActionCommand(CMD_BTN_CANCELAR);
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(300, 230, 117, 38);
		contentPane.add(btnNewButton);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Historial de compras");
		this.setBounds(260, 10, 746, 650);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
				
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;

		}
	}
	
	public void llenarTabla()
	{
		GestorCliente gesCli = new GestorCliente();
		Statement state = BasesDeDatos.getStatement();
		ArrayList<String>rs = gesCli.CodVuelo(state, correo, connection);
		System.out.println(rs.toString());
		if(rs!=null)
		{
			DefaultListModel DL = new DefaultListModel();
			ResultSet rs1;
			for(int i=0;i<rs.size();i++)
			{
				
				try 
				{
					String query = "select * from VUELO where ( cod_vuelo ='" + rs.get(i) + "')";
					PreparedStatement pat;
					pat = connection.prepareStatement(query);
					rs1= pat.executeQuery();
					DL.addElement(rs1.getString("cod_vuelo").concat(" --- ").concat(rs1.getString("fecha").concat(" --- ").concat(rs1.getString("cod_postal_o")).concat(" --- ").concat(rs1.getString("cod_postal_d"))));
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.setModel(DL);
		}
		
	}
}

package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;

import LD.BasesDeDatos;
import static COMUN.Definiciones.*;

public class CalendarioTrabajo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JList list;
	Connection connection = BasesDeDatos.getConnection();
	
	public CalendarioTrabajo()
	{
		CargarLista();
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista de trabajadores");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(85, 51, 253, 39);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 100, 573, 204);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnElegirTrabajador = new JButton("ELEGIR TRABAJADOR");
		btnElegirTrabajador.setActionCommand(CMD_ELEGIRTRABAJADOR);
		btnElegirTrabajador.addActionListener(this);
		btnElegirTrabajador.setBounds(498, 315, 161, 31);
		contentPane.add(btnElegirTrabajador);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de vuelos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(85, 351, 203, 39);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(85, 401, 574, 142);
		contentPane.add(scrollPaneTable);
		
		table = new JTable();
		scrollPaneTable.setViewportView(table);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(273, 578, 112, 31);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(427, 578, 112, 31);
		contentPane.add(btnCancelar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Calendario de Trabajo");
		setBounds(70, 10, 746, 650);
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CMD_BTN_ACEPTAR:
				break;
				
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
				
			case CMD_ELEGIRTRABAJADOR:
				break;
		}
	}
	
	public void CargarLista()
	{
		try
		{
			String query = "select dni_tra, nombre_tra, categoria from trabajador"; 
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("dni_tra").concat("--").concat(rs.getString("nombre_tra")).concat("--").concat(rs.getString("categoria")));
					DL.addElement("");
				}
			
			list.setModel(DL);
			
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay trabajadores disponibles");
		}
	}
}

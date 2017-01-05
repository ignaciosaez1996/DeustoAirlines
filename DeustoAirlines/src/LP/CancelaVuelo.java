package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import net.proteanit.sql.DbUtils;

public class CancelaVuelo extends JInternalFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	Connection connection = null;
	JPanel contentPane;
	private JTable table;
	private JList list ;
	private String codVueloSelec;
	
	public CancelaVuelo()
	{
		createAndShowGUI();
		connection = BasesDeDatos.getConnection();
		llenarLista();
	}
	
	private void createAndShowGUI()
	{
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CARGAR TABLA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String query = "select * from vuelo";
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception a)
				{
					a.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(328, 11, 142, 44);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 81, 667, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEligeElCodigo = new JLabel("Elige el codigo de vuelo que quiera eliminar");
		lblEligeElCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEligeElCodigo.setBounds(97, 261, 373, 44);
		contentPane.add(lblEligeElCodigo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(96, 312, 235, 153);
		contentPane.add(scrollPane_1);
		
		 list = new JList();
		scrollPane_1.setViewportView(list);
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				codVueloSelec = list.getSelectedValue().toString();
			}
		});
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setActionCommand("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(417, 313, 118, 50);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("CANCELAR");
	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(417, 388, 118, 50);
		contentPane.add(btnCancelar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cancelar Vuelo");
		setBounds(70, 10, 790, 540);
		
	}
	
	public void llenarLista()
	{
		try
		{
			String query = "select cod_vuelo from vuelo "; 
			PreparedStatement pst = connection.prepareStatement(query);	
			ResultSet rs = pst.executeQuery();
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("cod_vuelo"));
				}
			list.setModel(DL);
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles");
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case "ELIMINAR":
				
				EliminarVuelo();
			
			
			case "CANCELAR":
				this.dispose();
				break;
		}
		
	}
	
	
	public void EliminarVuelo()
	{
		
		GestorTrabajador  gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();

		String cod_vuelo = codVueloSelec;
		
		gesTra.CancelarVuelo(state, cod_vuelo);
		
		
	}
}

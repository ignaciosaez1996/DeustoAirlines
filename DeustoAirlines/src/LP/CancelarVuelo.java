package LP;

import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_BTN_ELIMINAR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.GestorTrabajador;

public class CancelarVuelo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	JPanel contentPane; 
	
	private JTable table;
	private JButton btnCancelar;

	
	Connection connection = null;
	private JButton btnEliminar_1;
	
	public CancelarVuelo()
	{
		createAndShowGUI();
		
	}
	
	
	//Para refrescar la tabla
	public void RefrescarTabla()
	{
		try
		{
			connection = BasesDeDatos.getConnection();
			String query = "select * from vuelo";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("cod_vuelo"));
			}
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Eliminar un vuelo");
		this.setBounds(260, 30, 802, 597);
		this.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 78, 706, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(490, 490, 102, 30);
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
		
		btnEliminar_1 = new JButton("ELIMINAR");
		btnEliminar_1.addActionListener(this);
			// BORRA TODOS LOS VUELOS.. tenemos que pensar alguna manera para que borre		
			//solo los seleccionados

		btnEliminar_1.setActionCommand(CMD_BTN_ELIMINAR);
		btnEliminar_1.setBounds(277, 490, 102, 30);
		contentPane.add(btnEliminar_1);
		
		RefrescarTabla();
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		 {	
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
				
			case CMD_BTN_ELIMINAR:
				Statement state = BasesDeDatos.getStatement();
				GestorTrabajador gesTra = new GestorTrabajador();
				
				connection = BasesDeDatos.getConnection();
				String query = "select * from vuelo";
			PreparedStatement pat;
			try
			{
				pat = connection.prepareStatement(query);
				ResultSet rs = pat.executeQuery();
				if(table.getSelectedRow()>-1)
				{
					for(int i = 0; i<=table.getRowCount(); i++)
					{
						if(i==table.getSelectedRow())
						{
						
		
							//Hay que conseguir sacar el codigo del resultset
						}
					}
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningún vuelo", "Correcto",JOptionPane.INFORMATION_MESSAGE);
				}	
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
				
				
				
				
				break;
		} 
		
	}

}

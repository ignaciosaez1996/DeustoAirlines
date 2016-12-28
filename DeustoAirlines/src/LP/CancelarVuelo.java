package LP;

import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_BTN_ELIMINAR;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;

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
		connection = BasesDeDatos.getConnection();
	}
	
	
	//para refrescar la tabla
	public void RefrescarTabla()
	{
		try
		{
		String query = "select * from vuelo";
		PreparedStatement pat = connection.prepareStatement(query);
		ResultSet rs = pat.executeQuery();
		System.out.println(rs);
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
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) // BORRA TODOS LOS VUELOS.. tenemos que pensar alguna manera para que borre
			{											//solo los seleccionados
				try
				{
					String query = " delete from vuelo ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Vuelo cancelado");
					
					pst.close();
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				RefrescarTabla();
			}
		});
		btnEliminar_1.setActionCommand("CMD_BTN_CANCELAR");
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
				System.out.println(table.getSelectedRow());
				break;
		} 
		
	}

}

package LP;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.GestorCliente;

/**
 * Clase que crea un formulario que ser� llamado desde PrincipalCliente y recogera los datos necesarios para eliminar un billete.
 * Despues se los mandara a GestorCliente para que elimine el billete de la Base de Datos
 */
public class CancelarBillete extends JInternalFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	Connection connection = null;
	JPanel contentPane;
	private JTable table;
	private JList list ;
	private String codBilleteSelec;
	
	/**
	 * Crea el JInternalFrame
	 * Obtiene la conexion de la Base de Datos
	 * Llama al metodo para que llene la lista con los billetes que tenga comprados el cliente
	 */
	public CancelarBillete()
	{
		createAndShowGUI();
		connection = BasesDeDatos.getConnection();
		llenarLista();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de Agenda de Trabajo
	 */
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
					String query = "select * from billete";
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception a)
				{
					a.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(328, 11, 142, 44);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 81, 667, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEligeElCodigo = new JLabel("Elige el codigo del billete que quiera eliminar");
		lblEligeElCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
				codBilleteSelec = list.getSelectedValue().toString();
			}
		});
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setActionCommand("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(492, 312, 118, 50);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(492, 388, 118, 50);
		contentPane.add(btnCancelar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cancelar Billete");
		setBounds(80, 30, 859, 540);
		
	}

	/**
	 * Metodo que se encarga de rellenar la lista con los codigos de los billetes que tenga comprados el cliente
	 */
	public void llenarLista()
	{
		try
		{
			String query = "select cod_billete from billete "; 
			PreparedStatement pst = connection.prepareStatement(query);	
			ResultSet rs = pst.executeQuery();
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("cod_billete"));
				}
			list.setModel(DL);
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay billetes disponibles");
		}
	}
	
	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case "ELIMINAR":
				boolean aviso = false;
				if(codBilleteSelec!=null)
				{
					aviso = this.Aviso();
					if(aviso!=true)
					{
						this.EliminarVuelo();
						this.dispose();
					}
				}else
				{
					JOptionPane.showMessageDialog(null, "Elija el billete que desea eliminar");
				}
				break;
			
			case "CANCELAR":
				this.dispose();
				break;
		}
		
	}
	
	/**
	 * Metodo que vuelve a preguntar al cliente si esta seguro de que desea eliminar el billete
	 * @return true en caso de que el cliente no quiera eliminar el billete
	 */
	private boolean Aviso() 
	{
		boolean aviso = false;
		 int respuesta = JOptionPane.showConfirmDialog(null, "�Seguro que desea eliminar?", "DeustoAirlines - Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    
	    if (respuesta == JOptionPane.NO_OPTION) 
	    {
	      aviso = true;
	    } 
	    else if (respuesta == JOptionPane.YES_OPTION) 
	    {
	      aviso = false;
	    } 
	    else if (respuesta == JOptionPane.CLOSED_OPTION) 
	    {
	      aviso = true;
	    }
	    
		return aviso;		
	}
	
	/**
	 * Metodo encargado de recoger el codigo de billete seleccionado y mandarselo al GestorCliente para que lo elimine
	 */
	public void EliminarVuelo()
	{
		GestorCliente  gesCliente = new GestorCliente();
		Statement state = BasesDeDatos.getStatement();

		String cod_billete =codBilleteSelec;
		
		gesCliente.CancelarBillete(state, cod_billete);
	}
	
}
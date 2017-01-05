package LP;

	import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_BTN_CARGARTABLA;
import static COMUN.Definiciones.CMD_BTN_ELIMINAR;

	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

	import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

	import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.clsBillete;


	public class CancelarBillete extends JFrame implements ActionListener
	{

		ArrayList<clsBillete>ListaBilletes;

		JPanel contentPane; 
		JLabel label;
		private JTable tabla;
		private JButton btnCancelar;
		private JScrollPane scrollPane;
		String correo;
		Connection connection = null;
		private JButton btnEliminar_1;

//		public static void main(String[] args)
//		{
//			BasesDeDatos.initBD("DeustoAirlinesBD");
//			CancelarBillete objCancel = new CancelarBillete();	
//			objCancel.setVisible(true);
//			
//		}
		public CancelarBillete(String correo) 
		
		{
			this.correo=correo;
			connection = BasesDeDatos.getConnection();
			CreateAndShowGUI();
			construirTabla();
		}
		
		public void CreateAndShowGUI()
		{
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("CANCELAR BILLETES");
			this.setBounds(260, 30, 802, 597);
			this.getContentPane().setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(70, 78, 706, 367);
			contentPane.add(scrollPane);
			
			
			label= new JLabel("TUS BILLETES");
			label.setBounds(324, 11, 200, 56);
			contentPane.add(label);
			
			btnCancelar = new JButton("CANCELAR");
			btnCancelar.setBounds(490, 490, 102, 30);
			btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
			btnCancelar.addActionListener(this);
			this.getRootPane().setDefaultButton(btnCancelar);
			contentPane.add(btnCancelar);
			
			
			btnEliminar_1 = new JButton("ELIMINAR");
			btnEliminar_1.addActionListener(this);
			btnEliminar_1.setActionCommand(CMD_BTN_ELIMINAR);
			btnEliminar_1.setBounds(277, 490, 102, 30);
			contentPane.add(btnEliminar_1);
			
		}
			
		public void construirTabla()
		{
			String COLUMNAS[]={ "CODIGO BILLETE", "PRECIO","VUELO"};
			String informacion[][]= obtenerMatriz();
			tabla= new JTable(informacion, COLUMNAS);
			scrollPane.setViewportView(tabla);
			
		}	
		public ArrayList<clsBillete> buscarBilletes()
		{
			Connection connection = null;
			connection = BasesDeDatos.getConnection();
			Statement state = BasesDeDatos.getStatement();
			ArrayList<clsBillete>listaBilletes= new ArrayList<clsBillete>();
			clsBillete objBillete;
			try
			{
				String query = "select * from BILLETE where (correo = '" + correo +  "')";
				PreparedStatement pat = connection.prepareStatement(query);
				ResultSet rs = pat.executeQuery();
				//tabla.setModel(DbUtils.resultSetToTableModel(rs));
				while(rs.next())
				{
					 objBillete = new clsBillete();
					 objBillete.setCod_vuelo(rs.getString("cod_billete"));
					 objBillete.setPrecio(Double.parseDouble(rs.getString("precio")));
					 objBillete.setCodigo_billete(rs.getString("cod_vuelo"));
					
					 listaBilletes.add(objBillete);
				}
				rs.close();
				state.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return listaBilletes;
			
		}
		public String[][] obtenerMatriz() 
		{
			ArrayList<clsBillete> listaBILLETES = buscarBilletes();
			String matrizInfo[][]= new String[listaBILLETES.size()][5];
			for (int i=0;i<listaBILLETES.size();i++)
			{
				matrizInfo[i][0]=listaBILLETES.get(i).getCod_vuelo().toString();
				matrizInfo[i][1]=listaBILLETES.get(i).getCodigo_billete().toString();
				matrizInfo[i][2]=listaBILLETES.get(i).getPrecio()+"";
			}
			return matrizInfo;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

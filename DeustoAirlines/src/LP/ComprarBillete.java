package LP;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ComprarBillete extends JFrame
{	
	private JPanel contentPane;
	private JTable table;
	public final static int panelWidth = 1400;
	public final static int panelHeight = 680;
	
	
	public ComprarBillete()
	{
		
		createAndShowGUI();
	}
	
	private void createAndShowGUI()
	
	{
		
		contentPane = new JPanel();	
		contentPane.setSize(panelWidth,panelHeight);	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);	
		
		JLabel lblBilletes = new JLabel("Escoga el viaje que quiere realizar");
		lblBilletes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBilletes.setBounds(10, 126, 327, 33);
		contentPane.add(lblBilletes);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(420, 136, 94, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("\u00BF A DONDE LE LLEVAMOS?\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(324, 11, 399, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblDe = new JLabel("DE");
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDe.setBounds(388, 140, 22, 14);
		contentPane.add(lblDe);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBounds(575, 139, 22, 16);
		contentPane.add(lblA);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(607, 136, 89, 19);
		contentPane.add(comboBox_1);
		
		table = new JTable();
		table.setBounds(10, 260, 951, 400);
		contentPane.add(table);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 199, 371, 33);
		contentPane.add(lblVuelosOfrecids);
	}
}

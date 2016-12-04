package LP;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;

public class ComprarBillete extends JFrame implements ActionListener

{	
	private JPanel contentPane;
	private JTable table;
	public final static int panelWidth = 1400;
	public final static int panelHeight = 680;
	
	public static void main(String[] args)
	{
		ComprarBillete obj = new ComprarBillete();
		obj.setVisible(true);
	}
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
		table.setBounds(10, 260, 950, 300);
		contentPane.add(table);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 199, 371, 33);
		contentPane.add(lblVuelosOfrecids);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(420, 600, 69, 43);
		contentPane.add(spinner);
		
		JButton btnNewButton = new JButton("COMPRAR\r\n");
		btnNewButton.setBounds(607, 600, 121, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione cuantos billetes desea comprar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(118, 600, 289, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("CANCELAR");
		btnNewButton_1.setBounds(777, 600, 121, 43);
		contentPane.add(btnNewButton_1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
		
	}
}

package LP;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Justificante extends JInternalFrame 
{

	
	private JPanel contentPane;
	
	public Justificante()
	{
		createAndShowGUI();
	}

	
	
	
	public void createAndShowGUI()
	{
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JButton btnEnviar = new JButton("ENVIAR");
	btnEnviar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			
			
			
		}
	});
	btnEnviar.setBounds(183, 179, 125, 40);
	contentPane.add(btnEnviar);
	
	}
	

}

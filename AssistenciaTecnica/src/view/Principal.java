package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClientes = new JButton("");
		btnClientes.setToolTipText("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes=new Clientes();
				clientes.setVisible(true);

			}
		});
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/icones/24702_client_male_man_person_user_icon (1).png")));
		btnClientes.setBounds(10, 11, 109, 94);
		contentPane.add(btnClientes);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Relatorios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/icones/49615_reports_report_documents_icon.png")));
		btnNewButton_1.setBounds(126, 11, 135, 117);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnNewButton_2.setBounds(312, 128, 109, 102);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setToolTipText("Ordem de servi\u00E7o");
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnNewButton_3.setBounds(20, 116, 89, 96);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(136, 128, 125, 102);
		contentPane.add(btnNewButton_4);
	}
}

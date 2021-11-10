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
	public JButton btnRelatorios;
	public JButton btnFuncionarios;

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
		
		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.setToolTipText("Relatorios");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/icones/49615_reports_report_documents_icon.png")));
		btnRelatorios.setBounds(126, 11, 135, 117);
		contentPane.add(btnRelatorios);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnNewButton_2.setBounds(312, 128, 109, 102);
		contentPane.add(btnNewButton_2);
		
		JButton btnOdemDeServico = new JButton("New button");
		btnOdemDeServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Serviços  servico =new Serviços();
				servico.setVisible(true);
			}
		});
		btnOdemDeServico.setToolTipText("Ordem de servi\u00E7o");
		btnOdemDeServico.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnOdemDeServico.setBounds(20, 116, 89, 96);
		contentPane.add(btnOdemDeServico);
		
		btnFuncionarios = new JButton("");
		btnFuncionarios.setEnabled(false);
		btnFuncionarios.setToolTipText("Funcionarios");
		btnFuncionarios.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios =new Funcionarios();
				funcionarios.setVisible(true);
			}
		});
		btnFuncionarios.setBounds(136, 130, 100, 100);
		contentPane.add(btnFuncionarios);
	}
}

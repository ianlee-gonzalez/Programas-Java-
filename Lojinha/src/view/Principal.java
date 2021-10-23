package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	private JLabel lblData;
	private JLabel lblStatus;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// envento disparado ao ativar o JFRAME
				setarData();
				// Operador vendo o status do banco
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/pc.png")));
		setTitle("Lojinha Sistema de gest\u00E3o de E-commerce");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 255));
		panel.setBounds(0, 216, 434, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/icones/dbof.png")));
		lblStatus.setBounds(10, 10, 32, 32);
		panel.add(lblStatus);

		lblData = new JLabel("");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblData.setBounds(198, 10, 202, 24);
		panel.add(lblData);

		JButton btnEstoque = new JButton("");
		btnEstoque.setToolTipText("Estoque");
		btnEstoque.setIcon(new ImageIcon(Principal.class.getResource("/icones/estoque.png")));
		btnEstoque.setBounds(10, 0, 118, 111);
		contentPane.add(btnEstoque);

		JButton btnRelatorios = new JButton("");
		btnRelatorios.setIcon(new ImageIcon(Principal.class.getResource("/icones/relatorios.png")));
		btnRelatorios.setToolTipText("Relatorios");
		btnRelatorios.setBounds(0, 122, 128, 83);
		contentPane.add(btnRelatorios);

		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes=new Clientes();
				clientes.setVisible(true);
				
				
			}
		});
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/icones/clientes.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(177, 0, 128, 111);
		contentPane.add(btnClientes);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botao
				Sobre sobre = new Sobre();// criar objeto sobre
				sobre.setVisible(true); // exibir o JDIALOG Sobre
			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBackground(SystemColor.window);
		btnSobre.setBorder(null);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/icones/sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(177, 122, 128, 89);
		contentPane.add(btnSobre);

		JLabel lblNewLabel = new JLabel("Lojinha");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(307, 151, 117, 60);
		contentPane.add(lblNewLabel);
	} // fim do construtor

	/**
	 * responsavel por setar data e hora no rodapé
	 */
	private void setarData() {
		// as linhas abaixo sao usadas para obter e formatar a hora do sistema
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
		// a linha abaixo substitui a label do rodapé pela data
		lblData.setText(formatador.format(dataLabel));
	}

	private void status() {
		DAO dao = new DAO();
		try {
			Connection con = dao.conectar();
			System.out.println(con);
			// mudando o icone do rodapé quando conectar no banco de dados
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbof.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbon.png")));

			}
			// IMPORTANTE sempre encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

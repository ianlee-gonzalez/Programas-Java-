package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setTitle("Infox-Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(23, 29, 46, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(73, 26, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel txtsenha = new JLabel("Senha");
		txtsenha.setBounds(23, 54, 46, 14);
		contentPane.add(txtsenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(73, 51, 86, 20);
		contentPane.add(txtSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(10, 163, 89, 23);
		contentPane.add(btnEntrar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dberror.png")));
		lblStatus.setBounds(304, 172, 32, 32);
		contentPane.add(lblStatus);
	}// fim do construtor

	DAO dao = new DAO();

	private void status() {
		DAO dao = new DAO();
		try {
			Connection con = dao.conectar();
			System.out.println(con);
			// mudando o icone do rodapé quando conectar no banco de dados
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dberror.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dbok.png")));

			}
			// IMPORTANTE sempre encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	private void logar() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			try {
				String read = "select * from usuarios where login=? and senha=md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());
				// a linha abaixo executa a query(instrução SQL) armazenando o resultado no
				// objeto rs
				ResultSet rs = pst.executeQuery();
				// se existir o login e senha correspondente
				if (rs.next()) {
					// capturar o perfil do usuário
					String perfil = rs.getString(5);
					System.out.println(perfil);

					// tratamento de perfil de usuário					
					if (perfil.equals("Administrador")) {
						Principal principal = new Principal();
						principal.setVisible(true);
						// liberar os botões
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						// finalizar o JFrame
						this.dispose();
					} else {
						Principal principal = new Principal();
						principal.setVisible(true);
						// finalizar o JFrame
						this.dispose();
					}				
				} else {
					JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	
}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

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
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icones/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(100, 55, 86, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(10, 115, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(100, 113, 108, 20);
		contentPane.add(txtSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logar();
			}
		});
		btnEntrar.setBounds(10, 170, 89, 23);
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/icones/dbof.png")));
		lblStatus.setBounds(292, 90, 115, 114);
		contentPane.add(lblStatus);
	}//Fim do construtor
	DAO dao = new DAO();
	private JLabel lblStatus;

	private void status() {
		DAO dao = new DAO();
		try {
			Connection con = dao.conectar();
			System.out.println(con);
			// mudando o icone do rodap� quando conectar no banco de dados
			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));

			}
			// IMPORTANTE sempre encerrar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	private void logar() {
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			try {
				String read = "select * from funcionarios where login=? and senha=md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				// a linha abaixo executa a query(instru��o SQL) armazenando o resultado no
				// objeto rs
				ResultSet rs = pst.executeQuery();
				// se existir o login e senha correspondente
				if (rs.next()) {
					// capturar o perfil do usu�rio
					String perfil = rs.getString(5);
					System.out.println(perfil);

					// tratamento de perfil de usu�rio					
					if (perfil.equals("Administrador")) {
						Principal principal = new Principal();
						principal.setVisible(true);
						// liberar os bot�es
						principal.btnRelatorios.setEnabled(true);
						principal.btnFuncionarios.setEnabled(true);
						// finalizar o JFrame
						this.dispose();
					} else {
						Principal principal = new Principal();
						principal.setVisible(true);
						// finalizar o JFrame
						this.dispose();
					}				
				} else {
					JOptionPane.showMessageDialog(null, "Usu�rio e/ou senha inv�lido(s)", "Aten��o!",
							JOptionPane.WARNING_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	
}

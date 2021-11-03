package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Usuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisar;
	private JTextField txtIdUsu;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JTextField txtPerfil;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setBounds(100, 100, 772, 505);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1, 473);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(22, 176, 70, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(22, 276, 70, 15);
		contentPanel.add(lblNewLabel_1);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setBounds(13, 27, 218, 40);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		lblNewLabel_2.setBounds(246, 27, 50, 40);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(24, 272, 30, 30);
		getContentPane().add(lblNewLabel_3);
		
		txtIdUsu = new JTextField();
		txtIdUsu.setEditable(false);
		txtIdUsu.setBounds(72, 272, 39, 25);
		getContentPane().add(txtIdUsu);
		txtIdUsu.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Usuario");
		lblNewLabel_4.setBounds(12, 314, 70, 15);
		getContentPane().add(lblNewLabel_4);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(82, 312, 114, 19);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(82, 372, 114, 19);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPerfil = new JTextField();
		txtPerfil.setBounds(445, 278, 114, 19);
		getContentPane().add(txtPerfil);
		txtPerfil.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Login");
		lblNewLabel_6.setBounds(12, 374, 88, 15);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Senha");
		lblNewLabel_7.setBounds(12, 401, 70, 15);
		getContentPane().add(lblNewLabel_7);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(92, 403, 104, 19);
		getContentPane().add(txtSenha);
		
		JLabel lblNewLabel_8 = new JLabel("Perfil");
		lblNewLabel_8.setBounds(371, 277, 70, 15);
		getContentPane().add(lblNewLabel_8);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(37, 160, 476, -68);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 92, 476, 71);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);
		
		btnAdicionar = new JButton("Adicionar Usuario");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBounds(264, 338, 117, 25);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("Editar Usuario");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario();
			}
		});
		btnEditar.setBounds(274, 372, 117, 25);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir Usuario");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(406, 338, 117, 25);
		getContentPane().add(btnExcluir);
		// uso da biblioteca atxy2k para validações
				RestrictedTextField usuario = new RestrictedTextField(this.txtUsuario);
				usuario.setLimit(50);
				RestrictedTextField login = new RestrictedTextField(this.txtLogin);
				login.setLimit(50);
				RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
				senha.setLimit(250);
		
	}
	
	//FIm do construtor
	DAO dao =new DAO();
	private JTable table;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private void pesquisarUsuario() {
		String read="select * from usuarios where usuario like ?";
		try {
			Connection con =dao.conectar();
			PreparedStatement pst =con.prepareStatement(read);
			pst.setString(1,txtPesquisar.getText()+ "%");
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void adicionarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Endere�o", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (txtPerfil.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtPerfil.requestFocus();
		} else {
			String create = "insert into usuarios(usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtPerfil.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
					con.close();
					

				}
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Aten��o ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	private void editarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Atenção Po", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Endere�o", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (txtPerfil.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtPerfil.requestFocus();
		} else {
			String update = "update usuarios set usuario=?,login=?,senha=md5(?),perfil=? where iduser=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtPerfil.getText());
				pst.setString(5, txtIdUsu.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				con.close();
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atençao ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuário", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			
			}

		}

	}
	private void setarCampos() {
		// a linha abaixo obtem o conteúdo da linha da tabela
		// int (índice) [0], [1], [2],...
		int setar = table.getSelectedRow();
		// setar os campos
		txtIdUsu.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		txtPerfil.setText(table.getModel().getValueAt(setar, 3).toString());
		// gerenciar os botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}
	public void setarSenha() {
		String read2 = "select senha from usuarios where iduser = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtIdUsu.getText());
			// a linha abaixo executa a instrução sql e armazena o resultado no objeto rs
			ResultSet rs = pst.executeQuery();
			// a linha abaixo verifica se existe uma senha para o id
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void excluirUsuario() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where iduser = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdUsu.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir\nCliente possui pedido em aberto", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private void limpar() {
		txtIdUsu.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);	
		txtPerfil.setText(null);
		// limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}


	

	
	}


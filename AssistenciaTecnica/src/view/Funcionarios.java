package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Funcionarios extends JDialog {
	private JTextField txtPesquisar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Funcionarios dialog = new Funcionarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Funcionarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				chkSenha.setVisible(false);
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/icones/pc.png")));
		setBounds(100, 100, 744, 440);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFuncionario();
			}
		});
		txtPesquisar.setBounds(26, 12, 162, 34);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Funcionarios.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(203, 12, 70, 41);
		getContentPane().add(lblNewLabel);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(26, 74, 523, 111);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 523, 111);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(26, 225, 70, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(26, 264, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(26, 291, 70, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel cbo = new JLabel("Perfil");
		cbo.setBounds(12, 359, 70, 15);
		getContentPane().add(cbo);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(98, 223, 114, 19);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(98, 254, 114, 19);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(98, 285, 114, 19);
		getContentPane().add(txtSenha);
		
		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Operador"}));
		cboPerfil.setBounds(100, 354, 100, 24);
		getContentPane().add(cboPerfil);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Funcionarios.class.getResource("/icones/211872_person_add_icon.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarFuncionario();
			}
		});
		btnAdicionar.setToolTipText("Adicionar CLiente");
		btnAdicionar.setBounds(312, 197, 117, 25);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Funcionarios.class.getResource("/icones/103748_edit_close_user_icon.png")));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkSenha.isSelected()) {
					editarFuncionario();
				}else {
					editarUsuarioPersonalizado();
				}
			}
		});
		btnEditar.setToolTipText("Editar Funcionario");
		btnEditar.setBounds(312, 236, 117, 70);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Funcionarios.class.getResource("/icones/4850488_blocked_chat_delete_muted_off_icon.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFuncionario();
			}
		
		});
		btnExcluir.setToolTipText("Excluir Funcionario");
		btnExcluir.setBounds(312, 309, 117, 69);
		getContentPane().add(btnExcluir);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(26, 198, 70, 15);
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(98, 197, 114, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		chkSenha = new JCheckBox("Confirmar Senha");
		chkSenha.setBounds(34, 309, 154, 23);
		getContentPane().add(chkSenha);
	}//Fim do construtor
	DAO dao =new DAO();
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JComboBox cboPerfil;
	private JTextField txtId;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;
	/**
	 * Metodo responsavel por pesquisar o funcionario no banco 
	 */
	private void pesquisarFuncionario() {
		String read="select * from funcionarios where usuario like ?";
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
	private void adicionarFuncionario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Atenï¿½ï¿½o Pï¿½", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Login", "AtenÃ§Ã£o Po", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "AtenÃ§ao Pï¿½", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		//} else if ( {
			//JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Atenï¿½ï¿½o Pï¿½", JOptionPane.ERROR_MESSAGE);
			//txtPerfil.requestFocus();
		} else {
			String create = "insert into funcionarios(usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuario adicionado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
					con.close();
					limpar();

				}
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atenï¿½ï¿½o ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	private void editarFuncionario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Usuario", "Atençao ", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Atenção", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Perfil", "", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update funcionarios set usuario=?,login=?,senha=md5(?),perfil=? where idfunc=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				con.close();
				limpar();
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atenção ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuario", "AtenÃ§Ã£o!",
						JOptionPane.WARNING_MESSAGE);
			
			}

		}

	}//fim do metodo editar
	/**
	 * metodo responsavel por editar os dados do usuario  e a senha
	 */
	private void editarUsuarioPersonalizado() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Atenção ", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Atenï¿½ï¿½o", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Perfil", "", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update funcionarios set usuario=?,login=?,perfil=? where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				con.close();
				limpar();
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atenção ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuario", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			
			}

		}

	}
	private void setarCampos() {
		// a linha abaixo obtem o conteÃºdo da linha da tabela
		// int (Ã­ndice) [0], [1], [2],...
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		cboPerfil.setSelectedItem(table.getModel().getValueAt(setar, 4).toString());
		// gerenciar os botÃµes
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		chkSenha.setVisible(true);
	}
	public void setarSenha() {
		String read2 = "select senha from funcionarios where idfunc = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// a linha abaixo executa a instruï¿½ao e armazena o resultado no objeto rs
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
	private void excluirFuncionario() {
		// confimaÃ§Ã£o de exclusÃ£o
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from funcionarios where idfunc = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Mensagem",
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
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);	
		cboPerfil.setSelectedItem(null);
		// limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}

package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/pc.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento "disparado" ao digitar na caixa de texto
				pesquisarCliente();

			}
		});
		txtPesquisar.setBounds(10, 11, 162, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
		lblNewLabel.setBounds(184, 11, 65, 32);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(388, 102, -381, -54);
		getContentPane().add(desktopPane);

		JLabel txtID = new JLabel("Id");
		txtID.setBounds(10, 112, 59, 20);
		getContentPane().add(txtID);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(127, 112, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(31, 112, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(163, 112, 86, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 143, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(52, 143, 120, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(184, 143, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(227, 140, 86, 20);
		getContentPane().add(txtSenha);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/adicionar.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setBounds(0, 181, 80, 80);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/editar.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(81, 181, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/excluir.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(163, 181, 80, 80);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 42, 368, 62);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarSenha();
				setarCampos();
				
			}
		});
		scrollPane.setViewportView(table);
		// uso da biblioteca atxy2k
		RestrictedTextField nome = new RestrictedTextField(this.txtNome);
		nome.setLimit(50);
		RestrictedTextField email = new RestrictedTextField(this.txtEmail);
		nome.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
		nome.setLimit(250);
		
	}// fim do construtor
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// criando um objeto para aessar a classe DAO

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Método responsavel pela pesquisa do clientes com uso da biblioteca rs2xml
	 */

	private void pesquisarCliente() {
		// "?" é o parametro
		String read = "select * from clientes where nome like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1" é o parametro (?)
			pst.setString(1, txtPesquisar.getText() + "%");
			// executar a query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Metodo responsavel pela adição de clientes
	 */
	private void adicionarCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Atenção PÔ", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Email", "Atenção PÔ", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();

		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "Atenção PÔ", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		}else{
			//adicionar o cliente no banco de dados
			String create ="insert into clientes (nome,email,senha) values (?,?,md5(?))";
			try {
				Connection con=dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1,txtNome.getText());
				pst.setString(2,txtEmail.getText());
				pst.setString(3,txtSenha.getText());
				//criando uma variavel que ira executar a query e receber o valor 1 em caso positivo(inserçao do cliente no banco
				int confirma =pst.executeUpdate();
				if (confirma ==1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso ", "Ei", JOptionPane.INFORMATION_MESSAGE);
				
				con.close();
				limpar();
					
					
				}
			}catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Email ja cadastrado", "Atenção ", JOptionPane.ERROR_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();
				
			}
			
			catch (Exception e) {
				System.out.println(e);
				
			}
		
			
		}

	}
	/**
	 * Metodo responsavel por setar os campos da tabela no formulario 
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice) [0] [1] 
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 2).toString());
		//gerenciar os botoes
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	
		
				
		
		
	}
	/**
	 * Metodo especifico para setar a senha
	 */
	private void setarSenha() {
		String read2 ="select senha from clientes where idcli=?";
		try {
			Connection con =dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1,txtId.getText());
			//abaixo executa a instruçao sql e armazena o resultado no objeto rs(ResultSet)
			ResultSet rs =pst.executeQuery();
			if (rs.next()){
				txtSenha.setText(rs.getString(1));
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * Limpar os campos
	 */
	private void limpar() {
		txtSenha.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtId.setText(null);
		txtPesquisar.setText(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botoes (default) 
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		
		
				
	}
}

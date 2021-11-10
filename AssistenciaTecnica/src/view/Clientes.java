package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtIdCli;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtCpf;
	private JTable table;
	private JComboBox cboUf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Clientes.class.getResource("/icones/24702_client_male_man_person_user_icon (1).png")));
		setBounds(100, 100, 552, 421);
		getContentPane().setLayout(null);
		{
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBackground(Color.WHITE);
			desktopPane.setBounds(10, 89, 401, -42);
			getContentPane().add(desktopPane);
		}
		{
			JLabel lblNewLabel = new JLabel("*Campos Obrigatorios");
			lblNewLabel.setBounds(393, 13, 143, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
			lblNewLabel_1.setBounds(239, 0, 50, 25);
			getContentPane().add(lblNewLabel_1);
		}
		{
			txtPesquisar = new JTextField();
			txtPesquisar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					pesquisarCliente();

				}
			});
			txtPesquisar.setBounds(10, 7, 203, 27);
			getContentPane().add(txtPesquisar);
			txtPesquisar.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("ID");
			lblNewLabel_2.setBounds(10, 154, 11, 14);
			getContentPane().add(lblNewLabel_2);
		}
		{
			txtIdCli = new JTextField();
			txtIdCli.setEditable(false);
			txtIdCli.setBounds(31, 148, 27, 20);
			getContentPane().add(txtIdCli);
			txtIdCli.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("*Nome");
			lblNewLabel_3.setBounds(68, 154, 46, 14);
			getContentPane().add(lblNewLabel_3);
		}
		{
			txtNome = new JTextField();
			txtNome.setBounds(114, 151, 99, 20);
			getContentPane().add(txtNome);
			txtNome.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Cep");
			lblNewLabel_4.setBounds(20, 216, 36, 14);
			getContentPane().add(lblNewLabel_4);
		}
		{
			txtCep = new JTextField();
			txtCep.setBounds(59, 213, 86, 20);
			getContentPane().add(txtCep);
			txtCep.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Endere\u00E7o");
			lblNewLabel_5.setBounds(10, 241, 46, 14);
			getContentPane().add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Numero");
			lblNewLabel_6.setBounds(10, 266, 46, 14);
			getContentPane().add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Bairro");
			lblNewLabel_7.setBounds(10, 300, 46, 14);
			getContentPane().add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Cidade");
			lblNewLabel_8.setBounds(10, 325, 46, 14);
			getContentPane().add(lblNewLabel_8);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Telefone");
			lblNewLabel_9.setBounds(216, 153, 58, 17);
			getContentPane().add(lblNewLabel_9);
		}
		{
			txtTelefone = new JTextField();
			txtTelefone.setBounds(273, 151, 86, 20);
			getContentPane().add(txtTelefone);
			txtTelefone.setColumns(10);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Email");
			lblNewLabel_10.setBounds(10, 191, 46, 14);
			getContentPane().add(lblNewLabel_10);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(59, 185, 123, 20);
			getContentPane().add(txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lblNewLabel_11 = new JLabel("UF");
			lblNewLabel_11.setBounds(10, 353, 46, 14);
			getContentPane().add(lblNewLabel_11);
		}
		{
			txtEndereco = new JTextField();
			txtEndereco.setBounds(59, 238, 86, 20);
			getContentPane().add(txtEndereco);
			txtEndereco.setColumns(10);
		}
		{
			txtNumero = new JTextField();
			txtNumero.setBounds(59, 263, 86, 20);
			getContentPane().add(txtNumero);
			txtNumero.setColumns(10);
		}
		{
			txtBairro = new JTextField();
			txtBairro.setBounds(59, 294, 86, 20);
			getContentPane().add(txtBairro);
			txtBairro.setColumns(10);
		}
		{
			txtCidade = new JTextField();
			txtCidade.setBounds(59, 322, 86, 20);
			getContentPane().add(txtCidade);
			txtCidade.setColumns(10);
		}
		{
			cboUf = new JComboBox();
			cboUf.setModel(new DefaultComboBoxModel(
					new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
							"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
			cboUf.setBounds(59, 349, 76, 22);
			getContentPane().add(cboUf);
		}

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("AdicionarCliente");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();

			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/211872_person_add_icon.png")));
		btnAdicionar.setBounds(437, 274, 84, 93);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}

		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/103748_edit_close_user_icon.png")));
		btnEditar.setBounds(338, 290, 89, 85);
		getContentPane().add(btnEditar);

		JButton btnBuscar = new JButton("BuscarCep");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();

			}
		});
		btnBuscar.setBounds(152, 212, 89, 23);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_12 = new JLabel("CPF");
		lblNewLabel_12.setBounds(195, 188, 46, 14);
		getContentPane().add(lblNewLabel_12);

		txtCpf = new JTextField();
		txtCpf.setBounds(235, 185, 86, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 511, 84);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_13 = new JLabel("Complemento");
		lblNewLabel_13.setBounds(167, 241, 74, 14);
		getContentPane().add(lblNewLabel_13);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(242, 238, 86, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);
		{
			btnExcluir = new JButton("");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					excluirCliente();
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/4850488_blocked_chat_delete_muted_off_icon.png")));
			btnExcluir.setBounds(239, 290, 89, 85);
			getContentPane().add(btnExcluir);
		}
		// fim do construtor

	}

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JTextField txtComplemento;
	private JButton btnExcluir;
	private JButton btnEditar;
	

	/**
	 * Metodo responsavel por buscar o cep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP nï¿½o encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Metodo Responsavel por pesquisar o cliente no banco
	 */
	private void pesquisarCliente() {
		String read = "select * from clientes where  nome like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1" ï¿½ o parametro (?)
			pst.setString(1, txtPesquisar.getText() + "%");
			// executar a query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void adicionarCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Atenção ", JOptionPane.ERROR_MESSAGE);
			txtTelefone.requestFocus();

		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Endereï¿½o", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção ", JOptionPane.ERROR_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Cidade", "Atenção PÔ", JOptionPane.ERROR_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo UF", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboUf.requestFocus();

		} else {
			String create = "insert into clientes(nome,doc,cep,endereco,numero,complemento,bairro,cidade,uf,telefone,email) values (?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtTelefone.getText());
				pst.setString(11, txtEmail.getText());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
					con.close();
					limpar();

				}
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Email ja cadastrado", "Atenção ", JOptionPane.ERROR_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();

			}
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void setarCampos() {
		int setar = table.getSelectedRow();
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtTelefone.setText(table.getModel().getValueAt(setar, 2).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 3).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 4).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 5).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 6).toString());
		txtBairro.setText(table.getModel().getValueAt(setar, 7).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 8).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar, 9).toString());
		txtCpf.setText(table.getModel().getValueAt(setar, 10).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 11).toString());
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	private void limpar() {
		txtTelefone.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtIdCli.setText(null);
		txtPesquisar.setText(null);
		txtEndereco.setText(null);
		txtCidade.setText(null);
		txtCpf.setText(null);
		txtComplemento.setText(null);
		txtNumero.setText(null);
		txtBairro.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.setText(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botoes (default)
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}

	// PAREI AQUI CONTINUAR AQUI
	private void editarCliente() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Atenï¿½ï¿½o Pï¿½", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtTelefone.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Endereï¿½o", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Atençao", JOptionPane.ERROR_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo ", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo UF", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboUf.requestFocus();
		} else {
			// 11
			String update = "update clientes set nome=?,cpf=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,fone=?,email=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCpf.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtTelefone.getText());
				pst.setString(11, txtEmail.getText());
				pst.setString(12, txtIdCli.getText());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Editado ", "Ei", JOptionPane.INFORMATION_MESSAGE);

					con.close();
					limpar();

				}
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Email ja cadastrado", "Atenção ", JOptionPane.ERROR_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();

			}

			catch (Exception e) {
				System.out.println(e);

			}

		}

	}

	private void excluirCliente() {
		// confirmaï¿½ao de exclusï¿½o
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse cliente??", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso", "Ei", JOptionPane.INFORMATION_MESSAGE);
			// codigo principal
			String delete = "delete from clientes where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdCli.getText());
				pst.executeUpdate();
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();

				}
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusï¿½o nï¿½o realizada.Cliente possui pedido em aberto.",
						"Atenï¿½ï¿½o!", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

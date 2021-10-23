package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtIdCli;
	private JTextField txtNomeCli;
	private JTextField txtFoneCli;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUf;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/pc.png")));
		setBounds(150, 150, 573, 436);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 24, 305, 32);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(364, 24, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("*Campos Obrigat\u00F3rios");
		lblNewLabel_1.setBounds(406, 37, 141, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(26, 201, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtIdCli = new JTextField();
		txtIdCli.setBounds(55, 198, 86, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(193, 198, 161, 20);
		getContentPane().add(txtNomeCli);
		txtNomeCli.setColumns(10);

		JLabel nometxt = new JLabel("*Nome");
		nometxt.setBounds(155, 201, 46, 14);
		getContentPane().add(nometxt);

		JLabel txtfonecli = new JLabel("*Fone");
		txtfonecli.setBounds(382, 201, 46, 14);
		getContentPane().add(txtfonecli);

		txtFoneCli = new JTextField();
		txtFoneCli.setBounds(438, 198, 114, 20);
		getContentPane().add(txtFoneCli);
		txtFoneCli.setColumns(10);

		JLabel txtcep = new JLabel("CEP");
		txtcep.setBounds(26, 239, 32, 14);
		getContentPane().add(txtcep);

		txtCep = new JTextField();
		txtCep.setBounds(55, 236, 86, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JButton btnBuscarCep = new JButton("Buscar");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(158, 235, 89, 23);
		getContentPane().add(btnBuscarCep);

		JLabel lblNewLabel_6 = new JLabel("*Endere\u00E7o");
		lblNewLabel_6.setBounds(290, 239, 64, 14);
		getContentPane().add(lblNewLabel_6);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(364, 236, 188, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("*Numero");
		lblNewLabel_7.setBounds(290, 266, 64, 14);
		getContentPane().add(lblNewLabel_7);

		txtNumero = new JTextField();
		txtNumero.setBounds(364, 263, 188, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel txtcomplemento = new JLabel("Complemento");
		txtcomplemento.setBounds(276, 291, 89, 14);
		getContentPane().add(txtcomplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(364, 288, 188, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel txtbairro = new JLabel("*Bairro");
		txtbairro.setBounds(290, 316, 46, 14);
		getContentPane().add(txtbairro);

		JLabel txtcidade = new JLabel("*Cidade");
		txtcidade.setBounds(290, 342, 46, 14);
		getContentPane().add(txtcidade);

		JLabel cbouf = new JLabel("*Uf");
		cbouf.setBounds(290, 367, 46, 14);
		getContentPane().add(cbouf);

		txtBairro = new JTextField();
		txtBairro.setBounds(364, 313, 188, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(363, 339, 189, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setToolTipText("");
		cboUf.setBounds(368, 363, 42, 22);
		getContentPane().add(cboUf);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(10, 306, 86, 64);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("New button");
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnEditar.setBounds(108, 306, 64, 64);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("New button");
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(193, 306, 64, 69);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(20, 67, 527, 87);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 pesquisarCliente();
			}
		});
		scrollPane.setBounds(0, 0, 527, 87);
		desktopPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	// Fim do construtor>>>>>>>>>>>>
	DAO dao=new DAO();
	/**
	 * buscarCep
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
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void pesquisarCliente() {
		String read = "select * from clientes where cliente like ?";
		try {
			//abrir a conexao com o banco
			Connection con = dao.conectar();
			//preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			//substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar.getText() + "%");
			//obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			//popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

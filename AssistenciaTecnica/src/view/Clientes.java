package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Clientes extends JDialog {
	private JTextField textoPesquisa;
	private JTextField txtIdCli;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icones/24702_client_male_man_person_user_icon (1).png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBackground(Color.WHITE);
			desktopPane.setBounds(10, 89, 401, -24);
			getContentPane().add(desktopPane);
		}
		{
			JLabel lblNewLabel = new JLabel("*Campos Obrigatorios");
			lblNewLabel.setBounds(281, 11, 143, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(Clientes.class.getResource("/icones/pesquisar.png")));
			lblNewLabel_1.setBounds(221, 11, 50, 25);
			getContentPane().add(lblNewLabel_1);
		}
		{
			textoPesquisa = new JTextField();
			textoPesquisa.setBounds(10, 18, 182, 20);
			getContentPane().add(textoPesquisa);
			textoPesquisa.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("ID");
			lblNewLabel_2.setBounds(10, 100, 11, 14);
			getContentPane().add(lblNewLabel_2);
		}
		{
			txtIdCli = new JTextField();
			txtIdCli.setBounds(43, 97, 86, 20);
			getContentPane().add(txtIdCli);
			txtIdCli.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("*Nome");
			lblNewLabel_3.setBounds(10, 125, 46, 14);
			getContentPane().add(lblNewLabel_3);
		}
		{
			txtNome = new JTextField();
			txtNome.setBounds(43, 122, 109, 20);
			getContentPane().add(txtNome);
			txtNome.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Cep");
			lblNewLabel_4.setBounds(162, 100, 36, 14);
			getContentPane().add(lblNewLabel_4);
		}
		{
			txtCep = new JTextField();
			txtCep.setBounds(191, 97, 86, 20);
			getContentPane().add(txtCep);
			txtCep.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Endere\u00E7o");
			lblNewLabel_5.setBounds(281, 146, 46, 14);
			getContentPane().add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Numero");
			lblNewLabel_6.setBounds(281, 168, 46, 14);
			getContentPane().add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Bairro");
			lblNewLabel_7.setBounds(281, 189, 46, 14);
			getContentPane().add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Cidade");
			lblNewLabel_8.setBounds(281, 214, 46, 14);
			getContentPane().add(lblNewLabel_8);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Telefone");
			lblNewLabel_9.setBounds(0, 146, 46, 17);
			getContentPane().add(lblNewLabel_9);
		}
		{
			txtTelefone = new JTextField();
			txtTelefone.setBounds(43, 143, 86, 20);
			getContentPane().add(txtTelefone);
			txtTelefone.setColumns(10);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Email");
			lblNewLabel_10.setBounds(0, 168, 46, 14);
			getContentPane().add(lblNewLabel_10);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(43, 165, 109, 20);
			getContentPane().add(txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JLabel lblNewLabel_11 = new JLabel("UF");
			lblNewLabel_11.setBounds(281, 239, 46, 14);
			getContentPane().add(lblNewLabel_11);
		}
		{
			txtEndereco = new JTextField();
			txtEndereco.setBounds(325, 143, 86, 20);
			getContentPane().add(txtEndereco);
			txtEndereco.setColumns(10);
		}
		{
			txtNumero = new JTextField();
			txtNumero.setBounds(325, 165, 86, 20);
			getContentPane().add(txtNumero);
			txtNumero.setColumns(10);
		}
		{
			txtBairro = new JTextField();
			txtBairro.setBounds(325, 186, 86, 20);
			getContentPane().add(txtBairro);
			txtBairro.setColumns(10);
		}
		{
			txtCidade = new JTextField();
			txtCidade.setBounds(325, 211, 86, 20);
			getContentPane().add(txtCidade);
			txtCidade.setColumns(10);
		}
		{
			JComboBox cboUf = new JComboBox();
			cboUf.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
			cboUf.setBounds(337, 235, 46, 22);
			getContentPane().add(cboUf);
		}
		
		JButton btnAdicionar = new JButton("New button");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/update.png")));
		btnAdicionar.setBounds(0, 189, 72, 68);
		getContentPane().add(btnAdicionar);
		
		JButton btnEditar = new JButton("New button");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/create.png")));
		btnEditar.setBounds(74, 189, 89, 64);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("New button");
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icones/delete.png")));
		btnExcluir.setBounds(162, 189, 89, 64);
		getContentPane().add(btnExcluir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBuscar.setBounds(294, 96, 89, 23);
		getContentPane().add(btnBuscar);
		//fim do construtor
	}
}


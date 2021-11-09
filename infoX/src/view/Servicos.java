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

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;

public class Servicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisar;

	/**
	 * Launch the application.
	 */

	private String tipo;

	public static void main(String[] args) {
		try {
			Servicos dialog = new Servicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Servicos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servicos.class.getResource("/img/pc.png")));
		setBounds(100, 100, 789, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarOs();
			}
		});
		txtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtPesquisar.setBounds(512, 11, 99, 20);
		contentPanel.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Servicos.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(621, 11, 46, 35);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(674, 14, 31, 14);
		contentPanel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		txtId.setBounds(694, 11, 79, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 25, 407, 134);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(10, 25, 76, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(220, 25, 131, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(163, 28, 46, 14);
		panel.add(lblNewLabel_2);
		chkOrcamento = new JCheckBox("Or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";

			}
		});
		buttonGroup.add(chkOrcamento);
		chkOrcamento.setBounds(10, 74, 97, 23);
		panel.add(chkOrcamento);

		chkServico = new JCheckBox("Servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		buttonGroup.add(chkServico);
		chkServico.setBounds(128, 74, 97, 23);
		panel.add(chkServico);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(258, 56, 46, 14);
		panel.add(lblNewLabel_3);

		cboStat = new JComboBox();
		cboStat.setModel(new DefaultComboBoxModel(new String[] { "Aguardando Retirada", "Retirado", "Na Bancada" }));
		cboStat.setBounds(258, 74, 93, 22);
		panel.add(cboStat);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(525, 61, 238, 134);
		contentPanel.add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 238, 134);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		btnAdicionar = new JButton("Adicionar Ordem de servi\u00E7o");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(10, 306, 89, 71);
		contentPanel.add(btnAdicionar);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisandoOs();
			}
		});
		btnPesquisar.setToolTipText("PesquisarOrdemDeServi\u00E7o");
		btnPesquisar.setIcon(new ImageIcon(Servicos.class.getResource("/img/read.png")));
		btnPesquisar.setBounds(98, 306, 89, 71);
		contentPanel.add(btnPesquisar);

		btnEditar = new JButton("Editar Ordem De Servi\u00E7o");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarOs();
			}
		});
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/update.png")));
		btnEditar.setBounds(186, 306, 89, 71);
		contentPanel.add(btnEditar);

		btnExcluir = new JButton("Excluir Ordem de servi\u00E7o");
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(273, 306, 89, 71);
		contentPanel.add(btnExcluir);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Servicos.class.getResource("/img/os.png")));
		lblNewLabel_4.setBounds(22, 170, 70, 87);
		contentPanel.add(lblNewLabel_4);

		JLabel novaaa = new JLabel("Equipamento");
		novaaa.setBounds(115, 181, 72, 14);
		contentPanel.add(novaaa);

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(203, 178, 86, 20);
		contentPanel.add(txtEquipamento);
		txtEquipamento.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Defeito");
		lblNewLabel_6.setBounds(125, 206, 46, 14);
		contentPanel.add(lblNewLabel_6);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(200, 203, 89, 20);
		contentPanel.add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Tecnico");
		lblNewLabel_7.setBounds(125, 231, 46, 14);
		contentPanel.add(lblNewLabel_7);

		txtTecnico = new JTextField();
		txtTecnico.setBounds(203, 228, 86, 20);
		contentPanel.add(txtTecnico);
		txtTecnico.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Valor");
		lblNewLabel_8.setBounds(125, 256, 46, 14);
		contentPanel.add(lblNewLabel_8);

		txtTotal = new JTextField();
		txtTotal.setText("0");
		txtTotal.setBounds(203, 253, 86, 20);
		contentPanel.add(txtTotal);
		txtTotal.setColumns(10);

		JButton btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(Servicos.class.getResource("/img/print.png")));
		btnImprimir.setBounds(361, 306, 89, 71);
		contentPanel.add(btnImprimir);
		
		RestrictedTextField equipamento = new RestrictedTextField(txtEquipamento);
		equipamento.setLimit(200);
		RestrictedTextField defeito = new RestrictedTextField(txtDefeito);
		defeito.setLimit(200);
		RestrictedTextField tecnico = new RestrictedTextField(txtTecnico);
		tecnico.setLimit(50);

	} // Fim do construtor

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtOs;
	private JTextField txtData;
	private JTable table;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtTecnico;
	private JTextField txtTotal;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;
	private JComboBox cboStat;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	private void pesquisarOs() {
		String read = "select idcli as ID, nome as Nome,fone as Telefone from clientes where nome like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar.getText() + "%");
			// obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCampos() {
		int setar = table.getSelectedRow();
			txtId.setText(table.getModel().getValueAt(setar, 0).toString());
			
}
	private void pesquisandoOs() {
		String numOs = JOptionPane.showInputDialog("numero da Os");
		String read = "select * from tbos where os=" + numOs;
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtId.setText(rs.getString(9));
				txtData.setText(rs.getString(2));
				if (rs.getString(3).equals("Orçamento")) {
					chkOrcamento.setSelected(true);
				}else {
					chkServico.setSelected(true);
					
				}
				cboStat.setSelectedItem(rs.getString(4));
				txtEquipamento.setText(rs.getString(5));
				txtDefeito.setText(rs.getString(6));
				txtTecnico.setText(rs.getString(7));
				txtTotal.setText(rs.getString(8));
				btnAdicionar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				
				
			}else {
				JOptionPane.showMessageDialog(null, "Número de OS não existe", "Atenção !",
						JOptionPane.WARNING_MESSAGE);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void emitirOs() {
		// validação
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtId.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStat.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStat.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			String create = "insert into tbos (tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values (?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, tipo);
				pst.setString(2, cboStat.getSelectedItem().toString());
				pst.setString(3, txtEquipamento.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtTecnico.getText());
				pst.setString(6, txtTotal.getText());
				pst.setString(7, txtId.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucessor", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método emitirOs()
	/**
	 * Método responsável por editar os dados da OS no banco de dados
	 */
	private void editarOs() {
		// validação
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtId.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStat.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStat.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			// editar os dados da OS no banco de dados
			String update = "update tbos set tipo=?,statusos=?,equipamento=?,defeito=?,tecnico=?,valor=? where os=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, tipo);
				pst.setString(2, cboStat.getSelectedItem().toString());
				pst.setString(3, txtEquipamento.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtTecnico.getText());
				pst.setString(6, txtTotal.getText());
				pst.setString(7, txtOs.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edição da OS no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS editada com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// fim do metodo editarOs()

	/**
	 * Método responsável por zerar todos os campos
	 */
	private void limpar() {
		// limpar campos
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtOs.setText(null);
		txtData.setText(null);
		chkOrcamento.setSelected(false);
		chkOrcamento.setSelected(false);
		cboStat.setSelectedItem(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtTecnico.setText(null);
		txtTotal.setText(null);
		// limpar tabela
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}// fim do método limpar()



	
	
		
		
	}
	
		
	



	


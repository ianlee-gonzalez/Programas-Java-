package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Serviços extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTable table;
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtDefeitocli;
	private JTextField txtAcessorio;
	private JTextField txtDefeitotec;
	private JTextField txtFormadepag;
	private JTextField txtObs;
	private JTextField txtTec;
	private JButton btnPesquisar;
	private JButton btnAdicionar;
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Serviços dialog = new Serviços();
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
	public Serviços() {
		setBounds(100, 100, 749, 464);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarOs();
			}
		});
		txtPesquisar.setBounds(499, 12, 114, 29);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		txtId = new JTextField();
		txtId.setBounds(674, 12, 61, 29);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(499, 54, 238, 174);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 238, 174);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel nenhuma = new JPanel();
		nenhuma.setBorder(new TitledBorder(null, "Os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		nenhuma.setBounds(12, 54, 475, 128);
		getContentPane().add(nenhuma);
		nenhuma.setLayout(null);
		
		txtOs = new JTextField();
		txtOs.setBounds(12, 22, 88, 27);
		nenhuma.add(txtOs);
		txtOs.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setBounds(118, 28, 70, 15);
		nenhuma.add(lblNewLabel);
		
		txtData = new JTextField();
		txtData.setBounds(170, 22, 114, 19);
		nenhuma.add(txtData);
		txtData.setColumns(10);
		
		chkOrcamento = new JCheckBox("Orçamento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipo = "Orçamento";
			}
		});
		chkOrcamento.setBounds(8, 57, 114, 23);
		nenhuma.add(chkOrcamento);
		
		chkServico = new JCheckBox("Serviço");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		chkServico.setBounds(18, 84, 100, 23);
		nenhuma.add(chkServico);
		
		cboStat = new JComboBox();
		cboStat.setModel(new DefaultComboBoxModel(new String[] {"Na Bancada", "Retirado"}));
		cboStat.setBounds(126, 69, 144, 24);
		nenhuma.add(cboStat);
		
		JLabel lblNewLabel_2_3 = new JLabel("Tecnico");
		lblNewLabel_2_3.setBounds(302, 22, 70, 15);
		nenhuma.add(lblNewLabel_2_3);
		
		txtTec = new JTextField();
		txtTec.setBounds(376, 24, 70, 19);
		nenhuma.add(txtTec);
		txtTec.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Marca");
		lblNewLabel_2.setBounds(22, 203, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Modelo");
		lblNewLabel_2_1.setBounds(144, 203, 70, 15);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Acessorio");
		lblNewLabel_2_2.setBounds(262, 194, 70, 15);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("Defeito Cli");
		lblNewLabel_3.setBounds(22, 247, 70, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Defeito Tecnico");
		lblNewLabel_2_4.setBounds(262, 247, 70, 15);
		getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Obs");
		lblNewLabel_2_5.setBounds(36, 290, 70, 15);
		getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_4 = new JLabel("Forma de Pagamento");
		lblNewLabel_4.setBounds(0, 369, 151, 15);
		getContentPane().add(lblNewLabel_4);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(12, 226, 94, 19);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(124, 226, 114, 19);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		txtDefeitocli = new JTextField();
		txtDefeitocli.setColumns(10);
		txtDefeitocli.setBounds(32, 263, 75, 19);
		getContentPane().add(txtDefeitocli);
		
		txtAcessorio = new JTextField();
		txtAcessorio.setColumns(10);
		txtAcessorio.setBounds(262, 221, 114, 19);
		getContentPane().add(txtAcessorio);
		
		txtDefeitotec = new JTextField();
		txtDefeitotec.setBounds(246, 263, 114, 19);
		getContentPane().add(txtDefeitotec);
		txtDefeitotec.setColumns(10);
		
		txtFormadepag = new JTextField();
		txtFormadepag.setBounds(169, 367, 114, 19);
		getContentPane().add(txtFormadepag);
		txtFormadepag.setColumns(10);
		
		txtObs = new JTextField();
		txtObs.setBounds(100, 288, 114, 19);
		getContentPane().add(txtObs);
		txtObs.setColumns(10);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setBounds(496, 260, 117, 25);
		getContentPane().add(btnAdicionar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(618, 263, 117, 25);
		getContentPane().add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOs();
			}
		});
		btnEditar.setBounds(499, 344, 117, 25);
		getContentPane().add(btnEditar);
		
		btnPesquisar = new JButton("Pesquisar Os");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisandoOs();
			}
		});
		btnPesquisar.setBounds(308, 312, 117, 25);
		getContentPane().add(btnPesquisar);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(636, 19, 41, 15);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_5 = new JLabel("Valor");
		lblNewLabel_5.setBounds(22, 322, 70, 15);
		getContentPane().add(lblNewLabel_5);
		
		txtValor = new JTextField();
		txtValor.setBounds(85, 317, 114, 19);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

	}//fim do construtor 
	DAO dao =new DAO();
	private JComboBox cboStat;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JLabel lblNewLabel_5;
	private JTextField txtValor;
	private void pesquisarOs() {
		String read = "select idcli as ID, nome as Nome,telefone as Telefone from clientes where nome like ?";
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
		String read = "select * from os where os=" + numOs;
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtId.setText(rs.getString(3));
				txtData.setText(rs.getString(2));
				if (rs.getString(3).equals("Orçamento")) {
					chkOrcamento.setSelected(true);
				}else {
					chkServico.setSelected(true);
					
				}
				cboStat.setSelectedItem(rs.getString(4));
				txtMarca.setText(rs.getString(5));
				txtDefeitocli.setText(rs.getString(6));
				txtTec.setText(rs.getString(7));
				txtValor.setText(rs.getString(8));
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
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtMarca.requestFocus();
		} else if (txtDefeitocli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			String create = "insert into os (tipo,osstatus,marca,modelo,defeitocli,valor,idcli) values (?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, tipo);
				pst.setString(2, cboStat.getSelectedItem().toString());
				pst.setString(3, txtMarca.getText());
				pst.setString(4, txtModelo.getText());
				pst.setString(5, txtDefeitocli.getText());
				pst.setString(6, txtValor.getText());
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
		} else if (txtMarca.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtMarca.requestFocus();
		} else if (txtDefeitocli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			// editar os dados da OS no banco de dados
			String update = "update os set tipo=?,osstatus=?,modelo=?,defeitocli=?,marca=?,valor=? where os=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, tipo);
				pst.setString(2, cboStat.getSelectedItem().toString());
				pst.setString(3, txtMarca.getText());
				pst.setString(4, txtModelo.getText());
				pst.setString(5, txtTec.getText());
				pst.setString(6, txtValor.getText());
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
		txtMarca.setText(null);
		txtModelo.setText(null);
		txtTec.setText(null);
		txtValor.setText(null);
		// limpar tabela
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}// fim do método limpar()



	
	
		
		

	
}

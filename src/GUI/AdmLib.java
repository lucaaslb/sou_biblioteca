/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;


import dbSQLite.dbConnection;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

import java.awt.Toolkit;

public class AdmLib extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtQuant;
	private JTextField txtGetAuthor;
	private JTextField txtGetISBN;
	private JTextField txtGetQuant;
	private JScrollPane scroll;
	private JList<String> list;

	dbConnection database = new dbConnection();

	/**
	 * Launch the application.
	 */
	private Login login = new Login();
	private JTextField txtRA;
	private JTextField txtIdbook;
	private JTextField txtGetID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmLib frame = new AdmLib();

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
	public AdmLib() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdmLib.class.getResource("/img/logo-anhembi.png")));
		setResizable(false);
		setTitle("Adm Sou Biblioteca");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 330, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(192, 192, 192));
		tabbedPane.setBounds(0, 0, 324, 341);
		contentPane.add(tabbedPane);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Acervo", null, layeredPane, null);
		layeredPane.setLayout(null);

		txtGetAuthor = new JTextField();
		txtGetAuthor.setEditable(false);
		txtGetAuthor.setBounds(97, 203, 176, 20);
		layeredPane.add(txtGetAuthor);
		txtGetAuthor.setColumns(10);

		txtGetISBN = new JTextField();
		txtGetISBN.setEditable(false);
		txtGetISBN.setBounds(97, 234, 176, 20);
		layeredPane.add(txtGetISBN);
		txtGetISBN.setColumns(10);

		txtGetQuant = new JTextField();
		txtGetQuant.setEditable(false);
		txtGetQuant.setBounds(97, 265, 176, 20);
		layeredPane.add(txtGetQuant);
		txtGetQuant.setColumns(10);

		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(11, 103, 98, 141);
		scroll = new JScrollPane(list);
		scroll.setBounds(10, 11, 295, 138);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		layeredPane.add(scroll);

		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Cadastro", null, layeredPane_1, null);
		layeredPane_1.setLayout(null);

		JLabel lblCadastroDeLivros = new JLabel("Cadastro de livros:");
		lblCadastroDeLivros.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		lblCadastroDeLivros.setBounds(41, 31, 129, 28);
		layeredPane_1.add(lblCadastroDeLivros);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(41, 103, 36, 14);
		layeredPane_1.add(lblTitulo);

		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setBounds(41, 159, 36, 14);
		layeredPane_1.add(lblIsbn);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(41, 131, 31, 14);
		layeredPane_1.add(lblAutor);

		JLabel label = new JLabel("");
		label.setBounds(42, 243, 46, 14);
		layeredPane_1.add(label);

		txtTitle = new JTextField();
		txtTitle.setBounds(81, 100, 210, 20);
		layeredPane_1.add(txtTitle);
		txtTitle.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setBounds(82, 128, 209, 20);
		layeredPane_1.add(txtAuthor);
		txtAuthor.setColumns(10);

		txtISBN = new JTextField();
		txtISBN.setBounds(82, 156, 209, 20);
		layeredPane_1.add(txtISBN);
		txtISBN.setColumns(10);

		JButton btnSend = new JButton("Cadastrar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String title = txtTitle.getText();
				String author = txtAuthor.getText();
				String ISBN = txtISBN.getText();
				int quant = Integer.parseInt(txtQuant.getText());

				database.insertBook(title, author, ISBN, quant);
				list.setListData(database.listBooks());
				txtTitle.setText("");
				txtAuthor.setText("");
				txtISBN.setText("");
				txtQuant.setText("");

			}
		});
		btnSend.setBounds(190, 220, 101, 23);
		layeredPane_1.add(btnSend);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) {
					String title = list.getSelectedValue().toString();
					String dados[] = database.bookInformations(title);
					txtGetID.setText(dados[0]);
					txtGetAuthor.setText(dados[1]);
					txtGetISBN.setText(dados[2]);
					txtGetQuant.setText(dados[3]);
					
				}
			}
		});

		JButton btnClear = new JButton("Limpar");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtTitle.setText("");
				txtAuthor.setText("");
				txtISBN.setText("");
				txtQuant.setText("");
			}
		});
		btnClear.setBounds(81, 220, 89, 23);
		layeredPane_1.add(btnClear);

		JLabel lblQuant = new JLabel("Quant.:");
		lblQuant.setBounds(31, 190, 46, 14);
		layeredPane_1.add(lblQuant);

		txtQuant = new JTextField();
		txtQuant.setBounds(81, 187, 210, 20);
		layeredPane_1.add(txtQuant);
		txtQuant.setColumns(10);

		JButton btnBack = new JButton("Voltar");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login.setLocationRelativeTo(null);
				login.setVisible(true);

			}
		});
		btnBack.setBounds(225, 345, 89, 23);
		contentPane.add(btnBack);

		list.setListData(database.listBooks());

		txtGetID = new JTextField();
		txtGetID.setEditable(false);
		txtGetID.setBounds(97, 172, 176, 20);
		layeredPane.add(txtGetID);
		txtGetID.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(41, 175, 46, 14);
		layeredPane.add(lblId);

		JLabel lblAutor_1 = new JLabel("Autor:");
		lblAutor_1.setBounds(41, 206, 46, 14);
		layeredPane.add(lblAutor_1);

		JLabel lblIsbn_1 = new JLabel("ISBN:");
		lblIsbn_1.setBounds(41, 237, 46, 14);
		layeredPane.add(lblIsbn_1);

		JLabel lblQuant_1 = new JLabel("Quant:");
		lblQuant_1.setBounds(41, 268, 46, 14);
		layeredPane.add(lblQuant_1);

		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Emprestimos", null, layeredPane_2, null);
		layeredPane_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("RA:");
		lblNewLabel.setBounds(34, 71, 46, 14);
		layeredPane_2.add(lblNewLabel);

		txtRA = new JTextField();
		txtRA.setBounds(90, 68, 169, 20);
		layeredPane_2.add(txtRA);
		txtRA.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Livro ID");
		lblNewLabel_1.setBounds(34, 122, 46, 14);
		layeredPane_2.add(lblNewLabel_1);

		txtIdbook = new JTextField();
		txtIdbook.setBounds(90, 119, 169, 20);
		layeredPane_2.add(txtIdbook);
		txtIdbook.setColumns(10);

		JButton btnNewButton = new JButton("Locar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ra = txtRA.getText();
				String id = txtIdbook.getText();
				String title = database.bookTitle(id);

				if (title != null) {
					database.Rent(title, ra);
					txtRA.setText("");
					txtIdbook.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "ID invalido");
				}

			}
		});
		
		btnNewButton.setBounds(170, 164, 89, 23);
		layeredPane_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Devolu\u00E7\u00E3o");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ra = txtRA.getText();
				String id = txtIdbook.getText();
				String title = database.bookTitle(id);

				if (title != null) {
					database.returnBook(ra, title);
					txtRA.setText("");
					txtIdbook.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "ID invalido");
				}
				
			}
		});
		btnNewButton_1.setBounds(64, 164, 96, 23);
		layeredPane_2.add(btnNewButton_1);

	}
}

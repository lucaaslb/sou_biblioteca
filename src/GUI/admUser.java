/*
 * Projeto Integrado 3� Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import FacebookConnection.FacebookC;
import Library.Alunos;
import Library.auxData;
import dbSQLite.dbConnection;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

public class admUser extends JFrame {

	private JPanel contentPane;

	static Alunos aluno = new Alunos();
	dbConnection database = new dbConnection();
	private JTextField txtDate_inicio;
	private JTextField contData;
	auxData date = new auxData();
	private JTextField txtMulta;
	private JList<String> list = new JList<String>();
	Login login = new Login();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admUser frame = new admUser("", "", "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admUser(String nome, String ra, String token) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(admUser.class.getResource("/img/logo-anhembi.png")));
		setTitle("Sou Biblioteca");

		aluno = new Alunos(nome, ra, token);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 387);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbName = new JLabel(aluno.getName());
		lbName.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		lbName.setBounds(51, 52, 247, 23);
		contentPane.add(lbName);

		JButton btnShare = new JButton("Compartilhe!");
		btnShare.setIcon(new ImageIcon(admUser.class.getResource("/img/facebook.png")));
		btnShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FacebookC face = new FacebookC();
				String title = list.getSelectedValue().toString();
				String author[] = database.bookInformations(title);
				if (aluno.getToken().equals("000")) {
					face.ConnectFB();
					String msg = JOptionPane.showInputDialog("Compartilhe o que esta lendo! ", "Estou lendo "+ title + " - " + author[1]);
					aluno.setToken(face.getToken());
					database.updateToken(aluno.getRA(), aluno.getToken());

					face.Share(msg, aluno.getToken());
				} else {
					String msg = JOptionPane.showInputDialog("Compartilhe o que esta lendo! ", "Estou lendo "+ title + " - " + author[1]);
					face.Share(msg, aluno.getToken());
				}
			}
		});
		btnShare.setForeground(new Color(255, 255, 255));
		btnShare.setBackground(Color.WHITE);
		btnShare.setBounds(266, 273, 107, 23);
		contentPane.add(btnShare);

	
		list.setBounds(23, 87, 199, 244);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(33, 118, 330, 84);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				String data = "";
				int devolucao;
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) {
					String title = list.getSelectedValue().toString();
					data = database.dateRent(title, aluno.getRA());
					txtDate_inicio.setText(data);
					try {
						devolucao = date.getDaysRunning(data);
						devolucao = date.days(devolucao);
						contData.setText("" + devolucao);
						txtMulta.setText("R$ 0,00");
						if (devolucao == 1) {
							JOptionPane.showMessageDialog(null, "Lembre-se de devolver seu livro na biblioteca !!!");
							
						} else if (devolucao < 1) {
							JOptionPane.showMessageDialog(null, "Passou o prazo de devolução. Você está gerando multa  =( ");

							txtMulta.setText(date.multa(devolucao));
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			}
		});

		list.setListData(database.listBookUser(aluno.getRA()));

		JLabel lblBemVindo = new JLabel("Bem vindo");
		lblBemVindo.setBounds(20, 11, 80, 14);
		contentPane.add(lblBemVindo);

		txtDate_inicio = new JTextField();
		txtDate_inicio.setEditable(false);
		txtDate_inicio.setBounds(42, 246, 86, 20);
		contentPane.add(txtDate_inicio);
		txtDate_inicio.setColumns(10);

		JLabel lblEntregaEm = new JLabel("Entrega em: ");
		lblEntregaEm.setBounds(155, 229, 80, 14);
		contentPane.add(lblEntregaEm);

		JLabel lblInicio = new JLabel("Inicio: ");
		lblInicio.setBounds(44, 229, 46, 14);
		contentPane.add(lblInicio);

		contData = new JTextField();
		contData.setEditable(false);
		contData.setBounds(155, 246, 34, 20);
		contentPane.add(contData);
		contData.setColumns(10);

		JLabel lblDias = new JLabel("Dias");
		lblDias.setBounds(199, 249, 46, 14);
		contentPane.add(lblDias);

		txtMulta = new JTextField();
		txtMulta.setText("R$ 0,00");
		txtMulta.setEditable(false);
		txtMulta.setBounds(89, 301, 86, 20);
		txtMulta.setColumns(10);

		JLabel lblMulta = new JLabel("Multa:");
		lblMulta.setBounds(44, 304, 46, 14);
		contentPane.add(txtMulta);
		contentPane.add(lblMulta);
		
		JLabel lblSeusLivros = new JLabel("Seus livros:");
		lblSeusLivros.setBounds(33, 103, 80, 14);
		contentPane.add(lblSeusLivros);
		
		JLabel lbldate = new JLabel(date.getDateTime());
		lbldate.setBounds(319, 11, 70, 14);
		contentPane.add(lbldate);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
			}
		});
		btnSair.setBounds(303, 300, 70, 23);
		contentPane.add(btnSair);

		System.out.println("user: " + aluno.getRA());
	}
}

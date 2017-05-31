/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */

package GUI;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import FacebookConnection.*;
import dbSQLite.dbConnection;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Login extends JFrame {


	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passLogin;

	private static AdmLib adm = new AdmLib();

	private static FacebookC face = new FacebookC();
	private dbConnection database = new dbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null); // centralizar a tela
														// toda vez que abrir
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
	public Login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo-anhembi.png")));
		setTitle("Sou Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-13, -29, 330, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtLogin = new JTextField();
		txtLogin.setBounds(75, 225, 171, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admUser user;
				String pass = "";
				String login = txtLogin.getText();
				String dados[];
				if (txtLogin.getText().equals("") || passLogin.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite dados de login");
				} else {
					for (int i = 0; i < passLogin.getPassword().length; i++) {
						pass += passLogin.getPassword()[i];
					}

					if (database.Login(login, pass)) {
						dados = database.studentLogin(login);
						user = new admUser(dados[1], dados[0], dados[3]);
						System.out.println("tokeN: " + dados[3]);
						dispose();
						user.setVisible(true);
						user.setLocationRelativeTo(null);
					}
				}

			}
		});
		btnNewButton.setBounds(117, 302, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/img/login_facebook.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				admUser user;
				face.ConnectFB();

				String nome = face.getName();
				String email = face.getEmail();
				String token = face.getToken();
				String RA;
				String password;

				if (face.getName() == null) {
					JOptionPane.showMessageDialog(null, "ERRO AO LOGAR NO FACEBOOK");

				} else if (!database.UserExist(face.getEmail())) {
					RA = JOptionPane.showInputDialog(null, "Cadastre seu RA: ");
					password = JOptionPane.showInputDialog(null, "Crie uma senha de acesso: ");
					database.insertStudent(RA, nome, email, token, password);
				}

				String dados[] = database.student(email);
				user = new admUser(dados[1], dados[0], dados[3]);
				dispose();
				user.setVisible(true);
				user.setLocationRelativeTo(null);

			}
		});
		btnNewButton_1.setBounds(60, 336, 205, 37);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(75, 256, 68, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("RA");
		lblNewLabel_1.setBounds(75, 210, 58, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Projeto Integrado 3\u00B0 Sem");
		lblNewLabel_2.setBounds(172, 494, 144, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblSou = new JLabel("SOU");
		lblSou.setFont(new Font("Verdana", Font.BOLD, 18));
		lblSou.setBounds(140, 56, 46, 31);
		contentPane.add(lblSou);

		JLabel lblBiblioteca = new JLabel("Biblioteca");
		lblBiblioteca.setFont(new Font("Verdana", Font.BOLD, 22));
		lblBiblioteca.setBounds(103, 97, 133, 28);
		contentPane.add(lblBiblioteca);

		passLogin = new JPasswordField();
		passLogin.setBounds(75, 271, 171, 20);
		contentPane.add(passLogin);

		JButton btnAdm = new JButton("ADM");
		btnAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String senha1 = "ragnar";

				String senha2 = JOptionPane.showInputDialog("Senha: ");

				if (senha1.equals(senha2)) {
					dispose();
					adm.setLocationRelativeTo(null);
					adm.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Senha invalida!");
				}

			}
		});
		btnAdm.setBounds(208, 470, 68, 23);
		contentPane.add(btnAdm);

		JLabel lblCadastro = new JLabel("Cadastre-se");
		lblCadastro.setBounds(24, 494, 73, 14);
		contentPane.add(lblCadastro);
		lblCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Mudar o cursor
																// ao passar por
																// cima da label
		/* abrir uma janela de cadastro ao clicar com o mouse */
		lblCadastro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					String RA = JOptionPane.showInputDialog("Digite seu RA: ");
					String nome = JOptionPane.showInputDialog("Digite seu nome: ");
					String email = JOptionPane.showInputDialog("Digite seu email: ");
					String senha = JOptionPane.showInputDialog("Digite sua senha: ");
					String token = "000";

					System.out.println(RA + nome + email + senha);
					database.insertStudent(RA, nome, email, token, senha);
				}
			}

		});
	}
}

/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class admuser2 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	
	Login login = new Login();
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admuser2 frame = new admuser2();
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
	public admuser2() {
		setBounds(100, 100, 328, 410);
		getContentPane().setLayout(null);
		
		JLabel lbName = new JLabel(/*Login.aluno.getName()*/"teste");
		lbName.setBounds(42, 41, 46, 14);
		getContentPane().add(lbName);
		
		textField = new JTextField();
		textField.setBounds(91, 60, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(55, 174, 89, 23);
		getContentPane().add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 208, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(88, 91, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		

	}

}

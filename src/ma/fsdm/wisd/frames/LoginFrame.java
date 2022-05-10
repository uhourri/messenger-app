package ma.fsdm.wisd.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.entites.Utilisateur;
import ma.fsdm.wisd.services.Service;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4856887825892584700L;
	private JPanel contentPane;
	private JTextField nomUtilisateurText;
	private JTextField motPasseText;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(95, 50, 122, 30);
		panel_1.add(lblNewLabel_1);

		nomUtilisateurText = new JTextField();
		nomUtilisateurText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomUtilisateurText.setBounds(227, 50, 130, 30);
		panel_1.add(nomUtilisateurText);
		nomUtilisateurText.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(95, 99, 122, 30);
		panel_1.add(lblNewLabel_2);

		motPasseText = new JTextField();
		motPasseText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motPasseText.setBounds(227, 99, 130, 30);
		panel_1.add(motPasseText);
		motPasseText.setColumns(10);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_3 = new JLabel("Vous n'avez pas un compte? ");
		panel_2.add(lblNewLabel_3);

		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(registerButton);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(loginButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				Utilisateur utilisateur = service.recupererUtilisateur(nomUtilisateurText.getText(), motPasseText.getText());
				
				if (utilisateur != null) {
					setVisible(false);
					try {
						try (FileWriter writer = new FileWriter("login.txt")) {
							writer.write("["+utilisateur.getCode()+"] "+ utilisateur.getPrenom()+" "+utilisateur.getNom());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					new MessengerFrame();
				} else {
					System.out.println("--> not !!");
				}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new RegisterFrame();
			}
		});
	}
}

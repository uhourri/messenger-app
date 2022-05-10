package ma.fsdm.wisd.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ma.fsdm.wisd.entites.Utilisateur;
import ma.fsdm.wisd.services.Service;

public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631477413815663449L;
	private JPanel contentPane;
	private JTextField nomUtilisateurText;
	private JTextField motPasseText;
	private JTextField nomText;
	private JTextField prenomText;


	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(95, 91, 122, 30);
		panel_1.add(lblNewLabel_1);

		nomUtilisateurText = new JTextField();
		nomUtilisateurText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomUtilisateurText.setBounds(227, 91, 130, 30);
		panel_1.add(nomUtilisateurText);
		nomUtilisateurText.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mot de passe : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(95, 132, 122, 30);
		panel_1.add(lblNewLabel_2);

		motPasseText = new JTextField();
		motPasseText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motPasseText.setBounds(227, 132, 130, 30);
		panel_1.add(motPasseText);
		motPasseText.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Prenom : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(95, 50, 122, 30);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Nom : ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(95, 11, 122, 30);
		panel_1.add(lblNewLabel_1_2);

		nomText = new JTextField();
		nomText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomText.setColumns(10);
		nomText.setBounds(227, 11, 130, 30);
		panel_1.add(nomText);

		prenomText = new JTextField();
		prenomText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		prenomText.setColumns(10);
		prenomText.setBounds(227, 50, 130, 30);
		panel_1.add(prenomText);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_3 = new JLabel("Vous avez deja un compte? ");
		panel_2.add(lblNewLabel_3);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(loginButton);


		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(registerButton);
		
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(nomText.getText());
				utilisateur.setPrenom(prenomText.getText());
				utilisateur.setNomUtilisateur(nomUtilisateurText.getText());
				utilisateur.setMotPasse(motPasseText.getText());

				service.ajouterUtilisateur(utilisateur);

				setVisible(false);
				new LoginFrame();
			}
		});
		
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginFrame();
			}
		});
	}

}

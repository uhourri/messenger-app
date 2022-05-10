package ma.fsdm.wisd.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ma.fsdm.wisd.entites.Message;
import ma.fsdm.wisd.services.Service;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Color;

public class MessengerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 910503695460890722L;
	private JPanel contentPane;
	private JTextField messageText;
	@SuppressWarnings("rawtypes")
	JList utilisateursList = new JList();
	JLabel headerLabel = new JLabel();
	@SuppressWarnings("rawtypes")
	JList messageList = new JList();
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public MessengerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(headerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.WEST);

		utilisateursList.setModel(new AbstractListModel() {
			Service service = new Service();
			String[] values = service.recupererUtilisateursList();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_1.add(utilisateursList);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);

		messageList.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		messageList.setBounds(2, 0, 315, 183);
		panel_3.add(messageList);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.SOUTH);

		messageText = new JTextField();
		panel_4.add(messageText);
		messageText.setColumns(30);

		JButton envoyerButton = new JButton("Envoyer");
		panel_4.add(envoyerButton);

		envoyerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String receUser = (String) utilisateursList.getSelectedValue();
				int receCode = Integer.parseInt(receUser.substring(receUser.indexOf("[") + 1, receUser.indexOf("]")));

				String destUser = headerLabel.getText();
				int destCode = Integer.parseInt(destUser.substring(destUser.indexOf("[") + 1, destUser.indexOf("]")));

				Service service = new Service();
				Message message = new Message(messageText.getText(), new Date(System.currentTimeMillis()),
						service.recupererUtilisateur(destCode), service.recupererUtilisateur(receCode));
				service.ajouterMessage(message);
				refrech();
				messageText.setText("");

			}
		});

		utilisateursList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				refrech();
			}
		});

		File myObj = new File("login.txt");
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String data = myReader.nextLine();
		System.out.println(data);
		headerLabel.setText(data);

	}

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	void refrech() {
		String receUser = (String) utilisateursList.getSelectedValue();
		int receCode = Integer.parseInt(receUser.substring(receUser.indexOf("[") + 1, receUser.indexOf("]")));

		String destUser = headerLabel.getText();
		int destCode = Integer.parseInt(destUser.substring(destUser.indexOf("[") + 1, destUser.indexOf("]")));

		Service service = new Service();

		List<Message> messages = service.recupererAllMessages(destCode, receCode);

		String[] messagesValues = new String[messages.size()];

		for (int i = 0; i < messages.size(); i++) {

			if (destCode == messages.get(i).getDestinateur().getCode()) {
				messagesValues[i] = " >> " + messages.get(i).getContenu();
			} else {
				messagesValues[i] = " << " + messages.get(i).getContenu();
			}

		}

		messageList.setModel(new AbstractListModel() {
			String[] values = messagesValues;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

}

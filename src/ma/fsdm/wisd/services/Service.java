package ma.fsdm.wisd.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fsdm.wisd.entites.Message;
import ma.fsdm.wisd.entites.Utilisateur;

public class Service implements IService {

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "INSERT INTO utilisateur(nom, prenom, nom_utilisateur, mot_passe) VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getNomUtilisateur());
			statement.setString(4, utilisateur.getMotPasse());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur recupererUtilisateur(int code) {
		Utilisateur utilisateur = new Utilisateur();
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM utilisateur WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			ResultSet result = statement.executeQuery();
			result.next();
			utilisateur.setCode(code);
			utilisateur.setNom(result.getString("nom"));
			utilisateur.setPrenom(result.getString("prenom"));
			utilisateur.setNomUtilisateur(result.getString("nom_utilisateur"));
			utilisateur.setMotPasse(result.getString("mot_passe"));
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	@Override
	public Utilisateur recupererUtilisateur(String nomUtilisateur, String motPasse) {
		Utilisateur utilisateur = null;
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM utilisateur WHERE nom_utilisateur = ? AND mot_passe = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nomUtilisateur);
			statement.setString(2, motPasse);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				utilisateur = new Utilisateur();
				
				utilisateur.setCode(result.getInt("code"));
				utilisateur.setNom(result.getString("nom"));
				utilisateur.setPrenom(result.getString("prenom"));
				utilisateur.setNomUtilisateur(nomUtilisateur);
				utilisateur.setMotPasse(motPasse);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	@Override
	public void supprimerUtilisateur(int code) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "DELETE FROM utilisateur WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Utilisateur> recupererUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM utilisateur";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setCode(result.getInt("code"));
				utilisateur.setNom(result.getString("nom"));
				utilisateur.setPrenom(result.getString("prenom"));
				utilisateur.setNomUtilisateur(result.getString("nom_utilisateur"));
				utilisateur.setMotPasse(result.getString("mot_passe"));
				utilisateurs.add(utilisateur);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateurs;
	}

	@Override
	public void ajouterMessage(Message message) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "INSERT INTO message(contenu, date, destinateur, recepteur) VALUES(?,CURRENT_TIMESTAMP,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, message.getContenu());
			Service service = new Service();
			Utilisateur destinateur = service.recupererUtilisateur(message.getDestinateur().getNomUtilisateur(), message.getDestinateur().getMotPasse());
			Utilisateur recepteur = service.recupererUtilisateur(message.getRecepteur().getNomUtilisateur(), message.getRecepteur().getMotPasse());
			statement.setInt(2, destinateur.getCode());
			statement.setInt(3, recepteur.getCode());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message recupererMessage(int code) {
		Message message = new Message();
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM message WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			ResultSet result = statement.executeQuery();
			result.next();
			message.setCode(code);
			message.setContenu(result.getString("contenu"));
			message.setDate(result.getDate("date"));
			message.setDestinateur(recupererUtilisateur(result.getInt("destinateur")));
			message.setRecepteur(recupererUtilisateur(result.getInt("recepteur")));
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}

	@Override
	public void supprimerMessage(int code) {
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "DELETE FROM message WHERE code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> recupererMessages(int destinateur, int recepteur) {
		List<Message> messages = new ArrayList<Message>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM message WHERE destinateur = ? AND recepteur = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, destinateur);
			statement.setInt(2, recepteur);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Message message = new Message();
				message.setCode(result.getInt("code"));
				message.setContenu(result.getString("contenu"));
				message.setDate(result.getDate("date"));
				message.setDestinateur(recupererUtilisateur(destinateur));
				message.setRecepteur(recupererUtilisateur(recepteur));	
				messages.add(message);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return messages;
	}

	@Override
	public String[] recupererUtilisateursList() {
		
		List<Utilisateur> utilisateurs = recupererUtilisateurs();
		String[] values = new String[utilisateurs.size()];
		
		for(int i=0; i<utilisateurs.size(); i++) {
			values[i] = "["+utilisateurs.get(i).getCode()+"] "+ utilisateurs.get(i).getPrenom()+" "+utilisateurs.get(i).getNom();
		}
		
		return values;
	}

	@Override
	public List<Message> recupererAllMessages(int destinateur, int recepteur) {
		List<Message> messages = new ArrayList<Message>();
		
		Connection connection = SingletonConnection.getConnection();
		try {
			String query = "SELECT * FROM message "
					+ "WHERE (destinateur = ? AND recepteur = ?) "
					+ "OR (destinateur = ? AND recepteur = ?) "
					+ "ORDER BY date";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, destinateur);
			statement.setInt(2, recepteur);
			statement.setInt(3, recepteur);
			statement.setInt(4, destinateur);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Message message = new Message();
				message.setCode(result.getInt("code"));
				message.setContenu(result.getString("contenu"));
				message.setDate(result.getDate("date"));
				message.setDestinateur(recupererUtilisateur(result.getInt("destinateur")));
				message.setRecepteur(recupererUtilisateur(result.getInt("recepteur")));	
				messages.add(message);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return messages;
	}

}

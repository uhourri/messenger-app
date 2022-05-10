package ma.fsdm.wisd.services;

import java.util.List;

import ma.fsdm.wisd.entites.Message;
import ma.fsdm.wisd.entites.Utilisateur;

public interface IService {	

	public void ajouterUtilisateur(Utilisateur utilisateur);
	public Utilisateur recupererUtilisateur(int code);
	public Utilisateur recupererUtilisateur(String nomUtilisateur, String motPasse);
	public void supprimerUtilisateur(int code);
	public List<Utilisateur> recupererUtilisateurs();
	public String[] recupererUtilisateursList();
	
	public void ajouterMessage(Message message);
	public Message recupererMessage(int code);
	public void supprimerMessage(int code);
	public List<Message> recupererMessages(int destinateur, int recepteur);
	public List<Message> recupererAllMessages(int destinateur, int recepteur);
	
}

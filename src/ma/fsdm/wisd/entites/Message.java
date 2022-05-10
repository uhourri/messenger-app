package ma.fsdm.wisd.entites;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable{
	private static final long serialVersionUID = 2295937574351615488L;
	
	private int code;
	private String contenu;
	private Date date;
	private Utilisateur destinateur;
	private Utilisateur recepteur;
	
	public String getContenu() {
		return contenu;
	}
	@Override
	public String toString() {
		return "Message [code=" + code + ", contenu=" + contenu + ", date=" + date + ", destinateur=" + destinateur
				+ ", recepteur=" + recepteur + "]";
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getDestinateur() {
		return destinateur;
	}
	public void setDestinateur(Utilisateur destinateur) {
		this.destinateur = destinateur;
	}
	public Utilisateur getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(Utilisateur recepteur) {
		this.recepteur = recepteur;
	}
	public Message(String contenu, Date date, Utilisateur destinateur, Utilisateur recepteur) {
		super();
		this.contenu = contenu;
		this.date = date;
		this.destinateur = destinateur;
		this.recepteur = recepteur;
	}
	public Message() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	

}

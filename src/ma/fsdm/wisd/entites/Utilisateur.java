package ma.fsdm.wisd.entites;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 6147697059844278891L;
	
	private int code;
	private String nom;
	private String prenom;
	private String nomUtilisateur;
	private String motPasse;
	public int getCode() {
		return code;
	}
	@Override
	public String toString() {
		return "Utilisateur [code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", nomUtilisateur="
				+ nomUtilisateur + ", motPasse=" + motPasse + "]";
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getMotPasse() {
		return motPasse;
	}
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	public Utilisateur(String nom, String prenom, String nomUtilisateur, String motPasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nomUtilisateur = nomUtilisateur;
		this.motPasse = motPasse;
	}
	public Utilisateur() {
		super();
	}
	
	
}

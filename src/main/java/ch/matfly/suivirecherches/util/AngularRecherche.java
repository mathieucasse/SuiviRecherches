package ch.matfly.suivirecherches.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.matfly.suivirecherches.model.Entreprise;
import ch.matfly.suivirecherches.model.Personne;
import ch.matfly.suivirecherches.model.Recherche;
import lombok.Data;

@Data
public class AngularRecherche {
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Long id;
	private Date dateContact;
	private String poste;
	private String statut;
	private String assignationORP;
	private Integer tauxActivite;
	private String approcheMedia;
	private String client;
	private String entreprise;
	private String contactNom;
	private String contactPrenom;
	private String contactEmail;
	private String contactTelephone;
	
	public AngularRecherche(Recherche recherche) {
		super();
		this.id = recherche.getId();
		this.dateContact = recherche.getContactDate();
		this.poste = recherche.getPoste();
		this.statut = recherche.getStatut();
		this.assignationORP = recherche.getAssignationORP();
		this.tauxActivite = (recherche.getTauxActivite() != null) ? Integer.valueOf(recherche.getTauxActivite()) : null;
		this.approcheMedia = recherche.getApprocheMedia();
		this.client = recherche.getClient();
		this.entreprise = recherche.getEntreprise().getNom();
		this.contactNom = recherche.getPersonne().getNom();
		this.contactPrenom = recherche.getPersonne().getPrenom();
		this.contactEmail = recherche.getPersonne().getEmail();
		this.contactTelephone = recherche.getPersonne().getTelephone();
	}
	
	public Recherche buildRecherche() throws ParseException{
		Entreprise entreprise = new Entreprise(this.entreprise, null, null);
		Personne personne = new Personne(contactNom, contactPrenom, contactTelephone, contactEmail);
		return new Recherche(id, dateContact,poste,client,statut, assignationORP,
				tauxActivite.toString(), approcheMedia, entreprise, personne);
		
	}

	public AngularRecherche(Long id, String dateContact, String poste, String statut, String assignationORP,
			Integer tauxActivite, String approcheMedia, String client, String entreprise, String contactNom,
			String contactPrenom, String contactEmail, String contactTelephone) {
		super();
		this.id = id;
		try {
			this.dateContact = sdf.parse(dateContact);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.dateContact = new Date();
		}
		this.poste = poste;
		this.statut = statut;
		this.assignationORP = assignationORP;
		this.tauxActivite = tauxActivite;
		this.approcheMedia = approcheMedia;
		this.client = client;
		this.entreprise = entreprise;
		this.contactNom = contactNom;
		this.contactPrenom = contactPrenom;
		this.contactEmail = contactEmail;
		this.contactTelephone = contactTelephone;
	}
	
}

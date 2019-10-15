package ch.matfly.suivirecherches.model.dto;

import ch.matfly.suivirecherches.model.Entreprise;
import ch.matfly.suivirecherches.model.Evenement;
import ch.matfly.suivirecherches.model.Personne;
import ch.matfly.suivirecherches.model.Recherche;
import ch.matfly.suivirecherches.util.MatFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AngularRechercheDto {
	
	private Long id;
	private String dateContact;
	private String poste;
	private String statut;
	private String assignationORP;
	private Integer tauxActivite;
	private String approcheMedia;
	private String entrepriseS;
	private String entrepriseTelS;
	private String contactNomS;
	private String contactPrenomS;
	private String contactEmailS;
	private String contactTelephoneS;
	private String entrepriseF;
	private String entrepriseTelF;
	private String contactNomF;
	private String contactPrenomF;
	private String contactEmailF;
	private String contactTelephoneF;

	public AngularRechercheDto(){
		super();
	}

	public AngularRechercheDto(String poste, String statut){
		super();
		this.poste = poste;
		this.statut = statut;
	}
	public AngularRechercheDto(Recherche recherche) {
		super();
		this.id = recherche.getId();
		this.dateContact = MatFormat.format(recherche.getContactDate());
		this.poste = recherche.getPoste();
		this.statut = recherche.getStatut();
		this.assignationORP = recherche.getAssignationORP();
		this.tauxActivite = (recherche.getTauxActivite() != null) ? Integer.valueOf(recherche.getTauxActivite()) : null;
		this.approcheMedia = recherche.getApprocheMedia();
		if(null != recherche.getEntrepriseService()) {
			this.entrepriseS = recherche.getEntrepriseService().getNom();
			this.entrepriseTelS = recherche.getEntrepriseService().getTelephone();
		}
		if(null != recherche.getPersonneService()) {
			this.contactNomS = recherche.getPersonneService().getNom();
			this.contactPrenomS = recherche.getPersonneService().getPrenom();
			this.contactEmailS = recherche.getPersonneService().getEmail();
			this.contactTelephoneS = recherche.getPersonneService().getTelephone();
		}
		if(null != recherche.getEntrepriseFinale()) {
			this.entrepriseF = recherche.getEntrepriseFinale().getNom();
			this.entrepriseTelF = recherche.getEntrepriseFinale().getTelephone();
		}
		if(null != recherche.getPersonneFinale()) {
			this.contactNomF = recherche.getPersonneFinale().getNom();
			this.contactPrenomF = recherche.getPersonneFinale().getPrenom();
			this.contactEmailF = recherche.getPersonneFinale().getEmail();
			this.contactTelephoneF = recherche.getPersonneFinale().getTelephone();
		}
	}
	
	public Recherche buildNewRecherche(){
		Entreprise entrepriseService = new Entreprise(this.entrepriseS, this.entrepriseTelS, null);

		Personne personneService = null;
		if(null != contactNomS) {
			personneService = new Personne(contactNomS, contactPrenomS, contactTelephoneS, contactEmailS);
		}
		Entreprise entrepriseFinale = null;
		if(null != entrepriseF){
			entrepriseFinale = new Entreprise(this.entrepriseF, this.entrepriseTelF, null);
		}
		Personne personneFinale = null;
		if(null != contactNomF){
			personneFinale = new Personne(contactNomF, contactPrenomF, contactTelephoneF, contactEmailF);
		}
		Date dc = MatFormat.parse(dateContact);

		Recherche recherche = new Recherche(dc, poste, statut, assignationORP,
				tauxActivite.toString(), approcheMedia,
				entrepriseService, personneService,
				entrepriseFinale, personneFinale);

		Evenement evenement = new Evenement(dc, "CREATION", "Fist Action", recherche );
		recherche.getEvenements().add(evenement);

		return recherche;
	}

	public AngularRechercheDto(Long id, String dateContact, String poste, String statut, String assignationORP,
			Integer tauxActivite, String approcheMedia, String entrepriseS, String contactNomS,
			String contactPrenomS, String contactEmailS, String contactTelephoneS, String entrepriseF, String contactNomF,
			String contactPrenomF, String contactEmailF, String contactTelephoneF) {
		super();
		this.id = id;
		this.dateContact = dateContact;
		this.poste = poste;
		this.statut = statut;
		this.assignationORP = assignationORP;
		this.tauxActivite = tauxActivite;
		this.approcheMedia = approcheMedia;
		this.entrepriseS = entrepriseS;
		this.contactNomS = contactNomS;
		this.contactPrenomS = contactPrenomS;
		this.contactEmailS = contactEmailS;
		this.contactTelephoneS = contactTelephoneS;
		this.entrepriseF = entrepriseF;
		this.contactNomF = contactNomF;
		this.contactPrenomF = contactPrenomF;
		this.contactEmailF = contactEmailF;
		this.contactTelephoneF = contactTelephoneF;
	}
	
}

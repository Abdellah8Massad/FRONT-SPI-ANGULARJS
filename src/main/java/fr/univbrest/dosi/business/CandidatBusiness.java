package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Candidat;

public interface CandidatBusiness {

	Candidat creerCandidat(Candidat newcandidat);
	void supprimerCandidat(String c);
	List<Candidat> rechercherParNom(String nom);
	List<Candidat> rechercherParUniv(String universiteOrigine);
	List<Candidat> recupererToutesLesCandidat();
	Candidat rechercherParId(String id);
}

package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Formation;

public interface FormationBusiness {
	
	Formation creerFormation(Formation newformation);
	List<Formation> rechercherParNom(String nom);
	List<Formation> recupererToutesLesFormation();
	List<Formation> rechercherParCode(String code);
	void supprimerFormation(String code);
	
}

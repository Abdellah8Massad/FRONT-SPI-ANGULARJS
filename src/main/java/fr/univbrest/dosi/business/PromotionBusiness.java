package fr.univbrest.dosi.business;

import java.math.BigDecimal;
import java.util.List;

import fr.univbrest.dosi.bean.Promotion;

public interface PromotionBusiness {

	Promotion creerPromotion(Promotion promotion);
	void supprimerPromotion(String annee,String formation);
	List<Promotion> rechercherToutesPromotion();
	Promotion rechercherIdPromotion(String annee,String formation);
	List<Promotion> rechercherParNoEnseignantPromotion(BigDecimal nE);
	
}

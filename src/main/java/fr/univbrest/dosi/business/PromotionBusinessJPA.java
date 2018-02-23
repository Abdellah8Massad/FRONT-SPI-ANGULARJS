package fr.univbrest.dosi.business;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.repositories.PromotionRepository;

@Component
public class PromotionBusinessJPA implements PromotionBusiness {

	private PromotionRepository repos;
	
	@Autowired
	public PromotionBusinessJPA(PromotionRepository repos) {
		this.repos=repos;
	}
	
	@Override
	public Promotion creerPromotion(Promotion promotion) {
		return repos.save(promotion);
	}

	@Override
	public void supprimerPromotion(String annee, String formation) {
		PromotionPK id = new PromotionPK(annee,formation);
		repos.delete(id);
	}

	@Override
	public List<Promotion> rechercherToutesPromotion() {
		return (List<Promotion>) repos.findAll();
	}

	@Override
	public Promotion rechercherIdPromotion(String annee, String formation) {
		PromotionPK id=new PromotionPK(annee,formation);
		return repos.findById(id);
	}

	@Override
	public List<Promotion> rechercherParNoEnseignantPromotion(BigDecimal nE) {
		return (List<Promotion>) repos.findBynoEnseignant(nE);
	}

}

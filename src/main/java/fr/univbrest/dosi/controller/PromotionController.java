package fr.univbrest.dosi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.business.PromotionBusiness;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

	PromotionBusiness business;
	
	@Autowired
	public PromotionController(PromotionBusiness business) {
		this.business=business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Promotion creerPromotion(@RequestBody Promotion p) {
		return business.creerPromotion(p);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Promotion> recupererToutesLesPromotions(){
		return business.rechercherToutesPromotion();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="id/{anneeUniversitaire}/{codeFormation}")
	public Promotion recuperParIdPromotion(@PathVariable("anneeUniversitaire") String anneeUniversitaire, @PathVariable("codeFormation") String codeFormation) { 
		return business.rechercherIdPromotion(anneeUniversitaire, codeFormation);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="enseignant/{no}")
	public List<Promotion> recuperParNoEnseignantPromotion(@PathVariable("no") BigDecimal no) { 
		return (List<Promotion>) business.rechercherParNoEnseignantPromotion(no);
	}
		
	@RequestMapping(method = RequestMethod.DELETE , value="delete/{anneeUniversitaire}/{codeFormation}")
	public void supprimerPromotion(@PathVariable("anneeUniversitaire") String anneeUniversitaire, @PathVariable("codeFormation") String codeFormation){ 
		business.supprimerPromotion(anneeUniversitaire, codeFormation);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="count/")
	public int countPromotions(){
		return business.rechercherToutesPromotion().size();
	}
}

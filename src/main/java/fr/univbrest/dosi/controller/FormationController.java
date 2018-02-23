package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.business.FormationBusiness;

@RestController
@RequestMapping("/formation")
public class FormationController {
	
	private FormationBusiness business;

	
	@Autowired
	public FormationController(FormationBusiness business){
		this.business=business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Formation creerFormation(@RequestBody Formation formation) {
		return business.creerFormation(formation);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Formation> recupererToutesLesFormation() {
		return business.recupererToutesLesFormation();
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/{nom}")
	public List<Formation> recupererParNomFormation(@PathVariable("nom") String nom) {
		return business.rechercherParNom(nom);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/code/{code}")
	public List<Formation> recupererParCodeFormation(@PathVariable("code") String code) {
		return business.rechercherParCode(code);
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value="/delete/{code}")
	public void supprimerParCodeFormation(@PathVariable("code") String code) {
		 business.supprimerFormation(code);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="count/")
	public int countFormation() {
		return business.recupererToutesLesFormation().size();
	}
}

package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.business.CandidatBusiness;

@RestController
@RequestMapping("/candidat")
public class CandidatController {

	CandidatBusiness business;
	
	@Autowired
	public CandidatController(CandidatBusiness business) {
		this.business=business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Candidat creerCandidat(@RequestBody Candidat c) {
		return business.creerCandidat(c);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Candidat> recupererToutesLesCandidats() {
		return business.recupererToutesLesCandidat();
	}
	
	@RequestMapping(method = RequestMethod.GET , value="nom/{nom}")
	public List<Candidat> recupererParNomCandidat(@PathVariable("nom")  String nom) {
		return business.rechercherParNom(nom);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="univ/{univ}")
	public List<Candidat> recupererParUnivCandidat(@PathVariable("univ") String univ) {
		return business.rechercherParUniv(univ);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="id/{id}")
	public Candidat rechercherCandiadt(@PathVariable("id")  String id) {
		 return business.rechercherParId(id);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="count/")
	public int countCandidat() {
		return business.recupererToutesLesCandidat().size();
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value="delete/{id}")
	public String supprimerCandiadt(@PathVariable("id")  String id) {
		  business.supprimerCandidat(id);
		  return "candidat supprimer";
	}
	
}

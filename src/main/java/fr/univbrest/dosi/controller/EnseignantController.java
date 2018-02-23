package fr.univbrest.dosi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.business.EnseignantBusiness;

@RestController
@RequestMapping("/enseignant")
public class EnseignantController {
	
	EnseignantBusiness business;
	
	@Autowired
	public EnseignantController(EnseignantBusiness business) {
		this.business=business;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Enseignant creerEnseignant(@RequestBody Enseignant enseignant) {
		return business.creerEnseignant(enseignant);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Enseignant> rechercherLesEnseignants(){
		return business.chercherToutesEnseignant();
	}
	
	@RequestMapping(method = RequestMethod.GET , value="nom/{nom}")
	public List<Enseignant> rechercherParNomEnseignant(@PathVariable("nom") String nom) {
		return (List<Enseignant>) business.chercherParNom(nom);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="mail/{mail:.+}")
	public Enseignant rechercherParMail(@PathVariable("mail") String mail) {
			return business.chercherParEmail(mail);
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value="delete/{no}")
	public void supprimerParNoEnseignant(@PathVariable("no") Long no) {
			business.supprimerEnseignant(no);
	}
	
	@RequestMapping(method = RequestMethod.GET , value="count/")
	public int countEnseignants(){
		return business.chercherToutesEnseignant().size();
	}

}

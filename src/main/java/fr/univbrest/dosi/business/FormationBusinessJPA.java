package fr.univbrest.dosi.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.repositories.FormationRepository;

@Component
public class FormationBusinessJPA implements FormationBusiness{

	private FormationRepository repos;
	
	@Autowired
	public FormationBusinessJPA(FormationRepository repos) {
		this.repos = repos;
	}
	
	@Override
	public Formation creerFormation(Formation newformation) {
		newformation.setDebutAccreditation(new Date());
		return repos.save(newformation);
	}

	@Override
	public List<Formation> rechercherParNom(String nom) {
		return repos.findByNomFormation(nom);
	}

	@Override
	public List<Formation> recupererToutesLesFormation() {
		return (List<Formation>) repos.findAll();
	}

	@Override
	public List<Formation> rechercherParCode(String code) {
			return (List<Formation>) repos.findByCodeFormation(code);
	}

	@Override
	public void supprimerFormation(String code) {
		repos.delete(code);
	}

}

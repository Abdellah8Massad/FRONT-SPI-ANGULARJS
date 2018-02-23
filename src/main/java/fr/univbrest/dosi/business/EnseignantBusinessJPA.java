package fr.univbrest.dosi.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.repositories.EnseignantRepository;

@Component
public class EnseignantBusinessJPA implements EnseignantBusiness{

	EnseignantRepository repos;
	
	@Autowired
	public EnseignantBusinessJPA(EnseignantRepository repos) {
		this.repos=repos;
	}
	
	
	@Override
	public Enseignant creerEnseignant(Enseignant enseignant) {
		return repos.save(enseignant);
	}

	@Override
	public void supprimerEnseignant(long no) {
		repos.delete(no);
	}

	@Override
	public List<Enseignant> chercherToutesEnseignant() {
		return (List<Enseignant>) repos.findAll();
	}

	@Override
	public List<Enseignant> chercherParNom(String nom) {
		return repos.findByNom(nom);
	}

	@Override
	public Enseignant chercherParEmail(String mail) {
		return repos.findByemailPerso(mail);
	}


	@Override
	public void supprimer(Enseignant enseignant) {
		repos.delete(enseignant);
	}

}

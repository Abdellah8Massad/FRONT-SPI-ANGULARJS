package fr.univbrest.dosi.business;

import java.util.List;

import fr.univbrest.dosi.bean.Enseignant;

public interface EnseignantBusiness {

	public Enseignant creerEnseignant(Enseignant enseignant);
	public void supprimerEnseignant(long no);
	public void supprimer(Enseignant enseignant);
	public List<Enseignant> chercherToutesEnseignant();
	public List<Enseignant> chercherParNom(String nom);
	public Enseignant chercherParEmail(String mail);
	
}

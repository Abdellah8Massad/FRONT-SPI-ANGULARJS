package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Enseignant;
import fr.univbrest.dosi.repositories.EnseignantRepository;

public class EnseignantBusinessJPATest {
	
	EnseignantBusinessJPA business;
	
	@Test
	public void doitcreerEnseignant() {
		EnseignantRepository repos = new EnseignantRepositoryList();
		business = new EnseignantBusinessJPA(repos);
		
		Enseignant enseignant1 = new Enseignant((long) 2,"AP","20150","mail@mail","Ali","Patrice");
		Enseignant resultat = business.creerEnseignant(enseignant1);
		
		assertThat(resultat.getNom()).isEqualTo("Ali");
		assertThat(repos.count()).isEqualTo(1);
		
	}
	
	@Test
	public void doitrechercherParNom() {
		Enseignant enseignant1 = new Enseignant((long) 2,"AP","20150","mail@mail","Ali","Patrice");
		Enseignant enseignant2 = new Enseignant((long) 3,"OC","20150","toto@toto","Ali","toto");
		
		EnseignantRepository repos = new EnseignantRepositoryList();
		repos.save(enseignant1);
		repos.save(enseignant2);
		
		business = new EnseignantBusinessJPA(repos);
		List<Enseignant> resultat = business.chercherParNom("Ali");
		assertThat(resultat.size()).isEqualTo(2);
		
	}
	
	@Test
	public void doitrechercherParmMail() {
		Enseignant enseignant1 = new Enseignant((long) 2,"AP","20150","mail@mail","Ali","Patrice");
		Enseignant enseignant2 = new Enseignant((long) 3,"OC","20150","toto@toto","Ali","toto");
		
		EnseignantRepository repos = new EnseignantRepositoryList();
		repos.save(enseignant1);
		repos.save(enseignant2);
		business = new EnseignantBusinessJPA(repos);
		Enseignant resultat = business.chercherParEmail("toto@toto");
		assertThat(resultat.getPrenom()).isEqualTo("toto");		
	}
	
	@Test
	public void doitsupprimer() {
		Enseignant enseignant1 = new Enseignant((long) 2,"AP","20150","mail@mail","Ali","Patrice");
		Enseignant enseignant2 = new Enseignant((long) 3,"OC","20150","toto@toto","Ali","toto");
		
		EnseignantRepository repos = new EnseignantRepositoryList();
		repos.save(enseignant1);
		repos.save(enseignant2);
		business = new EnseignantBusinessJPA(repos);
		business.supprimer(enseignant1);
		
		assertThat(repos.count()).isEqualTo(1);
	}
	
	
	public class EnseignantRepositoryList implements EnseignantRepository{

		private List<Enseignant> data;
		
		public EnseignantRepositoryList() {
			data = Lists.newArrayList();
		}
		

		@Override
		public <S extends Enseignant> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Enseignant> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enseignant findOne(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean exists(Long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Enseignant> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Enseignant> findAll(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			return data.size();
		}

		@Override
		public void delete(Long id) {
			Enseignant en=this.findOne(id);
			data.remove(en);
		}

		@Override
		public void delete(Enseignant entity) {
			data.remove(entity);
		}

		@Override
		public void delete(Iterable<? extends Enseignant> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Enseignant> findByNom(String nom) {
			return data.stream()
					.filter(ens -> ens.getNom().equals(nom))
					.collect(Collectors.toList());
		}

		@Override
		public Enseignant findByemailPerso(String mail) {
			return data.stream().filter(En -> En.getEmailPerso().equals(mail))
					.collect(Collectors.toList()).get(0);
		}
		
	}

}

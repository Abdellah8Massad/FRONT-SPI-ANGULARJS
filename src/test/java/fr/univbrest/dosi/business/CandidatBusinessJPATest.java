package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.business.CandidatBusinessJPA;
import fr.univbrest.dosi.repositories.CandidatRepository;

public class CandidatBusinessJPATest {

	CandidatBusinessJPA business;
	
	@Test
	public void doitcreerDesCandidats() {
		CandidatRepository repos = new CandidatRepositoryList();
		business = new CandidatBusinessJPA(repos);
		
		Candidat candidat1 = new Candidat("candidat10", "adresseeee", "25200", "oui", new Date(), new Date()
				, "Maroc", "a", "065565656","maroc", "ALLAMI", "Maroc", "SAID",
				BigDecimal.valueOf(2.0), "homme", "57454654", "FST", "MARRAKECH");
		
		Candidat resultat = business.creerCandidat(candidat1);
		assertThat(resultat.getNom()).isEqualTo("ALLAMI");
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitChercherParNomTest(){
		
		CandidatRepository repos = new CandidatRepositoryList();
		Candidat candidat1 = new Candidat("candidat10", "adresseeee", "25200", "oui", new Date(), new Date()
				, "Maroc", "a", "065565656","maroc", "ALLAMI", "Maroc", "SAID",
				BigDecimal.valueOf(2.0), "homme", "57454654", "FST", "MARRAKECH");
		Candidat candidat2 = new Candidat("candidat2", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "SADIQ", "Maroc", "bader",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UCA", "MARRAKECH");
		Candidat candidat3 = new Candidat("candidat3", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "AZZAM", "Maroc", "amal",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UBO", "MARRAKECH");
		
		repos.save(candidat1);
		repos.save(candidat2);
		repos.save(candidat3);
		
		business = new CandidatBusinessJPA(repos);
		
		List<Candidat> resultat= business.rechercherParNom("ALLAMI");
		assertThat(resultat.size()).isEqualTo(1);
	}
	
	@Test
	public void doitChercherParUnivTest(){
		CandidatRepository repos = new CandidatRepositoryList();
		business = new CandidatBusinessJPA(repos);
		
		Candidat candidat1 = new Candidat("candidat10", "adresseeee", "25200", "oui", new Date(), new Date()
				, "Maroc", "a", "065565656","maroc", "ALLAMI", "Maroc", "SAID",
				BigDecimal.valueOf(2.0), "homme", "57454654", "FST", "MARRAKECH");
		Candidat candidat2 = new Candidat("candidat2", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "SADIQ", "Maroc", "bader",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UCA", "MARRAKECH");
		Candidat candidat3 = new Candidat("candidat3", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "AZZAM", "Maroc", "amal",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UBO", "MARRAKECH");
		
		repos.save(candidat1);
		repos.save(candidat2);
		repos.save(candidat3);
		
		List<Candidat> resultat= business.rechercherParUniv("UBO");
		assertThat(resultat.size()).isEqualTo(1);
	}
	
	@Test
	public void doitSupprimerTest(){
		CandidatRepository repos = new CandidatRepositoryList();
		business = new CandidatBusinessJPA(repos);
		
		Candidat candidat1 = new Candidat("candidat10", "adresseeee", "25200", "oui", new Date(), new Date()
				, "Maroc", "a", "065565656","maroc", "ALLAMI", "Maroc", "SAID",
				BigDecimal.valueOf(2.0), "homme", "57454654", "FST", "MARRAKECH");
		Candidat candidat2 = new Candidat("candidat2", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "SADIQ", "Maroc", "bader",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UCA", "MARRAKECH");
		Candidat candidat3 = new Candidat("candidat3", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "AZZAM", "Maroc", "amal",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UBO", "MARRAKECH");
		
		repos.save(candidat1);
		repos.save(candidat2);
		repos.save(candidat3);
		
		business.supprimerCandidat("candidat2");
		assertThat(repos.count()).isEqualTo(2);
	}
	
	
	
	
	
	
	
	class CandidatRepositoryList implements CandidatRepository{

		private List<Candidat> data;
		
		public CandidatRepositoryList() {
			data = Lists.newArrayList();
		}
		
		
		@Override
		public <S extends Candidat> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Candidat> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Candidat> findAll() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public long count() {
			return data.size();
		}


		@Override
		public void delete(Candidat entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Iterable<? extends Candidat> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Candidat> findByNom(String nom) {
			List<Candidat> liste = Lists.newArrayList();
			
			for (Candidat i : data) { 
				if(i.getNom()==nom) {
			    liste.add((Candidat) i);
				}
			}
			
			return liste;
		}

		@Override
		public List<Candidat> findByUniversiteOrigine(String universiteOrigine) {
			List<Candidat> liste = Lists.newArrayList();
			
			for (Candidat i : data) { 
				if(i.getUniversiteOrigine()==universiteOrigine) {
			    liste.add((Candidat) i);
				}
			}
			
			return liste;
		}


		@Override
		public Candidat findOne(String id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public boolean exists(String id) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public Iterable<Candidat> findAll(Iterable<String> ids) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public void delete(String id) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

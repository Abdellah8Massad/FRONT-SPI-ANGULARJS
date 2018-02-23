package fr.univbrest.dosi.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.common.collect.Lists;

import fr.univbrest.dosi.bean.Formation;
import fr.univbrest.dosi.business.FormationBusinessJPA;
import fr.univbrest.dosi.repositories.FormationRepository;

public class FormationBusinessJPATest {

	FormationBusinessJPA business;
	
	@Test
	public void doitCreerUneFormation() {
		FormationRepository repos = new FormationRepositoryList();
		business = new FormationBusinessJPA(repos);
		Formation formationN = new Formation("33","M2","ISI",BigDecimal.valueOf(2.0),"DOSI");
		
		Formation resultat = business.creerFormation(formationN);
		
		assertThat(resultat.getDebutAccreditation()).isCloseTo(new Date(),500);
		assertThat(repos.count()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherParNomFormation() {
			
		Formation formation3 = new Formation("33","L1","ISI",BigDecimal.valueOf(2.0),"DOSI");
		Formation formation1 = new Formation("31","L2","ISI",BigDecimal.valueOf(2.0),"ISI");
		Formation formation2 = new Formation("32","M2","ISI",BigDecimal.valueOf(2.0),"LPI");
		
		FormationRepository repos = new FormationRepositoryList(formation3,formation1,formation2);
		business = new FormationBusinessJPA(repos);
		List<Formation> resultat = business.rechercherParNom("DOSI");
		
		assertThat(resultat.size()).isEqualTo(1);
	}
	
	@Test
	public void doitRechercherParCodeFormation() {
			
		Formation formation3 = new Formation("33","L1","ISI",BigDecimal.valueOf(2.0),"DOSI");
		Formation formation1 = new Formation("31","L2","ISI",BigDecimal.valueOf(2.0),"ISI");
		Formation formation2 = new Formation("32","M2","ISI",BigDecimal.valueOf(2.0),"LPI");
		
		FormationRepository repos = new FormationRepositoryList(formation3,formation1,formation2);
		business = new FormationBusinessJPA(repos);
		Formation resultat = (Formation) business.rechercherParCode("33");
		
		assertThat(resultat.getNomFormation()).isEqualTo("DOSI");
	}
	
	class FormationRepositoryList implements FormationRepository{
		
		private List<Formation> data;
		public FormationRepositoryList() {
			data = Lists.newArrayList();
		}
		
		public FormationRepositoryList(Formation...formations) {
			data=Lists.newArrayList(formations);
		}

		@Override
		public <S extends Formation> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Formation> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public Iterable<Formation> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			return data.size();
		}


		@Override
		public void delete(Formation entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Iterable<? extends Formation> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Formation> findByNomFormation(String nomFormation) {
			return data.stream()
					.filter(formation -> formation.getNomFormation().equalsIgnoreCase(nomFormation))
					.collect(Collectors.toList());
		}

		@Override
		public List<Formation> findByCodeFormation(String code) {
			return (List<Formation>) data.stream()
					.filter(formation -> formation.getCodeFormation().equalsIgnoreCase(code))
					.collect(Collectors.toList()).get(0);
		}

		@Override
		public Formation findOne(String id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean exists(String id) {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public void delete(String id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Iterable<Formation> findAll(Iterable<String> ids) {
			// TODO Auto-generated method stub
			return null;
		}

	

		
	}
}

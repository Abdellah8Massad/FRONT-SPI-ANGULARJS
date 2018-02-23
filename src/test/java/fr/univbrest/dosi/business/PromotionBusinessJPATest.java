package fr.univbrest.dosi.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;


import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;
import fr.univbrest.dosi.repositories.PromotionRepository;

public class PromotionBusinessJPATest {
	
		PromotionBusiness business;
		
		@Test
		public void doitcreerPromotion() {
			PromotionRepository repos = new PromotionRepositoryList();
			business = new PromotionBusinessJPA(repos);
			
			PromotionPK id = new PromotionPK("2015-2016","DOSI");
			Promotion promo1 = new Promotion(id,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			
			Promotion resultat = business.creerPromotion(promo1);
			assertThat(resultat.getLieuRentree()).isEqualTo("lieu a");
			assertThat(repos.count()).isEqualTo(1);

		}
		
		@Test 
		public void rechercherParEnseignement() {
			
			PromotionPK id = new PromotionPK("2015-2016","DOSI");
			Promotion promo1 = new Promotion(id,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			PromotionPK id2 = new PromotionPK("2015-2017","ISI");
			Promotion promo2 = new Promotion(id2,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			
			PromotionRepository repos = new PromotionRepositoryList(promo1,promo2);
			business = new PromotionBusinessJPA(repos);
			
			List<Promotion> resultat = business.rechercherParNoEnseignantPromotion(BigDecimal.valueOf(2.0));
			assertThat(resultat.size()).isEqualTo(2);
		}
		
		@Test
		public void rchercherParId() {
			
			PromotionPK id = new PromotionPK("2015-2016","DOSI");
			Promotion promo1 = new Promotion(id,"commentaire",new Date(),new Date(),new Date(),"lieu b",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			PromotionPK id2 = new PromotionPK("2015-2017","ISI");
			Promotion promo2 = new Promotion(id2,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			
			PromotionRepository repos = new PromotionRepositoryList(promo1,promo2);
			business = new PromotionBusinessJPA(repos);
			
			Promotion resultat = business.rechercherIdPromotion("2015-2016", "DOSI");
			assertThat(resultat.getLieuRentree()).isEqualTo("lieu b");
		}
		
		@Test
		public void doitsupprimerPromotion() {
			PromotionPK id = new PromotionPK("2015-2016","DOSI");
			Promotion promo1 = new Promotion(id,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			PromotionPK id2 = new PromotionPK("2015-2017","ISI");
			Promotion promo2 = new Promotion(id2,"commentaire",new Date(),new Date(),new Date(),"lieu a",BigDecimal.valueOf(2.0)
					,BigDecimal.valueOf(2.0),"aaaaa","bbbb");
			
			PromotionRepository repos = new PromotionRepositoryList(promo1,promo2);
			business = new PromotionBusinessJPA(repos);
			
			business.supprimerPromotion("2015-2016","DOSI");
			
			assertThat(repos.count()).isEqualTo(1);
		}
		
		
	class PromotionRepositoryList implements PromotionRepository{

		private List<Promotion> data;
		
		public PromotionRepositoryList() {
			data = Lists.newArrayList();
		}
		
		public PromotionRepositoryList(Promotion... promotions) {
			data=Lists.newArrayList(promotions);
		}
		
		
		@Override
		public <S extends Promotion> S save(S entity) {
			data.add(entity);
			return entity;
		}

		@Override
		public <S extends Promotion> Iterable<S> save(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Promotion findOne(PromotionPK id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean exists(PromotionPK id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Promotion> findAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterable<Promotion> findAll(Iterable<PromotionPK> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			return data.size();
		}

		@Override
		public void delete(PromotionPK id) {
			Promotion p = this.findById(id);
			data.remove(p);
		}

		@Override
		public void delete(Promotion entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(Iterable<? extends Promotion> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Promotion findById(PromotionPK id) {
			return data.stream().filter(p -> p.getId().equals(id))
					.collect(Collectors.toList()).get(0);
		}

		@Override
		public List<Promotion> findBynoEnseignant(BigDecimal nE) {
			return data.stream()
					.filter(promotion -> promotion.getNoEnseignant().equals(nE))
					.collect(Collectors.toList());
		}
	
	}
	
	
}

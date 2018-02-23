package fr.univbrest.dosi.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.bean.Promotion;
import fr.univbrest.dosi.bean.PromotionPK;

public interface PromotionRepository extends CrudRepository<Promotion, PromotionPK>{

	Promotion findById(PromotionPK id);
	List<Promotion> findBynoEnseignant(BigDecimal nE);
	
}

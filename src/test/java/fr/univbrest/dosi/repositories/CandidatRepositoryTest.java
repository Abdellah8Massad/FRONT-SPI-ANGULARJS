package fr.univbrest.dosi.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univbrest.dosi.AppTestConfig;
import fr.univbrest.dosi.bean.Candidat;
import fr.univbrest.dosi.bean.Promotion;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTestConfig.class)
public class CandidatRepositoryTest {
	
	@Autowired(required=true)
	CandidatRepository CandidatRepo;
	
	@Before
	public void setup() {
		Candidat candidat1 = new Candidat("candidat1", "adresseeee", "25200", "oui", new Date(), new Date()
				, "Maroc", "a", "065565656","maroc", "massad", "Maroc", "abdellah",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UCA", "MARRAKECH");
		Candidat candidat2 = new Candidat("candidat2", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "SADIQ", "Maroc", "bader",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UCA", "MARRAKECH");
		Candidat candidat3 = new Candidat("candidat3", "adresseeee", "25200", "oui", new Date(), new Date(), 
				 "Maroc", "a", "065565656","maroc", "AZZAM", "Maroc", "amal",
				BigDecimal.valueOf(2.0), "homme", "57454654", "UBO", "MARRAKECH");
		
		CandidatRepo.save(candidat1);
		CandidatRepo.save(candidat2);
	}
	
	@Test
	public void doitCompterLeNom() {
		
		List<Candidat> resultat=CandidatRepo.findByNom("massad");
		assertThat(resultat).hasSize(1);
		assertThat(resultat.get(0).getNom()).isEqualTo("massad");
	}
	
	@Test
	public void doitCompterUniv() {
		
		List<Candidat> resultat=CandidatRepo.findByUniversiteOrigine("UCA");
		assertThat(resultat).hasSize(2);
		assertThat(resultat.get(0).getNom()).isEqualTo("massad");
	}
	
}



package fr.univbrest.dosi.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.univbrest.dosi.bean.*;

public interface FormationRepository extends CrudRepository<Formation, String>{
			
	List<Formation> findByNomFormation(String nomFormation);
	List<Formation> findByCodeFormation(String code);
	
}

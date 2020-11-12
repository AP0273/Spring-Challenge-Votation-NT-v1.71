package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nt.votationt.model.Vote;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    
	  // Vote findById(Long Id);
	 Vote findByCpfPerson(Long CpfPerson);
	 List <Vote> findByIdAndAprovation(Long Id,boolean aprovation) ;

}
	


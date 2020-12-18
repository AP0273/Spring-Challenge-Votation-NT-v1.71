package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.votationt.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	// Vote findById(Long Id);
	@Query("FROM Vote WHERE cpf_person = ?1")
	Vote findByCpf_person(Long cpf_person);

	List<Vote> findByIdAndAprovation(Long Id, boolean aprovation);

}

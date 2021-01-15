package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.votationt.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	@Query("FROM Vote WHERE id = ?1")
	Vote FindById(Long id);

	List<Vote> findByCpfPerson(String cpfPerson);

	List<Vote> findByIdscheduleAndAprovation(Long idSchedule, boolean Aprovation);

}

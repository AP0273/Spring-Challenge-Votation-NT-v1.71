package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("FROM Schedule WHERE id = ?1")
	Schedule FindById(Long id);

	Schedule findByNameIgnoreCase(String Name);

	List<Schedule> findByCategoryIgnoreCase(String Category);

	@Query("FROM Vote WHERE idSchedule = ?1 AND aprovation = ?2")
	List<Vote> findByIdScheduleAndAprovation(Long idSchedule, boolean aprovation);

	@Query("FROM Vote WHERE cpfPerson = ?1 AND idSchedule = ?2")
	Vote findByCpfPersonAndIdSchedule(Long cpfPerson, Long idSchedule);
}

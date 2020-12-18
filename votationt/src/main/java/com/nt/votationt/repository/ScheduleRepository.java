package com.nt.votationt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nt.votationt.model.Schedule;
import com.nt.votationt.model.Vote;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("FROM Schedule WHERE id_schedule = ?1")
	Schedule FindScheduleID(Long id_schedule);

	Schedule findByNameIgnoreCase(String Name);

	Schedule findByCategory(String Category);

	@Query("FROM Vote WHERE id_schedule = ?1 AND aprovation = ?2")
	List<Vote> findById_scheduleAndAprovation(Long id_schedule, boolean aprovation);

	@Query("FROM Vote WHERE cpf_person = ?1 AND id_schedule = ?2")
	Vote findByCpf_personAndId_schedule(Long cpf_person, Long id_schedule);
}

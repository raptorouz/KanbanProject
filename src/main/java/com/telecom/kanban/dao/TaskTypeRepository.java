package com.telecom.kanban.dao;

import com.telecom.kanban.domain.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}

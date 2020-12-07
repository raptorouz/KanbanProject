package com.telecom.kanban.dao;

import com.telecom.kanban.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

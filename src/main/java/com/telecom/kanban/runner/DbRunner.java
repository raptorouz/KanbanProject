package com.telecom.kanban.runner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.dao.TaskStatusRepository;
import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DbRunner implements CommandLineRunner {
	@Autowired
	private DeveloperRepository developerRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskStatusRepository taskStatusRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		log.info("Launching Runner");

		Developer firstDev = new Developer("Eric", "Yammine", "eric.yammine@telecom-st-etienne.fr", "testingpass",
				LocalDate.of(2012, 6, 30));
		developerRepository.save(firstDev);
		Developer secondDev = new Developer("Georges", "Mitchell", "gmitch@gmail.com", "MyPass123",
				LocalDate.of(2020, 8, 20));
		developerRepository.save(secondDev);

		developerRepository.findAll().forEach((developer) -> {
			log.info("Created {}", developer);
		});

		Task firstTask = new Task();
		firstTask.setCreated(LocalDate.now());
		firstTask.setTitle("firstTask");
		firstTask.addDeveloper(firstDev);
		taskRepository.save(firstTask);
		log.info("Created {}", firstTask);

		TaskStatus todo = new TaskStatus(TaskStatus.TODO_ID, TaskStatus.TODO_LABEL);
		taskStatusRepository.save(todo);

		TaskStatus doing = new TaskStatus(TaskStatus.DOING_ID, TaskStatus.DOING_LABEL);
		taskStatusRepository.save(doing);

		TaskStatus test = new TaskStatus(TaskStatus.TEST_ID, TaskStatus.TEST_LABEL);
		taskStatusRepository.save(test);

		TaskStatus done = new TaskStatus(TaskStatus.DONE_ID, TaskStatus.DONE_LABEL);
		taskStatusRepository.save(done);

		taskStatusRepository.findAll().forEach((status) -> {
			log.info("Created {}", status);
		});

		log.info("Application intiialized with some data");
	}

}

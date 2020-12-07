package com.telecom.kanban;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.domain.Task;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void testFindAll() {
		log.info("run test find all tasks");

		List<Task> tasksList = taskRepository.findAll();
		int numberOfTasksInRepo = tasksList.size();

		// We assert that it's >0 because there are already 1 task in the DB
		Assert.assertTrue(numberOfTasksInRepo > 0);
	}

	public void saveTask() {
		log.info("run test save task");

		Task testTask = new Task();
		testTask.setTitle("testTask");
		taskRepository.save(testTask);

		List<Task> tasksList = taskRepository.findAll();
		int numberOfTasksInRepo = tasksList.size();
		// We assert that it's >1 after save because there is already 1 task in the DB
		Assert.assertTrue(numberOfTasksInRepo > 1);
	}
}

package com.telecom.kanban;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.domain.Task;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TaskTest {

	@Test
	public void testAddDeveloper() {
		log.info("run test task add dev");
		Task taskToTest = new Task();
		Developer developerToAdd = new Developer();

		taskToTest.addDeveloper(developerToAdd);
		int numberOfDevelopersInTask = taskToTest.getDevelopers().size();
		Assert.assertEquals(1, numberOfDevelopersInTask);
	}
}

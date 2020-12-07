package com.telecom.kanban;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;
import com.telecom.kanban.service.DeveloperService;
import com.telecom.kanban.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;
	@Autowired
	private DeveloperService developerService;

	@Test
	public void testFindAllTasks() {
		log.info("run test find all tasks");

		List<Task> tasksList = taskService.findAllTasks();
		int numberOfTasksInRepo = tasksList.size();

		// We assert that it's >0 because there are already 1 task in the DB
		Assert.assertTrue(numberOfTasksInRepo > 0);
	}

	@Test
	public void testFindTaskWithId() {
		log.info("run test find task by id");
		Task testTask = taskService.findTask(3L);

		Assert.assertEquals("firstTask", testTask.getTitle());
	}

	@Test
	public void testMoveRight() {
		log.info("run test move right task");
		// get the existing task statuses, to use them during the tests
		TaskStatus doneStatus = this.taskService.findTaskStatus(TaskStatus.DONE_ID);
		TaskStatus testStatus = this.taskService.findTaskStatus(TaskStatus.TEST_ID);
		TaskStatus doingStatus = this.taskService.findTaskStatus(TaskStatus.DOING_ID);
		TaskStatus todoStatus = taskService.findTaskStatus(TaskStatus.TODO_ID);

		// Generating the task for the tests
		Task testTask = new Task();
		Developer dev = developerService.findAllDevelopers().get(0);
		testTask.addDeveloper(dev);
		testTask.setTitle("MoveTaskTest");
		taskService.generateTask(testTask);

		// At this point, the task has the "todo" status , let's check and move it on
		// the right

		taskService.moveRightTask(testTask);
		// The status should now be : Doing
		assertEquals(doingStatus, testTask.getStatus());

		taskService.moveRightTask(testTask);
		assertEquals(testStatus, testTask.getStatus());

		taskService.moveRightTask(testTask);
		assertEquals(doneStatus, testTask.getStatus());

		// At this point we're at the last possible status, if we try to move right it
		// should do nothing,
		// So it should let us at the done status
		taskService.moveRightTask(testTask);
		assertEquals(doneStatus, testTask.getStatus());

		// At this point we've made 4 attempts to move to the right, so the Changelogs
		// size should be 4
		assertEquals(4, testTask.getChangeLogs().size());
	}

	@Test
	public void testMoveLeft() {
		log.info("run test move left task");
		// get the existing task statuses, to use them during the tests
		TaskStatus doneStatus = this.taskService.findTaskStatus(TaskStatus.DONE_ID);
		TaskStatus testStatus = this.taskService.findTaskStatus(TaskStatus.TEST_ID);
		TaskStatus doingStatus = this.taskService.findTaskStatus(TaskStatus.DOING_ID);
		TaskStatus todoStatus = taskService.findTaskStatus(TaskStatus.TODO_ID);

		// Generating the task for the tests
		Task testTask = new Task();
		Developer dev = developerService.findAllDevelopers().get(0);
		testTask.addDeveloper(dev);
		testTask.setTitle("MoveTaskTest");
		// generates the task at the done status
		taskService.generateTask(testTask, doneStatus);

		// At this point, the task has the "done" status , let's move it on the left
		taskService.moveLeftTask(testTask);
		// The status should now be : Test
		assertEquals(testStatus, testTask.getStatus());

		taskService.moveLeftTask(testTask);
		assertEquals(doingStatus, testTask.getStatus());

		taskService.moveLeftTask(testTask);
		assertEquals(todoStatus, testTask.getStatus());

		// At this point we're at the first possible status, if we try to move left it
		// should do nothing,
		// So it should let us at the todo status
		taskService.moveLeftTask(testTask);
		assertEquals(todoStatus, testTask.getStatus());

		// At this point we've made 4 attempts to move to the left, so the Changelogs
		// size should be 4
		assertEquals(4, testTask.getChangeLogs().size());
	}

	@Test
	public void testMoveLeftAndRight() {
		// Test to check if moving left and right "at the same time" works

		// get the existing task statuses, to use them during the tests
		TaskStatus doingStatus = this.taskService.findTaskStatus(TaskStatus.DOING_ID);
		TaskStatus todoStatus = taskService.findTaskStatus(TaskStatus.TODO_ID);

		// Generating the task for the tests
		Task testTask = new Task();
		Developer dev = developerService.findAllDevelopers().get(0);
		testTask.addDeveloper(dev);
		testTask.setTitle("MoveTaskTest");
		// generates the task at the "todo" status
		taskService.generateTask(testTask);

		// At this point, the task has the "todo" status , let's check and move it on
		// the right
		taskService.moveRightTask(testTask);
		// The status should now be : Doing
		assertEquals(doingStatus, testTask.getStatus());

		// Move it back to the left
		taskService.moveLeftTask(testTask);
		assertEquals(todoStatus, testTask.getStatus());
	}
}

package com.telecom.kanban.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.kanban.dao.TaskRepository;
import com.telecom.kanban.dao.TaskStatusRepository;
import com.telecom.kanban.domain.ChangeLog;
import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;
import com.telecom.kanban.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskStatusRepository taskStatusRepository;

	@Override
	public List<Task> findAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

	@Override
	public Task findTask(Long id) {

		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {
			return task.get();
		}
		return null;

	}

	@Override
	public Task moveRightTask(Task task) {
		return move(task, "right");

	}

	@Override
	public Task moveLeftTask(Task task) {
		return move(task, "left");

	}

	private Task move(Task task, String direction) {
		TaskStatus actualStatus = task.getStatus();
		TaskStatus nextStatus = getFutureStatus(actualStatus, direction);
		task.setStatus(nextStatus);
		generateChangeLogs(task, actualStatus, nextStatus);
		taskRepository.save(task);
		return task;
	}

	private TaskStatus getFutureStatus(TaskStatus actualStatus, String direction) {
		TaskStatus futureStatus = null;

		if (actualStatus != null) {
			// calculate Id of next TaskStatus based on direction (add 1 if right, -1 if
			// Left
			Long futureStatusId = actualStatus.getId() + (direction.equals("right") ? 1L : -1L);

			// Check if the future ID is in range : if it exists in the TaskStatus defined
			if (futureStatusId >= TaskStatus.TODO_ID && futureStatusId <= TaskStatus.DONE_ID) {
				// affecting the future status based on this computed id
				futureStatus = findTaskStatus(futureStatusId);
			}
			// else the futureStatus is the same as before because you can't go out of the
			// limits
			else {
				futureStatus = actualStatus;
			}
		}
		return futureStatus;
	}

	public TaskStatus findTaskStatus(Long id) {

		return this.taskStatusRepository.findById(id).orElse(null);
	}

	private void generateChangeLogs(Task task, TaskStatus oldStatus, TaskStatus nextStatus) {
		ChangeLog changeLog = new ChangeLog();
		changeLog.setSourceStatus(oldStatus);
		changeLog.setTargetStatus(nextStatus);
		changeLog.setOccured(LocalDateTime.now());
		task.addChangeLog(changeLog);

	}

	@Override
	public Task generateTask(Task task) {
		TaskStatus todo = findTaskStatus(TaskStatus.TODO_ID);
		return generateTask(task, todo);
	}

	@Override
	public Task generateTask(Task task, TaskStatus status) {
		task.setCreated(LocalDate.now());
		task.setStatus(status);
		return taskRepository.save(task);
	}

}
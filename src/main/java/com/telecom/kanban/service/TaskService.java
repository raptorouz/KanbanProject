package com.telecom.kanban.service;

import java.util.List;

import com.telecom.kanban.domain.Task;
import com.telecom.kanban.domain.TaskStatus;

public interface TaskService {
	public List<Task> findAllTasks();
	public Task findTask(Long id);
	public Task moveRightTask(Task task);
	public Task moveLeftTask(Task task);
	public TaskStatus findTaskStatus(Long todoId);
	//Create a task with the default Status (todo)
	public Task generateTask(Task task);
	// Create a task with a specified TaskStatus
	public Task generateTask(Task task, TaskStatus status);
}

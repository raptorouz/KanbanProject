package com.telecom.kanban.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskStatus {

	final public static Long TODO_ID = 10664L;

	final public static Long DOING_ID = 10665L;

	final public static Long TEST_ID = 10666L;

	final public static Long DONE_ID = 10667L;

	final public static String TODO_LABEL = "TODO";

	final public static String DOING_LABEL = "DOING";

	final public static String TEST_LABEL = "TEST";

	final public static String DONE_LABEL = "DONE";

	private @Id Long id;

	private String label;

	public TaskStatus(Long id, String label) {
		this.id = id;
		this.label = label;
	}

	public TaskStatus() {
	}

}

package model;

import java.time.LocalDateTime;

public class Task {
	
	private String title;
	private String description;
	private int priority;
	private LocalDateTime deadLine;
	
	public Task(String title, String description, int priority, LocalDateTime deadLine) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.deadLine = deadLine;
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public int getPriority() { return priority; }
	public void setPriority(int priority) { this.priority = priority; }

	public LocalDateTime getDeadLine() { return deadLine; }
	public void setDeadLine(LocalDateTime deadLine) { this.deadLine = deadLine; }

	@Override
	public String toString() {
		return "Task [title=" + title + ", description=" + description + ", priority=" + priority + ", deadLine="
				+ deadLine + "]";
	}

}

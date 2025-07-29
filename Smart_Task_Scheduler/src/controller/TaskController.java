package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import model.Task;

public class TaskController {
	
	private final PriorityQueue<Task> taskQueue;
	
	public TaskController() {
		this.taskQueue = new PriorityQueue<>();
	}
	
	//add
	public void addTask(Task task) {
		taskQueue.offer(task);
	}
	
	//delete
	public boolean deleteTask(String title) {
		return taskQueue.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
	}
	
	//edit
	public boolean editTask(String title, Task updatedTask) {
		List<Task> tempList = new ArrayList<>(taskQueue);
		boolean found = false;
		
		for(int i = 0; i < tempList.size(); i++) {
			if(tempList.get(i).getTitle().equalsIgnoreCase(title)) {
				tempList.set(i, updatedTask);
				found = true;
				break;
			}
		}
		if(found) {
			tempList.clear();
			taskQueue.addAll(tempList);
		}
		return found;
	}
	
	//get all
	public List<Task> getAllTasks(){
		return taskQueue.stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
	//get today tasks
	public List<Task> getTodayTasks(){
		return taskQueue.stream()
				.filter(task -> task.getDeadLine().toLocalDate().equals(LocalDateTime.now().toLocalDate()))
				.sorted()
				.collect(Collectors.toList());
	}
	
	//get high priority
	public List<Task> getHighPriorityTasks(){
		return taskQueue.stream()
				.filter(task -> task.getPriority() == 1)
				.sorted()
				.collect(Collectors.toList());
	}
	
	//get upcoming task
	public List<Task> getUpcomingTask(int minutesAhead){
		LocalDateTime now = LocalDateTime.now();
		return taskQueue.stream()
				.filter(task -> {
					long minutes = java.time.Duration.between(now, task.getDeadLine()).toMinutes();
					return minutes >= 0 && minutes <= minutesAhead;
				})
				.sorted()
				.collect(Collectors.toList());
	}
	
}

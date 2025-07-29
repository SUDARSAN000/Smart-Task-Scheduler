package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Task;

public class TaskStorage {
    private static final String FILE_PATH = "task.json";
    private static final Gson gson = new Gson();
    
    public static void saveTask(List<Task> task) {
    	try (FileWriter writer = new FileWriter(FILE_PATH)){
    		gson.toJson(task,writer);
    	}
    	catch(IOException e) {
    		System.out.println("Error saving task : "+e.getMessage());
    	}
    }
    
    public static List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(reader, taskListType);
        } catch (IOException e) {
            System.out.println("No existing task file found ! Starting fresh");
        }
        return new java.util.ArrayList<>();
    }
}
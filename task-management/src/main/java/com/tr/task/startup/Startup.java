package com.tr.task.startup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tr.task.entity.Task;
import com.tr.task.repository.TaskRepository;

@Component
public class Startup implements CommandLineRunner {

	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<Task>tasks=new ArrayList<>();
		
		for (int i=0;i<1_000;i++) {
			Task task=new Task();
			task.setName("görev-"+i);
			task.setDescription("görev aciklamasi"+i);
			tasks.add(task);
		}
		
		this.taskRepository.saveAll(tasks);
		
	}
}

package com.scalerproject.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.scalerproject.dto.AddTaskDTO;
import com.scalerproject.dto.UpdateTaskDTO;
import com.scalerproject.entity.TaskEntity;

@Service
public class TaskServiceImpl implements TaskService {
	
	private ArrayList<TaskEntity> taskList = new ArrayList<>();
	private Integer taskId = 1;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public TaskEntity addNewTask(AddTaskDTO taskBody) throws ParseException {
		
		TaskEntity task = new TaskEntity();
		
		task.setTaskId(taskId);
		task.setTitle(taskBody.getTitle());
		task.setDescription(taskBody.getDescription());
		task.setDeadline(format.parse(taskBody.getDeadline()));
		task.setCompleted(false);
		taskId++;
		
		taskList.add(task);
		
		return task;
		
	}
	
	
	public ArrayList<TaskEntity> getAllTasks(){
		
		return taskList;
	}
	
	
	public TaskEntity getTaskById(Integer taskId) {
		
		//TaskEntity task = taskList.stream().findFirst().filter(t -> t.getTaskId()==taskId).orElse(null);
		
		for(TaskEntity task : taskList)
			if(task.getTaskId() == taskId)
				return task;
				
		return null;
	}


	@Override
	public TaskEntity updateTaskById(Integer taskId, UpdateTaskDTO taskBody) throws ParseException {
		
		TaskEntity task = getTaskById(taskId);
		
		if(task == null)
			return null;
					
		if(taskBody.getTitle() != null)
			task.setTitle(taskBody.getTitle());
		
		if(taskBody.getDescription() != null)
			task.setDescription(taskBody.getDescription());
		
		if(taskBody.getDeadline() != null)
			task.setDeadline(format.parse(taskBody.getDeadline()));
		
		if(taskBody.getCompleted() != null)
			task.setCompleted(taskBody.getCompleted());
		
		return task;
	}


	
	@Override
	public TaskEntity deleteTaskById(Integer taskId) {
		
		TaskEntity returnTask = getTaskById(taskId);
		
		if(returnTask == null)
			return null;
	
		taskList = (ArrayList<TaskEntity>) taskList.stream().filter(task -> task.getTaskId()!= taskId).collect(Collectors.toList());
							
		return returnTask;
	}
}

package com.scalerproject.service;

import java.text.ParseException;
import java.util.ArrayList;

import com.scalerproject.dto.AddTaskDTO;
import com.scalerproject.dto.UpdateTaskDTO;
import com.scalerproject.entity.TaskEntity;

public interface TaskService {
	
	public TaskEntity addNewTask(AddTaskDTO taskBody) throws ParseException;
	public ArrayList<TaskEntity> getAllTasks();
	public TaskEntity getTaskById(Integer taskId);
	public TaskEntity updateTaskById(Integer taskId, UpdateTaskDTO taskBody) throws ParseException;
	public TaskEntity deleteTaskById(Integer taskId);
	
}

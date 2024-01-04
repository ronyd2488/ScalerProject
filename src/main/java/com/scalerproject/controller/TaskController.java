package com.scalerproject.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalerproject.dto.AddTaskDTO;
import com.scalerproject.dto.ErrorResponseDTO;
import com.scalerproject.dto.TaskResponseDTO;
import com.scalerproject.dto.UpdateTaskDTO;
import com.scalerproject.entity.TaskEntity;
import com.scalerproject.service.NoteService;
import com.scalerproject.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Autowired
	private NoteService service2;
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> errorHandler(Exception e){
		
		if(e instanceof ParseException)
			return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
		
	
		return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal server error"));
	}
	
	
	
	@PostMapping("")
	public ResponseEntity<TaskEntity> addNewTask(@RequestBody AddTaskDTO body) throws ParseException {
		
		TaskEntity task = this.service.addNewTask(body);
		return ResponseEntity.ok(task);
	}
	
	
	
	@GetMapping("")
	public ResponseEntity<ArrayList<TaskEntity>> getAllTasks(){
		
		ArrayList<TaskEntity> taskList = this.service.getAllTasks();
		return ResponseEntity.ok(taskList);	
	}
	
	
	
	@GetMapping("/{taskId}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer taskId){
		
		TaskEntity task = this.service.getTaskById(taskId);
		
		TaskResponseDTO taskResponse = new TaskResponseDTO(task, this.service2.getNotesForTask(taskId));
		
		if(task==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(taskResponse);
	}
	
	
	
	@PatchMapping("/{taskId}")
	public ResponseEntity<TaskEntity> updateTaskById(@PathVariable Integer taskId, @RequestBody UpdateTaskDTO taskBody) throws ParseException {
		
		TaskEntity task = this.service.updateTaskById(taskId, taskBody);
		
		if(task==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(task);
	}
	
	
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<String> deleteTaskById(@PathVariable Integer taskId) {
		
		TaskEntity task = this.service.deleteTaskById(taskId);
		
		if(task==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok("Task with id : " + taskId + " has been successfully deleted");

	}	
}

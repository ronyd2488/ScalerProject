package com.scalerproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scalerproject.dto.AddNoteDTO;
import com.scalerproject.dto.CreateNoteResponseDTO;
import com.scalerproject.entity.NoteEntity;
import com.scalerproject.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {
	
	@Autowired
	private NoteService service;
	
	
	
	@GetMapping("")
	public ResponseEntity<ArrayList<NoteEntity>> getNotesForTask(@PathVariable Integer taskId){
		
		ArrayList<NoteEntity> noteList = this.service.getNotesForTask(taskId);
		return ResponseEntity.ok(noteList);
	}
	
	
	@PostMapping("")
	public ResponseEntity<CreateNoteResponseDTO> addNewNote(@PathVariable Integer taskId, @RequestBody AddNoteDTO noteBody){
		
		NoteEntity note = this.service.addNewNote(taskId, noteBody);
		return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
	}
	
}

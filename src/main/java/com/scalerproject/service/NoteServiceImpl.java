package com.scalerproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalerproject.dto.AddNoteDTO;
import com.scalerproject.entity.NoteEntity;
import com.scalerproject.entity.TaskEntity;


@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private TaskService service;
	
	
	private HashMap<Integer, TaskNotesHolder> taskNotesHolder = new HashMap<>();

	
	class TaskNotesHolder{
		
		protected Integer noteId = 1;
		protected ArrayList<NoteEntity> noteList = new ArrayList<>();
	}
	

	
	public ArrayList<NoteEntity> getNotesForTask(Integer taskId){
		
		TaskEntity task = this.service.getTaskById(taskId);
		
		if(task==null)
			return null;
		
		if(taskNotesHolder.get(taskId) == null)
			taskNotesHolder.put(taskId, new TaskNotesHolder());
			
		return taskNotesHolder.get(taskId).noteList;
	}
	
	
	
	public NoteEntity addNewNote(Integer taskId, AddNoteDTO noteBody) {
		
		TaskEntity task = this.service.getTaskById(taskId);
		
		if(task==null)
			return null;
		
		if(taskNotesHolder.get(taskId) == null)
			taskNotesHolder.put(taskId, new TaskNotesHolder());
		
		TaskNotesHolder holder = taskNotesHolder.get(taskId);
		
		NoteEntity note = new NoteEntity();
        note.setNoteId(holder.noteId);
        note.setTitle(noteBody.getTitle());
        note.setBody(noteBody.getBody());
        holder.noteList.add(note);
        holder.noteId++;
        return note;
	}

}

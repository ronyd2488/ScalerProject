package com.scalerproject.service;

import java.util.ArrayList;

import com.scalerproject.dto.AddNoteDTO;
import com.scalerproject.entity.NoteEntity;

public interface NoteService {
	
	public ArrayList<NoteEntity> getNotesForTask(Integer taskId);
	public NoteEntity addNewNote(Integer taskId, AddNoteDTO noteBody);

}

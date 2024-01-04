package com.scalerproject.dto;

import java.util.ArrayList;

import com.scalerproject.entity.NoteEntity;
import com.scalerproject.entity.TaskEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
	
	private TaskEntity task;
	private ArrayList<NoteEntity> noteList;
}

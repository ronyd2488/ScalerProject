package com.scalerproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {
	
	private String title;
	private String description;
	private String deadline;
	private Boolean completed;

}

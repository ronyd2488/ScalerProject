package com.scalerproject.entity;

import java.util.Date;

import lombok.Data;

@Data
public class TaskEntity {
	
	private Integer taskId;
	private String title;
	private String description;
	private Date deadline;
	private Boolean completed;

}
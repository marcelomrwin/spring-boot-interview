package com.example.interview.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	private int statusCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;
}

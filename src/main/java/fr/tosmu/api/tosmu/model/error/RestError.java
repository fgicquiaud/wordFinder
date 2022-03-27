package fr.tosmu.api.tosmu.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestError {

	private String errorCode;
	private String message;
	private String details;
	
}

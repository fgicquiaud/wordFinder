package fr.tosmu.api.tosmu.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestError {
	private String errorCode;
	private String message;
	private String details;
}

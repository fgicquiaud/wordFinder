package fr.tosmu.api.tosmu.model.response;

import java.util.List;

import fr.tosmu.api.tosmu.model.error.RestError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractWordResponseModel {

	List<RestError> errors;
	
}

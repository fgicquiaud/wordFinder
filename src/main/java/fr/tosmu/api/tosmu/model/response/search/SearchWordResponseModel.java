package fr.tosmu.api.tosmu.model.response.search;

import java.util.List;

import fr.tosmu.api.tosmu.model.response.AbstractWordResponseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchWordResponseModel extends AbstractWordResponseModel {
	
	List<String> wordsFound;
}

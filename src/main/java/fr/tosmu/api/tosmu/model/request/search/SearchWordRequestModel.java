package fr.tosmu.api.tosmu.model.request.search;

import fr.tosmu.api.tosmu.model.request.AbstractWordRequestModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchWordRequestModel extends AbstractWordRequestModel {
	String lettres;
	String pattern;
	String lettersOut;
	int lengthWord;
}

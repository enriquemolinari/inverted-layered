package ar.cpfw.book.radio.model;

import java.util.List;
import java.util.Optional;

public interface Competitions {

	Optional<Competition> competitionBy(int id);
	
	List<Competition> competitionsForInscription()
			throws RadioException;
}

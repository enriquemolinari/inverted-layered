package ar.cpfw.book.radio.model;

import java.util.List;
import java.util.Optional;

public interface Competitions {

	Optional<Competition> competitionBy(int id);
	
//	void addInscription(String name, String lastName, String id,
//			String phone, String email, int idCompetition)
//			throws RadioException;

	List<Competition> competitionsForInscription()
			throws RadioException;
}

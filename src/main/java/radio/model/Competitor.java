package radio.model;

public interface Competitor {

	String name();

	String lastName();

	String phone();

	String personId();

	String email();
	
	default int actualPoints() {
	 return 0;
	}
}

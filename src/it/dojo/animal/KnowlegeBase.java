package it.dojo.animal;

public class KnowlegeBase {

	private String animal;

	boolean isLeaf;

	private KnowlegeBase left;

	private KnowlegeBase right;

	private String question;

	public KnowlegeBase(String animal) {
		this.animal = animal;
		isLeaf = true;
	}

	public KnowlegeBase(String question, KnowlegeBase left, KnowlegeBase right) {
		this.question = question;
		this.left = left;
		this.right = right;
		isLeaf = false;
	}

	public String getGuess() {
		if (isLeaf) return "e' un " + animal + "?";
		return question;
	}
	
//	public void setGuess(String guess)
//	{
//		this.question = guess;
//	}

	public boolean isLeaf()
	{
		return isLeaf;
	}
	
	

	public KnowlegeBase left() {
		return left;
	}

	public KnowlegeBase right() {
		return right;
	}

	public String getAnimal() {
		return animal;
	}

}

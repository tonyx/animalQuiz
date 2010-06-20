package it.dojo.animal;

public class Guesser {
	// String theAnimal;
	String firstGuess;

	private KnowlegeBase knowlegeBase;

	private KnowlegeBase currentBase;

	private boolean won = false;

	private String currentAnswer;
	private String learningAnswer;
	private String distinguishingQuestion;
	private String distinguishingAnswer;

	public Guesser() {
		knowlegeBase = new KnowlegeBase("elefante");
		currentBase = knowlegeBase;
	}

	public void postYesOrNo(String answer) {
		this.currentAnswer = answer;

		if (!currentBase.isLeaf()) {

			if (answer.equals("s")) currentBase = currentBase.left();
			else currentBase = currentBase.right();
		
		} else if (answer.equals("s")) {
			won = true;

		}

	}

	public boolean isLeafNowlegebase() {

		return currentBase.isLeaf();
	}

	public String getGuess() {
		if (!won) return currentBase.getGuess();
		return "ho vinto";
	}

	public String getWhatAnimalWereYouThinking() {
		return "A che animale stavi pensando?";
	}

	public String getLearningDistinguishingQuestion() {
		return "Dammi una domanda per distringuire un " + learningAnswer + " da un " + currentBase.getAnimal();
	}

	public void postLearningDistinguishingQuestion(String question) {
		this.distinguishingQuestion = question;
	}

	public void postLearningDistinguishingAnswer(String answer) {
		this.distinguishingAnswer = answer;
	}

	public void postAnimalImprovingKnowledge(String answer) {
		this.learningAnswer = answer;
	}



	public void playAgain() {

	}

	public void learn() {
		KnowlegeBase know;

		if (distinguishingAnswer.equals("s")) {
			know = new KnowlegeBase(distinguishingQuestion, new KnowlegeBase(learningAnswer), this.knowlegeBase);

		} else {
			know = new KnowlegeBase(distinguishingQuestion, this.knowlegeBase, new KnowlegeBase(learningAnswer));

		}

		this.knowlegeBase = know;
		this.currentBase = know;

	}
}

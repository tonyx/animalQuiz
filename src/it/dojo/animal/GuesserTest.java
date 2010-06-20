package it.dojo.animal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GuesserTest {



	@Test
	public void whenLearningANewAnimalTheFirstQuestionGuessChangesFromElefantToDistinguishingQuestion() {
		Guesser guesser = new Guesser();
		String q = guesser.getGuess();
		assertEquals("e' un elefante?", q);
		guesser.postYesOrNo("n");
		String learningQuestion = guesser.getWhatAnimalWereYouThinking();
		assertEquals("A che animale stavi pensando?", learningQuestion);
		guesser.postAnimalImprovingKnowledge("coniglio");
		String distQuestion = guesser.getLearningDistinguishingQuestion();
		assertEquals("Dammi una domanda per distringuire un coniglio da un elefante", distQuestion);
		guesser.postLearningDistinguishingQuestion("E'un animale piccolo?");
		guesser.postLearningDistinguishingAnswer("s");
		guesser.learn();
		assertFalse(guesser.isLeafNowlegebase());
		assertEquals("E'un animale piccolo?", guesser.getGuess());

	}

	@Test
	public void shouldBeAbleToGuessIfIThinkOfARabbitAfterLearning() {

		Guesser guesser = new Guesser();
		String q = guesser.getGuess();
		assertEquals("e' un elefante?", q);
		guesser.postYesOrNo("n");
		String learningQuestion = guesser.getWhatAnimalWereYouThinking();
		assertEquals("A che animale stavi pensando?", learningQuestion);
		guesser.postAnimalImprovingKnowledge("coniglio");
		String distQuestion = guesser.getLearningDistinguishingQuestion();
		assertEquals("Dammi una domanda per distringuire un coniglio da un elefante", distQuestion);
		guesser.postLearningDistinguishingQuestion("E'un animale piccolo?");
		guesser.postLearningDistinguishingAnswer("s");
		guesser.learn();
		assertFalse(guesser.isLeafNowlegebase());
		guesser.playAgain();
		assertEquals("E'un animale piccolo?", guesser.getGuess());
		guesser.postYesOrNo("s");
		assertEquals("e' un coniglio?", guesser.getGuess());
		guesser.postYesOrNo("s");
		assertEquals("ho vinto", guesser.getGuess());

	}

	@Test
	public void shouldBeAbleToMangeAConversationalState() {

		AnimalLearner learner = new AnimalLearner();
		// Guesser guesser = new Guesser();

		String message = learner.getMessage();
		// guesser.getGuess();

		
		assertEquals("e' un elefante?", message);

		
		// guesser.postYesOrNo("n");		
		learner.sendMessage("n");

		// String learningQuestion = guesser.getWhatAnimalWereYouThinking();
		message = learner.getMessage();
		
		
//		assertEquals("a che animale stavi pensando?",message);
		

		learner.sendMessage("coniglio");
		// guesser.postAnimalImprovingKnowledge("coniglio");
		
		

		
		//
		//
		// guesser.postAnimalImprovingKnowledge("coniglio");
		//
		// String distQuestion = guesser.getLearningDistinguishingQuestion();
		//
		// assertEquals("Dammi una domanda per distringuire un coniglio da un elefante",
		// distQuestion);
		//
		// guesser.postLearningDistinguishingQuestion("E'un animale piccolo?");
		//
		// guesser.postLearningDistinguishingAnswer("s");
		//
		// guesser.learn();
		//
		// assertFalse(guesser.isLeafNowlegebase());
		//
		// guesser.playAgain();
		//
		// assertEquals("E'un animale piccolo?", guesser.getGuess());
		//
		// guesser.postYesOrNo("s");
		//
		// assertEquals("è un coniglio?", guesser.getGuess());
		//
		// guesser.postYesOrNo("n");
		//
		// learningQuestion = guesser.getWhatAnimalWereYouThinking();
		//
		// assertEquals("A che animale stavi pensando?", learningQuestion);
		//
		// guesser.postAnimalImprovingKnowledge("pesce");
		//
		// distQuestion = guesser.getLearningDistinguishingQuestion();
		//
		// assertEquals("Dammi una domanda per distringuire un pesce da un coniglio",
		// distQuestion);
		//
		// guesser.postLearningDistinguishingQuestion("E'un animale che nuota?");
		//
		// guesser.postLearningDistinguishingAnswer("s");
		//
		// guesser.learn();

	}

}

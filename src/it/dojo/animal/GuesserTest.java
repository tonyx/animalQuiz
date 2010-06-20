package it.dojo.animal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GuesserTest {




    @Test
    public void leafKnowlegeBaseShouldBeAbleToReturnElementThatInitializedIt() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        assertEquals("elefante",kb.getAnimal());
    }

    @Test
    public void newInizializedKnowlegeBaseShouldBeAleaf() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        assertTrue(kb.isLeaf());
    }

    @Test
    public void canImproveFromALeafKNowlegeBaseToANewConcaptWithDiscriminatingQuestion() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.n,"e' un animale grande?","coniglio");
        assertFalse(improved.isLeaf());
    }



    @Test
    public void theImprovedBaseShouldContainElefantOnRightAndRabitOnLeft() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.n,"e' un animale grande?","coniglio");
        assertEquals("coniglio",improved.noBranch().getAnimal());
        assertEquals("elefante",improved.yesBranch().getAnimal());
    }

    @Test
    public void theImprovedBaseShouldContainElefantOnRightAndRabitInverted() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.s,"e' un animale piccolo?","coniglio");
        assertEquals("elefante",improved.noBranch().getAnimal());
        assertEquals("coniglio",improved.yesBranch().getAnimal());
    }

    @Test
    public void afterOneStepLeraningTheBaseIsNotLeafAndtheBranchesAreLeafes() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.s,"e' un animale piccolo?","coniglio");
        assertFalse(improved.isLeaf());
        assertTrue(improved.noBranch().isLeaf());
        assertTrue(improved.yesBranch().isLeaf());
    }


    @Test
    public void anyGuesserInitializedWithARestructuredKnowlegeBaseIsAbleToGuessANodeContainedInIt() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.s,"e' un animale piccolo?","coniglio");

        Guesser guesser = new Guesser(improved);
        assertEquals("e' un animale piccolo?",guesser.getGuess());

        KnowlegeBase yes =guesser.getBranchByDiscriminationValue(Guesser.YesOrNot.s);
        assertEquals("coniglio",yes.getAnimal());

        KnowlegeBase no =guesser.getBranchByDiscriminationValue(Guesser.YesOrNot.n);
        assertEquals("elefante",no.getAnimal());

    }


    @Test
    public void shouldBeAbleToImproveKnowlegeInMoreSteps() {
        KnowlegeBase kb = new KnowlegeBase("elefante");
        KnowlegeBase improved =  kb.getImprovedBase(Guesser.YesOrNot.s,"e' un animale piccolo?","coniglio");

        Guesser guesser = new Guesser(improved);
        assertEquals("e' un animale piccolo?",guesser.getGuess());

        KnowlegeBase yes =guesser.getBranchByDiscriminationValue(Guesser.YesOrNot.s);
        assertEquals("coniglio",yes.getAnimal());

        KnowlegeBase no =guesser.getBranchByDiscriminationValue(Guesser.YesOrNot.n);
        assertEquals("elefante",no.getAnimal());
    }

    @Test
    public void canTraverseCorrectlyGuessingYesNotAtAnyLevel() {
        KnowlegeBase kb = new KnowlegeBase("nuota?",new KnowlegeBase("pesce"),new KnowlegeBase("coniglio"));
        KnowlegeBase kbImproved = new KnowlegeBase("e' un animale piccolo?",kb,new KnowlegeBase("elefante"));

        assertFalse(kbImproved.isLeaf());
        KnowlegeBase kbSub = kbImproved.traverse(Guesser.YesOrNot.n);
        assertEquals("elefante",kbSub.getAnimal());


    }
















    @Test
    public void testWithRefactogingLearningInOneStep() {
        Guesser guesser = new Guesser(new KnowlegeBase("elefante"));
        String q = guesser.getGuess();
        assertEquals("e' un elefante?", q);
        //guesser.postYesOrNo("n");
        guesser.postYesOrNo(Guesser.YesOrNot.n);
        String learningQuestion = guesser.getWhatAnimalWereYouThinking();

        assertEquals("A che animale stavi pensando?", learningQuestion);

        //guesser.addNnowlegeFromCurrentNode("coniglio","E' un animale piccolo?","s");

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
	public void whenLearningANewAnimalTheFirstQuestionGuessChangesFromElefantToDistinguishingQuestion() {
		Guesser guesser = new Guesser(new KnowlegeBase("elefante"));
		String q = guesser.getGuess();
		assertEquals("e' un elefante?", q);
		//guesser.postYesOrNo("n");
        guesser.postYesOrNo(Guesser.YesOrNot.n);
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

		Guesser guesser = new Guesser(new KnowlegeBase("elefante"));
		String q = guesser.getGuess();
		assertEquals("e' un elefante?", q);
		//guesser.postYesOrNo("n");
        guesser.postYesOrNo(Guesser.YesOrNot.n);
		String learningQuestion = guesser.getWhatAnimalWereYouThinking();
		assertEquals("A che animale stavi pensando?", learningQuestion);
		guesser.postAnimalImprovingKnowledge("coniglio");
		String distQuestion = guesser.getLearningDistinguishingQuestion();
		assertEquals("Dammi una domanda per distringuire un coniglio da un elefante", distQuestion);
		guesser.postLearningDistinguishingQuestion("E'un animale piccolo?");
		guesser.postLearningDistinguishingAnswer("s");
		guesser.learn();
		assertFalse(guesser.isLeafNowlegebase());

		//guesser.playAgain();

		assertEquals("E'un animale piccolo?", guesser.getGuess());
		//guesser.postYesOrNo("s");
        guesser.postYesOrNo(Guesser.YesOrNot.s);
		assertEquals("e' un coniglio?", guesser.getGuess());
		//guesser.postYesOrNo("s");
        guesser.postYesOrNo(Guesser.YesOrNot.s);
		assertEquals("ho vinto", guesser.getGuess());

	}



    @Test
    public void anotherStepInLearning() {

        Guesser guesser = new Guesser(new KnowlegeBase("elefante"));
        String q = guesser.getGuess();
        assertEquals("e' un elefante?", q);
        //guesser.postYesOrNo("n");
        guesser.postYesOrNo(Guesser.YesOrNot.n);
        String learningQuestion = guesser.getWhatAnimalWereYouThinking();
        assertEquals("A che animale stavi pensando?", learningQuestion);
        guesser.postAnimalImprovingKnowledge("coniglio");
        String distQuestion = guesser.getLearningDistinguishingQuestion();
        assertEquals("Dammi una domanda per distringuire un coniglio da un elefante", distQuestion);
        guesser.postLearningDistinguishingQuestion("E'un animale piccolo?");
        guesser.postLearningDistinguishingAnswer("s");
        guesser.learn();
        assertFalse(guesser.isLeafNowlegebase());


        //guesser.playAgain();

        assertEquals("E'un animale piccolo?", guesser.getGuess());
        //guesser.postYesOrNo("s");
        guesser.postYesOrNo(Guesser.YesOrNot.s);
        assertEquals("e' un coniglio?", guesser.getGuess());
        //guesser.postYesOrNo("s");
        guesser.postYesOrNo(Guesser.YesOrNot.n);

        learningQuestion = guesser.getWhatAnimalWereYouThinking();
        assertEquals("A che animale stavi pensando?", learningQuestion);

        guesser.postAnimalImprovingKnowledge("pesce");
        distQuestion = guesser.getLearningDistinguishingQuestion();

        assertEquals("Dammi una domanda per distringuire un pesce da un coniglio", distQuestion);
        guesser.postLearningDistinguishingQuestion("vive nell'acqua?");
        guesser.postLearningDistinguishingAnswer("s");
        guesser.learn();
        guesser.playAgain();


//        assertEquals("E'un animale piccolo?", guesser.getGuess());
//        //guesser.postYesOrNo("s");
//        guesser.postYesOrNo(Guesser.YesOrNot.s);
//        assertEquals("e' un coniglio?", guesser.getGuess());
//        //guesser.postYesOrNo("s");
//        guesser.postYesOrNo(Guesser.YesOrNot.n);
//        assertEquals("e' un pesce?", guesser.getGuess());
//        guesser.postYesOrNo(Guesser.YesOrNot.s);
//        assertEquals("ho vinto", guesser.getGuess());



    }







    @Test
	public void shouldBeAbleToMangeAConversationalState() {
//
//		AnimalLearner learner = new AnimalLearner();
//		// Guesser guesser = new Guesser();
//
//		String message = learner.getMessage();
//		// guesser.getGuess();
//
//
//		assertEquals("e' un elefante?", message);
//
//
//		// guesser.postYesOrNo("n");
//		learner.sendMessage("n");
//
//		// String learningQuestion = guesser.getWhatAnimalWereYouThinking();
//		message = learner.getMessage();
//
//
////		assertEquals("a che animale stavi pensando?",message);
//
//
//		learner.sendMessage("coniglio");
//		// guesser.postAnimalImprovingKnowledge("coniglio");
//		


















		
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

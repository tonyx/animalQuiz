package it.dojo.animal;

public class Guesser {
    public enum YesOrNot {s,n};
    KnowlegeBase father;

	private KnowlegeBase knowlegeBase;
    private KnowlegeBase rootKnowlegeBase;

	private boolean won = false;

	private YesOrNot currentAnswer;
	private String learningAnswer;
	private String distinguishingQuestion;
	private String distinguishingAnswer;

    public Guesser(KnowlegeBase knowlegeBase)
    {
        this.knowlegeBase = knowlegeBase;
    }
     

//	public Guesser() {
//		knowlegeBase = new KnowlegeBase("elefante");
//        rootKnowlegeBase=knowlegeBase;
//	}

	public void postYesOrNo(YesOrNot answer) {
		this.currentAnswer = answer;
        if (!knowlegeBase.isLeaf()) {
            if (answer.equals(YesOrNot.s)) knowlegeBase = knowlegeBase.yesBranch();
            else knowlegeBase = knowlegeBase.noBranch();
        } else if (answer.equals(YesOrNot.s)) {
            won = true;
        }
	}


    public  KnowlegeBase getBranchByDiscriminationValue(YesOrNot yesOrNot)
    {
        if (!knowlegeBase.isLeaf()) {
            if (YesOrNot.s.equals(yesOrNot))
                return knowlegeBase.yesBranch();
            else
                return knowlegeBase.noBranch();
        }
        return new KnowlegeBase("se hai questa info c'e' un errore");
    }


	public boolean isLeafNowlegebase() {
        return knowlegeBase.isLeaf();
	}

	public String getGuess() {
        if (!won) return knowlegeBase.getGuess();
		return "ho vinto";
	}

	public String getWhatAnimalWereYouThinking() {
		return "A che animale stavi pensando?";
	}

	public String getLearningDistinguishingQuestion() {
        return "Dammi una domanda per distringuire un " + learningAnswer + " da un " + knowlegeBase.getAnimal();
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
        this.knowlegeBase=this.rootKnowlegeBase;
	}

	public void learn() {
		KnowlegeBase know;

		if (distinguishingAnswer.equals("s")) {
			know = new KnowlegeBase(distinguishingQuestion, new KnowlegeBase(learningAnswer), this.knowlegeBase);

		} else {
			know = new KnowlegeBase(distinguishingQuestion, this.knowlegeBase, new KnowlegeBase(learningAnswer));
		}

        this.knowlegeBase=know;
        //father = know;

	}

    public String traverse(Guesser.YesOrNot... sequenceYesAndNot)
    {
        KnowlegeBase temp = this.knowlegeBase;
//        for (int i=0;i<sequenceYesAndNot.length;i++) {
//            if (sequenceYesAndNot[i].equals(YesOrNot.s)) {
//                temp = this.knowlegeBase.yesBranch();
//            } else {
//                temp = this.knowlegeBase.noBranch();
//            }
//        }
        return temp.getAnimal();
    }

}

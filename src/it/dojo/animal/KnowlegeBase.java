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


    public KnowlegeBase traverse(Guesser.YesOrNot direction)
    {
        if (Guesser.YesOrNot.s==direction){
            return this.yesBranch();
        }
        return this.noBranch();
    }


	public KnowlegeBase(String question, KnowlegeBase left, KnowlegeBase right) {
		this.question = question;
		this.left = left;
		this.right = right;
		isLeaf = false;
	}

    public KnowlegeBase getImprovedBase(Guesser.YesOrNot yesOrNot, String discriminatingQuestion, String animal)
    {
        KnowlegeBase toReturn;
        if (yesOrNot.equals(Guesser.YesOrNot.s))
        {
            toReturn = new KnowlegeBase(discriminatingQuestion,new KnowlegeBase(animal),this);
        }  else
            toReturn = new KnowlegeBase(discriminatingQuestion,this,new KnowlegeBase(animal));
        return toReturn;
    }


	public String getGuess() {
		if (isLeaf) return "e' un " + animal + "?";
		return question;
	}

	public boolean isLeaf()
	{
		return isLeaf;
	}


	public KnowlegeBase yesBranch() {
		return left;
	}

	public KnowlegeBase noBranch() {
		return right;
	}

	public String getAnimal() {
		return animal;
	}

}

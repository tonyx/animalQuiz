package it.dojo.animal;

public class AnimalLearner {
    
	private enum State {
		STARTED {

			
		};


	};

	private final Guesser guesser;

	private State state;

	public AnimalLearner() {
		guesser = new Guesser();
	
		state= State.STARTED;
	}

	public String getMessage() {
        return guesser.getGuess();

        //return "è un elefante?";
//        return "fake";
		//return state.getMessage();
	}

//	public void sendMessage(String message) {
//		guesser.postYesOrNo(message);
//	}

}

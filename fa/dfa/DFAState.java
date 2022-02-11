package fa.dfa;

import java.util.HashMap;

public class DFAState extends fa.State {
	
	//Dictionary of input and next state
	private HashMap<Character,DFAState> transitions;
	public DFAState(String name) {
		this.name = name;
		transitions = new HashMap<Character,DFAState>();
	}
	
	public void getNextState(Character input) {
		transitions.get(input);
	}
	public void addNextState(Character input, DFAState newState) {
		if(transitions.get(input) == newState) {
			System.out.println("Transition already exists");
			return;
		}
		transitions.put(input, newState);
	}
}

package fa.dfa;

import java.util.HashMap;

public class DFAState extends fa.State {
	
	//Dictionary of input and next state
	private HashMap<Character,DFAState> transitions;
	public DFAState(String name) {
		this.name = name;
		transitions = new HashMap<Character,DFAState>();
	}
	
	public DFAState(String name, HashMap<Character,DFAState> t) {
		this.name = name;
		transitions = new HashMap<Character,DFAState>(t);
	}
	
	public DFAState getNextState(Character input) {
		return transitions.get(input);
	}
	public void addNextState(Character input, DFAState newState) {
		if(transitions.get(input) == newState) {
			System.out.println("Transition already exists");
			return;
		}
		transitions.put(input, newState);
	}
	public HashMap<Character,DFAState> getTransitions(){
		return transitions;
	}
	public void swapKeys(char symb1, char symb2) {
		DFAState symb1Holder = transitions.get(symb1);
		DFAState symb2Holder = transitions.get(symb2);
		transitions.replace(symb1, symb2Holder);
		transitions.replace(symb2, symb1Holder);
	}

	public void printTansitions() {
		System.out.println(transitions);
	}
}

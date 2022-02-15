package fa.dfa;

import java.util.HashMap;

/**
 * This class represents a state. With each state, we have
 * the state name and the transitions. The transitions are
 * stored in a hashmap, with a unique key(input) corresponding
 * to a next state name.
 * 
 * @author Kincaid Schmitt, Alex Silva
 *
 */
public class DFAState extends fa.State {
	
	//Dictionary of input and next state
	private HashMap<Character,DFAState> transitions;
	
	/**
	 * Constructor to create a new DFAState object
	 * 
	 * @param name - State name
	 */
	public DFAState(String name) {
		this.name = name;
		transitions = new HashMap<Character,DFAState>();
	}
	
	
	/**
	 * Gets the next state given an input
	 * 
	 * @param input - the input symbol
	 * @return the DFAState of the next state
	 */
	public DFAState getNextState(Character input) {
		return transitions.get(input);
	}
	/**
	 * @param input
	 * @param newState
	 */
	public void addNextState(Character input, DFAState newState) {
		if(transitions.get(input) == newState) {
			System.out.println("Transition already exists");
			return;
		}
		transitions.put(input, newState);
	}
	/**
	 * @return
	 */
	public HashMap<Character,DFAState> getTransitions(){
		return transitions;
	}
	/**
	 * @param symb1
	 * @param symb2
	 */
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

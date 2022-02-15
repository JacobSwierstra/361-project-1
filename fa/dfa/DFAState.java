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
	 * Adds the a new next state to the transition hashmap
	 * 
	 * @param input - the input to get to next state
	 * @param newState - the new state to go to
	 */
	public void addNextState(Character input, DFAState newState) {
		if(transitions.get(input) == newState) {
			System.out.println("Transition already exists");
			return;
		}
		transitions.put(input, newState);
	}
	
	/**
	 * Gets the full hashmap of transitions
	 * 
	 * @return the hashmap of transitions
	 */
	public HashMap<Character,DFAState> getTransitions(){
		return transitions;
	}
	
	/**
	 * Swaps the key inputs of the hashmap
	 * 
	 * @param symb1 - first char
	 * @param symb2 - second char
	 */
	public void swapKeys(char symb1, char symb2) {
		DFAState symb1Holder = transitions.get(symb1);
		DFAState symb2Holder = transitions.get(symb2);
		transitions.replace(symb1, symb2Holder);
		transitions.replace(symb2, symb1Holder);
	}


	/**
	 * Prints the transitions. Helped with debugging and validation
	 */
	public void printTansitions() {
		System.out.println(transitions);
	}
}

package fa.dfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

/**
 * A DFA object that represents a full DFA. Each DFA has a 
 * corresponding alphabet, states, finalstates, and a startState.
 * There are many methods that we implemented to add and change 
 * our DFA object. The DFA also can take in string input and 
 * decide if the string is accepted or rejected
 * 
 * @author Kincaid Schmitt, Alex Silva
 *
 */
public class DFA implements DFAInterface {
	
	private Set<Character> alphabet;
	private Set<DFAState> states;
	private Set<DFAState> finalStates;
	private DFAState startState;
	
	/**
	 * Constructor to create a new DFA object
	 */
	public DFA(){
		//initalize empty values
		alphabet = new LinkedHashSet<>();
		states = new LinkedHashSet<>();
		finalStates = new LinkedHashSet<>();
		startState = null;
	}

	@Override
	public void addStartState(String name) {
		//check if state already exists
		for(DFAState s: states) {
			if(s.getName().equals(name)) {
				this.startState = s;
				return;
			}
		}
		
		//create new state and add it to set
		DFAState startState = new DFAState(name);
		states.add(startState);
		this.startState = startState;

	}

	@Override
	public void addState(String name) {
		//check if state already exists
		for(DFAState s: states) {
			if(s.getName().equals(name)) return;
		}
		//add the new state if not exists
		DFAState newState = new DFAState(name);
		states.add(newState);

	}

	@Override
	public void addFinalState(String name) {
		//check if state already exists. If so, add to set
		for(DFAState s: states) {
			if(s.getName().equals(name)) {
				finalStates.add(s);
				return;
			}
		}
		//create new state and add it to set
		DFAState finalState = new DFAState(name);
		states.add(finalState);
		finalStates.add(finalState);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		//find the to and from state by iterating through states and checking names
		DFAState fState = null;
		DFAState tState = null;
		for(DFAState s: states) {
			if(fState != null && tState != null) break;
			if(s.getName().equals(fromState)) {
				fState = s;
			}
			if(s.getName().equals(toState)) {
				tState = s;
			}
		}
		//update the transitions and add symbols to alpha
		fState.addNextState(onSymb, tState);
		addToAlphabet(onSymb);
	}
	
	/**
	 * Add new symbols to the alphabet as we see new ones
	 * 
	 * @param onSymb
	 */
	private void addToAlphabet(char onSymb) {
		//check if it already exists in the set
		for(Character c : alphabet) {
			if(c == onSymb) {
				return;
			}
		}
		//add if it is new
		alphabet.add(onSymb);
	}

	@Override
	public Set<? extends State> getStates() {
		return states;
	}
	
	/**
	 * Returns the states in the specified object type
	 * 
	 * @return a Set<DFAState> object
	 */
	public Set<DFAState> getStates2(){
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		return finalStates;
	}

	@Override
	public State getStartState() {
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		return alphabet;
	}

	@Override
	public boolean accepts(String s) {
		//current state starts at start state
		DFAState currentState = startState;
		//update current state to next state
		for(char symb: s.toCharArray()) {
			currentState = currentState.getNextState(symb);
		}
		//for(DFAState state: finalStates) {
			//if(state.getName().equals(currentState.getName())) return true;
		//}
		//return false;
		
		//check if current state is in final state
		return finalStates.contains(currentState);
	}

	@Override
	public DFA swap(char symb1, char symb2) {
		//create a deep copy and swap the keys
		DFA dfaCopy = deepCopy();		
		for(State copyS: dfaCopy.getStates()) {
			((DFAState) copyS).swapKeys(symb1, symb2);
		}
		return dfaCopy;
	}

	/**
	 * A method to create a deep copy of the primary dfa object
	 * 
	 * @return a deep copy of the dfa object
	 */
	private DFA deepCopy() {
		//initalize the new DFA object
		DFA dfa = new DFA();	
		
		//manually add the alphabet, states, and final states
		for(char symb: alphabet) {
			dfa.addToAlphabet(symb);
		} 
		for(DFAState state: states) {
			dfa.addState(state.getName());
		}
		//We think that our bug exists in this part of the code.
		for(DFAState state: states) {
			for(DFAState newStates: dfa.getStates2()) {
				if(state.getName().equals(newStates.getName())) {
					//gets the old transitions from the old object
					HashMap<Character,DFAState> oldTrans = state.getTransitions();
					//iterate over each ket in old object
					for(char input : oldTrans.keySet()) {
						//gets the next state given the key
						DFAState wantedState = state.getNextState(input);
						//adds transition input and state to new dfa object
						newStates.addNextState(input, wantedState);
					}
				}
			}
			
		}
		
		for(DFAState fStates: finalStates) {
			dfa.addFinalState(fStates.getName());
		}
		dfa.addStartState(startState.getName());
			
		return dfa;
	}
	
	@Override
	public State getToState(DFAState from, char onSymb) {
		return from.getNextState(onSymb);
	}
	/**
	 * Construct the textual representation of the DFA, for example
	 * A simple two state DFA
	 * Q = { a b }
	 * Sigma = { 0 1 }
	 * delta =
	 *		0	1	
	 *	a	a	b	
	 *	b	a	b	
	 * q0 = a
	 * F = { b }
	 * 
	 * The order of the states and the alphabet is the order
	 * in which they were instantiated in the DFA.
	 * @return String representation of the DFA
	 */
	@Override
	public String toString() {
		//use string builder to build the string output
		StringBuilder sb = new StringBuilder();	
		//iterate over states
		sb.append("Q = { ");
		for(DFAState s: states) {
			String str = String.format("%s ", s.getName());
			sb.append(str);
		}
		sb.append("}\n");
		//iterate over alphabet
		sb.append("Sigma = { ");
		for(Character c : alphabet) {
			String str = String.format("%s ", c);
			sb.append(str);
		}
		sb.append("}\n");
		//crates transition table
		sb.append("delta = \n\t\t");
		for(Character c : alphabet) {
			String str = String.format("%s\t", c);
			sb.append(str);
		}
		sb.append("\n");
		for(DFAState s: states) {
			String str = String.format("\t%s", s.getName());
			sb.append(str);
			for(Character c : alphabet) {
				String nextState = String.format("\t%s", s.getNextState(c).getName());
				sb.append(nextState);
			}
			sb.append("\n");
		}
		//start state
		sb.append("q0 = " + startState + "\n");
		//final state
		sb.append("F = { ");
		for(DFAState s: finalStates) {
			String str = String.format("%s ", s.getName());
			sb.append(str);
		}
		sb.append("}\n");
		return sb.toString();
	}	
}

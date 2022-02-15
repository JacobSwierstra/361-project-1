package fa.dfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface {
	
	private Set<Character> alphabet;
	private Set<DFAState> states;
	private Set<DFAState> finalStates;
	private DFAState startState;
	
	/**
	 * Constructor to create a new DFA object
	 */
	public DFA(){
		alphabet = new LinkedHashSet<>();
		states = new LinkedHashSet<>();
		finalStates = new LinkedHashSet<>();
		startState = null;
	}

	@Override
	public void addStartState(String name) {
		for(DFAState s: states) {
			if(s.getName().equals(name)) {
				this.startState = s;
				return;
			}
		}
		
		DFAState startState = new DFAState(name);
		states.add(startState);
		this.startState = startState;

	}

	@Override
	public void addState(String name) {
		for(DFAState s: states) {
			if(s.getName().equals(name)) return;
		}
		DFAState newState = new DFAState(name);
		states.add(newState);

	}

	@Override
	public void addFinalState(String name) {
		for(DFAState s: states) {
			if(s.getName().equals(name)) {
				finalStates.add(s);
				return;
			}
		}
		
		DFAState finalState = new DFAState(name);
		states.add(finalState);
		finalStates.add(finalState);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
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
		fState.addNextState(onSymb, tState);
		addToAlphabet(onSymb);
	}
	
	/**
	 * Add new symbols to the alphabet as we see them
	 * 
	 * @param onSymb
	 */
	private void addToAlphabet(char onSymb) {
		for(Character c : alphabet) {
			if(c == onSymb) {
				return;
			}
		}
		alphabet.add(onSymb);
	}

	@Override
	public Set<? extends State> getStates() {
		return states;
	}
	
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
		DFAState currentState = startState;
		for(char symb: s.toCharArray()) {
			currentState = currentState.getNextState(symb);
		}
		for(DFAState state: finalStates) {
			if(state.getName().equals(currentState.getName())) return true;
		}
		return false;
	}

	@Override
	public DFA swap(char symb1, char symb2) {
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
		DFA dfa = new DFA();	
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
					HashMap<Character,DFAState> oldTrans = state.getTransitions();
					for(char input : oldTrans.keySet()) {
						DFAState wantedState = state.getNextState(input);
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
		StringBuilder sb = new StringBuilder();	
		sb.append("Q = { ");
		for(DFAState s: states) {
			String str = String.format("%s ", s.getName());
			sb.append(str);
		}
		sb.append("}\n");
		sb.append("Sigma = { ");
		for(Character c : alphabet) {
			String str = String.format("%s ", c);
			sb.append(str);
		}
		sb.append("}\n");
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
		sb.append("q0 = " + startState + "\n");
		sb.append("F = { ");
		for(DFAState s: finalStates) {
			String str = String.format("%s ", s.getName());
			sb.append(str);
		}
		sb.append("}\n");
		return sb.toString();
	}	
}

package fa.dfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface {
	
	private Set<Character> alphabet;
	private Set<DFAState> states;
	private Set<DFAState> finalStates;
	private DFAState startState;
	
	public DFA(){
		alphabet = new LinkedHashSet<>();
		states = new LinkedHashSet<>();
		finalStates = new LinkedHashSet<>();
		startState = null;
	}

	@Override
	public void addStartState(String name) {
		//Do we need to check if the state already exist in the list??
		//I think the case the start state is a final state
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Character> getABC() {
		return alphabet;
	}

	@Override
	public boolean accepts(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DFA swap(char symb1, char symb2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

}

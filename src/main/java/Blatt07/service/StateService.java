package Blatt07.service;

import Blatt07.construct.Graph;
import Blatt07.construct.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateService {

    private List<State> stateList = new ArrayList<>();

    public void extractState(Graph graph) {
        stateList.add(new State(graph.getNodeMap()));
    }

    public void printAll(){
        stateList.forEach(State::toString);
    }

    private class State{
        private Map<String, Node> map;

        public State(Map<String, Node> map){
            this.map = map;
        }

        @Override
        public String toString() {

            return map.get("A1D1").getLetterSet().toString() + "\t" + map.get("A2D1").getLetterSet() + "\t" + map.get("A3D1").getLetterSet() +
                    map.get("A1D2").getLetterSet() + "\t" + map.get("A2D2").getLetterSet() + "\t" + map.get("A3D2").getLetterSet() +
                    map.get("A1D3").getLetterSet() + "\t" + map.get("A2D3").getLetterSet() + "\t" + map.get("A3D3").getLetterSet();
        }
    }
}

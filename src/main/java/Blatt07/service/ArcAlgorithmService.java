package Blatt07.service;

import Blatt07.construct.Graph;
import Blatt07.construct.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArcAlgorithmService {


    private Set<String> wordSet;
    private Graph graph;
    private StateService stateService;

    public ArcAlgorithmService(Set<String> wordSet) {

        this.wordSet = wordSet;
        graph = new Graph(wordSet);
        this.stateService = new StateService();
    }


    public Graph startArcAlgorithm() {
        List<Node> nodeList = new ArrayList<>();
        graph.getNodeMap().forEach((s, node) -> nodeList.add(node)); // Packen mal alle Nodes in die Nodelist

        while (!nodeList.isEmpty()) { // Solange diese nicht leer ist
            Node node = nodeList.get(0); // Nimm das erste Element

            int beforeSize = node.getLetterSet().size(); // Merke dir die lettersize

            node.getConstraintList().forEach(constraint -> {
                Node actorTwo = graph.getNodeMap().get(constraint.getActorTwo()); // Hol dir den Constraintpartner
                node.setLetterSet(ComputationHelper.trimSetOfCharacters(node.getName() ,node.getLetterSet(), constraint.getPositionOne(), wordSet, actorTwo.getLetterSet(), constraint.getPositionTwo())); //Vergleiche hier
            });


            if (beforeSize == node.getLetterSet().size()) { // Ist ein Buchstabe weg ?
                nodeList.remove(0); // Wenn nein, dann schmeiße die Node raus
            } else {
                graph.getNodeMap().forEach((s, node1) -> nodeList.add(nodeList.size(), node1)); //
            }

            if (nodeList.isEmpty()) {

                System.out.println("Einmal fertig: ");
                graph.getNodeMap().forEach((s, node1) -> System.out.println(node1.getName() + ": " + node1.getLetterSet()));
                final boolean[] finished = {true};
                graph.getNodeMap().forEach((s, node1) -> {
                    if (node1.getLetterSet().size() > 1) {
                        finished[0] = false;
                    }
                });

                if (!finished[0]) {
                    for (Map.Entry<String, Node> enrty : graph.getNodeMap().entrySet()) {
                        if (enrty.getValue().getLetterSet().size() > 1) {
                            System.out.println("Remove " + enrty.getValue().getLetterSet().iterator().next() + " von "+ enrty.getKey());
                            enrty.getValue().getLetterSet().remove(enrty.getValue().getLetterSet().iterator().next());
                            break;
                        }
                    }
                    graph.getNodeMap().forEach((s, node1) -> nodeList.add(node1));
                }

            }
        }

        return graph;
    }
}

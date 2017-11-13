package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.internalRepresentation.GoalNode;
import objectives.internalRepresentation.Node;

import java.util.*;

public class DepthFirstSearch {

    private Labyrinth labyrinth;
    private SearchSpaceService searchSpaceService;

    public DepthFirstSearch(final Labyrinth labyrinth){
        this.labyrinth = labyrinth;
        this.searchSpaceService = new SearchSpaceService();
    }

    public List<Node> startDepthSearch(){
        final Stack<Node> stack = new Stack<>();
        final Map<Position, Position> dictionary = new HashMap<>();
        stack.push(labyrinth.getGraph().getStartNode());

        while(!stack.isEmpty()){
            Node node = stack.pop();

            if(node instanceof GoalNode){
                searchSpaceService.printAllSearchStates();
                labyrinth.getGraph().resetAllNodes();
                return constructPath(dictionary, node);
            }

            addNodeToStack(node.getWestNachbar(), stack, dictionary, node);
            addNodeToStack(node.getSouthNachbar(), stack, dictionary, node);
            addNodeToStack(node.getEastNachbar(), stack, dictionary, node);
            addNodeToStack(node.getNorthNachbar(), stack, dictionary, node);


            node.setInPath(true);

            searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, node.getPosition());
        }
        searchSpaceService.printAllSearchStates();
        return Collections.emptyList();
    }

    private List<Node> constructPath(Map<Position, Position> dictionary, Node goalNode) {
        List<Node> nodeList = new ArrayList<>();
        while (goalNode != null) {
            goalNode.setInPath(true);
            nodeList.add(goalNode);
            goalNode = labyrinth.getGraph().getNodeAtPosition(dictionary.get(goalNode.getPosition()));
        }
        System.out.println(nodeList);
        searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, labyrinth.getGraph().getGoalNode().getPosition());
        searchSpaceService.printAllSearchStates();
        return nodeList;
    }


    private void addNodeToStack(Node node, Stack<Node> stack, Map<Position, Position> map, Node parentNode){
        if (node != null
                && node.isPossible()
                && !node.isAlreadyVisited()){
            stack.push(node);
            node.setAlreadyVisisted(true);
            map.put(node.getPosition(), parentNode.getPosition());
        }
    }
}

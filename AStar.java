/**
 * Implements the A* search algorithm to find the shortest path
 *  in a graph between a start node and a goal node.
 * If start or goal are null, it returns null
 * If start and goal are the same, it returns an empty path
 * Otherwise, it returns a Path consisting of a list of Edges that will
 * connect the start node to the goal node.
 */

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class AStar {

    /**
     * Finds the shortest path between two stops
     */
    public static List<Edge> findShortestPath(Stop start, Stop goal) {
        if (start == null || goal == null) {
            return null;
        }

        List<Edge> path = new ArrayList<>();

        PriorityQueue<SearchQueueItem> fringe = new PriorityQueue<>();
        Map<Stop, Edge> backpointers = new HashMap<>();
        Set<Stop> visited = new HashSet<>();
        fringe.add(new SearchQueueItem(start, null, 0, start.distanceTo(goal), 0, start.distanceTo(goal)/Transport.TRAIN_SPEED_MPS));
        while (!fringe.isEmpty()) {
            SearchQueueItem curr = fringe.poll();
            if (!visited.contains(curr.getStop())) {
                visited.add(curr.getStop());
                backpointers.put(curr.getStop(), curr.getEdge());
                if (curr.getStop() == goal) {
                    Stop here = goal;
                    while (here != start) {
                        path.add(0, backpointers.get(here));
                        here = backpointers.get(here).fromStop();
                    }
                    break;
                }
                for (Edge edge : curr.getStop().getEdges()) {
                    Stop neighbour = edge.toStop();
                    if (!visited.contains(neighbour)) {
                        double lenToNeigh = curr.getTravelled() + edge.distance();
                        double estTotal = lenToNeigh + neighbour.distanceTo(goal);
                        double time;
                        if (edge.transpType() == Transport.WALKING) {
                            time = curr.getTime()+edge.distance()*Transport.WALKING_SPEED_MPS;
                        }
                        else {
                            time = edge.line().getTimes().get(edge.line().getStops().indexOf(neighbour));
                        }
                        double estTotalTime = estTotal/Transport.TRAIN_SPEED_MPS;
                        fringe.add(new SearchQueueItem(neighbour, edge, lenToNeigh, estTotal, time, estTotalTime));
                    }
                }
            }
        }

        return path;
    }

}

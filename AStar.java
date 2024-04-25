/**
 * Implements the A* search algorithm to find the shortest path
 *  in a graph between a start node and a goal node.
 * If start or goal are null, it returns null
 * If start and goal are the same, it returns an empty path
 * Otherwise, it returns a Path consisting of a list of Edges that will
 * connect the start node to the goal node.
 */

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    public static List<Edge> findShortestPath(Stop start, Stop goal, LocalTime startTime) {
        if (start == null || goal == null) {
            return null;
        }

        if (startTime == null) {
            startTime = LocalTime.now();
        }
        LocalTime currentTime;

        List<Edge> path = new ArrayList<>();

        PriorityQueue<SearchQueueItem> fringe = new PriorityQueue<>();
        Map<Stop, Edge> backpointers = new HashMap<>();
        Set<Stop> visited = new HashSet<>();
        fringe.add(new SearchQueueItem(start, null, 0, start.distanceTo(goal), 0, start.distanceTo(goal)/Transport.TRAIN_SPEED_MPS, startTime));
        while (!fringe.isEmpty()) {
            SearchQueueItem curr = fringe.poll();
            if (!visited.contains(curr.getStop())) {
                visited.add(curr.getStop());
                backpointers.put(curr.getStop(), curr.getEdge());
                currentTime = curr.getTime();
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
                        LocalTime nextTime;
                        if (edge.transpType() != Transport.WALKING) {
                            Trip tripAhead = edge.trip();
                            nextTime = tripAhead.getTimes().get(tripAhead.getStops().indexOf(neighbour));
                        }
                        else {
                            nextTime = currentTime.plusSeconds(Math.round(edge.distance()/Transport.WALKING_SPEED_MPS));
                        }
                        if (nextTime.isAfter(currentTime)) {
                            double lenToNeigh = curr.getTravelled() + edge.distance();
                            double estTotal = lenToNeigh + neighbour.distanceTo(goal);
                            double time;
                            if (edge.transpType() == Transport.WALKING) {
                                time = curr.getTravelledTime() + edge.distance()/Transport.WALKING_SPEED_MPS;
                            } else {
                                time = curr.getTravelledTime()+currentTime.until(nextTime, ChronoUnit.SECONDS);
                            }
                            /*if (backpointers.get(curr) == null) {
                                time += 600; //10min
                            }
                            else if (edge.trip().getLine() != backpointers.get(curr).trip().getLine()) {
                                time += 600;
                            }*/
                            double estTotalTime = estTotal / Transport.TRAIN_SPEED_MPS; // best case
                            fringe.add(new SearchQueueItem(neighbour, edge, lenToNeigh, estTotal, time, estTotalTime, nextTime));
                        }
                    }
                }
            }
        }

        return path;
    }

}

/**
 * AStar search uses a priority queue of partial paths
 * that the search is building.
 * Each partial path needs several pieces of information
 * to specify the path to that point, its cost so far, and
 * its estimated total cost
 */

public class SearchQueueItem implements Comparable<SearchQueueItem> {

    private Stop stop;
    private Edge edge;
    private double travelled;
    private double estTotal;

    public SearchQueueItem(Stop stop, Edge edge, double len, double est) {
        this.stop = stop;
        this.edge = edge;
        this.travelled = len;
        this.estTotal = est;
    }

    public Stop getStop() {
        return stop;
    }

    public Edge getEdge() {
        return edge;
    }

    public double getTravelled() {
        return travelled;
    }

    public double getEstTotal() {
        return estTotal;
    }

    // stub needed for file to compile.
    public int compareTo(SearchQueueItem other) {
        return Double.compare(estTotal, other.estTotal);
    }


}

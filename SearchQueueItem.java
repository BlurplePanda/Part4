import java.time.LocalTime;

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
    private double travelledTime;
    private double estTotalTime;
    private LocalTime time;

    public SearchQueueItem(Stop stop, Edge edge, double len, double est, double travelledTime, double estTotalTime, LocalTime time) {
        this.stop = stop;
        this.edge = edge;
        this.travelled = len;
        this.estTotal = est;
        this.travelledTime = travelledTime;
        this.estTotalTime = estTotalTime;
        this.time = time;
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

    public double getTravelledTime() {
        return travelledTime;
    }

    public double getEstTotalTime() {
        return estTotalTime;
    }

    public LocalTime getTime() {
        return time;
    }

    public int compareTo(SearchQueueItem other) {
        return this.estTotalTime == other.estTotalTime ? Double.compare(this.travelledTime, other.travelledTime) : Double.compare(this.estTotalTime, other.estTotalTime);
        //return Double.compare(estTotal, other.estTotal);
    }


}

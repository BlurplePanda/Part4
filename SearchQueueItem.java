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
    private double time;
    private double estTotalTime;

    public SearchQueueItem(Stop stop, Edge edge, double len, double est, double time, double estTotalTime) {
        this.stop = stop;
        this.edge = edge;
        this.travelled = len;
        this.estTotal = est;
        this.time = time;
        this.estTotalTime = estTotalTime;
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

    public double getTime() {
        return time;
    }

    public double getEstTotalTime() {
        return estTotalTime;
    }

    public int compareTo(SearchQueueItem other) {
        return this.estTotalTime == other.estTotalTime ? Double.compare(this.time, other.time) : Double.compare(this.estTotalTime, other.estTotalTime);
        //return Double.compare(estTotal, other.estTotal);
    }


}

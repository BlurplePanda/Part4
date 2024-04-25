import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Structure for holding information about a bus/train/ferry/cablecar line
 */

public class Trip {

    private final Line line;
    private final String id;
    // paired lists with stop id and stop times.  need to make sure they remain in order
    private List<Stop> stops;
    private List<LocalTime> times;
    private Set<LocalDate> dates;



    // constructor used to create and then add stops to the line
    public Trip(String id, Line line, List<Stop> stops, List<LocalTime> times, Set<LocalDate> dates) {
        this.line = line;
        this.id = id;
        this.stops = stops;
        this.times = times;
        this.dates = dates;
    }


    public String getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    // to string
    public String toString() {
        String s = "";
        s += "Line: " + line.getId() + " ("+line.getType()+")\t stops: " + stops.toString() + "\t times: " + times.toString();
        return s;
    }

    /**
     * Return the stops for each stop in the line.
     */
    public List<Stop> getStops() {
        return Collections.unmodifiableList(stops);
    }
    /**
     * Return the times for each stop in the line.
     * @return the list of times in seconds
     */
    public List<LocalTime> getTimes() {
        return  Collections.unmodifiableList(times);
    }

}

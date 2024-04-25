import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Structure for holding information about a bus/train/ferry/cablecar line
 */

public class Line {
    
    private final String lineId;
    private final String name;
    private final String transpType;   // one of "bus", "train", "cablecar", "ferry"



    // constructor used to create and then add stops to the line
    public Line(String lineId, String lineName, String transpType) {
        this.lineId = lineId;
        this.transpType = transpType;
        /*this.stops = new ArrayList<Stop>();
        this.times = new ArrayList<Integer>();*/
        this.name = lineName;
    }


    public String getId() {
        return lineId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return transpType;
    }

    // to string
    public String toString() {
        String s = "";
        s += "Line: " + lineId + " ("+transpType+")";
        return s;
    }


}

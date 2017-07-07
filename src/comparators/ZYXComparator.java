package comparators;

import java.util.Comparator;
import logic.Point;

/**
 * Created by LG-2 on 6/9/2017.
 */
public class ZYXComparator implements Comparator<Point>{
    public int compare(Point o1, Point o2) {
        if(o1.z>o2.z)
            return 1;
        else if(o1.z<o2.z)
            return -1;
        else {
            if(o1.y>o2.y)
                return 1;
            else if(o1.y<o2.y)
                return -1;
            else{
                if(o1.x>o2.x)
                    return 1;
                else if(o1.x<o2.x)
                    return -1;
                else
                    return 0;
            }
        }
    }
}

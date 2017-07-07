package comparators;

import java.util.Comparator;
import logic.Cuboid;

/**
 * Created by LG-2 on 6/9/2017.
 */
public class BottomSurfaceAreaComparator implements Comparator<Cuboid> {
    @Override
    public int compare(Cuboid o1, Cuboid o2) {
        if(o1.length*o1.breadth>o2.length*o2.breadth)
            return -1;
        else if(o1.length*o1.breadth<o2.length*o2.breadth)
            return 1;
        else
            return 0;
    }
}

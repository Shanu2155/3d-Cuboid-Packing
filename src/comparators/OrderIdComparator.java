package comparators;

import java.util.Comparator;
import logic.Cuboid;

/**
 * Created by Shantanu on 22-06-2017.
 */
public class OrderIdComparator implements Comparator<Cuboid>{
    public int compare(Cuboid o1, Cuboid o2) {
        if(o1.id>o2.id){
            return 1;
        }else if(o1.id<o2.id){
            return -1;
        }else {
            return new VolumeComparator().compare(o1,o2);
        }
    }
}

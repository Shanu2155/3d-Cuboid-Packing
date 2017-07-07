package logic;

import java.util.List;

/**
 * Created by Shantanu on 22-06-2017.
 */
public class Orders {
    public String vehicleName;
    public List<Order> orders;

    @Override
    public String toString() {
        return "(Vehicle name="+vehicleName+" Orders="+orders+")";
    }
}

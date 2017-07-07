package logic;

import java.util.List;

/**
 * Created by Shantanu on 22-06-2017.
 */
public class Order {
    public long orderId;
    public String itemName;
    public long itemQuantity;

    @Override
    public String toString() {
        return "(Order id="+orderId+" Item name="+itemName+" Item Quantity="+itemQuantity+")";
    }
}

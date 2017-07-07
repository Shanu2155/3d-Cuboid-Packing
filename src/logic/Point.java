package logic;

import java.util.ArrayList;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class Point {
    public double x,y,z;
    public Point(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public String toString(){
        return "("+x+","+y+","+z+")";
    }
    public boolean equals(Point p){
        return (this.x==p.x && this.y==p.y && this.z==p.z);
    }
}

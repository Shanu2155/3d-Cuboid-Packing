package logic;

import javafx.scene.shape.Box;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class Cuboid {
    public double length,breadth,height;
    public Point topLeftFront,topRightFront,topLeftRear,topRightRear,bottomLeftFront,bottomRightFront,bottomLeftRear,bottomRightRear;
    public float[] color;
    public boolean isFragile,thisSideUp;
    public static Random r;
    public static long idCounter;
    public long id=0;
    public double weight;
    static{
        r=new Random();
        idCounter=1;
    }

    public Cuboid(Cuboid c) {
        this.id=c.id;
        this.length = c.length;
        this.breadth = c.breadth;
        this.height = c.height;
        this.weight = c.weight;
        this.isFragile = c.isFragile;
        this.thisSideUp = c.thisSideUp;
        color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        if(color[0]==0.0f && color[1]==1.0f && color[2]==0.0f){
            color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        }
    }

    public Cuboid(double length,double breadth,double height,double weight){
        this.length=length;
        this.breadth=breadth;
        this.height=height;
        this.weight=weight;
        color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        if(color[0]==0.0f && color[1]==1.0f && color[3]==0.0f){
            color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        }
//        id=idCounter++;
//        if(id%6==0)
//            isFragile=true;
//        else
//            isFragile=false;
//
//        if(id%4==0)
//            thisSideUp=true;
//        else
//            thisSideUp=false;
    }
    public String toString(){
        return "("+id+" L="+length+",B="+breadth+",H="+height+" Weight="+weight+" BLR="+bottomLeftRear+")";
    }

    public Cuboid(long id,double length, double breadth, double height, double weight, boolean isFragile, boolean thisSideUp) {
        this.id=id;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.weight = weight;
        this.isFragile = isFragile;
        this.thisSideUp = thisSideUp;
        color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        if(color[0]==0.0f && color[1]==1.0f && color[2]==0.0f){
            color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        }
    }

    public double getLeft(){ return this.topLeftFront.x; }
    public double getRight(){
        return this.topRightFront.x;
    }
    public double getTop(){
        return this.topLeftFront.y;
    }
    public double getBottom(){
        return this.bottomLeftFront.y;
    }
    public double getFront(){
        return this.topLeftFront.z;
    }
    public double getRear(){
        return this.topLeftRear.z;
    }


    public void setBottomLeftRear(Point bottomLeftRear){
        this.bottomLeftRear=bottomLeftRear;
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.topLeftFront=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y+height,this.bottomLeftFront.z);
        this.topRightFront=new Point(this.topLeftFront.x+length,this.topLeftFront.y,this.topLeftFront.z);
        this.topRightRear=new Point(this.topRightFront.x,this.topRightFront.y,this.topRightFront.z-breadth);
        this.topLeftRear=new Point(this.topRightRear.x-length,this.topRightRear.y,this.topRightRear.z);
    }
    public void setBottomRightRear(Point bottomRightRear){
        this.bottomRightRear=bottomRightRear;
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.topLeftRear=new Point(this.bottomLeftRear.x,this.bottomLeftRear.y+height,this.bottomLeftRear.z);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
    }
    public void setBottomRightFront(Point bottomRightFront){
        this.bottomRightFront=bottomRightFront;
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.topRightRear=new Point(this.bottomRightRear.x,this.bottomRightRear.y+height,this.bottomRightRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
    }
    public void setBottomLeftFront(Point bottomLeftFront){
        this.bottomLeftFront=bottomLeftFront;
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.topRightFront=new Point(this.bottomRightFront.x,this.bottomRightFront.y+height,this.bottomRightFront.z);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
    }
    public void setTopLeftFront(Point topLeftFront){
        this.topLeftFront=topLeftFront;
        this.topRightFront=new Point(this.topLeftFront.x+this.length,this.topLeftFront.y,this.topLeftFront.z);
        this.topRightRear=new Point(this.topRightFront.x,this.topRightFront.y,this.topRightFront.z-this.breadth);
        this.topLeftRear=new Point(this.topRightRear.x-this.length,this.topRightRear.y,this.topRightRear.z);
        this.bottomLeftFront=new Point(this.topLeftFront.x,this.topLeftFront.y-height,this.topLeftFront.z);
        this.bottomRightFront=new Point(this.bottomLeftFront.x+length,this.bottomLeftFront.y,this.bottomLeftFront.z);
        this.bottomRightRear=new Point(this.bottomRightFront.x,this.bottomRightFront.y,this.bottomRightFront.z-breadth);
        this.bottomLeftRear=new Point(this.bottomRightRear.x-length,this.bottomRightRear.y,this.bottomRightRear.z);
    }
    public void setTopLeftRear(Point topLeftRear){
        this.topLeftRear=topLeftRear;
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.bottomLeftFront=new Point(this.topLeftFront.x,this.topLeftFront.y-height,this.topLeftFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
    }
    public void setTopRightRear(Point topRightRear){
        this.topRightRear=topRightRear;
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.bottomLeftRear=new Point(this.topLeftRear.x,this.topLeftRear.y-height,this.topLeftRear.z);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
    }
    public void setTopRightFront(Point topRightFront){
        this.topRightFront=topRightFront;
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.bottomRightRear=new Point(this.topRightRear.x,this.topRightRear.y-height,this.topRightRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
    }
    public void reset(){
        this.topRightFront=new Point(0,0,0);
        this.topLeftFront=new Point(0,0,0);
        this.topLeftRear=new Point(0,0,0);
        this.topRightRear=new Point(0,0,0);
        this.bottomRightRear=new Point(0,0,0);
        this.bottomRightFront=new Point(0,0,0);
        this.bottomLeftFront=new Point(0,0,0);
        this.bottomLeftRear=new Point(0,0,0);
    }
    public boolean intersects(Cuboid c){
        double left1=this.getLeft(),left2=c.getLeft(),right1=this.getRight(),right2=c.getRight(),front1=this.getFront(),front2=c.getFront(),rear1=this.getRear(),rear2=c.getRear(),top1=this.getTop(),top2=c.getTop(),bottom1=this.getBottom(),bottom2=c.getBottom();
        if(right1<=left2 || rear1>=front2 || left1>=right2 || front1<=rear2 || bottom1>=top2 || top1<=bottom2)
            return false;
        return true;
    }
    public boolean intersects(ArrayList<Cuboid> cuboids){
        boolean intersects=false;
        for(Cuboid c:cuboids){
            if(this.intersects(c)){
                intersects=true;
                break;
            }
        }
        return intersects;
    }

    public boolean containsAllCornerPoints(ArrayList<Cuboid> fitCuboids){
        boolean contains=false;
        double a=0,b=0,c=0,d=0;
        for(Cuboid curr:fitCuboids) {
            Rectangle topFace = new Rectangle(curr.length, curr.breadth);
            topFace.setTopLeft(new Point2D(curr.bottomLeftRear.x, curr.bottomLeftRear.z));
            Rectangle bottomFace = new Rectangle(this.length, this.breadth);
            bottomFace.setTopLeft(new Point2D(this.bottomLeftRear.x, this.bottomLeftRear.z));
            if (curr.topLeftFront.y == this.bottomLeftRear.y) {
                if (topFace.contains1(bottomFace.topLeft))
                    a++;
                if (topFace.contains1(bottomFace.topRight))
                    b++;
                if (topFace.contains1(bottomFace.bottomLeft))
                    c++;
                if (topFace.contains1(bottomFace.bottomRight))
                    d++;
                if ((a == 1 || d==1)  || (c==1 || b==1)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    public boolean contains(ArrayList<Point> points){
        boolean contains=false;
        double c=0;
        for(Point p:points){
            if(this.bottomLeftRear.y==p.y) {
                Rectangle rectangle=new Rectangle(this.length,this.breadth);
                rectangle.setTopLeft(new Point2D(this.bottomLeftRear.x,this.bottomLeftRear.z));
                if (rectangle.contains(p)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    public boolean contains1(ArrayList<Cuboid> cuboids){
        boolean contains = false;
        Point2D midPoint=new Point2D((this.bottomLeftRear.x+this.bottomRightRear.x)/2,(this.bottomLeftFront.z+this.bottomLeftRear.z)/2);
        for(Cuboid c:cuboids) {
            Rectangle rectangle = new Rectangle(c.length, c.breadth);
            rectangle.setTopLeft(new Point2D(c.topLeftRear.x, c.topLeftRear.z));
            if (this.bottomLeftRear.y == c.topLeftFront.y) {
                if (rectangle.contains(midPoint)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    public void rotateLengthBreadth(){
        double temp=this.length;
        this.length=this.breadth;
        this.breadth=temp;
    }

    public void rotateLengthHeight(){
        double temp=this.length;
        this.length=this.height;
        this.height=temp;
    }

    public void rotateHeightBreadth(){
        double temp=this.height;
        this.height=this.breadth;
        this.breadth=temp;
    }

    public static void main(String[] args) {
        Cuboid c1=new Cuboid(5,5,5,5);
        Cuboid c2=new Cuboid(5,5,5,5);
        ArrayList<Cuboid> cuboids = new ArrayList<Cuboid>();
        cuboids.add(c2);
        c1.setBottomLeftRear(new Point(5,0,0));
        c2.setBottomLeftRear(new Point(0,0,0));
        System.out.println(c1.intersects(c2));
    }
}

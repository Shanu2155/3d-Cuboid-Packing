package logic;



import java.util.ArrayList;

/**
 * Created by LG-2 on 6/1/2017.
 */
public class Rectangle {
    public double length,breadth;
    public static long counter,id;
    public Point2D bottomLeft,bottomRight,topLeft,topRight;
    public Rectangle(double length, double breadth){
        this.id=counter++;
        this.length=length;
        this.breadth=breadth;
    }
    public Rectangle(Point2D topLeft, double length, double breadth){
        this.length=length;
        this.breadth=breadth;
        this.topLeft=topLeft;
        this.topRight=new Point2D(topLeft.x+length,topLeft.y);
        this.bottomLeft=new Point2D(topLeft.x,topLeft.y+breadth);
        this.bottomRight=new Point2D(topLeft.x+length,topLeft.y+breadth);
    }
    public void setTopLeft(Point2D topLeft){
        this.topLeft=topLeft;
        this.topRight=new Point2D(topLeft.x+length,topLeft.y);
        this.bottomLeft=new Point2D(topLeft.x,topLeft.y+breadth);
        this.bottomRight=new Point2D(topLeft.x+length,topLeft.y+breadth);
    }
    public void setTopRight(Point2D topRight){
        this.topRight=topRight;
        this.topLeft=new Point2D(this.topRight.x-length,this.topRight.y);
        this.bottomLeft=new Point2D(this.topLeft.x,this.topLeft.y+breadth);
        this.bottomRight=new Point2D(this.bottomLeft.x+breadth,this.bottomLeft.y);
    }
    public void setBottomLeft(Point2D bottomLeft){
        this.bottomLeft=bottomLeft;
        this.bottomRight=new Point2D(this.bottomLeft.x+length,this.bottomLeft.y);
        this.topLeft=new Point2D(this.bottomLeft.x,this.bottomLeft.y-breadth);
        this.topRight=new Point2D(this.topLeft.x+length,this.topLeft.y);
    }
    public void setBottomRight(Point2D bottomRight){
        this.bottomRight=bottomRight;
        this.bottomLeft=new Point2D(this.bottomRight.x-length,this.bottomRight.y);
        this.topLeft=new Point2D(this.bottomLeft.x,this.bottomLeft.y-breadth);
        this.topRight=new Point2D(this.topLeft.x+length,this.topLeft.y);
    }
    public void reset(){
        this.topLeft=new Point2D(0,0);
        this.topRight=new Point2D(0,0);
        this.bottomLeft=new Point2D(0,0);
        this.bottomRight=new Point2D(0,0);
    }
    public void rotate(){
        double temp=this.length;
        this.length=this.breadth;
        this.breadth=temp;
    }
    public boolean intersects(Rectangle r){
        java.awt.Rectangle r1=new java.awt.Rectangle((int)Math.ceil(r.topLeft.x),(int)Math.ceil(r.topLeft.y),(int)Math.ceil(r.length),(int)Math.ceil(r.breadth));
        java.awt.Rectangle r2=new java.awt.Rectangle((int)Math.ceil(this.topLeft.x),(int)Math.ceil(this.topLeft.y),(int)Math.ceil(this.length),(int)Math.ceil(this.breadth));
        java.awt.Rectangle inter=r1.intersection(r2);
        return !inter.isEmpty();
    }
    public boolean intersects(ArrayList<Rectangle> rect){
        boolean intersects=false;
        for(Rectangle r:rect){
            if(this.intersects1(r)){
                intersects=true;
                break;
            }
        }
        return intersects;
    }
    public boolean contains(Point p){
        java.awt.Rectangle rectangle=new java.awt.Rectangle((int)this.topLeft.x,(int)this.topLeft.y,(int)this.length,(int)this.breadth);
        return rectangle.contains(p.x,p.z);
    }
    public boolean contains(Point2D p){
        java.awt.Rectangle rectangle=new java.awt.Rectangle((int)this.topLeft.x,(int)this.topLeft.y,(int)this.length,(int)this.breadth);
        return rectangle.contains(p.x,p.y);
    }
    public boolean contains1(Point2D p){
        if(p.x >= this.topLeft.x && p.x <= this.topRight.x && p.y >= this.topLeft.y && p.y <= this.bottomLeft.y)
            return true;
        return false;
    }
    public boolean contains(ArrayList<Point> points){
        boolean contains=false;
        int c=0;
        for(Point p:points){
            if(this.contains(p) && p.z>=this.breadth/2){
                contains=true;
                break;
            }
        }
        return contains;
    }
    public boolean containsAllCornerPoints(Rectangle rectangle){
        return this.contains(rectangle.topLeft) && this.contains(rectangle.topRight) && this.contains(bottomLeft) && this.contains(bottomRight);
    }
    public boolean containsAllCornerPoints(ArrayList<Rectangle> topFaces){
        boolean contains=false;
        int c=0;
        for(Rectangle topFace:topFaces){
            if(topFace.contains(this.topLeft))
                c++;
            if(topFace.contains(this.topRight))
                c++;
            if(topFace.contains(this.bottomLeft))
                c++;
            if(topFace.contains(this.bottomRight))
                c++;
            if(c==4) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    public boolean isOutOfBin(Rectangle bin){
        java.awt.Rectangle r1=new java.awt.Rectangle((int)this.topLeft.x,(int)this.topLeft.y,(int)this.length,(int)this.breadth);
        java.awt.Rectangle r2=new java.awt.Rectangle((int)bin.topLeft.x,(int)bin.topLeft.y,(int)bin.length,(int)bin.breadth);
//        java.awt.Rectangle inter=r1.intersection(r2);
//        int area=new Double(inter.getWidth()).intValue()*new Double(inter.getHeight()).intValue();
//        if(area<=(r1.width*r1.height))
        return !r2.contains(r1);
    }
    public String toString(){
        return "("+id+" L="+length+",B="+breadth+",TL="+topLeft+",TR="+topRight+",BL="+bottomLeft+",BR="+bottomRight+")";
    }

    public boolean intersects1(Rectangle r){
        Rectangle rectangle=new Rectangle(this.topLeft,this.length,this.breadth);
        if((r.bottomRight.x<=rectangle.bottomLeft.x && r.topRight.x<=rectangle.topLeft.x) || (r.topRight.y>=rectangle.bottomRight.y && r.topLeft.y>=rectangle.bottomLeft.y) || (r.topLeft.x>=rectangle.topRight.x && r.bottomLeft.x>=rectangle.bottomRight.x) || (r.bottomLeft.y<=rectangle.topLeft.y && r.bottomRight.y<=rectangle.topRight.y))
            return false;
        return true;
    }

    public static void main(String[] args) {
        Rectangle r1=new Rectangle(new Point2D(300,300),300,300);
        Rectangle r2=new Rectangle(new Point2D(0,0),300,300);
//        r1.setTopLeft(new Point2D(0,0));
        Point2D d=new Point2D(0,299);
        System.out.println(r1.intersects1(r2));


    }
}

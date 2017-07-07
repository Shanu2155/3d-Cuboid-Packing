package logic;

import comparators.ZYXComparator;
import comparators.OrderIdComparator;
import java.util.*;

public class PackingAlgorithm {
    public ArrayList<Point> list,midPoints;
    public ArrayList<Rectangle> fragileRects,topFaces;
    ArrayList<Cuboid> fitCuboids;
    public Cuboid bin;
    public double capacity;
    public void addAllPoints(Cuboid c){
        Point midPoint=new Point((c.topLeftRear.x+c.topRightRear.x)/2,c.topLeftRear.y,(c.topLeftRear.z+c.topLeftFront.z)/2);
        midPoints.add(midPoint);
        Rectangle rectangle=new Rectangle(c.length,c.breadth);
        rectangle.setTopLeft(new Point2D(c.topLeftRear.x,c.topLeftRear.z));
        topFaces.add(rectangle);
        for(double z=c.bottomLeftRear.z;z<=c.bottomLeftFront.z;z++)
            list.add(new Point(c.bottomRightRear.x,c.bottomRightRear.y,z));
        for(double x=c.bottomLeftRear.x;x<c.bottomRightRear.x;x++)
            list.add(new Point(x,c.bottomLeftFront.y,c.bottomLeftFront.z));
        if(!c.isFragile){
            for(double x=c.topLeftRear.x;x<=c.topRightRear.x;x++)
                list.add(new Point(x,c.topLeftRear.y,c.topLeftRear.z));
            for(double z=c.topLeftRear.z;z<c.topRightFront.z;z++)
                list.add(new Point(c.topLeftRear.x,c.topLeftRear.y,z));
        }else {
            fragileRects.add(new Rectangle(new Point2D(c.topLeftRear.x,c.topLeftRear.z),c.length,c.breadth));
            //c.color=new float[]{0f,1f,0f};
        }
        Iterator<Point> listIterator=list.iterator();
        while(listIterator.hasNext()){
            Point p=listIterator.next();
            if(p.z<c.bottomLeftRear.z && p.x>=c.bottomLeftRear.x && p.x<c.bottomRightRear.x && p.y>=c.bottomLeftRear.y && p.y<c.topLeftRear.y){
                listIterator.remove();
            }
        }
        //Collections.sort(list,new YZXComparator());
        //Collections.sort(list,new XZYComparator());
        Collections.sort(list,new ZYXComparator());
    }
    public ArrayList<Cuboid> fitCuboids(double length,double breadth,double height,double weight,ArrayList<Cuboid> cuboids){
        Collections.sort(cuboids,new OrderIdComparator());
        capacity=weight;
        midPoints=new ArrayList<Point>();
        fragileRects=new ArrayList<Rectangle>();
        topFaces=new ArrayList<Rectangle>();
        fitCuboids=new ArrayList<Cuboid>();
        bin=new Cuboid(length, breadth, height, weight);
        Cuboid c=cuboids.get(0);
//        c.setBottomLeftRear(new Point(0.0, 0.0, 0.0));
//        System.out.println("1st Cuboid "+c);
//        if (!c.thisSideUp) {
//            if (c.height > c.length || c.height > c.breadth) {
//                double SALB=c.length*c.breadth,SABH=c.height*c.breadth,SALH=c.length*c.height;
//                if(SABH>SALB && SABH>SALH){
//                    c.rotateLengthHeight();
//                }else if(SALH>SABH && SALH>SALB){
//                    c.rotateHeightBreadth();
//                }
//            }
//        }
        c.setBottomLeftRear(new Point(0.0, 0.0, 0.0));
        System.out.println(c);
        fitCuboids.add(c);
//        System.out.println("FIT:-"+fitCuboids);
        list=new ArrayList<Point>();
        for(double x=c.bottomRightRear.x;x<=length;x++)
            list.add(new Point(x,0.0,0.0));
        for(double z=c.bottomLeftFront.z;z<=breadth;z++)
            list.add(new Point(0.0,0.0,z));
        addAllPoints(c);                                
//        fitCuboids.add(c);
        for(int i=1;i<cuboids.size();i++) {
            c = cuboids.get(i);
            if (capacity >= capacity - c.weight) {
                if (!c.thisSideUp) {
                    if (c.height > c.length || c.height > c.breadth) {
                        double SALB=c.length*c.breadth,SABH=c.height*c.breadth,SALH=c.length*c.height;
                        if(SABH>SALB && SABH>SALH){
                            c.rotateLengthHeight();
                        }else if(SALH>SABH && SALH>SALB){
                            c.rotateHeightBreadth();
                        }
                    }
                }
                for (int j = 0; j < list.size(); j++) {
                    Point p = list.get(j);
                    c.setBottomLeftRear(p);
                    Rectangle bottomFace = new Rectangle(new Point2D(c.bottomLeftRear.x, c.bottomLeftRear.z), c.length, c.breadth);
                    if (!bottomFace.intersects(fragileRects)) {
                        if (c.bottomLeftRear.y > 0.0) {
                            
//                            System.out.println(p + " " + c.contains1(fitCuboids) + " " + c.containsAllCornerPoints(fitCuboids) + " " + !c.intersects(fitCuboids));
                            if (c.contains1(fitCuboids) && c.containsAllCornerPoints(fitCuboids) && !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                fitCuboids.add(c);
                                addAllPoints(c);
                                capacity=capacity-c.weight;
//                                System.out.println("Cuboid:- "+c+" Point:-"+p);
                                break;
                            } else{
                                c.reset();
                                c.rotateLengthBreadth();
                                c.setBottomLeftRear(p);
                                if (c.contains1(fitCuboids) && c.containsAllCornerPoints(fitCuboids) && !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                    fitCuboids.add(c);
                                    addAllPoints(c);
                                    capacity=capacity-c.weight;
//                                    System.out.println("Cuboid:- "+c+" Point:-"+p);
                                    break;
                                }else
                                {
                                    c.reset();
                                    c.rotateLengthBreadth();
                                    c.rotateLengthHeight();
                                    c.setBottomLeftRear(p);
                                    if (c.contains1(fitCuboids) && c.containsAllCornerPoints(fitCuboids) && !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                        fitCuboids.add(c);
                                        addAllPoints(c);
                                        capacity=capacity-c.weight;
//                                    System.out.println("Cuboid:- "+c+" Point:-"+p);
                                        break;
                                    }else
                                    {
                                        c.reset();
                                        c.rotateLengthHeight();
                                        c.rotateHeightBreadth();
                                        c.setBottomLeftRear(p);
                                        if (c.contains1(fitCuboids) && c.containsAllCornerPoints(fitCuboids) && !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                            fitCuboids.add(c);
                                            addAllPoints(c);
                                            capacity=capacity-c.weight;
//                                    System.out.println("Cuboid:- "+c+" Point:-"+p);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                fitCuboids.add(c);
                                addAllPoints(c);
                                capacity=capacity-c.weight;
//                                System.out.println("Cuboid:- "+c+" Point:-"+p);
                                break;
                            } else {
                                c.reset();
                                c.rotateLengthBreadth();
                                c.setBottomLeftRear(p);
                                if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                    fitCuboids.add(c);
                                    addAllPoints(c);
                                    capacity=capacity-c.weight;
//                                    System.out.println("Cuboid:- "+c+" Point:-"+p);
                                    break;
                                }else
                                {
                                    c.reset();
                                    c.rotateLengthBreadth();
                                    c.rotateHeightBreadth();
                                    c.setBottomLeftRear(p);
                                    if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                        fitCuboids.add(c);
                                        addAllPoints(c);
                                        capacity=capacity-c.weight;
    //                                    System.out.println("Cuboid:- "+c+" Point:-"+p);
                                        break;
                                    }else
                                    {
                                        c.reset();
                                    c.rotateHeightBreadth();
                                    c.rotateLengthHeight();
                                    c.setBottomLeftRear(p);
                                    if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                        fitCuboids.add(c);
                                        addAllPoints(c);
                                        capacity=capacity-c.weight;
//                                      System.out.println("Cuboid:- "+c+" Point:-"+p);
                                        break;
                                }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        
        ArrayList<Cuboid> fitCuboids1=new ArrayList<>();
        c=new Cuboid(length,breadth,height,weight);
        c.setBottomLeftRear(new Point(0.0,0.0,0.0));
        fitCuboids1.add(c);
        fitCuboids1.addAll(fitCuboids);
//        System.out.println(fitCuboids);
        return fitCuboids1;
    }
    
    public ArrayList<Cuboid> fitCuboids(Cuboid bin,ArrayList<Cuboid> cuboids){
        this.bin=new Cuboid(bin);
        capacity=bin.weight;
        midPoints=new ArrayList<>();
        fragileRects=new ArrayList<>();
        topFaces=new ArrayList<>();
        fitCuboids=new ArrayList<>();
        list=new ArrayList<>();
        int count=0;
        //Check all the conditions for the first cuboid to be placed
        while(fitCuboids.isEmpty() && count<cuboids.size()){
            Cuboid c=cuboids.get(count++);
            Point point=new Point(0,0,0);
            c.setBottomLeftRear(point);
            //Check id dimensions of cuboid are less than that of bin
            if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                fitCuboids.add(c);
                for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                    list.add(new Point(x,0.0,0.0));
                for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                    list.add(new Point(0.0,0.0,z));
                addAllPoints(c); 
                break;
            }
            //Interchange length and breadth and check again
            c.rotateLengthBreadth();
            c.setBottomLeftRear(point);
            if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                fitCuboids.add(c);
                for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                    list.add(new Point(x,0.0,0.0));
                for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                    list.add(new Point(0.0,0.0,z));
                addAllPoints(c); 
                break;
            }
            //Check if the object is height rotatable
            if(!c.thisSideUp){
                //Interchange height and breadth and check again
                c.rotateHeightBreadth();
                c.setBottomLeftRear(point);
                if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                    fitCuboids.add(c);
                    for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                        list.add(new Point(x,0.0,0.0));
                    for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                        list.add(new Point(0.0,0.0,z));
                    addAllPoints(c); 
                    break;
                }
                c.rotateHeightBreadth();
                //Interchange length and breadth and check again
                c.rotateLengthBreadth();
                c.setBottomLeftRear(point);
                if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                    fitCuboids.add(c);
                    for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                        list.add(new Point(x,0.0,0.0));
                    for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                        list.add(new Point(0.0,0.0,z));
                    addAllPoints(c); 
                    break;
                }
                c.rotateLengthHeight();
                c.setBottomLeftRear(point);
                if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                    fitCuboids.add(c);
                    for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                        list.add(new Point(x,0.0,0.0));
                    for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                        list.add(new Point(0.0,0.0,z));
                    addAllPoints(c); 
                    break;
                }
                c.rotateLengthHeight();
                c.rotateLengthBreadth();
                c.setBottomLeftRear(point);
                if (c.length <= bin.length && c.height <= bin.height && c.breadth <= bin.breadth ) {
                    fitCuboids.add(c);
                    for(double x=c.bottomRightRear.x;x<=bin.length;x++)
                        list.add(new Point(x,0.0,0.0));
                    for(double z=c.bottomLeftFront.z;z<=bin.breadth;z++)
                        list.add(new Point(0.0,0.0,z));
                    addAllPoints(c); 
                    break;
                }
            }
        }
        Cuboid c;
        for(int i=count;i<cuboids.size();i++) {
            c = cuboids.get(i);
            if (capacity >= capacity - c.weight) {
                c=orientation(c);
                if(!c.bottomLeftRear.equals(new Point(0, 0, 0))){
                    fitCuboids.add(c);
                    addAllPoints(c);
                    capacity=capacity-c.weight;
                }
            }
        }
        for(Cuboid c1:fitCuboids){
            System.out.println(c1);
        }
        ArrayList<Cuboid> fitCuboids1=new ArrayList<>();
        c=new Cuboid(bin.length,bin.breadth,bin.height,bin.weight);
        c.setBottomLeftRear(new Point(0.0,0.0,0.0));
        fitCuboids1.add(c);
        fitCuboids1.addAll(fitCuboids);
        return fitCuboids1;
    }
    
    public Cuboid orientation(Cuboid c){
        ArrayList<Cuboid> cuboids=new ArrayList<>();
        boolean flag1=true,flag2=true,flag3=true,flag4=true,flag5=true,flag6=true;
        for (int j = 0; j < list.size(); j++) {
            if (!flag1 && !flag2 && !flag3 && !flag4 && !flag5 && !flag6)
                break;
            Point p = list.get(j);
            Cuboid c1=new Cuboid(c);
            c1.setBottomLeftRear(p);
            Rectangle bottomFace = new Rectangle(new Point2D(c1.bottomLeftRear.x, c1.bottomLeftRear.z), c1.length, c1.breadth);
            if (!bottomFace.intersects(fragileRects)) {
                if ( c1.bottomLeftRear.y > 0.0){ 
                    if (flag1 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag1=false;
                    } 
                    c1=new Cuboid(c);
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag2 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {                        cuboids.add(c1);
                        cuboids.add(c1);
                        flag2=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateLengthHeight();
                    c1.setBottomLeftRear(p);
                    if (flag3 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {                        cuboids.add(c1);
                        cuboids.add(c1);
                        flag3=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateLengthHeight();
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag4 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {                        cuboids.add(c1);
                        cuboids.add(c1);
                        flag4=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateHeightBreadth();
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag5 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {                        cuboids.add(c1);
                        cuboids.add(c1);
                        flag5=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateHeightBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag6 && c1.contains1(fitCuboids) && c1.containsAllCornerPoints(fitCuboids) && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {                        cuboids.add(c1);
                        cuboids.add(c1);
                        flag6=false;
                    }
                } else {
                   if (flag1 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag1=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag2 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag2=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateLengthHeight();
                    c1.setBottomLeftRear(p);
                    if (flag3 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag3=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateLengthHeight();
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag4 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag4=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateHeightBreadth();
                    c1.rotateLengthBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag5 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag5=false;
                    }
                    c1=new Cuboid(c);
                    c1.rotateHeightBreadth();
                    c1.setBottomLeftRear(p);
                    if (flag6 && !c1.intersects(fitCuboids) && p.x + c1.length <= bin.length && p.x + c1.length >= 0 && p.y + c1.height <= bin.height && p.y + c1.height >= 0 && p.z + c1.breadth <= bin.breadth && p.z + c1.breadth >= 0) {
                        cuboids.add(c1);
                        flag6=false;
                    }
                }
            }
        }
        if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6)
            c.setBottomLeftRear(new Point(0,0,0)); 
        double zmin=Double.MAX_VALUE;
        for(Cuboid c2:cuboids){
            if(c2.bottomLeftRear.z<zmin){
                zmin=c2.bottomLeftRear.z;
            }
        }
        double bmin=Double.MAX_VALUE;
        for(Cuboid c2:cuboids){
            if(c2.breadth<bmin){
                bmin=c2.breadth;
            }
        }
        for(Cuboid c2:cuboids){
            if(c2.bottomLeftRear.z==zmin){
                return c2;
            }
        }
        return c;
    }
    
    public static void main(String[] args) {
//        Random r=new Random();//This is for random generation of cuboids
//        ArrayList<Cuboid> cuboids=new ArrayList<>();
//        for(int i=0;i<50;i++)
//            cuboids.add(new Cuboid((r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20,((r.nextInt(5)+1)*20)*((r.nextInt(10)+1)*20)));
//        PackingAlgorithm packing=new PackingAlgorithm();
//         System.out.println(packing.fitCuboids(500, 500, 500, 5,cuboids));
    }
}

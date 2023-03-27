package Examples;

import java.util.Date;

/**
 * @Auther:JW
 * @Date:2023-02-03 - 02 - 03 - 10:39 p.m.
 * @Description:Examples
 * @Version:1.0
 */
public class House  implements Cloneable,Comparable<House>{
    private int id;
    private double area;
    private Date whenBuilt;

    public House(int id, double area) {
        this.id = id;
        this.area = area;
        whenBuilt = new Date();
    }

    public int getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public Date getWhenBuilt() {
        return whenBuilt;
    }

    @Override
    /** Override the protected clone method defined in the Object class, and strengthen its accessibility */
    //native => shallow copy , defined in Object Class, so Interface Cloneable is empty
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override // Implement the compareTo method defined in Comparable
    public int compareTo(House o) {
        if (area > o.area)
            return 1;
        else if (area < o.area)
            return -1;
        else
            return 0;
    }

}

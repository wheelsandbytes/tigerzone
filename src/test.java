import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String args[]){
		
		RegionMap r = new RegionMap();
		System.out.println(r);
		Lake l4 = new Lake(0, 1, 0, false);
		Lake l3 = new Lake(0, 1, 0, false);
		r.mergeRegion(l4, l3);
		
		Lake l1 = new Lake(0, 1, 1, false);
		Lake l2 = new Lake(0, 1, 1, false);
		Jungle f = new Jungle(l1, l2);
		Jungle j = new Jungle();
		r.mergeRegion(f, j);
		System.out.println(r);
		System.out.println(r.getJungles().get(0).adjLakes.get(0).toString());
		System.out.println(r.getJungles().get(0).adjLakes.get(1).toString());
		System.out.println(f);
		System.out.println(j);
		Jungle f2 = new Jungle();
		r.mergeRegion(f, f2);
		r.mergeRegion(l1, l2);
		System.out.println(r);
		System.out.println(r.getJungles().get(0).adjLakes.get(0).toString());
		System.out.println(r.getJungles().get(0).adjLakes.get(1).toString());
		System.out.println(f);
		System.out.println(j);
		System.out.println(f2);
		Jungle a = new Jungle();
		Jungle b = new Jungle();
		r.mergeRegion(a, b);
		r.mergeRegion(l3, l1);
		System.out.println(r);
		r.mergeRegion(f2, a);
		System.out.println(r + "\n");
		
		Edge e1 = new Edge(f);
		Edge e2 = new Edge(f,j,f2);
		System.out.println(e1.equals(e2));
		
		Trail l = new Trail();
		e2 = new Edge(f,j,l);
		System.out.println(e1.equals(e2));
		
	}
	
}
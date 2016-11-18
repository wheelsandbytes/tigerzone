
public class test {
	public static void main(String args[]){
		RegionMap r = new RegionMap();
		System.out.println(r);
		Road f = new Road();
		Road j = new Road();
		r.mergeRegion(f, j);
		System.out.println(r);
		System.out.println(f);
		System.out.println(j);
		Road f2 = new Road();
		r.mergeRegion(f, f2);
		System.out.println(r);
		System.out.println(f);
		System.out.println(j);
		System.out.println(f2);
		Road a = new Road();
		Road b = new Road();
		r.mergeRegion(a, b);
		System.out.println(r);
		r.mergeRegion(f2, a);
		System.out.println(r);
	}
}
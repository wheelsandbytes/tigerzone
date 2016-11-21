
public class test {
	public static void main(String args[]){
		RegionMap r = new RegionMap();
		System.out.println(r);
		Trail f = new Trail();
		Trail j = new Trail();
		r.mergeRegion(f, j);
		System.out.println(r);
		System.out.println(f);
		System.out.println(j);
		Trail f2 = new Trail();
		r.mergeRegion(f, f2);
		System.out.println(r);
		System.out.println(f);
		System.out.println(j);
		System.out.println(f2);
		Trail a = new Trail();
		Trail b = new Trail();
		r.mergeRegion(a, b);
		System.out.println(r);
		r.mergeRegion(f2, a);
		System.out.println(r);
	}
}
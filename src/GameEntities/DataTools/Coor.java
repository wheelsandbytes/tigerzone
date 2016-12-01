package GameEntities.DataTools;

public class Coor {
	public int x, y;
	
	public Coor(int x, int y){
		this.x = x;
		this.y = y;
	}

	public String toString(){
		return "x: " + x + " " + "y: " + y;
	}
	
	public boolean equals(Coor c){
		return c.x == x && c.y == y;
	}
	
	public boolean equals(int x, int y){
		return this.x == x && this.y == y;
	}
}

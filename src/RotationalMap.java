import java.util.HashMap;
import java.util.Map;

public class RotationalMap {
	//Rotation Mapping (Works like an Adaptor between server and our internal system)
	private Map<Integer, Integer> zoneToIndex = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> indexToZone = new HashMap<Integer, Integer>();
	
	public RotationalMap(){
	
//We actually only have to have one that we can reference for everything since we have to search "in order"
//So in the search we look through RegionArray[zoneToIndex.get((rot*90)+i)]
//Saving the greatest score as a region and a number (i)
//We can place the meeple on the region and pass the number to the server
	
		zoneToIndex.put(0+1, 0);
		zoneToIndex.put(0+2, 1);
		zoneToIndex.put(0+3, 2);
		zoneToIndex.put(0+4, 3);
		zoneToIndex.put(0+5, 4);
		zoneToIndex.put(0+6, 5);
		zoneToIndex.put(0+7, 6);
		zoneToIndex.put(0+8, 7);
		zoneToIndex.put(0+9, 8);

		zoneToIndex.put(90+1, 2);
		zoneToIndex.put(90+2, 5);
		zoneToIndex.put(90+3, 8);
		zoneToIndex.put(90+4, 1);
		zoneToIndex.put(90+5, 4);
		zoneToIndex.put(90+6, 7);
		zoneToIndex.put(90+7, 0);
		zoneToIndex.put(90+8, 3);
		zoneToIndex.put(90+9, 6);
		
		zoneToIndex.put(180+1, 8);
		zoneToIndex.put(180+2, 7);
		zoneToIndex.put(180+3, 6);
		zoneToIndex.put(180+4, 5);
		zoneToIndex.put(180+5, 4);
		zoneToIndex.put(180+6, 3);
		zoneToIndex.put(180+7, 2);
		zoneToIndex.put(180+8, 1);
		zoneToIndex.put(180+9, 0);

		zoneToIndex.put(270+1, 6);
		zoneToIndex.put(270+2, 3);
		zoneToIndex.put(270+3, 0);
		zoneToIndex.put(270+4, 7);
		zoneToIndex.put(270+5, 4);
		zoneToIndex.put(270+6, 1);
		zoneToIndex.put(270+7, 8);
		zoneToIndex.put(270+8, 5);
		zoneToIndex.put(270+9, 2);
		
		indexToZone.put(0+0, 1);
		indexToZone.put(0+1, 2);
		indexToZone.put(0+2, 3);
		indexToZone.put(0+3, 4);
		indexToZone.put(0+4, 5);
		indexToZone.put(0+5, 6);
		indexToZone.put(0+6, 7);
		indexToZone.put(0+7, 8);
		indexToZone.put(0+8, 9);

		indexToZone.put(90+0, 7);
		indexToZone.put(90+1, 4);
		indexToZone.put(90+2, 1);
		indexToZone.put(90+3, 8);
		indexToZone.put(90+4, 5);
		indexToZone.put(90+5, 2);
		indexToZone.put(90+6, 9);
		indexToZone.put(90+7, 6);
		indexToZone.put(90+8, 3);
		
		indexToZone.put(180+0, 9);
		indexToZone.put(180+1, 8);
		indexToZone.put(180+2, 7);
		indexToZone.put(180+3, 6);
		indexToZone.put(180+4, 5);
		indexToZone.put(180+5, 4);
		indexToZone.put(180+6, 3);
		indexToZone.put(180+7, 2);
		indexToZone.put(180+8, 1);

		indexToZone.put(270+0, 3);
		indexToZone.put(270+1, 6);
		indexToZone.put(270+2, 9);
		indexToZone.put(270+3, 2);
		indexToZone.put(270+4, 5);
		indexToZone.put(270+5, 8);
		indexToZone.put(270+6, 1);
		indexToZone.put(270+7, 4);
		indexToZone.put(270+8, 7);

	}
	
	public int getZone(int rotation, int z){
		return zoneToIndex.get((rotation*90)+z);
	}
	
	public int getIndex(int rotation, int z){
		return indexToZone.get((rotation*90)+z);
	}
}

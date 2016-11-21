/*-----------------------------------------------------------------------------------------------
|	TileFactory Class:
|  	Helps create different types of Tiles (For more info on possible types check out GameInfo class)
-------------------------------------------------------------------------------------------------*/

public class TileFactory implements Factory<Tile>{

	@Override
	public Tile create(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//tile factory, don't know how sever will pass us the order of the deck. most likely this code istrash
    //type of tile represented by string of 5 chars. first char is type of the northen edge, second char is eastern edge ect.
	//t stands for trail, l stands for lake and j stands for jungle
	//5th char in string either says if it is a den or the type of prey on the tile(dens do not have prey).
	//d means it's a den, e there is a deer, o means there is a boar, u for buffalo and n for no prey
//	void factory(String tileType){
//		den = false;
//		prey = 0;
//		for(int i = 0; i<= 3; i++){
//			if(tileType.CharAt(i) == 't'){
//				/*create new edge*/
//				tileEdges.at(i) = /* add correct region to first position in edge*/;
//				tileEdges.at(i) = /* add second correct region to edge */;
//				tileEdges.at(i) = /* add third correct region to edge */;
//			}
//			if(tileType.CharAt(i) == 'l'){
//				/*create new edge*/
//				tileEdges.at(i) = /* add correct region to first position in edge*/;
//				tileEdges.at(i) = /* add second correct region to edge */;
//				tileEdges.at(i) = /* add third correct region to edge */;
//			}
//			if(tileType.CharAt(i) == 'j'){
//				/*create new edge*/
//				tileEdges.at(i) = /* add correct region to first position in edge*/;
//				tileEdges.at(i) = /* add second correct region to edge */;
//				tileEdges.at(i) = /* add third correct region to edge */;
//			}
//		}
//		if(tileType.CharAt(4) == 'd'){
//			den = true;
//		}
//		if(tileType.CharAt(4) == 'e'){
//			prey = 1;
//		}
//		if(tileType.CharAt(4) == 'o'){
//			prey = 2;
//		}
//		if(tileType.CharAt(4) == 'u'){
//			prey = 3;
//		}
//	
//	}
}

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/*------------------------------------------------------------------------------
|	Deck Class:
|  	Contains the Common Deck with already shuffled Tiles
------------------------------------------------------------------------------*/

public class Deck {
	private int curr = 0;
	private boolean generated = false;
	private Random rg = new Random();
	private TileFactory tf = new TileFactory();
	private List<Tile> deck = new LinkedList<Tile>();
	
	//Ability to set up the Deck after server generates it
	public void setStandardDeck(List<Tile> deck){
		this.deck = deck;
		generated = true;
	}
	
	public Deck(){
	}
	
	public Deck(List<Tile> deck){
		this.deck = deck;
		generated = true;
	}
	
	//Generates a shuffled deck for testing
	public void generateDeck(){
		String type;
		//Randomize MAX_TILES number of Tile Objects
		for(int i=0; i<GameInfo.MAX_TILES; i++){
			type = GameInfo.allowedTiles[rg.nextInt(GameInfo.MAX_TYPES)];
			deck.add(tf.create(type));
		}
		generated = true;
	}
	
	//Returns the current Tile in play
	public Tile getCurrent(){
		if(generated)
			return deck.get(curr);
		throw new RuntimeException("Deck must be generated before using it");
	}
	
	//Goes to the next Tile
	public void next(){
		if(generated)
			curr++;
	}
	
	//Checks for the end of deck
	public boolean isDone(){
		if(generated)
			return curr >= deck.size();
		return true;
	}
}

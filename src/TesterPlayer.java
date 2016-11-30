import java.util.Scanner;

/*------------------------------------------------------------------------------
|	TesterPlayer Class:
|  	This kind of player is designed to wait for user input (or use test Data)
|	in order to decide a Move or a Meeple placement
------------------------------------------------------------------------------*/

public class TesterPlayer extends Player{
	Scanner sc = new Scanner(System.in);
	
	//Public constructors
	public TesterPlayer(){};
	public TesterPlayer(Board mainBoard, String name, Deck deck){
		super(mainBoard, name, deck);
	}
	
	
	//Tester Player gets input from console input
	@Override
	public Move decideMove() {
		Coor c = new Coor(sc.nextInt(), sc.nextInt());
		Tile t = globalDeck.getCurrent();
		t.setRot(sc.nextInt()/90);
		return new Move(c, t.getRotation(), t);
	}
	
	
	//Decide MeeplePlacement based on console input
	@Override
	public MeeplePlacement decideMeeple() {
		char type = sc.next().charAt(0);
		
		if(type == 't')
			type = GameInfo.TIGER;
		else if(type == 'c')
			type = GameInfo.CROCODILE;
		
		return new MeeplePlacement(type, sc.nextInt());
	}
}

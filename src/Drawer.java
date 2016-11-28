import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.shape.DrawMode;


/*-----------------------------------------------------------------------------------------------	
|	Drawer Class:											
|	In charge of Drawing a GUI representation of the Game Board  										
 --------------------------------------------------------------------------------------------------*/

public class Drawer {
	private Deck deck;
	private Graph board;	
	private Frame frame;
	private Screen screen;
	private final int TILE_WIDTH = 80, TILE_HEIGHT = 80, MENU_WIDTH = 100, ERROR_SPACE = 1, MEEPLE_SIZE = 10;
	private final int LIMIT_LEFT = -5, LIMIT_RIGHT = 5, LIMIT_UP = 4, LIMIT_DOWN = -4;
	private final int BOARD_WIDTH = ((-1)*LIMIT_LEFT + LIMIT_RIGHT + 1)*TILE_WIDTH, BOARD_HEIGHT = ((-1)*LIMIT_DOWN + LIMIT_UP + 1)*TILE_HEIGHT;
	private Map<String, BufferedImage> resources = new HashMap<String, BufferedImage>();
	private BufferedImage tiger;
	private BufferedImage crocodile;
	private Map<Integer, Coor> map;
	
	//Inner Classes
	//-------------------------------------------------------------------------------
	private class Screen extends JPanel{
		Coor c;
		AffineTransform tx;
		AffineTransformOp op;
		private static final long serialVersionUID = 1L;
		
		//Constructor
		public Screen(){
			repaint();
		}
		
		//Iterates through the graph and draws Tiles
		public void paint(Graphics g){
			for(int y=LIMIT_DOWN; y<=LIMIT_UP; y++){
				for(int x=LIMIT_LEFT; x<=LIMIT_RIGHT; x++){
					c = getCoordinates(x, y);
					drawTile(c.x, c.y, board.locate(x, y), g);
					if(board.locate(x, y) == null)
						g.drawString("("+x+","+y+")", c.x+ERROR_SPACE*2, c.y+ERROR_SPACE*12);
				}
			}
			//Draws next Tile and Grid
			drawNext(g);
			drawGrid(g);
		}
		
		//If Tile is not null, draw it!
		public void drawTile(int x, int y, Tile t, Graphics g){
			if(t != null){
				g.drawImage(rotate(resources.get(t.getType()), t.getAngle()), x, y, TILE_WIDTH, TILE_HEIGHT, null);
				if(t.hasCrocodile){
					g.drawImage(crocodile, x+10, y+10, MEEPLE_SIZE, MEEPLE_SIZE, null);
					System.out.println(t.hasCrocodile);
				}
				for(int i=0; i<GameInfo.MAX_ZONES; i++){
					if(!t.getRegionAt(i).getMeeples().isEmpty() && t.getRegionAt(i).getMeeples().get(0) != null){
						drawTiger(x, y, GameInfo.TIGERZONE.getZone(t.getRotation(), i+1), tiger, g);
						break;
					}
						
				}
			}	
		}
		
		//Draws next Tile
		public void drawNext(Graphics g){
			drawTile(BOARD_WIDTH+(MENU_WIDTH-TILE_WIDTH)/2, 10, deck.getCurrent(), g);
		}
		
		//Draws Grid System
		public void drawGrid(Graphics g){
			//Vertical Lines
			for(int i=0; i<=BOARD_WIDTH; i+=TILE_WIDTH){
				g.drawLine(i, 0, i, BOARD_HEIGHT);
			}
			//Horizontal Lines
			for(int i=0; i<=BOARD_HEIGHT; i+=TILE_HEIGHT){
				g.drawLine(0, i, BOARD_WIDTH, i);
			}
		}
		
		//Draws coordinates on left top corner of every slot
		
		public void drawTiger(int x, int y, int pos, BufferedImage image, Graphics g){
			g.drawImage(image, x+map.get(pos).x, y+map.get(pos).y, MEEPLE_SIZE, MEEPLE_SIZE, null);
		}
		
		//Rotate Tile
		public BufferedImage rotate(BufferedImage image, int angle){
			double rotationRequired = Math.toRadians ((-1)*angle);
			double locationX = image.getWidth() / 2;
			double locationY = image.getHeight() / 2;
			tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			
			return op.filter(image, null);
		}
		
		//Refresh/Repaint
		public void refresh(){
			revalidate();
			repaint();
		}
	}
	
	private class Frame extends JFrame{
		private static final long serialVersionUID = 1L;
		
		public Frame(){
			setTitle("TigerZone-TM");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(BOARD_WIDTH + MENU_WIDTH, BOARD_HEIGHT);
			setResizable(false);
		}
		
		public void init(Screen s){
			setLocationRelativeTo(null);
			setLayout(new GridLayout(1, 1, 0, 0));
			add(s);
			setVisible(true);
		}
	};
	
	
	
	//Public Methods
	//------------------------------------------------------------------------------------
	public Drawer(Graph board, Deck deck){
		try{
			tiger = ImageIO.read(getClass().getResourceAsStream("/tiger.PNG"));
			crocodile = ImageIO.read(getClass().getResourceAsStream("/crocodile.PNG"));
		}
		catch(Exception e){
			e.printStackTrace();
			tiger = null;
			crocodile = null;
		}
		loadResources();
		this.board = board;
		this.deck = deck;
		screen = new Screen();
		frame = new Frame();
		frame.init(screen);
		map = new HashMap<Integer, Coor>();
		map.put(0, new Coor(5,5));
		map.put(1, new Coor(TILE_WIDTH/2-MEEPLE_SIZE/2,5));
		map.put(2, new Coor(TILE_WIDTH-3*MEEPLE_SIZE/2,5));
		map.put(3, new Coor(5,TILE_HEIGHT/2-MEEPLE_SIZE/2));
		map.put(4, new Coor(TILE_WIDTH/2-MEEPLE_SIZE/2,TILE_HEIGHT/2-MEEPLE_SIZE/2));
		map.put(5, new Coor(TILE_WIDTH-3*MEEPLE_SIZE/2,TILE_HEIGHT/2-MEEPLE_SIZE/2));
		map.put(6, new Coor(5,TILE_HEIGHT-3*MEEPLE_SIZE));
		map.put(7, new Coor(TILE_WIDTH/2-MEEPLE_SIZE/2,TILE_HEIGHT-3*MEEPLE_SIZE));
		map.put(8, new Coor(TILE_WIDTH-3*MEEPLE_SIZE,TILE_HEIGHT-3*MEEPLE_SIZE));
		
	}
	
	
	//This Mapping is Beats!
	public Coor getCoordinates(int x, int y){
		//do some mapping
		int newX = Math.abs(LIMIT_LEFT)*TILE_WIDTH + x*TILE_WIDTH;
		int newY = LIMIT_UP*TILE_WIDTH + (-1)*y*TILE_HEIGHT;
		return new Coor(newX+ERROR_SPACE, newY+ERROR_SPACE);
	}
	
	
	public void refresh(){
		screen.refresh();
	}
	
	
	//Loads all the Images into the HashMap
	private void loadResources(){
		BufferedImage image;
		for(int i=0; i<GameInfo.MAX_TYPES; i++){
			try{
				image = ImageIO.read(getClass().getResourceAsStream("/"+GameInfo.allowedTiles[i]+".PNG"));
			}
			catch(Exception e){
				e.printStackTrace();
				image = null;
			}
			resources.put(GameInfo.allowedTiles[i], image);
		}
	}
}

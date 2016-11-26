import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*-----------------------------------------------------------------------------------------------	
|	Drawer Class:											
|	In charge of Drawing a GUI representation of the Game Board  										
 --------------------------------------------------------------------------------------------------*/

public class Drawer {
	private Graph board;
	private String[] images;
	private Map<String, BufferedImage> resources;
	private Frame frame;
	private Screen screen;
	private final int TILE_WIDTH = 80, TILE_HEIGHT = 80;
	private final int LIMIT_LEFT = -10, LIMIT_RIGHT = 10, LIMIT_UP = 10, LIMIT_DOWN = -10;
	
	
	//Inner Classes
	//-------------------------------------------------------------------------------
	private class Screen extends JPanel{
		Coor c;
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
				}
			}
		}
		
		//If Tile is not null, draw it!
		public void drawTile(int x, int y, Tile t, Graphics g){
			if(t != null)
				g.drawImage(resources.get(t.getType()), x, y, TILE_WIDTH, TILE_HEIGHT, null);
		}
	}
	
	private class Frame extends JFrame{
		private static final long serialVersionUID = 1L;
		
		public Frame(){
			setTitle("TigerZone-TM");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(GameInfo.WIDTH, GameInfo.HEIGHT);
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
	public Drawer(Graph board){
		this.board = board;
		frame = new Frame();
		frame.init(screen);
	}
	
	public Drawer(){
		board = new MatrixGraph(null);
		frame = new Frame();
		frame.init(screen);
	}
	
	public void loadResources(){
		//Implementation goes here....
	}
	
	//Creates a new Frame to redraw the Board
	public void update(){
		frame.init(new Screen());
	}
	
	public Coor getCoordinates(int x, int y){
		//do some mapping
		return new Coor(x, y);
	}
}

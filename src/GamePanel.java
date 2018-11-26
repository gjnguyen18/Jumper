//Panel

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel { 
	 private Image background;	
	 private ArrayList<Platform> platforms;
	 private ArrayList<Box> boxes;
	 private ArrayList<Unit> units;
	 private ArrayList<Platform> base;
	 private ArrayList<Platform> startBase;
	 private ArrayList<Platform> placement;
	 private Player player;	
	 private Projectile projectile;
	 private Rectangle test;
	 private Title title;
	 private Title end;
	 private Title restart;
	 private Title blue;
	 private Clock clock;
   
	public GamePanel(String path) {
  		this(new ImageIcon(path).getImage());
      platforms = new ArrayList<Platform>();
      boxes = new ArrayList<Box>();
      units = new ArrayList<Unit>();
      base = new ArrayList<Platform>();
      startBase = new ArrayList<Platform>();
      placement = new ArrayList<Platform>();
      player = new Player("GameImages\\player.PNG", 600, 1100);
      projectile = new Projectile("GameImages\\projectile.PNG", -300, 1100);
      title = new Title("GameImages\\title.PNG", 200, 250);
      end = new Title("GameImages\\end.PNG", 200, 250);
      blue = new Title("GameImages\\blue.PNG", 0, -3000);
      restart = new Title("GameImages\\replay.PNG", 0, -3000);
      clock = new Clock(2000, 30);
      test = player.hitbox();
  	}   
   
  	public GamePanel(Image img)	{
    	background = img;
    	Dimension size = new Dimension(1200, 700);//img.getWidth(null), img.getHeight(null));	
     	setPreferredSize(size);
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setSize(size);
  	}

  	public void paintComponent(Graphics g) {	
    	g.drawImage(background, 0, 0, null); 
      for(Unit img:units) {
         g.drawImage(img.getImage(), (int)(img.getX()*.5), (int)(img.getY()*.5), (int)(img.getW()*.5), (int)(img.getH()*.5), null);
      }
      for(Platform img:base) {
         g.drawImage(img.getImage(), (int)(img.getX()*.5), (int)(img.getY()*.5), (int)(img.getW()*.5), (int)(img.getH()*.5), null);
      }
      for(Platform img:startBase) {
         g.drawImage(img.getImage(), (int)(img.getX()*.5), (int)(img.getY()*.5), (int)(img.getW()*.5), (int)(img.getH()*.5), null);
      }      
      for(Platform img:placement) {
         g.drawImage(img.getImage(), (int)(img.getX()*.5), (int)(img.getY()*.5), (int)(img.getW()*.5), (int)(img.getH()*.5), null);
      }
      
      for(int i = 0; i<6; i++) {  
         g.drawImage(clock.getImage(i), (int)(clock.getX()+50*i), (int)(clock.getY()), null);
      }
      
      g.drawImage(projectile.getImage(), (int)(projectile.getX()*.5), (int)(projectile.getY()*.5), 
      		 (int)(projectile.getW()*.5), (int)(projectile.getH()*.5),null);
      g.drawImage(player.getImage(), (int)(player.getX()*.5), (int)(player.getY()*.5),
      		 (int)(player.getW()*.5), (int)(player.getH()*.5),null);
      g.drawImage(title.getImage(), (int)(title.getX()*.5), (int)(title.getY()*.5), 
      		 (int)(title.getW()*.5), (int)(title.getH()*.5),null);
      g.drawImage(end.getImage(), (int)(end.getX()*.5), (int)(end.getY()*.5), 
      		 (int)(end.getW()*.5), (int)(end.getH()*.5),null);
      g.drawImage(blue.getImage(), (int)(blue.getX()*.5), (int)(blue.getY()*.5), 
      		 (int)(blue.getW()*.5), (int)(blue.getH()*.5),null);
      g.drawImage(restart.getImage(), (int)(restart.getX()*.5), (int)(restart.getY()*.5), 
      		 (int)(restart.getW()*.5), (int)(restart.getH()*.5),null);
   }
  	
   public void update(ArrayList<Unit> newUnits, Player newPlayer, ArrayList<Platform> newBase, 
         Projectile newProj, Title newTitle, ArrayList<Platform> newStartBase, ArrayList<Platform> newPlacement, Title newEnd) {
      units = newUnits;
      base = newBase;
      player = newPlayer;
      projectile = newProj;
      title = newTitle;
      end = newEnd;
      startBase = newStartBase;
      placement = newPlacement;
  		repaint();  
   }
  	
   public void updateEnd(Title newBlue, Title newRestart) {
      blue = newBlue;
      restart = newRestart;
   }

   public void updateTimer(Clock newClock) {
      clock = newClock;
   }
}

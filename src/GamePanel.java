//Panel

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel 
{ 
  	private Image background;	
   private ArrayList<Platform> platforms;
   private ArrayList<Saw> saws;
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
   
	public GamePanel(String path) 
  	{
  		this(new ImageIcon(path).getImage());
      platforms = new ArrayList<Platform>();
      saws = new ArrayList<Saw>();
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
   
  	public GamePanel(Image img)
  	{
    	background = img;
    	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));	
     	setPreferredSize(size);
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setSize(size);
  	}

  	public void paintComponent(Graphics g) 
  	{	
    	g.drawImage(background, 0, 0, null); 
      for(Unit img:units)
      {
         g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
      }
      for(Platform img:base)
      {
         g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
      }
      for(Platform img:startBase)
      {
         g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
      }      
      for(Platform img:placement)
      {
         g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);
      }
      
      for(int i = 0; i<6; i++)
      {  
         g.drawImage(clock.getImage(i), (int)(clock.getX()+50*i), (int)(clock.getY()), null);
      }
      
      g.drawImage(projectile.getImage(), (int)(projectile.getX()), (int)(projectile.getY()), null);
      g.drawImage(player.getImage(), (int)(player.getX()), (int)(player.getY()), null);
      g.drawImage(title.getImage(), (int)(title.getX()), (int)(title.getY()), null);
      g.drawImage(end.getImage(), (int)(end.getX()), (int)(end.getY()), null);
      g.drawImage(blue.getImage(), (int)(blue.getX()), (int)(blue.getY()), null);
      g.drawImage(restart.getImage(), (int)(restart.getX()), (int)(restart.getY()), null);
   }
  	
  	public void update(ArrayList<Unit> newUnits, Player newPlayer, ArrayList<Platform> newBase, 
         Projectile newProj, Title newTitle, ArrayList<Platform> newStartBase, ArrayList<Platform> newPlacement, Title newEnd)
  	{
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
   public void updateEnd(Title newBlue, Title newRestart)
   {
      blue = newBlue;
      restart = newRestart;
   }
   
   
   public void updateTimer(Clock newClock)
   {
      clock = newClock;
   }
   
}

//Main

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Jumper extends JComponent implements KeyListener
{
   public boolean[] key = new boolean[256];
   private JFrame frame;
   private ArrayList<Saw> saws;
   private ArrayList<Box> boxes;
   private ArrayList<Platform> platforms;
   private ArrayList<Unit> units;
   private Player player;
   private GamePanel gamePanel;
   private ArrayList<Platform> base;
   private ArrayList<Platform> startBase;
   private ArrayList<Platform> placement;
   private Projectile projectile;
   private Title title;
   private Title end;
   private Title restart;
   private Title blue;
   private Clock clock;
   
   public void keyPressed(KeyEvent e) {
      key[e.getKeyCode()] = true;
   }

   public void keyReleased(KeyEvent e) {
      key[e.getKeyCode()] = false;    
   }
   
   public void keyTyped(KeyEvent e) {}

   public Jumper() {  
      saws = new ArrayList<Saw>();
      boxes = new ArrayList<Box>();     
      platforms = new ArrayList<Platform>();
      units = new ArrayList<Unit>();
      player = new Player("GameImages\\player.PNG", 600+80, 800+140);
      base = new ArrayList<Platform>();
      projectile = new Projectile("GameImages\\projectile.PNG", -300, 940);
      title = new Title("GameImages\\title.PNG", 200, 310);
      end = new Title("GameImages\\end.PNG", 100, -1400);
      blue = new Title("GameImages\\blue.PNG", 0, -3000);
      restart = new Title("GameImages\\replay.PNG", 200, -3000);
      startBase = new ArrayList<Platform>();
      placement = new ArrayList<Platform>();
      clock = new Clock(2000, 30);
      init();
      addObjects();
      gamePanel.addKeyListener(this);
      gamePanel.setFocusable(true);
      gamePanel.requestFocusInWindow();
      run();
   }

   public void init()  { 
      gamePanel = new GamePanel("GameImages\\bg2.JPG");
      frame = new JFrame("Jumper");              
      frame.setSize(1200, 700); 
      frame.add(gamePanel);     
      frame.setVisible(true);     
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(true);
      //frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
   }
   
   public void addObjects() {
      for(int i = 0; i<20; i++) {
         Box box = new Box("GameImages\\box1.PNG", (int)(Math.random() * 15)*300+2700, (int)(Math.random() * 3)*300-100);
         boxes.add(box);
         units.add(box);
      }
      for(int i = 0; i<30; i++) {
         Platform platform = new Platform("GameImages\\platform.PNG", (int)(Math.random() * 15)*300+2700, (int)(Math.random() * 3)*300+200);
         platforms.add(platform);
         units.add(platform);
      }
      for(int i = 0; i<15; i++) {
         Platform platform = new Platform("GameImages\\platform.PNG", 300*i+2700, 1100);
         platforms.add(platform);
         units.add(platform);
      }
      for(int i = 0; i<=11; i++) {
         Platform platform = new Platform("GameImages\\platform.PNG", 300*i, 1100);
         startBase.add(platform);
         units.add(platform);
      }
      for(int i = 0; i<=15; i++) {
         Platform platform = new Platform("GameImages\\platform.PNG", 300*i+2400, 1700);
         placement.add(platform);
         units.add(platform);
      }
      for(int i = 0; i<30; i++) {
         Platform platform = new Platform("GameImages\\platform.PNG", 300*i, 1700);
         base.add(platform);
         units.add(platform);
      }
   }
   
   //run
   public void run() {  
      while(true) {
	      int speed = 5;
	      
	      boolean running1 = true;
	      boolean running2 = false;
	      boolean running3 = false;
	     
	      player.setPos(400, 60);
	      title.setPos(170, 250);
	      clock.setPos(-3000, -3000);
	      gamePanel.updateTimer(clock);
	      
	      long lastTime = System.currentTimeMillis(); 
	      while(running1) {
	         long time = System.currentTimeMillis(); 
	         if(time - 10 > lastTime) {
	            lastTime = time; 
	
	            for(Platform i: startBase) {
	               i.moveLeft(5);
	               i.setPos(i.getX(), 1100);
	               if(i.getX()<-300) {
	                  i.setPos(3300, 1100);
	               }
	            }
	                         
	            gamePanel.update(units, player, base, projectile, title, startBase, placement, end);
	            if(key[KeyEvent.VK_UP]) {
	               running2 = true;
	               running1 = false;
	            }
	         }
	      }
	      
	      clock.setPos(2000,30);
	      player.setVel(30);
	      boolean canStart = false;
	      //long lastTime2 = System.currentTimeMillis(); 
	      while(running2) {
	         long time2 = System.currentTimeMillis();
	         if(time2 - 10 > lastTime) {
	            lastTime = time2; 
	            //scorekeeping
	            clock.addTime();
	            clock.setDigits();
	            gamePanel.updateTimer(clock);
	            if(clock.getTime()%1000 == 0) {
	               speed++;
	            }
	            //moves title away      
	            if(title.getX()>-2500) {
	               title.moveLeft(35);
	            }
	           
	            //moves all units at a designated speed
	            if(startBase.get(0).getX()%300==0)
	            canStart = true;
	            if(canStart) {
	               for(Unit i: units) {
	                  i.moveLeft(speed);
	               }    
	               for(Unit i: startBase) {
	                  i.moveLeft(-speed);
	               } 
	            }   
	            for(Platform i: startBase) {
	               i.moveLeft(speed);
	            } 
	            //resetting units
	            for(Platform i: platforms) {
	               if(i.getX()<-300) {
	                  i.setPos(i.getX()+300+(int)(Math.random() * 10)*300+3600, (int)(Math.random() * 4)*300+200);
	               }
	            }
	            for(Platform i: base) {
	               if(i.getX()<-300) {
	                  i.setPos(i.getX()+9000, 1400);
	               }
	            }
	            for(Platform i: placement) {
	               if(i.getX()<2400) {
	                  i.setPos(i.getX()+4500, 1400);
	               }
	            }
	            for(Box i: boxes) {
	               if(i.getX()<-300) {
	                  i.setPos(i.getX()+300+placement.get((int)(Math.random()*15)).getX(), (int)(Math.random()*3)*300-100);
	               }
	            }
	            //Projectile
	            player.move();                
	            //moves player left
	            if(key[KeyEvent.VK_LEFT]) {
	               player.moveL(15);
	            }
	            player.resetIgnore();
	            boolean touching = false;
	            boolean touchingBox = false;
	            boolean touchingTop = false;
	            boolean touchingRight = false;
	            for(Platform i:platforms) {
	               if(player.touch(i)) {
	                  if(player.getVel()<0) {
	                     player.land();
	                     player.setVel(0);
	                     player.setY(i.getY()-160);
	                     touching = true;
	                  }  
	               }  
	            }
	            for(Platform i:startBase) {
	               if(player.touch(i)) {
	                  if(player.getVel()<0) {
	                     player.land();
	                     player.setVel(0);
	                     player.setY(i.getY()-160);
	                     touching = true;
	                  }  
	               }  
	            }
	            if(!player.movable()) {
	               player.moveL(speed+5);
	            }
	            boolean free = true;
	            boolean touchLeft = false;
	            boolean touchCollide = false;
	            for(Box t:boxes) {
	               boolean onTop = false;
	               
	               if(player.collide(t)) {
	                  free = false;
	                  player.setMovable(false);
	                  touchingRight = true;
	                  for(Platform i:platforms) {
	                     if(player.touch(i))
	                     touchCollide = true;
	                  }
	                  //System.out.println("Collided");
	               }
	               if(player.collideLeft(t)) {
	                  player.setPos(t.getX()+t.getW(), player.getY());
	               }
	                                         
	               for(Platform i:platforms) {
	                  if(t.touch(i)) {
	                     onTop = true;
	                  }
	               }
	               for(Platform i:base) {
	                  if(t.touch(i)) {
	                     onTop = true; 
	                  }
	               }
	               for(Platform i:startBase) {
	                  if(t.touch(i)) {
	                     onTop = true;
	                  }
	               }
	
	               for(Box i:boxes) {
	                  if(t.touch(i) && t!=i) {
	                     onTop = true;
	                  }
	               }
	               if(!onTop) {
	                  t.fall();
	               }
	               if(player.touch(t)) {
	                  if(player.getVel()<0) {
	                     player.land();
	                     player.setVel(0);
	                     player.setY(t.getY()-160);
	                     touching = true;
	                     player.setIgnoreFalse();
	                     touchingBox = true;
	                  }
	                  touchingBox = true;                  
	               }
	               if(player.touchUp(t)) {
	                  player.setVel(-5);
	                  player.setY(t.getY()+t.getH());
	               }
	            }
	            if(!touchingRight) {
	               if(key[KeyEvent.VK_RIGHT] && player.movable()) {
	                  player.moveR(15);
	               }
	            }
	            if(free) {
	               player.setMovable(true);
	            }
	            if(key[KeyEvent.VK_UP] && !touchCollide) {
	               player.jump();
	            }   
	            if(key[KeyEvent.VK_DOWN]) {
	               if(!touchingBox) {
	                  player.drop();
	                  player.setIgnore();
	               }
	            }
	            if(!touching) {
	               player.drop();
	            }
	            
	            projectile.countdown();    
	            //fire            
	            if(key[KeyEvent.VK_SPACE]) {
	               if(projectile.getCounter() >= 70) {
	                  projectile.setPos(player.getX(),player.getY()+80);
	                  projectile.startCounter();
	                  projectile.setFiring(true);
	               }
	            }
	            //checks if projectile hits target
	            if(projectile.isFiring()) {
	               projectile.move(50);
	               for(Box c:boxes) {
	                  if(projectile.collide(c) || projectile.getX()>2400) {
	                     boolean hit = true;
	                     int x = player.getX(); 
	                                                      
	                     while(x<3000) {
	                        for(Box i:boxes) {
	                           if(i.getX()==x && (projectile.getY()+5 > i.getY() && projectile.getY()+5 < i.getY()+i.getH())) {
	                              i.setPos(-300/*placement.get((int)(Math.random()*15)).getX()*/, (int)(Math.random()*3)*300-100);
	                              x = 10000;
	                              System.out.println("hit");
	                           }
	                        }
	                        x++;
	                     }
	                     c.setPos(placement.get((int)(Math.random()*15)).getX(), (int)(Math.random()*3)*300-100); 
	                     projectile.setFiring(false);
	                     projectile.setPos(-3000, 600);
	                      
	                     c.setPos(placement.get((int)(Math.random()*15)).getX(), (int)(Math.random()*3)*300-100);
	                     gamePanel.update(units, player, base, projectile, title, startBase, placement, end);
	                     //System.out.println("hit!");
	                  }
	               }
	            }
	            //highlight
	            projectile.setPos(player.getX()-4000, player.getY());
	            if(projectile.getCounter()>65) {
	               player.changeColorDark();
	               projectile.setFiring(false);
	            }
	            if(projectile.getCounter()<=65) {
	               player.changeColorLight();
	            }
	            //endgame
	            if(player.getX()<-180 || player.getY()>1400) {
	               running3 = true;
	               running2 = false;
	            }
	         }  
	         gamePanel.update(units, player, base, projectile, title, startBase, placement, end);
	      }
	      long lastTime2 = System.currentTimeMillis(); 
	      while(running3) {
	         long time2 = System.currentTimeMillis(); 
	         if(time2 - 10 > lastTime2) {
	            lastTime2 = time2; 
	            projectile.setFiring(false);
	            projectile.setPos(9000,9000);
	            
	            for(Platform i: platforms) {
	               if(i.getX()<-300) {
	                  i.setPos((int)(Math.random() * 10)*300+3600, (int)(Math.random() * 4)*300+200);
	               }
	            }
	            for(Platform i: base) {
	               if(i.getX()<-300) {
	                  i.setPos(8700, 1400);
	               }
	            }
	            
	            for(Platform i: placement) {
	               if(i.getX()<2400) {
	                  i.setPos(6900, 1400);
	               }
	            }
	            
	            for(Box i: boxes) {
	               if(i.getX()<-300) {
	                  i.setPos(placement.get((int)(Math.random()*15)).getX(), (int)(Math.random()*3)*300-100);
	               }
	            }
	            for(Unit i:units) {
	               i.moveLeft(5);
	            }
	
	            projectile.setPos(-300,2000);
	            if(end.getY()<100) {
	               end.setY(end.getY()+20);
	            }
	            blue.setY(0);
	            restart.setY(1000);
	            
	            if(key[KeyEvent.VK_SPACE]) {
	               running1 = true;
	               running3 = false;
	            }
	         }
	         gamePanel.update(units, player, base, projectile, title, startBase, placement, end);
	         gamePanel.updateEnd(blue, restart);
	      }
	      clock.reset();
	      gamePanel.updateTimer(clock);
	      blue.setY(-3000);
	      end.setY(-1400);
	      player.setPos(400, 60);
	      title.setPos(170, 250);
	      restart.setY(-3000);
	      for(Box i:boxes) {
	         i.setPos((int)(Math.random() * 15)*300+2700, (int)(Math.random() * 3)*300-100);
	      }
	      for(int i = 0; i<30; i++) {
	         platforms.get(i).setPos((int)(Math.random() * 15)*300+2700, (int)(Math.random() * 3)*300+200);
	      }
	      for(int i = 15; i<45; i++) {
	         platforms.get(i).setPos(300*i+2700, 1100);
	      }
	      for(int i = 0; i<11; i++) {
	         startBase.get(i).setPos(300*i, 1100);
	      }
	      for(int i = 0; i<15; i++) {
	         placement.get(i).setPos(300*i+2400, 1700);
	      }
	      for(int i = 0; i<30; i++) {
	         base.get(i).setPos(300*i, 1400);
	      }
	      gamePanel.update(units, player, base, projectile, title, startBase, placement, end);
	      gamePanel.updateEnd(blue, restart);
	   }
   }
   
   public static void main(String[] args)
   {
      Jumper newGame = new Jumper();
   }
}
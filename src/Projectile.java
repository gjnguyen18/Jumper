//Projectile

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Projectile extends JComponent {
   private int x;
   private int y;
   private int w;
   private Image image;
   private boolean firing = false;
   private int resetCounter;
   
   public Projectile(String path, int xPos, int yPos) {
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	}

   public Projectile(Image img, int xPos, int yPos) {
      x = xPos;
      y = yPos;
      image = img;
      w = img.getWidth(null);
   }
   
   public int getX() {
      return x;
   }
   
   public Image getImage() {
      return image;
   }
   
   public int getY() {
      return y;
   }
   
   public int getW() {
  	 return image.getWidth(null);
   }
   
   public int getH() {
  	 return image.getHeight(null);
   }
   
   public void countdown() {
      resetCounter++;
   }
   
   public void startCounter() {
      resetCounter = 0;
   }
   
   public int getCounter() {
      return resetCounter;
   }
   
   public boolean isFiring() {
      return firing;
   }
   
   public void setPos(int xPos, int yPos) {
      if(!firing) {
         x = xPos;
         y = yPos;
      }
   }
  
   public void setImage(Image img)  {
      image = img;
   }
   
   public void setFiring(boolean f) {
      firing = f;
   }
   
   public void move(int amount){
      x += amount;
   }
   
   public boolean collide(Unit unit){
      if(y+60 > unit.getY() && y < unit.getY()+unit.getH()) {
         if(x+w >= unit.getX() && x+w <= unit.getX()+250 ){
            return true;
         }
         return false;
      }
      return false;
   }
}

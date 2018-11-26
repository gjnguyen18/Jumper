//Unit

import java.awt.*;
import javax.swing.*;

class Unit {
   private Image image;
   private Rectangle hitbox;
   private int x;
   private int y;
   
   public Unit(String path, int xPos, int yPos) {
  	 this(new ImageIcon(path).getImage(), xPos, yPos);	
	 }
   
   public Unit(Image img, int xPos, int yPos) {
      x = xPos;
      y = yPos;
      image = img;
      hitbox = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
   }
   
   public int getX() {
      return x;
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
   
   public Rectangle hitbox() {
      return hitbox;
   }
   
   public void moveLeft(int amount) {
      x -= amount;
   }
   
   public void setPos(int xPos, int yPos) {
      x = xPos;
      y = yPos;
   }
   
   public void setY(int yPos) {
      y = yPos;
   }
   
   public Image getImage() {
      return image;
   }
}

class Platform extends Unit {
	public Platform(String path, int xPos, int yPos) {
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	}
   
  public Platform(Image img, int xPos, int yPos) {
  	super(img, xPos, yPos);
  }
}

class Box extends Unit {
   private boolean hit = false;
   
   public Box(String path, int xPos, int yPos) {
  	 this(new ImageIcon(path).getImage(), xPos, yPos);	
   }
   
   public Box(Image img, int xPos, int yPos) {
      super(img, xPos, yPos);
   }
   
   public void setHit(boolean n) {
      hit = n;
   }
   
   public void setY(int yPos) {
      super.setPos(yPos, super.getX());
   }
   
   public void fall() {
      super.setY(super.getY()+20);
   }
   
   public boolean touch(Unit unit) {
      if(super.getY()+300 == unit.getY()){
         if(super.getX()+150 >= unit.getX() && super.getX()+150 <= unit.getX()+300) {
            return true;
         }
         return false;
      }
      return false;
   }
}
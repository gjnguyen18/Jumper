//start screen

import java.awt.*;
import javax.swing.*;

class Title {
   private Image image;
   private int x;
   private int y;
   
   public Title(String path, int xPos, int yPos) {
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	 }
   
   public Title(Image img, int xPos, int yPos) {
      x = xPos;
      y = yPos;
      image = img;
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
    
   public void moveLeft(int amount) {
      x -= amount;
   }
      
   public void setY(int yPos) {
      y = yPos;
   }
   
   public void setPos(int xPos, int yPos) {
      x = xPos;
      y = yPos;
   }
   
   public Image getImage() {
      return image;
   }
}

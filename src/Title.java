//start screen

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class Title extends JComponent
{
   private Image image;
   private int x;
   private int y;
   
   public Title(String path, int xPos, int yPos)
	{
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	}
   
   public Title(Image img, int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
      image = img;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
    
   public void moveLeft(int amount)
   {
      x -= amount;
   }
      
   public void setY(int yPos)
   {
      y = yPos;
   }
   
   public void setPos(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }
   
   public Image getImage()
   {
      return image;
   }
}
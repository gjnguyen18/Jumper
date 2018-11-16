//Player

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player 
{
   private int x;
   private int y;
   private Image image;
   private int velocity;
   private int w;
   private boolean isStanding = true;
   private boolean ignore = false;
   public Rectangle hitbox;
   private boolean canMove = true;
   private int amountIgnored;
   //public Rectangle bottom;
   
   
   public Player(String path, int xPos, int yPos)
	{
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	}
   public Player(Image img, int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
      image = img;
      hitbox = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
      w = img.getWidth(null);
   }
   
   public boolean movable()
   {
      return canMove;
   }
   
   public void setMovable(boolean n)
   {
      canMove = n;
   }
  
   public void setIgnoreFalse()
   {
      ignore = false;
   }
   
   /*
   public void scaleUp()
   {
      Image newImg = image.getScaledInstance(
   }
   */
  
   public void setIgnore()
   {
      if(!ignore)
      ignore = true;
   } 
    
   public void changeColorLight()
   {
      image = new ImageIcon("GameImages\\player2.PNG").getImage();
   }
    
   public void changeColorDark()
   {
      image = new ImageIcon("GameImages\\player.PNG").getImage();
   }
    
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public int getW()
   {
      return image.getWidth(null);
   }
   
   public int getH()
   {
      return image.getHeight(null);
   } 
   
   public int getVel()
   {
      return velocity;
   }
   
   public Rectangle hitbox()
   {
      return hitbox;
   }
   
   public Image getImage()
   {
      return image;
   }
   
   public boolean standing()
   {
      return isStanding;
   }
   
   public void setVel(int vel)
   {
      velocity = vel;
   }
   
   public void moveR(int n)
   {
      x += n;
   }   
   
   public void moveL(int n)
   {
      x -= n;
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
   
   public void jump()
   {
      if(isStanding)
      {
         isStanding = false;
         velocity = 55;
      }
   }
   
   public void land()
   {
      isStanding = true;
      velocity = 0;
   }
   
   public void move()
   {
      if(!isStanding)
      {      
         y -= velocity;
         velocity -= 4;
         if(velocity < -65)
         {
            velocity = -64;
         }
      }
   }
   
   public void resetIgnore()
   {
      if(ignore)
      {
         if(amountIgnored <= 100)
         {
            amountIgnored -= velocity;
         }
         else
         {
            ignore = false;
            amountIgnored = 0;
         }
      }
   }
   
   public void drop()
   {
      if(isStanding)
      {
         velocity = -5;
         isStanding = false;
      }
   }
   
   public boolean touch(Unit unit)
   {
      if(!ignore)
      {
         if(y+160 >= unit.getY() && y+160 <= unit.getY()+100)
         {
            if( ((x > unit.getX()) && (x < unit.getX()+300)) || ((x+160 > unit.getX()) && (x+this.getW() < unit.getX()+300)))
            {
               return true;
            }
            return false;
         }
         return false;
      }
      return false;
   }
   
   public boolean touchUp(Box unit)
   {
      if(!ignore)
      {
         if(y <= unit.getY()+unit.getH() && y >= unit.getY()+unit.getH()-50)
         {
            if( ((x > unit.getX()) && (x < unit.getX()+300)) || ((x+160 > unit.getX()) && (x+this.getW() < unit.getX()+300)))
            {
               return true;
            }
            return false;
         }
         return false;
      }
      return false;
   }

   
   public boolean collide(Box unit)
   {
      if(y <= unit.getY()+unit.getH() && y >= unit.getY())
      {
         if(x+w >= unit.getX() && x+w <= unit.getX()+100 )
         {
            return true;
         }
         else
         return false;
      }
      else
      {
         return false;
      }
   }
   
   public boolean collideLeft(Box unit)
   {
      if(y <= unit.getY()+unit.getH() && y >= unit.getY())
      {
         if(x <= unit.getX()+unit.getW() && x >= unit.getX()+unit.getW()-11 )
         {
            return true;
         }
         else
         return false;
      }
      else
      {
         return false;
      }
   }
}
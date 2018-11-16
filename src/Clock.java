//Scorekeep

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Clock extends Object
{
   private ArrayList<Title> numbers = new ArrayList<Title>();
   private ArrayList<Integer> clock;
   private ArrayList<Image> digits;
   
   private int time;
   
   private int x;
   private int y;
   
   public Clock(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
     
      //numbers = new ArrayList<Title>(10);
      clock = new ArrayList<Integer>();
      digits = new ArrayList<Image>();
      
      numbers.add(0, new Title("GameImages\\0.PNG", x, y));
      numbers.add(1, new Title("GameImages\\1.PNG", x+30, y));
      numbers.add(2, new Title("GameImages\\2.PNG", x+60, y));
      numbers.add(3, new Title("GameImages\\3.PNG", x+90, y));
      numbers.add(4, new Title("GameImages\\4.PNG", x+120, y));
      numbers.add(5, new Title("GameImages\\5.PNG", x+150, y));
      numbers.add(6, new Title("GameImages\\6.PNG", x+180, y));
      numbers.add(7, new Title("GameImages\\7.PNG", x+210, y));
      numbers.add(8, new Title("GameImages\\8.PNG", x+240, y));
      numbers.add(9, new Title("GameImages\\9.PNG", x+270, y));
      
      /*
      Title img0 = new Title("GameImages\\0.PNG", x, y);
      Title img1 = new Title("GameImages\\1.PNG", x+30, y);
      Title img2 = new Title("GameImages\\2.PNG", x+60, y);
      Title img3 = new Title("GameImages\\3.PNG", x+90, y);
      Title img4 = new Title("GameImages\\4.PNG", x+120, y);
      Title img5 = new Title("GameImages\\5.PNG", x+150, y);
      Title img6 = new Title("GameImages\\6.PNG", x+180, y);
      Title img7 = new Title("GameImages\\7.PNG", x+210, y);
      Title img8 = new Title("GameImages\\8.PNG", x+240, y);
      Title img9 = new Title("GameImages\\9.PNG", x+270, y);
      
      numbers.add(0, img0);
      numbers.add(1, img1);
      numbers.add(2, img2);
      numbers.add(3, img3);
      numbers.add(4, img4);
      numbers.add(5, img5);
      numbers.add(6, img6);
      numbers.add(7, img7);
      numbers.add(8, img8);
      numbers.add(9, img9);
      */
     

      for(int i = 0; i<6; i++)
      {
         clock.add(0);
         digits.add(numbers.get(0).getImage());
      }
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public int getTime()
   {
      return time;
   }
   
   public void setPos(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }
      
   public void setDigits()
   {
      for(int i = 0; i<6; i++)
      {
         digits.set(i, numbers.get(clock.get(i)).getImage());
      }
   }
   
   public void addTime()
   {
      time++;
      clock.set(5, clock.get(5)+1);
      
      for(int i = 5; i>0; i--)
      {
         if(clock.get(i) == 10)
         {
            clock.set(i-1, clock.get(i-1)+1);
            clock.set(i, 0);
         }
      }
   }
   
   public void reset()
   {
      time = 0;
      for(int i = 0; i<6; i++)
      {
         clock.set(i, 0);
         digits.set(i, numbers.get(0).getImage());
      }
   }
   
   public Image getImage(int digit)
   {
      return digits.get(digit);
   }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsignal;

/**
 *
 * @author rishwantariq
 */

//main background component

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;


import java.util.Random;



class TrafficSignal extends JPanel implements ActionListener {
    
    public static LinkedList<Car> north = new LinkedList<>(); //queue for thread for north side
    public static LinkedList<Car>south = new LinkedList<>(); //queue for thread that updates south
    public static LinkedList<Car>east = new LinkedList<>(); //queue for thread that updates east
    public static LinkedList<Car>west = new LinkedList<>(); //queue for thread that updates west 
    long seconds = 0;
    public static boolean timesup=false;
    public static boolean timesup1=false;
    public static boolean timesup2=false;
    public static boolean timesup3=false;

                  long start = System.nanoTime();
                  long current;

    
       private Image road;
       private Image car;
       Signal s = new Signal(200,10);
       Signal s2 = new Signal(800,10);
       Signal s3 = new Signal(200,400);
       Signal s1 = new Signal(800,400);
       
       

       
       private Image signal1;
       private Image signal2;
       private Image signal3;
       private Image signal4;

      public static boolean we = false;
      public static boolean ea = false;
      public static boolean no = false;
      public static boolean so = false;
      
    TrafficSignal() {
        setSize(new Dimension(1000, 600));
        setBackground(Color.DARK_GRAY); 
        new Timer(50, this).start();

        //timer
    }
    public void actionPerformed(ActionEvent arg0) {

        current=System.nanoTime();
       
       
         //decide time for each signal based on number of cars 
        int duration;
        duration=0; 

       
        if(we)
        {
            
         duration=west.size();
        
         
        
          s.update(3);

               for(Car w : west)
                {
                   
                     Thread t1 = new Thread(new Runnable() {
                        
                        
    
                         
                         @Override
                         public void run() {
                        // code goes here.
                        
                                if(!timesup)
                                {
                                
                                        w.update();
                                }
                                 
                               else if(timesup && w.x>=250)
                                {
                                    w.update();
                                }
                                
                               
                          
                        
                        }
                     });  
                     
                      
                    
          try{ //this is to timeout a thread (each signal) based on number of cars
            
         long calc=System.nanoTime() - start;
         seconds = TimeUnit.SECONDS.convert(calc, TimeUnit.NANOSECONDS);
                 if(seconds>=duration) { //More than 10 minutes past the start
                 timesup=true;
                 s.update(1);
                 ea=true;
                 //we=false;
                 

                 }
                
        
               t1.start();
                    
        }
        
        catch(Exception e)
        {
            
        } 
      }
               
               for(int i=0; i<west.size(); i++)
               {
                   Car c = west.get(i);
                   if (c.x>1000)
                   {
                       west.remove(c);
                      
                   }
                       
               }
               
               if(west.isEmpty())
               {
                   
                   we=false;
                   ea=true;
                   s.update(1);
               }
               
               //timesup1=false;
               
        }
     
        if(ea)
        {
           duration+=east.size();
           

           
           
             //we=false;
             //start = System.nanoTime();
              s1.update(3); //change to green

                  for(Car e : east)
                {
                   
                     Thread t1 = new Thread(new Runnable() {
                        
                         @Override
                         public void run() {
                          if(!timesup1)
                           {
                               e.update2();
                           }
                          
                          else if(timesup1 && e.x<=700)
                          {
                              e.update2();
                             
                          }
                         
                         
                          
                        }
                     });  
                     
        try{ //this is to timeout a thread (each signal) based on number of cars
            
         long calc=System.nanoTime() - start;
         seconds = TimeUnit.SECONDS.convert(calc, TimeUnit.NANOSECONDS);
                 if(seconds>=duration+5) { //More than 10 minutes past the start
                 timesup1=true;
                 s1.update(1);
                 //ea=false;
                 no=true;

                 }
                
        
               t1.start();
                    
        }
        
        catch(Exception e1)
        {
            
        }     
      
        
                }
        for(int i=0; i<east.size(); i++)
               {
                   Car c = east.get(i);
                   if (c.x<0)
                   {
                       east.remove(c);
                   }
                       
               }
               
               if(east.isEmpty())
               {
                   
                   ea=false;
                   no=true;
                   s1.update(1);
               }
                
               //timesup2=false;
               
                  
        }
        
        
        
         if(no) //north signal
        {
             duration+=north.size();
            
             if(timesup2)
             {
                 s2.update(1);
             }
             //ea=false;
             else if(!timesup2)
             {
                 s2.update(3);
             }
           
                  for(Car n : north)
                {
                  
                    if(north.isEmpty())
                        break;
                     Thread t1 = new Thread(new Runnable() {
                        
                         @Override
                         public void run() {
                         if(!timesup2)
                           {
                               n.update3();
                           }
                         else if(timesup2 && n.y>=50)
                         {
                             n.update3();
                         }
                                 
                         if(timesup2 && north.isEmpty())
                             no=false;
                          
                        }
                     });  
         try{ //this is to timeout a thread (each signal) based on number of cars
            
         long calc=System.nanoTime() - start;
          
         seconds = TimeUnit.SECONDS.convert(calc, TimeUnit.NANOSECONDS);
                 if(seconds>=duration+14) { //More than 10 minutes past the start
                 timesup2=true;
                 s2.update(1);
                 so=true;

                 }
                
        
               t1.start();
                    
        }
        
        catch(Exception e)
        {
            
        } 
                          
        
                }
        for(int i=0; i<north.size(); i++)
               {
                   Car c = north.get(i);
                   if (c.y>600)
                   {
                       north.remove(c);
                   }
                       
               }
               
               if(north.isEmpty())
               {
                   
                   no=false;
                   so=true;
                   s2.update(1);
               }
                
                  
        }
         
          if(so)
        {
                  
            duration+=south.size();
              s3.update(3); //change to green
                  for(Car s : south)
                {
                   
                     Thread t1 = new Thread(new Runnable() {
                        
                         @Override
                         public void run() {
                         if(!timesup3)
                           {
                               s.update4();
                           }
                         
                         else if(timesup3 && s.y<=500)
                         {
                             s.update4();
                             
                         }
                                 
                          
                        }
                     });  
                     
                          
        try{ //this is to timeout a thread (each signal) based on number of cars
            
         long calc=System.nanoTime() - start;
         seconds = TimeUnit.SECONDS.convert(calc, TimeUnit.NANOSECONDS);
                 if(seconds>=duration+20) { //More than 10 minutes past the start
                 timesup3=true;
                 s3.update(1);
                 //so=false;
                 we=true;
                 }
                
        
               t1.start();
                    
        }
        
        catch(Exception e)
        {
           
        } 
        
                }
        for(int i=0; i<south.size(); i++)
               {
                   Car c = south.get(i);
                   if (c.y<=0)
                   {
                       south.remove(c);
                   }
                   
                       
               }
               
               if(south.isEmpty())
               {
                   so=false;
                   we=true;
                   s3.update(1);
                   s.update(3);
                   start=current;
                   timesup=false;
                   
                   
                   
               }
     
            
               
               
              
        }
            
        
        repaint();
        
      
    
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        ImageIcon ii = new ImageIcon("Images/road1.png");
        road =ii.getImage();
        g.drawImage(road,0,0, this);

         Graphics2D g2 = (Graphics2D) g;
         s.drawRed(g2);
         s1.drawRed(g2);       
         s2.drawRed(g2);     
         s3.drawRed(g2);

       
        
        
        Graphics2D g2d = (Graphics2D) g;
        for (Car c : Car.cars)
        {
            if(c.dir ==1)
            {
                for(Car w : west)
                {
                    w.draw(g2d);
                }

            }
            
            else if(c.dir ==2)
            {
              for(Car e : east)
              {
                  e.draw2(g2d);
              }
            }
           
            else if(c.dir == 3)
            {
                for(Car n: north)
                {
                    n.draw3(g2d);
                }
            }
            
            else if(c.dir == 4)
            {
                for(Car s: south)
                {
                    s.draw4(g2d);
                }
            }
        }
    }
    
  
    
    
    
    public static void main(String[] args) {
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1000,600);
       TrafficSignal cars = new TrafficSignal();
       
   

       

        
  
        frame.add(cars);
        frame.setVisible(true);
        
        //Creater thread that creates cars based on random number Queue
        create c1 = new create();
        c1.start();//creates cars at each end using a single creator thread
        
        
        
       
        
        
      
        
    }



    
  


static class create extends Thread
{
    public void run()
    {
        
        Random rand = new Random();
        int startx=0;
        int starty=0;
        
        //for west
        for(int i=1; i<4; i++)
        {
           
            west.add(new Car(startx,230,1.2,1)); //the seperate queue that we will use for each thread 
            startx+=60;
            we = true;
        
        }
     
        // //for east
        startx=700;
        for(int i=1; i<6; i++)
        {
            
            east.add(new Car(startx,290,1.2,2));
            startx+=60;
            
        } 

        //for north
        starty=100;
        for(int i=1; i<rand.nextInt(6)+1; i++)
        {
            
            north.add(new Car(530,starty,1.2,3)); //3 is north
            starty-=60;
            
        } 
        
        starty = 400;
        //for south
        for(int i=1; i<3; i++)
        {
           
            south.add(new Car(450,starty,1.2,4));
            starty+=60;
            
        }
        
    }
}


public static class Signal
{
    Image signal;
    int pos=1;
    double  x, y;
    
    public Signal(double x, double y)
    {
        this.x = x;
        this.y = y;
        
    }
    
     public void drawRed(Graphics2D g2d) {
         if(this.pos == 1)
         {
            ImageIcon i = new ImageIcon("Images/red.png");
             signal=i.getImage();
         }
         
         if(this.pos == 2)
         {
            ImageIcon i = new ImageIcon("Images/yellow.png");
            signal=i.getImage();
         }
         
         if(this.pos == 3)
         {
            ImageIcon i = new ImageIcon("Images/green.png");
            signal=i.getImage();
         }
         
        g2d.drawImage(signal,(int) x, (int) y, null);
        
     }
     
      public void update(int pos) {
        this.pos=pos;
        
    }
             
    
}
}
class Car {
     Image car;
     
     

    public static final LinkedList<Car> cars = new LinkedList<>(); //main queue inside car class itself (LinkedList acts as FIFO Queue for simplicity)
    double  x, y, speed,dir; //dir = 1 for west, 2 for east, 3 for north, 4 for south
    
   
    public Car(double x, double y, double s,double dir) {
        this.x = x;
        this.y = y;
        this.speed = s;
        this.dir = dir;
        cars.add(this);
    }
    
    public static LinkedList<Car> getList()
    {
        return cars;
    }
    public void update() {
        Random rand = new Random();
        x += 4;
        y += (rand.nextInt(1));
        
    }
    
    public void update2() {
        Random rand = new Random();
        x -= 4;
        y += (rand.nextInt(1));
    }
    
    public void update3() {
        Random rand = new Random();
        x += (rand.nextInt(1));
        y += 4;
    }
    
    public void update4() {
        Random rand = new Random();
        x += (rand.nextInt(1));
        y -= 4;
    }
    
   
    
    
    public void draw(Graphics2D g2d) {
        ImageIcon i = new ImageIcon("Images/car1.png");
        car=i.getImage();
        g2d.drawImage(car,(int) x, (int) y, null);
        
    }
    public void draw2(Graphics2D g2d) {
        
        ImageIcon i = new ImageIcon("Images/car2.png");
        car=i.getImage();
        g2d.drawImage(car,(int) x, (int) y, null);
        
    }
    
    public void draw3(Graphics2D g2d) {
        
        ImageIcon i = new ImageIcon("Images/car4.png");
        car=i.getImage();
        g2d.drawImage(car,(int) x, (int) y, null);
        
    }
    
    public void draw4(Graphics2D g2d) {
        ImageIcon i = new ImageIcon("Images/car3.png");
        car=i.getImage();
        g2d.drawImage(car,(int) x, (int) y, null);
        
    }
} 
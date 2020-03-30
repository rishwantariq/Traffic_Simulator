/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsignal;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author rishwantariq
 */
public class Grass extends JPanel {
    // traffic light surface
  private JPanel panel;
  // drawing mechanism
  private Graphics g;
  // keep track of status of traffic light
  private int status;
  // color of "off" red light 
  private Color offRed;
  // color of "off" yellow light
  private Color offYellow;
  // color of "off" green light
  private Color offGreen;
    
   

	public Grass() {
		panel = new JPanel(new FlowLayout());
		panel.setSize(90, 250);
		panel.setBackground(Color.LIGHT_GRAY);
		offYellow = new Color(192, 192, 0);
		offGreen = new Color(0, 96, 0);
		status = 0;

	}

	// This is where you should do ALL your painting.
	// Here and in methods it calls.
	// Don't call it yourself. Swing does that for you.
	@Override
	public void paintComponent(Graphics g) {
		drawBackground(g);
		drawIlluminatedLights(g);
	}

	// I have moved all the painting stuff from your constructor here.
	private void drawBackground(Graphics g) {

		// insert "off" red light
		g.setColor(offRed);
		g.fillOval(10, 10, 70, 70);
		g.setColor(Color.BLACK);
		g.drawOval(10, 10, 70, 70);

		// insert "off" yellow light
		g.setColor(offYellow);
		g.fillOval(10, 90, 70, 70);
		g.setColor(Color.BLACK);
		g.drawOval(10, 90, 70, 70);

		// insert "off" green light
		g.setColor(offGreen);
		g.fillOval(10, 170, 70, 70);
		g.setColor(Color.BLACK);
		g.drawOval(10, 170, 70, 70);
	}

	// Here is the graphics stuff from your event handler.
	// I have stripped out changing the status.
	private void drawIlluminatedLights(Graphics g) {
		// traffic light is entirely "off"
		if (status == 0) {
			// turn "on" red light
			g.setColor(Color.RED);
			g.fillOval(10, 10, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 10, 70, 70);
		} else if (status == 1) { // red light is "on"
			// turn "off" red light
			g.setColor(offRed);
			g.fillOval(10, 10, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 10, 70, 70);

			// turn "on" yellow light
			g.setColor(Color.YELLOW);
			g.fillOval(10, 90, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 90, 70, 70);
		} else if (status == 2) { // yellow light is "on"
			// turn "off" yellow light
			g.setColor(offYellow);
			g.fillOval(10, 90, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 90, 70, 70);

			// turn "on" green light
			g.setColor(Color.GREEN);
			g.fillOval(10, 170, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 170, 70, 70);
		} else if (status == 3) { // green light is "on"
			// turn "off" green light
			g.setColor(offGreen);
			g.fillOval(10, 170, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 170, 70, 70);

			// turn "on" red light
			g.setColor(Color.RED);
			g.fillOval(10, 10, 70, 70);
			g.setColor(Color.BLACK);
			g.drawOval(10, 10, 70, 70);
		}
	}

    
} 
    


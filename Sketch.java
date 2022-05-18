import processing.core.PApplet;

public class Sketch extends PApplet {
  float fltPlayX = 200;
  float fltPlayY = 100;
  float[] circleY = new float[20];
  int intTries = 3;
  boolean IsTryLost = false;
  boolean[] IsBallVis = new boolean[20];
  boolean IsMouseClicked = false;

	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  // Y variables for snow balls
  for (int i = 0; i < circleY.length; i++) 
  { 
    circleY[i] = random(height);
    IsBallVis[i] = true;
  }
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(0, 20, 40); //Midnight Blue

    fill(0, 0, 255); // Blue
    ellipse(fltPlayX, fltPlayY, 20, 20); 

     
    for (int i = 0; i < circleY.length; i++) 
    {
      float circleX = width * i / circleY.length; // Makes x variable in correspondance to Y variable
      
      if (IsBallVis[i]) 
      { 
        fill(255); // White
        ellipse(circleX, circleY[i], 25, 25);
      }

     // Speed changes based on arrow key presses
     if (keyPressed) {
      if (keyCode == UP) {
         // Speed decreases
        circleY[i] -= 2;
      }
      else if (keyCode == DOWN) {
         // Speed increases 
        circleY[i] += 5;
      }
    }
    
      // Default speed for snow to fall
      circleY[i]+=2;

      // Edge detection for the bottom
      if (circleY[i] > height+(25/2)) {
        circleY[i] = 0; // Sets Y value to 0
      }

      // Collision detection for player and the snowballs
      if (fltPlayX >= circleX-12.5 && fltPlayX <= circleX+12.5 && fltPlayY >= circleY[i]-12.5 && fltPlayY <= circleY[i]+12.5 && IsBallVis[i]) 
      {
      // If collision happens, a try is taken off and the snowball becomes invisible
      intTries -= 1;
      IsBallVis[i] = false;
      }
    
      // Clicking on the snowballs make them go invisible
      if (mouseX >= circleX-12.5 && mouseX <= circleX+12.5 && mouseY >= circleY[i]-12.5 && mouseY <= circleY[i]+12.5 && mousePressed) 
      {
        IsBallVis[i] = false;
      }
    }
    // Restrictions so the player doesnt go off screen
    if (fltPlayX < 0) 
    {
      fltPlayX = 0+(25/2);
    }
    if (fltPlayY < 0) 
    {
      fltPlayY = 0+(25/2);
    }
    if (fltPlayX > width)
     {
      fltPlayX = width-(25/2);
    }
    if (fltPlayY > height) 
    {
      fltPlayY = height-(25/2);
    }

  
    // Display the tries remaining as rectangles
    if (intTries == 3) 
    {
      fill(0, 255, 0); // Green
      rect(385, 5, 10, 20);
      rect(370, 5, 10, 20);
      rect(355, 5, 10, 20);
    }
    if (intTries == 2) 
    {
      fill(255, 255, 0); // Yellow
      rect(385, 5, 10, 20);
      rect(370, 5, 10, 20);
    }
    if (intTries == 1) 
    {
      fill(255, 0, 0); // Red
      rect(385, 5, 10, 20);
    }
    if (intTries <= 0) {
      background(255, 0, 0); // Red
    }
  }
// Defining other methods 

  /**
  * This method detects if a key is pressed and executes a certain action because of it
  *
  */
  public void keyPressed()
   {
    // Move the player around with the WASD keys 
   if (key == 'w') {
     fltPlayY -= 5;
   }
   if (key == 's') {
    fltPlayY += 5;
   }
   if (key == 'a') {
    fltPlayX -= 5;
   }
   if (key == 'd') {
     fltPlayX += 5; 
   }
 }


 /**
 * This method detects if the mouse is pressed and sets the boolean "IsMouseClicked" to true
 * 
 */
 public void mousePressed() 
 {
   IsMouseClicked = true;
 }

 /**
 * This method detects if a mouse click is released and then makes the boolean "IsMouseClicked" to false
 * 
 */
 public void mouseReleased() 
 {
   IsMouseClicked = false;
 }
}

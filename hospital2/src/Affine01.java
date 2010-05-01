import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

class Affine01{
  public static void main(String[] args){
	  GUI guiObj = new GUI();
  }//end main
}//end controlling class Affine01
 
class GUI extends Frame {
  int res;//store screen resolution here
  static final int ds = 72;//default scale, 72 units/inch
  
  GUI(){//constructor
    //Get screen resolution
    res = Toolkit.getDefaultToolkit().getScreenResolution();
 
    //Set Frame size to four-inches by four-inches
    this.setSize(4*res,4*res);
    this.setVisible(true);
    this.setTitle("Copyright 1999, R.G.Baldwin");
       
    //Window listener to terminate program.
    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);}});
  }//end constructor
  
  //Override the paint() method to draw and manipulate a
  // square.
  public void paint(Graphics g){
    //Downcast the Graphics object to a Graphics2D object
    Graphics2D g2 = (Graphics2D)g;
 
    //Display contents of default AffineTransform object
    System.out.println("Default Transform");
    displayMatrix(g2.getTransform());    
    
    //Update transform to include a scale component, 
    // and display the values.
    System.out.println("Add Scale Transform");
    g2.scale((double)res/72,(double)res/72);
    displayMatrix(g2.getTransform());
 
    //Update transform to include a translate component, 
    // and display the values.
    System.out.println("Add Translate Transform");
    g2.translate(0.25*ds,0.25*ds);
    displayMatrix(g2.getTransform());
 
    //Update transform to include a shear component, 
    // and display the values.
    System.out.println("Add Shear Transform");
    g2.shear(0.05,0.1);
    displayMatrix(g2.getTransform());
 
    //Update transform to provide rotation and display, 
    // the transform values.    
    System.out.println("Add Rotate Transform");
    //11.25 degrees about center
    g2.rotate(Math.PI/16,2.0*ds, 2.0*ds);
    displayMatrix(g2.getTransform());
 
    //Draw five concentric squares and apply the transform
    // that results from the above transform updates.  If
    // the above scale transform is applied, the outer
    // square is one inch on each side, and the squares
    // are separated by 0.1 inch 
    double delta = 0.1;
    for(int cnt = 0; cnt < 5; cnt++){
      g2.draw(new Rectangle2D.Double(
         (1.5+cnt*delta)*ds, (1.5+cnt*delta)*ds, 
         (1.0-cnt*2*delta)*ds, (1.0-cnt*2*delta)*ds));
    }//end for loop
 
    //Superimpose some text on the squares with the 
    // lower left corner of the first character at the
    // center of the squares.
    g2.drawString("Exit ->",2.0f*ds,2.0f*ds);
  }//end overridden paint()
    
  //This method receives a reference to an AffineTransform
  // and displays the six controllable values in the
  // transform matrix
  void displayMatrix(AffineTransform theTransform){
    double[] theMatrix = new double[6];
    theTransform.getMatrix(theMatrix);
    
    //Display first row of values by displaying every
    // other element in the array starting with element
    // zero.
    for(int cnt = 0; cnt < 6; cnt+=2){
      System.out.print(theMatrix[cnt] + " ");
    }//end for loop
    
    //Display second row of values displaying every
    // other element in the array starting with element
    // number one.
    System.out.println();//new line
    for(int cnt = 1; cnt < 6; cnt+=2){
      System.out.print(theMatrix[cnt] + " ");
    }//end for loop
    System.out.println();//end of line
    System.out.println();//blank line
    
  }//end displayMatrix
}//end class GUI

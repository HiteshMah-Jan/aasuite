/*
 * PrintExample.java
 * 
 * Created on Dec 21, 2007, 6:45:23 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package print;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.*;

public class PrintExample extends JFrame
                          implements ActionListener {
  public static void main(String[] args) {
    PrintExample pr = new PrintExample();
    PrintUtilities.printComponent(pr);
  }

  public PrintExample() {
    super("Printing Swing Components");
    Container content = getContentPane();
    JButton printButton = new JButton("Print");
    printButton.addActionListener(this);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.white);
    buttonPanel.add(printButton);
    content.add(buttonPanel, BorderLayout.SOUTH);
    DrawingPanel drawingPanel = new DrawingPanel();
    content.add(drawingPanel, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    PrintUtilities.printComponent(this);
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import util.Log;

/**
 *
 * @author Charliemagne Mark
 */
public class SampleTest {

    static int b = 3;
    

    static {
        System.out.println(b);
        b = 5;
    }
    

    static {
        System.out.println(b);
        b = 4;
    }

    String s = "base" ;
    public static void main(String[] args) {
         try { 
             boolean choice = false;
            if (choice) {
                 while (true) {
                     System.out.println("t");
                 }
            } else {
                System.exit(1);
            }
        } finally {
             System.out.println("FINALLY CALLED");
        }
        S2 S2=new S3();
        S2.display ();
        S1 S1=new S1();
        Log.out("Print S1 ",S1.gets());
        Log.out("Print S2 ",S2.gets());
    }

    static class S1 {
        String S = "S1";
        public String gets() {
            return S;
        }
        void display() {
            Log.out("Display in S1 ",S);
        }
    }

    static class S2 extends S1 {
        String S = "S2";
        void display() {
            super.display();
            Log.out("Display in S2 ",S);
        }
    }
    
    static class S3 extends S2 {
        String S = "S3";
        void display() {
            super.display();
            Log.out("Display in S3 ",S);
        }
    }
}

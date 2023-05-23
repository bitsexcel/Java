/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package be.javacrypt1998;

import java.awt.Frame;
import javax.swing.JDialog;
import oreilly.jonathan.awt.SeederDialog;

/**
 *
 * @author jdaniel
 */
public class JavaCrypt1998 {
    static Frame f;
    static JDialog jd;

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        //f = new Frame("example");
        jd = new JDialog();
        
        SeederDialog sd = new SeederDialog(f, 5);
    }
}

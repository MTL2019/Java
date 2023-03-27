package com.prog2.main;

import com.prog2.mainframe.MainJFrame;

/**
 * @author adinashby
 *
 */

public class Main extends javax.swing.JFrame{

	/**
	 * Write your test code below in the main.
	 *
	 */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

	
}
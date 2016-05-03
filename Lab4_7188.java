/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_7188;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
 
/**
 * A Swing component that smoothly animates a spiral in a hypnotic way.
 */
public class Lab4_7188 extends JFrame {
    public Lab4_7188(){
        super("Lab 4");
        ScreenSaver saver = new ScreenSaver();
        setLayout(new BorderLayout());
        add(saver, BorderLayout.CENTER);
    }
    /**
     * program used to draw a spiral
     * using drawPolyline one line at
     * a time every second
     */
    class ScreenSaver extends JPanel implements ActionListener, MouseWheelListener{
        private int x[] = new int[60];
        private int y[] = new int[60];
        private int numOfPoints = 0;
        private int radius = 10;
        private int delay = 1000;
        private Timer timer = null; 
        
        public ScreenSaver(){
            this.timer = new Timer(delay, this);
            timer.start(); //starts timer
            timer.addActionListener(this); //used to produce a line a second
        }
        /**
         * method to plot the points
         * in a spiral order
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            double maxX = getSize(null).getWidth();
            double maxY = getSize(null).getHeight();
            int centerX = (int) maxX/2;
            int centerY = (int) maxY/2;
            double x = centerX + radius * Math.cos(numOfPoints * 2 * Math.PI/6);
            double y = centerY + radius * Math.sin(numOfPoints * 2 * Math.PI/6);
            radius += 3;
            this.x[numOfPoints] = (int) x;
            this.y[numOfPoints] = (int) y;
            numOfPoints++;
            if(numOfPoints == 60){
                numOfPoints = 0;
                radius = 10;
            }
            repaint(); //calls paintComponent method
        }
        /**
         * method to draw the spiral 
         */
        public void paintComponent(Graphics g){
            super.paintComponents(g);
            g.clearRect(0,0,(int)getSize().getWidth(),(int)getSize().getHeight());
            g.setColor(Color.BLACK);
            g.drawPolyline(x, y, numOfPoints);
        }
        /**
         * method to read the movement of
         * the mouse and alter the timer 
         * based on which way it was moved
         * down for a faster drawing and 
         * up for a slower drawing
         */
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int rotations = e.getWheelRotation();
            if(rotations < 0){
                delay -= 500;
                timer.setDelay(delay);
            }
            else if(rotations > 0){
                delay += 500;
                timer.setDelay(delay);
            }
            else{
            }
        }
    }
    /**
     * main method to call screen saver
     */
    public static void main(String[] args){
        Lab4_7188 lab4 = new Lab4_7188();
        lab4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lab4.setSize(200, 210);
        lab4.setResizable(true);
        lab4.setVisible(true);
    }

}  
package view.gui;

import view.interfaces.IPaintCanvas;

import javax.swing.JComponent;

import model.interfaces.IObserver;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;


public class PaintCanvas extends JComponent implements IPaintCanvas{
	
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();         // return a graphics Object that we use to draw shapes
    }
    
}
    


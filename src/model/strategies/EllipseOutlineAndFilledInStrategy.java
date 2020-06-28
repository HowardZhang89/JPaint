package model.strategies;

import java.awt.Color;
import java.awt.Graphics2D;

import model.ColorAdapter;
import model.interfaces.IShape;
import model.persistence.IDrawStrategy;

public class EllipseOutlineAndFilledInStrategy implements IDrawStrategy{
	
	@Override 
	public void draw (IShape shape, Graphics2D graphics){
		// set x and y coordinates
		int x = (int) shape.getStartPoint().getX();
		int y = (int) shape.getStartPoint().getY();
		int width = (int) (shape.getEndPoint().getX() - x);
		int height = (int)(shape.getEndPoint().getY() - y);

		// convert ShapeColor to java.awt.Color type using adapter Pattern
		ColorAdapter colorAdapter = new ColorAdapter(shape);
		Color pColor = colorAdapter.getPrimaryColor();
		Color sColor = colorAdapter.getSecondaryColor();

		// draw filled in rectangle first
		graphics.setColor(sColor);
		graphics.fillOval(x, y, width, height);
		
		// draw outline on top 
		graphics.setColor(pColor);
		graphics.drawOval(x, y, width, height);     
	}
}

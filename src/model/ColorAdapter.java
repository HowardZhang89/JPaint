package model;

import java.awt.Color;

import model.interfaces.IColor;
import model.interfaces.IShape;

public class ColorAdapter implements IColor{
	//example of an adapter. allows IShape to call getPrimaryColor and getSecondaryColor 
	private IShape shape;
	
	public ColorAdapter(IShape shape){
		this.shape = shape;
	}
	
	public Color getPrimaryColor(){
		return getColor(shape.getPrimaryColor());
	}
	
	public Color getSecondaryColor(){
		return getColor(shape.getSecondaryColor());
	}
	
	private Color getColor(ShapeColor color){
		if(color == ShapeColor.BLACK)
			return Color.BLACK;
		else if(color == ShapeColor.BLUE)
			return Color.BLUE;
		else if(color == ShapeColor.CYAN)
			return Color.CYAN;
		else if(color == ShapeColor.DARK_GRAY)
			return Color.DARK_GRAY;
		else if(color == ShapeColor.GRAY)
			return Color.GRAY;
		else if(color == ShapeColor.GREEN)
			return Color.GREEN;
		else if(color == ShapeColor.LIGHT_GRAY)
			return Color.LIGHT_GRAY;
		else if(color == ShapeColor.MAGENTA)
			return Color.MAGENTA;
		else if(color == ShapeColor.ORANGE)
			return Color.ORANGE;
		else if(color == ShapeColor.PINK)
			return Color.PINK;
		else if(color == ShapeColor.RED)
			return Color.RED;
		else if(color == ShapeColor.WHITE)
			return Color.WHITE;
		else if(color == ShapeColor.YELLOW)
			return Color.YELLOW;
		else 
			return Color.BLACK;           // otherwise return black
		
	}
}

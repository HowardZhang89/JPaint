package model.shapes;

import java.awt.Graphics2D;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.persistence.IDrawStrategy;
import model.strategies.DrawStrategyFactory;

class Ellipse implements IShape {        // not public so only ShapeFactory can return shapes
	
	private Point start;
	private Point end;
	private boolean selected;
	private ShapeColor primaryColor;
	private ShapeColor secondaryColor;
	private ShapeShadingType shapeShadingType;
	private ShapeType shapeType;

	public Ellipse(Point start, Point end, ShapeColor primaryColor, ShapeColor secondaryColor, 
			ShapeShadingType shapeShadingType, ShapeType shapeType){                      // graphics passed in from ShapeFactory	

		this.start = start;
		this.end = end;
		this.selected = true;                              // every new shape starts off as selected (good design?) 
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.shapeShadingType = shapeShadingType;
		this.shapeType = shapeType;                  // not passing this in, should be obvious
	}
	
	@Override
	public void draw(Graphics2D graphics){  
		IDrawStrategy drawStrategy = DrawStrategyFactory.getDrawStrategy(shapeType, shapeShadingType);
		drawStrategy.draw(this, graphics);
	}
	

	// Thanks: https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
		@Override 
		public boolean overlaps(Point p1, Point p2){             // keep in mind the lower the y, the greater its value for java's implementation
			if(p1.getX() < end.getX() && p2.getX() > start.getX() &&
			   p2.getY() > start.getY() && p1.getY() < end.getY() )    
			{		
				return true;
			}
			else
				return false;	
		}
		//Getters and Setters
		@Override
		public Point getStartPoint() {
			return this.start;
		}
		
		@Override
		public void setStartPoint(Point p){
			this.start = p;
		}
		
		
		@Override
		public void setEndPoint(Point p){
			this.end = p;
		}
		
		@Override
		public Point getEndPoint() {
			return this.end;
		}
		
		@Override
		public boolean getSelected(){
			return this.selected;
		}
		@Override
		public void setSelected(boolean bool){
			this.selected = bool;
		}
		
		@Override
		public ShapeColor getPrimaryColor(){
			return this.primaryColor;
		}
		
		@Override 
		public void setPrimaryColor( ShapeColor primaryColor){
			this.primaryColor = primaryColor;
		}
		
		@Override
		public ShapeColor getSecondaryColor(){
			return this.secondaryColor;
		}
		
		@Override 
		public void setSecondaryColor( ShapeColor secondaryColor){
			this.secondaryColor = secondaryColor;
		}
		
		@Override
		public ShapeShadingType getShapeShadingType(){
			return this.shapeShadingType;
		}
		@Override
		public void setShapeShadingType( ShapeShadingType shapeShadingType){
			this.shapeShadingType = shapeShadingType;
		}
		@Override
		public ShapeType getShapeType(){
			return this.shapeType;
		}
		@Override
		public void setShapeType(ShapeType shapeType){
			this.shapeType = shapeType;
		} 

}

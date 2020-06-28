package model.strategies;

import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.IDrawStrategy;

public class DrawStrategyFactory {

	public static IDrawStrategy getDrawStrategy(ShapeType shapeType, ShapeShadingType shapeShadingType){

		if (shapeType.equals(ShapeType.RECTANGLE)){
			if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
				return new RectangleOutlineStrategy();
			}
			else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
				return new RectangleFilledInStrategy();
			}
			else { // (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
				return new RectangleOutlineAndFilledInStrategy();
			}

		}
		else if(shapeType.equals(ShapeType.ELLIPSE)){
			if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
				return new EllipseOutlineStrategy();
			}
			else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
				return new EllipseFilledInStrategy();
			}
			else { // (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
				return new EllipseOutlineAndFilledInStrategy();
			}
		}
		else if (shapeType.equals(ShapeType.TRIANGLE)){
			if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
				return new TriangleOutlineStrategy();
			}
			else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
				return new TriangleFilledInStrategy();
			}
			else { // (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
				return new TriangleOutlineAndFilledInStrategy();
			}
		}
		else
			return null;
	}
}

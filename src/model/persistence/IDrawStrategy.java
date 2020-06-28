package model.persistence;

import java.awt.Graphics2D;

import model.interfaces.IShape;

public interface IDrawStrategy {
	
	void draw(IShape shape, Graphics2D graphics);
}

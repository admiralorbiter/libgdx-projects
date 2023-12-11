package com.mygdx.game.Pathfinding;

import java.awt.Point;

public class PathfindingNode {
	Point point;
	int priority;
	
	public PathfindingNode(Point point, int priority) {
		this.point=new Point(point);
		this.priority=priority;
	}
	
	//Getters and Setters
	public Point getPoint() {return point;}
	public int getPriority() {return priority;}
}

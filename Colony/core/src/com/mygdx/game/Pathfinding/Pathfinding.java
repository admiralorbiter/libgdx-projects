package com.mygdx.game.Pathfinding;

import com.mygdx.game.Entity;
import com.mygdx.game.Utilities;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public final class Pathfinding {

	public static int rows = Utilities.getWorldSize().height;
	public static int cols = Utilities.getWorldSize().width;

	public static List<Node> findPath(Point start, Point goal, ArrayList<Point> blockedPoints) {
		Node initialNode = new Node(start);
		Node finalNode = new Node(goal);

		if(blockedPoints.contains(goal)){
			//TODO: Find the nearest open

            //this is hacked together doesn't even take into account all the possibilities
            //also need a way to set the destion for the entity
            //to the right
            if(goal.x>start.x){
                if(!blockedPoints.contains(new Point(goal.x-1, goal.y)))
                    goal=new Point(goal.x-1, goal.y);
                else if(!blockedPoints.contains(new Point(goal.x-1, goal.y+1)))
                    goal=new Point(goal.x-1, goal.y+1);
                else if(!blockedPoints.contains(new Point(goal.x-1, goal.y-1)))
                    goal=new Point(goal.x-1, goal.y-1);
            }
            else{
                if(!blockedPoints.contains(new Point(goal.x+1, goal.y)))
                    goal=new Point(goal.x+1, goal.y);
                else if(!blockedPoints.contains(new Point(goal.x+1, goal.y+1)))
                    goal=new Point(goal.x+1, goal.y+1);
                else if(!blockedPoints.contains(new Point(goal.x+1, goal.y-1)))
                    goal=new Point(goal.x+1, goal.y-1);
            }
		}

		AStar aStar = new AStar(rows, cols, initialNode, finalNode);
		aStar.setBlocks(blockedPoints);

		List<Node> path = aStar.findPath();

		return path;
	}

	//Rewrite using entity list
	public static ArrayList<Point> createBlockedPointList(Point referenceEntityPoint, List<Entity> entities) {
		ArrayList<Point> blockedList = new ArrayList<Point>();

		for(Entity entity: entities){
			blockedList.add(new Point((int)(entity.position.x), (int)(entity.position.y)));
			//TODO - Right now it doesn't work
			/*for(int x=0; x<entity.bounds.width; x++){
				for(int y=0; y<entity.bounds.height; y++){
					blockedList.add(new Point((int)(entity.position.x+x), (int)(entity.position.y+y)));
				}
			}*/
		}
		blockedList.remove(referenceEntityPoint);

		return blockedList;
	}

	/*
	public static Point findClosest(Tile[][] worldArray, String flag, Point start, boolean alive) {

		ArrayList<Point> list = new ArrayList<Point>();
		Point p = null;
		double distance=0;

		for(int x=0; x<cols; x++) {
			for(int y=0; y<rows; y++) {
				if(worldArray[x][y].hasEntity()) {
					if(worldArray[x][y].getEntity().getCharacterID().equals(flag)) {
						if(alive) {
							if(worldArray[x][y].getEntity().getAlive())
								list.add(new Point(x, y));
						}else {
							list.add(new Point(x, y));
						}
					}
				}
			}
		}

		if(list.size()>1) {
			p=new Point(list.get(0));
			distance = Point.distance(start.x,start.y, p.x, p.y);
			for(int i=1; i<list.size(); i++) {
				if(Point.distance(start.x,start.y, list.get(i).x, list.get(i).y)<distance) {
					p = new Point(list.get(i));
					distance=Point.distance(start.x,start.y, p.x, p.y);
				}
			}

			return p;
		}else if(list.size()==1) {
			return new Point(list.get(0));
		}else {
			return null;
		}
	}

	public static Point findClosest(Tile[][] worldArray, Point start, ArrayList<Point> list) {
		Point p = null;
		double distance=0;

		if(list.size()>1) {
			p=new Point(list.get(0));
			distance = Point.distance(start.x,start.y, p.x, p.y);
			for(int i=1; i<list.size(); i++) {
				if(Point.distance(start.x,start.y, list.get(i).x, list.get(i).y)<distance) {
					p = new Point(list.get(i));
					distance=Point.distance(start.x,start.y, p.x, p.y);
				}
			}

			return p;
		}else if(list.size()==1) {
			return new Point(list.get(0));
		}else {
			return null;
		}
	}

	 */
	/*
	public static Point findClosestMaterial(Tile[][] worldArray, Material material, Point start) {

		ArrayList<Point> list = new ArrayList<Point>();
		Point p = null;
		double distance=0;

		for(int x=0; x<cols; x++) {
			for(int y=0; y<rows; y++) {
				if(worldArray[x][y].hasMaterial()) {
					if(worldArray[x][y].getMaterial().getName().equals(material.getName())) {
						list.add(new Point(x, y));
					}
				}
			}
		}

		if(list.size()>1) {
			p=new Point(list.get(0));
			distance = Point.distance(start.x,start.y, p.x, p.y);
			for(int i=1; i<list.size(); i++) {
				if(Point.distance(start.x,start.y, list.get(i).x, list.get(i).y)<distance) {
					p = new Point(list.get(i));
					distance=Point.distance(start.x,start.y, p.x, p.y);
				}
			}

			return p;
		}else if(list.size()==1) {
			return new Point(list.get(0));
		}else {
			return null;
		}
	}


	public static IncompleteStructure findClosestIncompleteStructure(Point start, ArrayList<IncompleteStructure> list) {

		IncompleteStructure goal = null;
		double distance=0;

		if(list.size()>1) {
			goal=list.get(0);
			distance = Point.distance(start.x,start.y, goal.getPosition().x, goal.getPosition().y);
			for(int i=1; i<list.size(); i++) {
				if(Point.distance(start.x,start.y, list.get(i).getPosition().x, list.get(i).getPosition().y)<distance) {
					goal = list.get(i);
					distance=Point.distance(start.x,start.y, goal.getPosition().x, goal.getPosition().y);
				}
			}

			return goal;
		}else if(list.size()==1) {
			return list.get(0);
		}else {
			return null;
		}
	}

	 */
}

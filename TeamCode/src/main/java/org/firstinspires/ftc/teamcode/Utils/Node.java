package org.firstinspires.ftc.teamcode.Utils;

import java.util.ArrayList;

public class Node {
    Node parent = null;
    int x;
    int y;
    double f;
    double g;
    double h;
    
    public Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Node (int x, int y, Node parent, Node goal) {
        this(x, y);
        this.parent = parent;
        this.g = parent.g + Math.sqrt(Math.pow(parent.x - x, 2) + Math.pow(parent.y - y, 2));
        double dx = Math.abs(x - goal.x);
        double dy = Math.abs(y - goal.y);
        this.h = (dx + dy) + (Math.sqrt(2) - 2) * Math.min(dx, dy); //517.7320151
        //this.h = Math.sqrt(Math.pow(goal.y - y, 2) + Math.pow(goal.x + x, 2));
        System.out.println(this.h);
        System.out.println(this.g);
        System.out.println("");
        this.f = this.g + this.h;
    }
    public boolean equals (Node thing) {
        if (thing.x == this.x && thing.y == this.y) {
            return true;
        }
        return false;
        
    }
    public boolean isTraversable(ArrayList<ArrayList<Integer>> maze) {
        if (maze.get(this.y).get(this.x).equals(1)) {
            return false;
        }
        return true;
        
    }
    
}


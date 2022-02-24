package org.firstinspires.ftc.teamcode.Utils;
import org.firstinspires.ftc.teamcode.Utils.Node;
import java.util.ArrayList;
import java.math.*;
import java.util.List;

public class AStar {
    // recover the path from the closed List. Start at end Node and work backwards through parents.
    // recursive utility method
    public static ArrayList<int[]> traceBack (Node node, ArrayList<int[]> path) {
        
        if (!(node.parent == null)) {
            traceBack(node.parent, path);
        }
        path.add(new int[]{node.x, node.y});
        return path;
    }
    
    public static ArrayList<int[]> find(ArrayList<ArrayList<Integer>> maze, int startX, int startY, int endX, int endY) {
        // create list for storing known and chosen Nodes
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();
        // create start and end Nodes
        Node start = new Node(startX, startY);
        start.f = start.h = start.g = 0.0;
        Node end = new Node(endX, endY);
        // add start Node to openList BC it is the only known Node currently
        openList.add(start);
    
        int[][] adjacent_squares = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
        
        while (!openList.isEmpty()) {
            //removes the lowest F Node from the open List and sets it as current
            Node current = openList.get(openList.size() - 1);
            for (int i = 0; i < openList.size()-1; i++) {
                if (openList.get(i).f < current.f) {
                    current = openList.get(i);
                }
            }
            openList.remove(current);
            //System.out.print(current.x+",");
            //System.out.println(current.y+"");
            // array to hold adjacent squares called children and loop to create them
            ArrayList<Node> children = new ArrayList<Node>();
            for(int i = 0; i < adjacent_squares.length; i++) {
                children.add(new Node(current.x + adjacent_squares[i][0], current.y + adjacent_squares[i][1], current, end));
            }
            // check validity of children
            outerloop:
            for(int i = 0; i < children.size(); i++) {
                // if child is not traversable skip it
                if(!children.get(i).isTraversable(maze)) {
                    continue outerloop;
                }
                // if child is equal to node on closedList but with a higher ot equal f, then skip
                for (Node node : closedList) {
                    if (children.get(i).equals(node)) {
                        if (children.get(i).f >= node.f) {
                            continue outerloop;
                        }
                    }
                }
                // if child is equal to node on openList but with a lower g, update the g and parent of the node
                for (Node node : openList) {
                    if (children.get(i).equals(node) && (children.get(i). g < node.g)) {
                        node.g = children.get(i).g;
                        node.f = children.get(i).g + children.get(i).h;
                        node.parent = children.get(i).parent;
                        continue outerloop;
                    }
                    // if child is already in openList but with a higher g then skip it
                    else if (children.get(i).equals(node) && (children.get(i). g >= node.g)) {
                        continue outerloop;
                    }
                }
                //if child is valid add it to the open List
                openList.add(children.get(i));
            }
            // add current to the closedList
            closedList.add(current);
            // if the current is the end Node then stop searching
            if (current.equals(end)) {
                // ArrayList to hold Path
                ArrayList<int[]> path = new ArrayList<int[]>();
                //recursively get path and return it.
                return traceBack(closedList.get(closedList.size() -1), path);
            }
        }
        // ArrayList to hold Path
        ArrayList<int[]> path = new ArrayList<int[]>();
        //recursively get path and return it.
        return traceBack(closedList.get(closedList.size() -1), path);
        
    }
    
    

}

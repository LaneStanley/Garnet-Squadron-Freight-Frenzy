package org.firstinspires.ftc.teamcode.Utils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

class PathGenerator {
    public static void main(String[] args) throws IOException {
        
        // load maze
        Scanner scanner = new Scanner(new File("A:\\Garnet-Squadron-Freight-Frenzy\\TeamCode\\src\\main\\java\\org" +
                "\\firstinspires\\ftc\\teamcode\\Utils\\maze"));
        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        
        while(scanner.hasNextLine()) {
            //get line of file
            String[] row = scanner.nextLine().split(" " );
            ArrayList<Integer> x = new ArrayList<Integer>();
            for(int i = 0; i < row.length; i++ ) {
                x.add(Integer.parseInt(row[i]));
            }
            maze.add(x);
        }
        // creating a Calendar object
        ArrayList<int[]> path = AStar.find(maze, 50,50, 350, 350 );//44, 44);
        FileWriter myWriter = new FileWriter("A:\\Garnet-Squadron-Freight-Frenzy\\TeamCode\\src\\main\\java" +
                "\\org\\firstinspires\\ftc\\teamcode\\Utils\\path");
    
        for (int i = 0; i < path.size(); i++) {
            int x = path.get(i)[0];
            int y = path.get(i)[1];
            maze.get(y).set(x, 7);
        }
        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j < maze.get(i).size(); j++) {
                myWriter.write( maze.get( i ).get( j ) + "" );
                myWriter.write( " " );
            }
            myWriter.write("\n");
        }
        myWriter.close();
    }
}

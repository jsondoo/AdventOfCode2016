import java.util.*;

public class Main {
    public enum Direction{
        N, W, E, S
    }

    private static int x = 0;
    private static int y = 0;
    private static Direction dir = Direction.N;

    private static ArrayList<Coordinates> points = new ArrayList<>();
    private static int answer;
    private static boolean answerFound = false;

    public static void main(String[] args) {
        String input = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1";
        String[] parts = input.split(",\\s*");

        /* PART 1
        for(String s : parts) {
            char letter = s.charAt(0);
            int number = Integer.parseInt(s.substring(1));
            dir = getNewDirection(dir, letter);
            move(number);
        }
        int answer = Math.abs(x)+Math.abs(y);
        System.out.println(answer);
        */

        // PART 2
        points.add(new Coordinates(0,0));
        for(int i = 0; i < parts.length && !answerFound ; i++) {
            char letter = parts[i].charAt(0);
            int number = Integer.parseInt(parts[i].substring(1));
            dir = getNewDirection(dir, letter);
            move(number);
        }
        System.out.println(answer);


    }

    public static void move(int number){
        if(dir == Direction.N){
            while(number>0){
                y++;
                Coordinates point = new Coordinates(x,y);
                if(points.contains(point)){
                    answer = point.getDistance();
                    answerFound = true;
                }
                points.add(point);
                number--;
            }
        }
        else if(dir == Direction.E){
            while(number>0){
                x++;
                Coordinates point = new Coordinates(x,y);
                if(points.contains(point)){
                    answer = point.getDistance();
                    answerFound = true;
                }
                points.add(point);
                number--;
            }
        }
        else if(dir == Direction.S){
            while(number>0){
                y--;
                Coordinates point = new Coordinates(x,y);
                if(points.contains(point)){
                    answer = point.getDistance();
                    answerFound = true;
                }
                points.add(point);
                number--;
            }
        }
        else if(dir == Direction.W){
            while(number>0){
                x--;
                Coordinates point = new Coordinates(x,y);
                if(points.contains(point)){
                    answer = point.getDistance();
                    answerFound = true;
                }
                points.add(point);
                number--;
            }
        }
    }

    public static Direction getNewDirection(Direction currDir, char turnDir) {
        if (currDir == Direction.N) {
            if (turnDir == 'R')
                return Direction.E;
            else
                return Direction.W;
        }
        else if (currDir == Direction.E) {
            if (turnDir == 'R')
                return Direction.S;
            else
                return Direction.N;
        }
        else if (currDir == Direction.S) {
            if (turnDir == 'R')
                return Direction.W;
            else
                return Direction.E;
        }
        else if (currDir == Direction.W) {
            if (turnDir == 'R')
                return Direction.N;
            else
                return Direction.S;
        }

        // should never reach these lines
        System.out.println("Error");
        return currDir;
    }
}
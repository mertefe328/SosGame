import java.util.Random;
import java.util.Scanner;

public class SOS { 
    public static char[][] grid;
    public static int gridSize = -1;
    public static char player1 = 'N';
    public static char player2 = 'N';
    public static char currentPlayer = 'N';

    public static int player1Score = 0;
    public static int player2Score = 0;

    public static void main(String[] args) {
        System.out.print("Enter grid size n [3, 4, 5, 6, 7]: ");
        Scanner myObj = new Scanner(System.in);
        gridSize = myObj.nextInt();
        initializeGrid();
        assignTeamRandomly();
        System.out.println("Game is started. Your Team is: " + String.valueOf(player1));
        printGrid();

        while (true) {
           makeMove();
           printGrid();
           if (checkGameIsOver()) {
                break;
            }
        }
        System.out.println("Player1 Score: " + player1Score);
        System.out.println("Player2 Score: " + player2Score);
        if (player1Score > player2Score) {
            System.out.println("You Won!!!!");
        } else if (player1Score < player2Score) {
            System.out.println("You Lost :(");
        } else {
            System.out.println("Draw, nobody won!");
        }

    }
    public static boolean isCellInsideGrid(int x, int y){
     if (x < 0 || ( x > gridSize - 1) || y < 0 || ( y > gridSize - 1)) {
        return false;
     }
     return true;

    }
    public static boolean isMoveValid(int x, int y){
        // check the limits.
        if (!isCellInsideGrid(x,y)) {
            return false;
        }
        // check grid is already filled.
        return grid[x][y] == 'N';
    }   
    public static void makeMove(){
        if (currentPlayer == player1) {
            System.out.print("Enter x and y: ");
            Scanner myObj1 = new Scanner(System.in);
            int x = myObj1.nextInt();
            int y = myObj1.nextInt();
            while(!isMoveValid(x, y)) {
                System.out.print("Wrong x and y, enter again: ");
                Scanner myObj2 = new Scanner(System.in);
                x = myObj2.nextInt();
                y = myObj2.nextInt();  
            }
            grid[x][y] = player1;
            System.out.println("You played with: " + x + " and " + y);
            
            // calculate moveScore now
            int currentScore = moveScore(x, y);
            player1Score += currentScore;

            // change turn.
            currentPlayer = player2;


        } else { // computer is playing.
            int x = new Random().nextInt(gridSize);
            int y = new Random().nextInt(gridSize);
            while(!isMoveValid(x, y)) {
                System.out.println("Computer made wrong and entered " + x + " and " + y);
                x = new Random().nextInt(gridSize);
                y = new Random().nextInt(gridSize);
            }
            grid[x][y] = player2;
            System.out.println("Computer played with: " + x + " and " + y);
            
            int currentScore = moveScore(x, y);
            player2Score += currentScore;
            
            // change turn.
            currentPlayer = player1;
        }

    }

    public static void initializeGrid() {
        grid = new char[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 'N';
            }
        }
    }
    public static void assignTeamRandomly(){
        if ((new Random().nextInt(100) % 2 == 0)) {
            player1 = 'S';
            player2 = 'O';
            currentPlayer = 'S';
            
        } else {
            player1 = 'O';
            player2 = 'S';
            currentPlayer = 'O';
        }
    }

    public static void printGrid() {
        System.out.println("=================");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(String.valueOf(grid[i][j]) + ' ');
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    public static boolean checkGameIsOver(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 'N') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int moveScore(int x, int y) {
        int moveScore = 0;
        if ( isCellInsideGrid(x-1, y) && isCellInsideGrid(x+1, y)  && grid[x-1][y] == 'S' && grid[x][y] == 'O' && grid[x+1][y] == 'S') {
            moveScore++;
        }
        if (isCellInsideGrid(x, y+1) && isCellInsideGrid(x, y-1) && grid[x][y+1] == 'S' && grid[x][y] == 'O' && grid[x][y-1] == 'S') {
            moveScore++;
        }
        return moveScore;
    }

}
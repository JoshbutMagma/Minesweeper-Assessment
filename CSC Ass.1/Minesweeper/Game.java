
/**
 * Play a game of Minesweeper
 *
 * Joshua Hunter
 * 06/05-
 */
import java.util.Scanner;
import java.util.Random;
public class Game
{
    static void print(String[][] board)
    {
        System.out.print("  ");
        for (int i=0; i<10; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i=1; i<9; i++){
            for (int j=1; j<11; j++){
                if(j==1){
                    System.out.print((i-1) + " ");
                }
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        //innitializing variables, scanner, and board
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();
        String[][] board = new String[12][10];
        String[][] underBoard = new String[12][10];
        int bombsPlaced = 0;
        int bombX;
        int bombY;
        int xTest;
        int yTest;
        int nearbyBombs = 0;
        String method;
        boolean gameRunning = true;
        
        //Creating the initial board
        for (int i=1; i<9; i++){
            for (int j=1; j<11; j++){
                board[j][i] = "X";
                underBoard[j][i] = " ";
            }
        }
        print(board);
        
        //Randomly planting bombs
        while (bombsPlaced<10){
            bombX = random.nextInt(10)+1;
            bombY = random.nextInt(8)+1;
            if(underBoard[bombX][bombY]!="B"){
                underBoard[bombX][bombY] = "B";
                bombsPlaced++;
            }
        }
        //Fill the board with numbers
        for (int i=1; i<9; i++){
            for (int j=1; j<11; j++){
                if (underBoard[j][i]!="B"){
                    for (int k=0; k<3; k++){
                        for (int l=0; l<3; l++){
                            if (underBoard[(j+k-1)][(i+l-1)]=="B"){
                                nearbyBombs++;
                            }
                        }
                    }
                    underBoard[j][i] = String.valueOf(nearbyBombs);
                    nearbyBombs = 0;
                }
            }
        }
        for (int i=1; i<9; i++){
            for (int j=1; j<11; j++){
                if (underBoard[j][i].equals("0")){
                    underBoard[j][i] = " ";
                }
            }
        }
        
        //Playing the game
        while (gameRunning){
            //Inputting tiles
            System.out.println("Please enter the method you'd like to test with. Type T to test the tile, and F to flag the tile");
            method = keyboard.nextLine();
            method = method.toUpperCase();
            System.out.println("Please enter the column you would like to test");
            xTest = keyboard.nextInt()+1;
            System.out.println("Please enter the row you would like to test");
            yTest = keyboard.nextInt()+1;
            keyboard.nextLine();

            //Reveal the corresponding square that was entered, and print the new board
            if(method.equals("T")&&!(board[xTest][yTest].equals("F"))){
                board[xTest][yTest] = underBoard[xTest][yTest];
            }else if(method.equals("F")&&!(board[xTest][yTest].equals("F"))){
                board[xTest][yTest] = "F";
            }else if(method.equals("F")&&board[xTest][yTest].equals("F")){
                board[xTest][yTest] = "X";
            }else{
                System.out.println("Bad input, try again");
            }
            for(int c=0; c<10; c++){
                for (int i=1; i<9; i++){
                    for (int j=1; j<11; j++){
                        if (board[j][i]==" "){
                            for (int k=0; k<3; k++){
                                for (int l=0; l<3; l++){
                                    if ((j+k-1)!=0&&(i+l-1)!=0){
                                        board[(j+k-1)][(i+l-1)] = underBoard[(j+k-1)][(i+l-1)];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            print(board);
            
            //Was that turn Game Over?
            if(board[xTest][yTest].equals("B")){
                System.out.println("Game over");
                gameRunning = false;
            }
        }
    }
}

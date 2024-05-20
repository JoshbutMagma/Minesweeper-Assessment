
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
        for (int i=0; i<8; i++){
            for (int j=0; j<10; j++){
                if(j==0){
                    System.out.print(i + " ");
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
        String[][] board = new String[10][8];
        String[][] underBoard = new String[10][8];
        int bombsPlaced = 0;
        int bombX;
        int bombY;
        int xTest;
        int yTest;
        int safeTilesLeft;
        boolean gameRunning = true;
        
        //Creating the initial board
        for (int i=0; i<8; i++){
            for (int j=0; j<10; j++){
                board[j][i] = "X";
                underBoard[j][i] = " ";
            }
        }
        
        //Randomly planting bombs
        while (bombsPlaced<10){
            bombX = random.nextInt(10);
            bombY = random.nextInt(8);
            if(underBoard[bombX][bombY]!="B"){
                underBoard[bombX][bombY] = "B";
                bombsPlaced++;
            }
        }
        
        //Printing the board
        print(board);
        
        //Playing the game
        while (gameRunning){
            //Inputting tiles
            System.out.println("Please enter the column you would like to test");
            xTest = keyboard.nextInt();
            System.out.println("Please enter the row you would like to test");
            yTest = keyboard.nextInt();
            //Reveal the corresponding square that was entered
            board[xTest][yTest] = underBoard[xTest][yTest];
            
            //Printing the new board
            print(board);
            
            //Was that turn Game Over?
            if(board[xTest][yTest]=="B"){
                System.out.println("Game over");
                gameRunning = false;
            }
        }
    }
}

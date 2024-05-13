
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
    public static void main(String args[])
    {
        //innitializing variables, scanner, and board
        Scanner keyboard = new Scanner(System.in);
        String[][] board = new String[10][8];
        int xTest;
        int yTest;
        boolean gameRunning = true;
        
        //Creating and printing the innitial board
        for (int i=0; i<8; i++){
            for (int j=0; j<10; j++){
                board[j][i] = "X";
            }
        }
        for (int i=0; i<8; i++){
            for (int j=0; j<10; j++){
                /**if(!j==9){
                     System.out.print(board[j][i] + " ");
                }*/
            }
            System.out.println();
        }
        
        while (gameRunning){
            System.out.println("Please enter the column you would like to test");
            xTest = (keyboard.nextInt()-1);
            if (xTest==-1){
                gameRunning = false;
            }
            System.out.println("Please enter the row you would like to test");
            yTest = (keyboard.nextInt()-1);
            board[xTest][yTest] = " ";
            for (int i=0; i<8; i++){
                for (int j=0; j<10; j++){
                    /**if(!j==9){
                        System.out.print(board[j][i] + " ");
                    }*/
                }
                System.out.println();
            }
        }
    }
}

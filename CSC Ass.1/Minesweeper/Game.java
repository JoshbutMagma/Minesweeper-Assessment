
/**
 * Play a game of Minesweeper
 * By default, it uses Google Minesweeper easy settings (10/8 board, 10 bombs), however it's easily changeable in the code by replacing the values for "bombs", "X", and "Y"
 *
 * Joshua Hunter
 * 06/05
 * 
 * Suggestions:
 * Try formatting the numbers on the board better (not starting at 0, using letters, etc)
 */
import java.util.Scanner;
import java.util.Random;
public class Game
{
    /**
     * This method goes allows the board to print by looking at every tile and printing what it is
     * The method intakes the current board state as a 2D string array, as well as the values of how large the board is as integers
     */
    static void print(String[][] board, int X, int Y)
    {
        //This loop, and the similar one below, prints numbers at the top of the screen for reference when inputting a tile
        System.out.print("  ");
        for (int i=0; i<X; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
                //This loop, and the similar one below, prints the numbers on the side for reference when inputting a tile
                if(j==1){
                    System.out.print((i-1) + " ");
                }
                System.out.print(board[j][i] + " ");
                if(j==X){
                    System.out.print(i-1);
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i=0; i<X; i++){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    /**
     * This method determines whether or not an input is an integer, or if it isn't and needs to be re-inputted
     * The method intakes an integer to use for testing
     * The method returns the valid integer
     */
    static int input(int xTest)
    {
        Scanner Keyboard = new Scanner(System.in);
        while(!Keyboard.hasNextInt()){
            System.out.println("Input error, please try again");
            Keyboard.nextLine();
        }
        int num = Keyboard.nextInt();
        return num;
    }
    /**
     * This is where the game is made and played
     */
    public static void main(String[] args)
    {
        //innitializing variables, scanner, and board
        System.out.print('\u000c');
        int bombs = 10;
        int X = 10;
        int Y = 8;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();
        String[][] board = new String[X+2][Y+2];
        String[][] underBoard = new String[X+2][Y+2];
        int bombX;
        int bombY;
        int num = 0;
        int xTest;
        int yTest;
        int bombsPlaced = 0;
        int nearbyBombs = 0;
        int flagsPlaced = 0;
        int tilesLeft = 0;
        int turn = 1;
        String method;
        boolean gameRunning = true;
        
        //Creating the initial board. The board is what is played on and the underBoard contains the bombs and numbers
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
                board[j][i] = "■";
                underBoard[j][i] = "·";
            }
        }
        print(board,X,Y);
        
        //Randomly planting bombs
        while (bombsPlaced<bombs){
            bombX = random.nextInt(X)+1;
            bombY = random.nextInt(Y)+1;
            if(underBoard[bombX][bombY]!="💣"){//This checks for if a bomb is already in a tile, so that we do have exactly X bombs instead of doubling up
                underBoard[bombX][bombY] = "💣";
                bombsPlaced++;
            }
        }
        //Fill the board with numbers. j and i allow the program to repeat on every tile
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
                if (underBoard[j][i]!="💣"){
                    //Then, it checks each nearby cell for if it's a bob, and adds +1 to itself
                    for (int k=0; k<3; k++){
                        for (int l=0; l<3; l++){
                            if (underBoard[(j+k-1)][(i+l-1)]=="💣"){
                                nearbyBombs++;
                            }
                        }
                    }
                    underBoard[j][i] = String.valueOf(nearbyBombs);
                    nearbyBombs = 0;
                }
            }
        }
        //Replace every 0 with a "·"
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
                if (underBoard[j][i].equals("0")){
                    underBoard[j][i] = "·";
                }
            }
        }
        
        //Playing the game
        System.out.println("You've placed 0/" + bombs + " flags.");
        while (gameRunning){
            //Inputting tiles
            System.out.println("Please enter the method you'd like to test with. Type T to test the tile, and F to flag the tile");
            method = keyboard.nextLine();
            method = method.toUpperCase();
            System.out.println("Please enter the column you would like to test");
            xTest = input(num)+1;
            System.out.println("Please enter the row you would like to test");
            yTest = input(num)+1;
            System.out.print('\u000c');
            
            //This if bracket around everything prevents invalid integers crashing the game
            if(method.equals("T")||method.equals("F")&&xTest<(X+1)&&xTest>-1&&yTest<(Y+1)&&yTest>-1){
                //Reveal the corresponding square that was entered, and print the new board
                if(method.equals("T")&&!(board[xTest][yTest].equals("⚑"))){
                    board[xTest][yTest] = underBoard[xTest][yTest];
                }else if(method.equals("F")&&(board[xTest][yTest].equals("■"))&&!(flagsPlaced==bombs)){
                    board[xTest][yTest] = "⚑";
                    flagsPlaced++;
                }else if(method.equals("F")&&board[xTest][yTest].equals("⚑")){
                    board[xTest][yTest] = "■";
                    flagsPlaced--;
                }
                
                //Clears tiles automatically. c repeats the program so that the board is fully able to clear from the bottom right corner to the top left if needed. j and i check every tile
                for(int c=0; c<15; c++){
                    for (int i=1; i<(Y+1); i++){
                        for (int j=1; j<(X+1); j++){
                            //The program then checks if a tile is blank, and reveals any tile in an area around the blank.
                            if (board[j][i]=="·"){
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
                
                //Checks if any tile is still covered up, and stores that to determine if the game is won
                for(int i=1; i<(Y+1); i++){
                    for(int j=1; j<(X+1); j++){
                        if(board[j][i]=="■"||board[j][i]=="💣"){
                            tilesLeft++;
                        }
                    }
                }
                
                //Prints the board and flag count if the game is still running
                if(!board[xTest][yTest].equals("💣")&&tilesLeft!=0){
                    print(board,X,Y);
                    System.out.println("You've placed " + flagsPlaced + "/" + bombs + " flags.");
                }
                
                //Was that turn Game Over? This checks for and outputs if a win or loss happens
                if(board[xTest][yTest].equals("💣")){
                    print(board,X,Y);
                    System.out.println("Game over");
                    gameRunning = false;
                }
                if(tilesLeft==0){
                    print(board,X,Y);
                    System.out.println("You win! Congratulations! You won in " + turn + " turns.");
                    gameRunning = false;
                }
                turn++;
                tilesLeft = 0;
            }else{
                //If the input doesn't work, 
                System.out.println("Bad input, try again");
                print(board,X,Y);
                System.out.println("You've placed " + flagsPlaced + "/" + bombs + " flags.");
            }
        }
    }
}

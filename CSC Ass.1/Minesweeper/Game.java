
/**
 * Play a game of Minesweeper
 * By default, it uses Google Minesweeper easy settings (10/8 board, 10 bombs), however it's easily changeable in the code by replacing the values for "bombs", "X", and "Y"
 *
 * Joshua Hunter
 * 06/05-
 * 
 * Suggestions:
 * Try formatting the numbers on the board better (not starting at 0, using letters, etc)
 */
import java.util.Scanner;
import java.util.Random;
public class Game
{
    static void print(String[][] board, int X, int Y)
    {
        System.out.print("  ");
        for (int i=0; i<X; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
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
    static int input(int xTest)
    {
        Scanner Keyboard = new Scanner(System.in);
        while(!Keyboard.hasNextInt()){
            System.out.println("Input error");
            Keyboard.nextLine();
        }
        int num = Keyboard.nextInt();
        return num;
    }
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
        
        //Creating the initial board
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
            if(underBoard[bombX][bombY]!="💣"){
                underBoard[bombX][bombY] = "💣";
                bombsPlaced++;
            }
        }
        //Fill the board with numbers. j and i allow the program to repeat on every tile. Then, it checks if the cell is a bomb, and adds +1 to each nearby square.
        for (int i=1; i<(Y+1); i++){
            for (int j=1; j<(X+1); j++){
                if (underBoard[j][i]!="💣"){
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
            
            //This "if" bracket around everything prevents invalid integers crashing the game
            if(xTest<(X+1)&&xTest>-1&&yTest<(Y+1)&&yTest>-1){
                //Reveal the corresponding square that was entered, and print the new board
                if(method.equals("T")&&!(board[xTest][yTest].equals("⚑"))){
                    board[xTest][yTest] = underBoard[xTest][yTest];
                }else if(method.equals("F")&&(board[xTest][yTest].equals("■"))&&!(flagsPlaced==bombs)){
                    board[xTest][yTest] = "⚑";
                    flagsPlaced++;
                }else if(method.equals("F")&&board[xTest][yTest].equals("⚑")){
                    board[xTest][yTest] = "■";
                    flagsPlaced--;
                }else{
                    System.out.println("Bad input, try again");
                }
                
                //Clears tiles automatically. c repeats the program so that the board is fully able to clear from the bottom right corner to the top left if needed. j and i check every tile. The program then checks if a tile is blank, and reveals any tile in an area around the blank.
                for(int c=0; c<15; c++){
                    for (int i=1; i<(Y+1); i++){
                        for (int j=1; j<(X+1); j++){
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
                
                //Checks if any tile is still covered up, and stores that to determine if the game is won.
                for(int i=1; i<(Y+1); i++){
                    for(int j=1; j<(X+1); j++){
                        if(board[j][i]=="■"||board[j][i]=="💣"){
                            tilesLeft++;
                        }
                    }
                }
                
                if(!board[xTest][yTest].equals("💣")&&tilesLeft!=0){
                    print(board,X,Y);
                    System.out.println("You've placed " + flagsPlaced + "/" + bombs + " flags.");
                }
                
                //Was that turn Game Over?
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
                System.out.println("Bad input, try again");
                print(board,X,Y);
                System.out.println("You've placed " + flagsPlaced + "/" + bombs + " flags.");
            }
        }
    }
}

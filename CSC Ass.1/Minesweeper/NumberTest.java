
/**
 * Allow the user to input bombs and see the outputted numbers
 *
 * Joshua Hunter
 * 27/05/24
 */
import java.util.Scanner;
public class NumberTest
{
    public static void main(String[] args)
    {
        //innitializing variables, scanner, and board
        Scanner keyboard = new Scanner(System.in);
        String[][] underBoard = new String[12][10];
        boolean gameRunning = true;
        int bombX;
        int bombY;
        int nearbyBombs = 0;
        String bombs;
        
        //Creating the initial board
        for (int i=0; i<8; i++){
            for (int j=0; j<10; j++){
                underBoard[j][i] = " ";
            }
        }
        
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
                System.out.print(underBoard[j][i] + " ");
            }
            System.out.println();
        }
        
        while (gameRunning){
            bombX = keyboard.nextInt();
            bombY = keyboard.nextInt();
            
            underBoard[bombX+1][bombY] = "B";
            
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
            
            System.out.print("  ");
            for (int i=0; i<10; i++){
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i=1; i<9; i++){
                for (int j=1; j<11; j++){
                    if(j==1){
                        System.out.print(i + " ");
                    }
                    if (underBoard[j][i]=="0"){
                        underBoard[j][i] = "  ";
                    }else{
                        System.out.print(underBoard[j][i] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}

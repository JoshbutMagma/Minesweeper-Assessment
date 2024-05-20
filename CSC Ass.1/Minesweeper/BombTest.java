
/**
 * Randomly print 10 screens of bombs
 *
 * Joshua Hunter
 * 21/05/24
 */
import java.util.Random;
public class BombTest
{
    public static void main(String[] args)
    {
        Random random = new Random();
        String[][] underBoard = new String[10][8];
        int bombsPlaced;
        int bombX;
        int bombY;
        
        for (int h=0; h<10; h++){
            //Creating the initial board
            for (int i=0; i<8; i++){
                for (int j=0; j<10; j++){
                    underBoard[j][i] = " ";
                }
            }
            
            //Randomly planting bombs
            bombsPlaced = 0;
            while (bombsPlaced<10){
                bombX = random.nextInt(10);
                bombY = random.nextInt(8);
                if(underBoard[bombX][bombY]!="B"){
                    underBoard[bombX][bombY] = "B";
                    bombsPlaced++;
                }
            }
            
            //Printing the board
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
        }
    }
}

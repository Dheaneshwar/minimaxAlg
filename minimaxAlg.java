
import java.util.*;

public class minimaxAlg{
    private static void print(int[][] board, char choice){
        System.out.println(" +---+---+---+");
        for(int i=0;i<3;i++){
            System.out.print(" | ");
            for(int j=0;j<3;j++){
                char ch;
                if(board[i][j]==1)ch=choice;
                else if(board[i][j]==0){
                    if(choice=='X')ch='O';
                    else ch='X';
                }
                else ch=' ';
                System.out.print(ch+" | ");
            }
            System.out.println();
            System.out.println(" +---+---+---+");
        }
        System.out.println();
    }
    private static int finalResult(int[][] grid){
        for(int i=0;i<3;i++){
            //check rows
            if((grid[i][0]==grid[i][1]) && (grid[i][0]==grid[i][2]) && (grid[i][0]!=-1))return(grid[i][0]);
            //check cols
            if((grid[0][i]==grid[1][i]) && (grid[0][i]==grid[2][i]) && (grid[0][i]!=-1))return(grid[0][i]);
        }
        //check nw to se diag
        if((grid[0][0]==grid[1][1])&&(grid[1][1]==grid[2][2])&&(grid[0][0]!=-1))return(grid[0][0]);
        //check ne to sw diag
        if((grid[0][2]==grid[1][1])&&(grid[1][1]==grid[0][2])&&(grid[0][2]!=-1))return(grid[0][2]);
        return(-1);
    }
    public static void main(String[] args) {
        int[][] board=new int[3][3];
        Random rand=new Random();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)board[i][j]=-1;
        }
        int moves=0;
        //int currPlayer=1; //1 and 0 players, -1 for null (commented out as nxt moves r handled)
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose your symbol (O/X):");
        char choice=sc.next().charAt(0);
        System.out.println("YOUR CHOICE:"+choice);
        while(moves<9){
            System.out.print("Enter row:");
            int row=sc.nextInt();
            System.out.print("Enter col:");
            int col=sc.nextInt();
            if(board[row][col]!=-1){
                System.out.println("SPOT ALREADY TAKEN, TRY AGAIN!!");
                continue;
            }
            board[row][col]=1; 
            print(board, choice);
            //currPlayer^=1; //toggle
            moves+=1;
            int res=finalResult(board);

            if(res==1){
                System.out.println("PLAYER WINS");
                break;
            }
            else if(res==0){
                System.out.println("COMPUTER WINS");
                break;
            }
            else if(moves==9){
                System.out.println("DRAW!");
                break;
            }

            int compRow=rand.nextInt(3);
            int compCol=rand.nextInt(3);
            while(board[compRow][compCol]!=-1){
                compRow=rand.nextInt(3);
                compCol=rand.nextInt(3);
            }
            System.out.println("Computer chooses "+compRow+" and "+compCol);
            board[compRow][compCol]=0;
            print(board,choice);

            res=finalResult(board);
            if(res==1){
                System.out.println("PLAYER WINS");
                break;
            }
            else if(res==0){
                System.out.println("COMPUTER WINS");
                break;
            }
            else if(moves==9){
                System.out.println("DRAW!");
                break;
            }
        }
    }
}
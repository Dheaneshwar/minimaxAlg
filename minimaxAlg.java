
import java.util.*;

public class minimaxAlg{
    private static void print(int[][] board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                char ch;
                if(board[i][j]==1)ch='X';
                else if(board[i][j]==0) ch='O';
                else ch='_';
                System.out.print(ch+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static int finalResult(int[][] grid){
        for(int i=0;i<3;i++){
            //check rows
            if((grid[i][0]==grid[i][1]) && (grid[i][0]==grid[i][2]) && (grid[i][0]!=0))return(grid[i][0]);
            //check cols
            if((grid[0][i]==grid[1][i]) && (grid[0][i]==grid[2][i]) && (grid[0][i]!=0))return(grid[0][i]);
        }
        //check nw to se diag
        if((grid[0][0]==grid[1][1])&&(grid[1][1]==grid[2][2])&&(grid[0][0]!=0))return(grid[0][0]);
        //check ne to sw diag
        if((grid[0][2]==grid[1][1])&&(grid[1][1]==grid[0][2])&&(grid[0][2]!=0))return(grid[0][2]);
        return(0);
    }
    public static void main(String[] args) {
        int[][] board=new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)board[i][j]=-1;
        }
        int moves=0;
        int currPlayer=1; //1 and 0 players, -1 for null
        Scanner sc=new Scanner(System.in);
        while(moves<9){
            System.out.print("Enter row:");
            int row=sc.nextInt();
            System.out.print("Enter col:");
            int col=sc.nextInt();
            if(board[row][col]!=-1){
                System.out.println("SPOT ALREADY TAKEN, TRY AGAIN!!");
                continue;
            }
            board[row][col]=currPlayer; 
            print(board);
            currPlayer^=1; //toggle
            moves+=1;
            int res=finalResult(board);
            if(res==1){
                System.out.println("PLAYER X WINS");
                break;
            }
            else if(res==2){
                System.out.println("PLAYER Y WINS");
                break;
            }
            else if(moves==9){
                System.out.println("DRAW");
                break;
            }
        }
    }
}
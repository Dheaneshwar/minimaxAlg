import java.util.*;
public class vsCompPlay {
    private static void print(char[][] board){
        System.out.println(" +---+---+---+");
        for(int i=0;i<3;i++){
            System.out.print(" | ");
            for(int j=0;j<3;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println(" +---+---+---+");
        }
        System.out.println();
    }
    private static int checkWinner(char[][] grid){
        for(int i=0;i<3;i++){
            //check rows
            if((grid[i][0]==grid[i][1]) && (grid[i][0]==grid[i][2]) && (grid[i][0]=='X' || grid[i][0]=='O')){
                if(grid[i][0]=='X')return(1);
                else return(-1);
            }
            //check cols
            if((grid[0][i]==grid[1][i]) && (grid[0][i]==grid[2][i]) && (grid[0][i]=='X'||grid[0][i]=='O')){
                if(grid[0][i]=='X')return(1);
                else return(-1);
            }
        }
        //check nw to se diag
        if((grid[0][0]==grid[1][1])&&(grid[1][1]==grid[2][2])&&(grid[0][0]=='X'||grid[0][0]=='O')){
            if(grid[0][0]=='X')return(1);
            else return(-1);
        }
        //check ne to sw diag
        if((grid[0][2]==grid[1][1])&&(grid[1][1]==grid[2][0])&&(grid[0][2]=='X'||grid[0][2]=='O')){
            if(grid[0][2]=='X')return(1);
            else return(-1);
        }
        return(0);
    }
    private static boolean isFull(char[][] board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)if(board[i][j]==' ')return(false);
        }
        return(true);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        char[][] board=new char[3][3];
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++)board[r][c]=' ';
        }
        int currPlayer=1; //1==>human 0==>AI
        int moves=0;
        int res;
        //human==>O
        //AI==>X
        while (moves<=9){
            print(board);
            res=checkWinner(board);
            if(res!=0){
                if(res==-1)System.out.print("PLAYER WINS");
                else System.out.print("COMPUTER WINS");
                break;
            }
            if(moves==9){
                System.out.print("DRAW");
                break;
            }
            if(currPlayer==1){
            System.out.print("Enter row: ");
            int row=sc.nextInt();
            System.out.print("Enter col: ");
            int col=sc.nextInt();
            if(board[row][col]!='O' && board[row][col]!='X')board[row][col]='O';
            else{
                System.out.println("Enter a valid Move");
                continue;
            }
            }
            else{
                int bestScore=Integer.MIN_VALUE;
                int compRow=0;
                int compCol=0;
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        if(board[r][c]==' '){
                            board[r][c]='X';
                            int score=minimax(board,false);
                            board[r][c]=' ';
                            if(score>bestScore){
                                bestScore=score;
                                compRow=r;
                                compCol=c;
                            }
                        }
                    }
                }
                System.out.println("Computer chooses");
                System.out.println("row: "+compRow);
                System.out.println("col: "+compCol);
                board[compRow][compCol]='X';
            }
            moves++;
            currPlayer^=1;
        }
    }
    private static int minimax(char[][] board,  boolean isMax){
        int res=checkWinner(board);
        if(res==1)return(1);
        if(res==-1)return(-1);
        if(res==0 && isFull(board))return(0);
        if(isMax){
            //System.out.println("here");
            int bestScore=Integer.MIN_VALUE;
            for(int r=0;r<3;r++){
                for(int c=0;c<3;c++){
                    if(board[r][c]==' '){
                        board[r][c]='X';
                        int score=minimax(board,false);
                        board[r][c]=' ';
                        bestScore=Math.max(bestScore,score);
                    }
                }
            }
            return(bestScore);
        }
        else{
            //System.out.println("there");
            int bestScore=Integer.MAX_VALUE;
            for(int r=0;r<3;r++){
                for(int c=0;c<3;c++){
                    if(board[r][c]==' '){
                        board[r][c]='O';
                        int score=minimax(board,true);
                        board[r][c]=' ';
                        bestScore=Math.min(bestScore,score);
                    }
                }
            }
            return(bestScore);
        }
    }
}

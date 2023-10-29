import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Game {
    int [][] grid;
    int obstacleCount=0;

    Random random;

    int row=6,column=8;
    Position player;

    Position goal;
    public Game() {
        this.random = new Random();
        createGrid();
        player=new Position(0,0);
        goal=new Position();
        printGrid();
    }

    void createGrid(){
        grid=new int[row][column];
        obstacleCount =row+column;
        System.out.println("Obstacle count "+ obstacleCount);
        generateObstaclePosition();
        generateGoalPosition();
    }

    void printGrid(){
        System.out.println("-------------------------");
        for(int i=0;i<row;i++){
            System.out.print(" | ");
            for (int j=0;j<column;j++){
                if(i==player.x && j==player.y){
                    System.out.print(getObject( 1));

                }else{

                    System.out.print(getObject( grid[i][j]));

                }
                System.out.print(" | ");

            }
            System.out.println("\n");
        }
    }

    void  generateObstaclePosition(){
        int i=0;
        while (i<obstacleCount){
            int obstacleRow =random.nextInt(row-1)+1;
            int obstacleColumn =random.nextInt(column-1)+1;
            if(grid[obstacleRow][obstacleColumn]!=10 && grid[obstacleRow][obstacleColumn]!=-1) {
                grid[obstacleRow][obstacleColumn]=-1;
                i++;
            }

        }

    }

    void  generateGoalPosition(){
        while(true){
            int goalRow =random.nextInt(row-2)+2;
            int goalColumn =random.nextInt(column-2)+2;

            if(grid[goalRow][goalColumn]!=-1
            && grid[goalRow-1][goalColumn]!=-1 || grid[goalRow][goalColumn-1]!=-1 ){
                grid[goalRow][goalColumn]=10;
                goal=new Position(goalRow,goalColumn);
                break;
            }

        }


    }

    void playGame(){
        Scanner scanner=new Scanner(System.in);
        String input;
        while (true){
            System.out.println("Press the Following  keys : \n w :Up , s : Down , d :right , a:left ");
            System.out.println("Input :  ");
            input=scanner.nextLine();
            if (input.equals("W") || input.equals("w") ) {
                if( validMoveYaxis(player.x-1)){
                    player.moveUp();
                }else{
                    break;
                }


            }else if (input.equals("S") || input.equals("s") ) {
                if( validMoveYaxis(player.x+1)){

                    player.moveDown();
                }else {
                    break;
                }

            }
            else if (input.equals("A") || input.equals("a") ) {
                if( validMoveXaxis(player.y-1)){
                    player.moveLeft();
                }else {
                    break;
                }


            }  else if (input.equals("D") || input.equals("d") ) {
                if(validMoveXaxis(player.y+1)){

                    player.moveRight();
                }else {
                    break;
                }

            } else if (input.equals("Z") || input.equals("z")) {
                break;
            }else{
                System.out.println("Invalid Move");
            }

            printGrid();
        }
    }

    private boolean validMoveXaxis(int pos){
        if(pos>=0 && pos<column){
//            System.out.println("player position  x " + player.x + " y " +pos);

            if(grid[player.x][pos]==-1){
                System.out.println("Game Ended");
                return false;
            }else if(grid[player.x][pos]==10){
                System.out.println("Congrats You won");
                return false;
            }

            return true;
        }
        System.out.println("Game Ended Player move out of Board");
        return false;
    }


    private boolean validMoveYaxis(int pos) {
        if(pos>=0 && pos<row){
//            System.out.println("player position x " + pos + " y " +player.y);
            if(grid[pos][player.y]==-1){
                System.out.println("Game Ended");
                System.out.println("Obstacle Hit");
                return false;
            }else if(grid[pos][player.y]==10){
                System.out.println("Congrats You Won");
                return false;
            }

            return true;
        }
        System.out.println("Game Ended Player move out of Board");
        return false;
    }


    
    private String getObject(int value){
        switch (value){
            case -1:
                return "X";
            case 1:
                return "P";
            case 10:
                return "G";
            default:
                return " ";
        }
    }


    public static void main(String[] args) {
        System.out.println("Text Based Game \n -1 : Obstacle \t 0: Empty Space \t 1: Person \t 10 :Goal ");

        Game game=new Game();
        game.playGame();
    }
}
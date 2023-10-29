public class Position {

    int x=0;
    int y=0;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    void moveUp(){
        x=x-1;
    }

    void moveDown(){
        x=x+1;
    }

    void moveRight(){
        y=y+1;
    }


    void moveLeft(){
        y=y-1;
    }
}

package TankBattleGame.TankBattleGame02;

public class Tanke {
    //坦克坐标 x
    private int x;
    //坦克坐标 y
    private int y;
    //坦克方向
    private int direct = 0;

    private int speed = 0;

    public int getDirect() {
        return direct;
    }

    public void moveUP(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft(){
        x -= speed;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tanke(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
}

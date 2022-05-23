package TankBattleGame.TankBattleGame04;


//爆炸
public class explode {
    //爆炸坐标
    int x;
    int y;
    //爆炸的时间
    int life = 9;
    //爆炸是否(产生)存活
    boolean isLive = true;

    public explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少爆炸的时间(周期)
    public void lifeDown(){
        if (life > 0){
            life--;
        } else {
            isLive = false;
        }
    }
}

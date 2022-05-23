package TankBattleGame.TankBattleGame04;

//友方坦克
public class MyHero extends Tanke {

    //定义一个Shot对象
    Shot shot = null;

    public MyHero(int x, int y) {
        super(x, y);
    }

    //射击
    public void ShotEnemyTanke(){
        //创建 Shot对象,根据 MyHero对象的位置和方向来创建一个Shot对象
        //得到MyHero的方向
        switch (getDirect()) {
            //方向:上
            case 0 -> shot = new Shot(getX() + 20, getY(), 0);

            //方向:右
            case 1 -> shot = new Shot(getX() + 60, getY() + 20, 1);

            //方向:下
            case 2 -> shot = new Shot(getX() + 20, getY() + 60, 2);

            //方向:左
            case 3 -> shot = new Shot(getX(), getY() + 20, 3);
        }
        //启动线程
        new Thread(shot).start();
    }

}

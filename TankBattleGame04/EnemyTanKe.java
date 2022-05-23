package TankBattleGame.TankBattleGame04;


import java.util.Vector;

//敌人的坦克
public class EnemyTanKe extends Tanke implements Runnable{

    //在敌人坦克类,使用 vector 保存多个 shot
    Vector<Shot> shots = new Vector<>();
    //坦克生命
    boolean isLive = true;
    public EnemyTanKe(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 10; i++) {
                        //方向
                        if (getY() > 0) {
                            moveUP();
                        }
                        //休眠
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 10; i++) {
                        //方向
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        //休眠
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        //方向
                        if (getY() + 60 < 700) {
                            moveDown();
                            //休眠
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 10; i++) {
                        //方向
                        if (getX() > 0) {
                            moveLeft();
                        }
                        //休眠
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            //随机方向
            setDirect((int)(Math.random() * 4));//

            //线程结束
            if (isLive == false){
                break;
            }
        }
    }
}

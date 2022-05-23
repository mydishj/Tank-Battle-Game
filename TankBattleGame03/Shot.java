package TankBattleGame.TankBattleGame03;

/*
*
*   分析如何实现用户按下J键,我们的坦克就会发射一颗子弹.
*   思路分析
*   1.当发射一颗子弹后,就相当于启动一个线程
*   2.MyHreo 有子弹的对象,当按下J时,我们就启动一个发射行为(线程),
*     让子弹不停的运动,形成一个射击的效果
*   3.我们MYPanel 需要不停的重绘子弹,才能出现该效果
*   4.当子弹移动到面板的边界时,就应该销毁(把启动的子弹的线程进行销毁)
*
* */
public class Shot implements Runnable{

    //子弹的x坐标
    int x;
    //子弹的y坐标
    int y;
    //子弹的方向
    int direct = 0;
    //子弹的速度
    int speed = 6;
    //子弹是否存活
    boolean isLive = true;

    //构造器
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        //射击行为
        while(true){

            //(子弹)休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //根据方向来改变x,y坐标
            //0 为上 1 为右 2 为下 3 为左
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            //测试
            System.out.println("子弹 x =" + x + "y=" + y);

            //如果
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 1000)){
                isLive = false;
                break;
            }
        }
    }
}

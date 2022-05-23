package TankBattleGame.TankBattleGame04;

import javax.swing.*;

public class TankBattleGame04 extends JFrame {
    //坦克大战4.0版
    //绘制我方坦克
    //可以通过按键控制我方的坦克进行上下左右的移动
    //可以进行一个坦克的射击


    //定义一个Mypanel
    Panel mp = null;
    public static void main(String[] args) {

        TankBattleGame04 tankBattleGame04 = new TankBattleGame04();

    }

    public TankBattleGame04(){
        mp = new Panel();
        //将mp 放入到 Thread,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1200,950);
        //让JFrame 监听键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

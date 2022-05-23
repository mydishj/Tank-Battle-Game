package TankBattleGame.TankBattleGame03;

import javax.swing.*;

public class TankBattleGame03 extends JFrame {
    //坦克大战3.0版
    //绘制我方坦克
    //可以通过按键控制我方的坦克进行上下左右的移动
    //可以进行一个坦克的射击
    //可以发射子弹


    //定义一个Mypanel
    Panel mp = null;
    public static void main(String[] args) {

        TankBattleGame03 tankBattleGame03 = new TankBattleGame03();

    }

    public TankBattleGame03(){
        mp = new Panel();
        //将mp 放入到 Thread,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1000,750);
        //让JFrame 监听键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

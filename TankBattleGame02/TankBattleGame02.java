package TankBattleGame.TankBattleGame02;

import javax.swing.*;

public class TankBattleGame02 extends JFrame {
    //坦克大战2.0版
    //绘制我方坦克
    //可以通过按键控制我方的坦克进行上下左右的移动

    //定义一个Mypanel
    Panel mp = null;
    public static void main(String[] args) {

        TankBattleGame02 tankBattleGame02 = new TankBattleGame02();

    }

    public TankBattleGame02(){
        mp = new Panel();
        this.add(mp);
        this.setSize(1000,750);
        //让JFrame 监听键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

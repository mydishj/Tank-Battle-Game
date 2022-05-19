package TankBattleGame.TankBattleGame01;

import javax.swing.*;

public class TankBattleGame01 extends JFrame {
    //坦克大战1.0版
    //绘制我方坦克

    //定义一个Mypanel
    Panel mp = null;
    public static void main(String[] args) {

        TankBattleGame01 tankBattleGame01 = new TankBattleGame01();

    }

    public TankBattleGame01(){
        mp = new Panel();
        this.add(mp);
        this.setSize(1000,750);
        //让JFrame 监听键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

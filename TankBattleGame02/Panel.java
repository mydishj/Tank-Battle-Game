package TankBattleGame.TankBattleGame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了监听键盘事件,实现 KeyListener
public class Panel extends JPanel implements KeyListener {
    //绘图区

    //定义我的坦克
    MyHero hero = null;

    //定义敌人坦克,放入到Vector
    Vector<EnemyTanKe> enemyTanKes = new Vector<>();

    //敌人数量
    int enemTankeSize = 3;

    public Panel(){
        //初始化坦克（位置）
        hero = new MyHero(80,100);
        //调节速度
        hero.setSpeed(5);

        //初始化敌人坦克
        for (int i = 0; i < enemTankeSize; i++) {
            EnemyTanKe enemyTanKe = new EnemyTanKe((80 * (i + 1)),100);
            //敌人的初始方向
            enemyTanKe.setDirect(2);
            enemyTanKes.add(enemyTanKe);

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形,默认为黑色
        g.fillRect(0,0,1000,1000);

        //画出坦克-友方
        drawTanke(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        drawTanke(hero.getX() + 80,hero.getY(),g,hero.getDirect(),0);
        drawTanke(hero.getX() + 160,hero.getY(),g,hero.getDirect(),0);
        drawTanke(hero.getX() + 240,hero.getY(),g,hero.getDirect(),0);

        //画出敌人坦克,遍历Vector
        for (int i = 0; i < enemyTanKes.size(); i++) {
            //取出坦克
            EnemyTanKe enemyTanKe = enemyTanKes.get(i);
            drawTanke(enemyTanKe.getX(),enemyTanKe.getY() + 80,g,enemyTanKe.getDirect(),1);
        }
    }

    //编写方法-画出坦克

    //x 为坦克的x坐标
    //y 为坦克的y坐标
    //g 为画笔
    //direction 为 坦克方向
    //type 为坦克颜色
    public void drawTanke(int x,int y,Graphics g,int direction,int type){

        switch (type){
            //坦克颜色
            //0 为友方坦克
            //1 为敌方坦克
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克的方向,绘制坦克
        switch (direction){
            //坦克方向
            //0 为向上
            //1 为向右
            //2 为向下
            //3 为向左
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fill3DRect(x + 10,y + 10,20,40,false);
                g.fillOval(x + 10,y + 20,20,20);
                g.drawLine(x + 20,y + 30,x + 20,y);
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y + 30,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.drawLine(x + 30,y + 20,x + 60,y + 20);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fill3DRect(x + 10,y + 10,20,40,false);
                g.fillOval(x + 10,y + 20,20,20);
                g.drawLine(x + 20,y + 30,x + 20,y + 60);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y + 30,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.drawLine(x + 30,y + 20,x,y + 20);
                break;
            default:
                System.out.println("但未完成");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //处理wdsa,按下的键

        //按下W
        if (e.getKeyCode() == KeyEvent.VK_W){
            //改变坦克的方向
            hero.setDirect(0);
            //修改坦克的坐标
            hero.moveUP();
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            //按下D
            //改变坦克的方向
            hero.setDirect(1);
            //修改坦克的坐标
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            //按下S
            //改变坦克的方向
            hero.setDirect(2);
            //修改坦克的坐标
            hero.moveDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            //按下A
            //改变坦克的方向
            hero.setDirect(3);
            //修改坦克的坐标
            hero.moveLeft();
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

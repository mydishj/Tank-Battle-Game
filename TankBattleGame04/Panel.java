package TankBattleGame.TankBattleGame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了监听键盘事件,实现 KeyListener
//为了让Panel 不停的重绘子弹,需要将 MyPanel 实现 Runnable,当做一个线程使用
public class Panel extends JPanel implements KeyListener,Runnable {
    //绘图区

    //定义我的坦克
    MyHero hero = null;

    //定义敌人坦克,放入到Vector
    Vector<EnemyTanKe> enemyTanKes = new Vector<>();

    //定义一个Vector,用于存放爆炸
    //说明,当子弹击中坦克时,就加入一个bomb对象到bombs
    Vector<explode> bombs = new Vector<>();

    //敌人数量
    int enemTankeSize = 3;

    //定义三张炸弹图片,用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public Panel(){
        //初始化坦克（位置）
        hero = new MyHero(80,200);
        //调节速度
        hero.setSpeed(5);

        //初始化敌人坦克
        for (int i = 0; i < enemTankeSize; i++) {
            //创建敌人坦克
            EnemyTanKe enemyTanKe = new EnemyTanKe((80 * (i + 1)),100);
            //敌人的初始方向
            enemyTanKe.setDirect(2);
            //启动敌人坦克线程
            new Thread(enemyTanKe).start();
            //给该EnemyTanKe 加入一颗子弹
            Shot shot = new Shot(enemyTanKe.getX() + 20, enemyTanKe.getY() + 60, enemyTanKe.getDirect());
            //加入EnemyTanKe的Vector 属性
            enemyTanKe.shots.add(shot);
            //启动 shot 对象
            Thread thread = new Thread(shot);
            thread.start();
            enemyTanKes.add(enemyTanKe);

        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb-1.jpg"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb-2.jpg"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb-3.jpg"));
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形,默认为黑色
        //g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,700);

        //画出坦克-友方
        drawTanke(hero.getX(),hero.getY(),g,hero.getDirect(),0);
//        drawTanke(hero.getX() + 80,hero.getY(),g,hero.getDirect(),0);
//        drawTanke(hero.getX() + 160,hero.getY(),g,hero.getDirect(),0);
//        drawTanke(hero.getX() + 240,hero.getY(),g,hero.getDirect(),0);

        //画出MyHero子弹
        if (hero.shot != null && hero.shot.isLive){
            //测试
            //System.out.println("子弹被绘制");
            g.fillOval(hero.shot.x,hero.shot.y,10,10);
        }

        //如果bombs 集合中有对象,就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出爆炸
            explode bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            } else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //让爆炸的时间减少
            bomb.lifeDown();
            //如果bomb life 为0,就从bombs的集合中删除
            if (bomb.life == 0 ){
                bombs.remove(bomb);
            }

        }

        //画出敌人坦克,遍历Vector
        for (EnemyTanKe enemyTanKe : enemyTanKes) {
            //取出坦克
            //当敌人坦克是否是存活的,才能画出该坦克
            if (enemyTanKe.isLive) {
                drawTanke(enemyTanKe.getX(), enemyTanKe.getY(), g, enemyTanKe.getDirect(), 1);
                //画出 EnemyTanKe 所有的子弹
                for (int j = 0; j < enemyTanKe.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTanKe.shots.get(j);
                    //绘制子弹
                    //判断子弹是否存活 shot.isLive == true
                    if (shot.isLive ) {
                        g.fillOval(shot.x, shot.y, 10, 10);
                    }
                }
            } else {
                enemyTanKes.remove(enemyTanKe);
            }
        }

    }

    //编写方法-画出坦克

    //x 为坦克的x坐标
    //y 为坦克的y坐标
    //g 为画笔
    //direction 为 坦克方向
    //type 为坦克颜色
    public void drawTanke(int x,int y,Graphics g,int direction,int type){

        switch (type) {
            //坦克颜色
            //0 为友方坦克
            //1 为敌方坦克
            case 0 -> g.setColor(Color.cyan);
            case 1 -> g.setColor(Color.yellow);
        }

        //根据坦克的方向,绘制坦克
        switch (direction) {
            //坦克方向
            //0 为向上
            //1 为向右
            //2 为向下
            //3 为向左
            case 0 -> {
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
            }
            case 1 -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
            }
            case 2 -> {
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
            }
            case 3 -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
            }
            default -> System.out.println("但未完成");
        }
    }

    //编写方法,判断我方子弹是否击中敌方坦克
    //什么时候判断 我方坦克什么时候击中坦克
    public void HitTanke(Shot s,EnemyTanKe enemyTanKe){
        //判断s 击中坦克
        switch (enemyTanKe.getDirect()){
            //向上
            case 0:
            //向下
            case 2:
                if (s.x > enemyTanKe.getX() && s.x < enemyTanKe.getX() + 40 &&
                s.y > enemyTanKe.getY() && s.y < enemyTanKe.getY() + 60){
                    s.isLive = false;
                    enemyTanKe.isLive = false;
                    //当子弹击中敌人的坦克后,将 enemyTanKe 从 Vector 拿掉
                    //enemyTanKes.remove(enemyTanKe);
                    //创建一个explode对象,加入到玻bombs集合中
                    explode bomb = new explode(enemyTanKe.getX(), enemyTanKe.getY());
                    bombs.add(bomb);
                }
                break;
            //向右
            case 1:
            //向左
            case 3:
                if (s.x > enemyTanKe.getX() && s.x < enemyTanKe.getX() + 60 &&
                s.y > enemyTanKe.getY() && s.y < enemyTanKe.getY() + 40){
                    s.isLive = false;
                    enemyTanKe.isLive = false;
                    //当子弹击中敌人的坦克后,将 enemyTanKe 从 Vector 拿掉
                    //enemyTanKes.remove(enemyTanKe);
                    //创建一个explode对象,加入到玻bombs集合中
                    explode bomb = new explode(enemyTanKe.getX(), enemyTanKe.getY());
                    bombs.add(bomb);
                }
                break;
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
            if (hero.getY() > 0) {
                hero.moveUP();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            //按下D
            //改变坦克的方向
            hero.setDirect(1);
            //修改坦克的坐标
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            //按下S
            //改变坦克的方向
            hero.setDirect(2);
            //修改坦克的坐标
            if (hero.getY() + 60 < 700)
            hero.moveDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            //按下A
            //改变坦克的方向
            hero.setDirect(3);
            //修改坦克的坐标
            if (hero.getX() > 0 )
            hero.moveLeft();
        }

        //如果用户按下的是J,就会实现ShotEnemyTanke的线程,就进行发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J){
            System.out.println("按下J,射击中");
            hero.ShotEnemyTanke();
        }

        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        //每隔100毫秒,重绘区域,刷新绘图区域,子弹就会进行移动
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断是否击中了敌人坦克
            //当前我的子弹还存活
            if (hero.shot != null && hero.shot.isLive){
                //遍历敌人所有的坦克
                for (EnemyTanKe enemyTanKe : enemyTanKes) {
                    HitTanke(hero.shot, enemyTanKe);
                }
            }
            this.repaint();
        }
    }
}

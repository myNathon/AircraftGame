package com.none.game.frame;

import com.none.game.element.Bullet;
import com.none.game.element.Explode;
import com.none.game.element.Plane;
import com.none.game.util.GameFrameBasic;
import com.none.game.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * 游戏主窗口
 * Created by Nathon on 2017/8/20.
 */
public class GameFrame extends GameFrameBasic {
    // 背景图
    Image bg = GameUtil.getImage("images/background.png");

    // 玩家
    Plane player = new Plane("images/player.png", 50, 50);

    // 爆炸
    Explode explode;

    // 游戏时间统计
    private Date start;
    private Date end;

    // 障碍物：有多个，使用可扩数组ArrayList
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

    public void paint(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        player.draw(g);

        // 画出子弹
        for(Bullet b: bulletList) {
            b.draw(g);

            // 检测子弹和飞机的碰撞
            boolean bang = b.getRect().intersects(player.getRect());
            if (bang) {
                // 设置存活状态为false
                player.setLive(false);

                // 游戏结束时间
                if(end == null) {
                    end = new Date();  // 保证只能执行一次
                }

                // 显示爆炸
                if (explode == null) {
                    explode = new Explode(player.getX(), player.getY());
                }
                explode.draw(g);

                break; // 碰到后退出循环
            }

            // 判断是否还活着
            if(!player.isLive()) {
                printInfo(g, "GAME OVER", Color.WHITE, 30, 80, 160);

                // 打印时间信息
                int gTime = (int)((end.getTime() - start.getTime()) / 1000);
                printInfo(g, "坚持时间：" + gTime, Color.YELLOW, 30, 40, 60);

                // 游戏等级划分
                gameLevel(g, gTime);
            }
        }
    }

    /**
     * 显示游戏进行的等级
     */
    private void gameLevel(Graphics g, int gTime) {
        if(gTime < 5) {
            printInfo(g, "你的等级：垃圾", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 20) {
            printInfo(g, "你的等级：一般", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 60) {
            printInfo(g, "你的等级：还可以", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 100) {
            printInfo(g, "你的等级：正常", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 150) {
            printInfo(g, "你的等级：不错哦", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 210) {
            printInfo(g, "你的等级：厉害", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 280) {
            printInfo(g, "你的等级：太厉害", Color.YELLOW, 30, 40, 100);
        } else if(gTime < 350) {
            printInfo(g, "你的等级：超神", Color.YELLOW, 30, 40, 100);
        } else {
            printInfo(g, "你的等级：已逆天，无人能挡", Color.YELLOW, 30, 40, 100);
        }
    }

    /**
     * 向窗口中打印信息
     * @param g
     * @param msg   需要打印的信息
     * @param c     字体颜色
     * @param fSize 字体大小
     * @param x     打印的x轴
     * @param y     打印的y轴
     */
    private void printInfo(Graphics g, String msg, Color c, int fSize, int x, int y) {
        Color oldC = g.getColor();
        Font oldF = g.getFont();
        g.setColor(c);
        Font f = new Font("宋体", Font.BOLD, fSize);
        g.setFont(f);
        g.drawString(msg, x, y);
        g.setColor(oldC);
        g.setFont(oldF);
    }

    @Override
    public void launchFrame() {
        super.launchFrame();

        // 增加键盘监听
        addKeyListener(new keyListener());

        // 生成一堆子弹
        for(int i = 0; i < 10; i++) {
            bulletList.add(new Bullet());
        }

        // 游戏开始时间
        start = new Date();
    }

    /**
     * 键盘监听内部类
     * 定义完还需要注册才能使用
     */
    private class keyListener extends KeyAdapter {
        // 按键按下去
        @Override
        public void keyPressed(KeyEvent e) {
            player.setDirection(e, true);
        }

        // 按钮弹起来
        @Override
        public void keyReleased(KeyEvent e) {
            player.setDirection(e, false);
        }
    }
}

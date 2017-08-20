package com.none.game.util;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏窗口基类
 * Created by Nathon on 2017/8/10.
 */
public class GameFrameBasic extends Frame {

    /**
     * 加载窗口
     */
    public void launchFrame() {
        // 设置窗口大小
        setSize(Constant.WINDOWS_WIDTH, Constant.WINDOWS_HEIGHT);

        // 设置位置
        setLocation(Constant.LOCATION_X, Constant.LOCATION_Y);

        // 窗口默认是隐藏的，设置为可见
        setVisible(true);

        // 启动重画线程
        new PaintThread().start();

        // 添加窗口监听
        // 匿名内部类"WindowAdapter"
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 下面为双缓冲代码，防止闪烁
     */
    private Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(Constant.WINDOWS_WIDTH, Constant.WINDOWS_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 定义一个重画窗口线程类
     * 让图片运动
     * 内部类，方便访问外部类属性
     */
    private class PaintThread extends Thread {
        @Override
        public void run() {
            while(true) {
                repaint();   // 重画

                try {
                    Thread.sleep(40); // 1s --> 1000ms，这里休眠40ms，即是1s画25次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

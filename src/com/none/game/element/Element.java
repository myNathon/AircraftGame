package com.none.game.element;

import com.none.game.util.GameUtil;

import java.awt.*;

/**
 * Created by Nathon on 2017/8/20.
 */
public abstract class Element {
    // 图片
    Image img;

    // 坐标
    double x;
    double y;

    // 移动速度
    int speed = 10;

    // 图片宽高
    int width;
    int height;

    public Element() {}

    public Element(String imgPath, double x, double y, int speed) {
        this.img = GameUtil.getImage(imgPath);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * 获取图片所占的矩形
     * @return
     */
    public Rectangle getRect() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    /**
     * 窗口画图片
     * @param g
     */
    public abstract void draw(Graphics g);
}

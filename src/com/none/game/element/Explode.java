package com.none.game.element;

import com.none.game.util.GameUtil;

import java.awt.*;

/**
 * 爆炸类
 * Created by Nathon on 2017/8/20.
 */
public class Explode {
    // 坐标
    double x;
    double y;

    // 一堆图片
    static Image[] imgs;

    // 图片数组的索引
    int index;

    // 静态语句块初始化爆炸图片
    static {
        imgs = new Image[16];
        for(int i = 0; i < imgs.length; i++) {
            imgs[i] = GameUtil.getImage("images/explode/e" + (i+1) + ".gif");
            imgs[i].getWidth(null);  // 只为了将图片加载进来，默认处理
        }
    }

    public Explode() {}

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;

        this.index = 0;
    }

    /**
     * 画图片
     * @param g
     */
    public void draw(Graphics g) {
        if(this.index < imgs.length) {
            g.drawImage(imgs[index++], (int)x, (int)y, null);
        }
    }
}

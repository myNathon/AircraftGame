package com.none.game.element;

import com.none.game.util.Constant;

import java.awt.*;

/**
 * Created by Nathon on 2017/8/20.
 */
public class Bullet extends Element {
    // 角度
    private double degree;

    public Bullet() {
        degree = Math.random() * Math.PI * 2;  // [0, 1) * pi * 2 == [0, 2pi)
        this.x = Constant.WINDOWS_WIDTH / 2;
        this.y = Constant.WINDOWS_HEIGHT / 2;

        this.width = 10;
        this.height = 10;

        this.speed = 3;
    }

    /**
     * 在窗口画出图像
     * @param g
     */
    public void draw(Graphics g) {
        Color old = g.getColor();

        g.setColor(Color.YELLOW);
        g.fillOval((int)this.x, (int)this.y, this.width, this.height);

        // 方向改变
        this.x += speed * Math.cos(degree);
        this.y += speed * Math.sin(degree);

        if(this.y > Constant.WINDOWS_HEIGHT - 17 || this.y < 30) {
            this.degree = -this.degree;
        }

        if(this.x < 8 || this.x > Constant.WINDOWS_WIDTH - 17) {
            this.degree = Math.PI - this.degree;
        }

        g.setColor(old); // 还原原来传进来的颜色
    }
}

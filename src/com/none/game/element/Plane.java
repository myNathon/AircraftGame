package com.none.game.element;

import com.none.game.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Nathon on 2017/8/20.
 */
public class Plane extends Element {
    // 方向控制标识
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 存活状态，默认活的
    private boolean live = true;

    public Plane() {}

    public Plane(String imgPath, double x, double y) {
        super(imgPath, x, y, 10);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    /**
     * 移动处理
     */
    public void move() {
        if(left) {
            this.x -= speed;
        }

        if(right) {
            this.x += speed;
        }

        if(up) {
            this.y -= speed;
        }

        if(down) {
            this.y += speed;
        }
    }

    /**
     * 根据键盘按键设置方向标识
     * @param e
     * @param flag 按下为true，释放为false
     */
    public void setDirection(KeyEvent e, boolean flag) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.left = flag;
                break;
            case KeyEvent.VK_UP:
                this.up = flag;
                break;
            case KeyEvent.VK_RIGHT:
                this.right = flag;
                break;
            case KeyEvent.VK_DOWN:
                this.down = flag;
                break;
            default:
                break;
        }
    }

    /**
     * 在窗口画出图像
     * @param g
     */
    public void draw(Graphics g) {
        if(live) {
            g.drawImage(img, (int) this.x, (int) this.y, null);

            // 画图像
            this.move();
        }
    }
}

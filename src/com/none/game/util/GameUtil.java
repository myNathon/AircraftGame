package com.none.game.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏开发中常用的工具类（比如：图片加载等方法）
 * Created by Nathon on 2017/8/10.
 */
public class GameUtil {
    private GameUtil() {} // 工具类私构造器

    /**
     * 获取图片
     * @param path
     * @return
     */
    public static Image getImage(String path) {
        URL url = GameUtil.class.getClassLoader().getResource(path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}

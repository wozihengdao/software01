package com.stdu.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;


public class WatermarkService {

    public static void addTextWatermark(File input, File output) throws Exception {
        BufferedImage sourceImage = ImageIO.read(input);
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // 设置水印参数
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));

        String text = new Date().toString();
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int x = sourceImage.getWidth() - fontMetrics.stringWidth(text) - 20;
        int y = sourceImage.getHeight() - fontMetrics.getHeight() + 20;

        // 绘制水印
        g2d.drawString(text, x, y);
        g2d.dispose();

        // 保存图片
        ImageIO.write(sourceImage, "png", output);
    }

    public static void main(String[] args) {
        try {
            File inputFile;
            File outputFile;

            // 处理命令行参数
            if (args.length == 2) {
                inputFile = new File(args[0]);
                outputFile = new File(args[1]);
            } else {
                // 使用默认测试文件路径
                inputFile = new File("C:\\Users\\16029\\Pictures\\input.jpg");
                outputFile = new File("C:\\Users\\16029\\Pictures\\output.png");

                System.out.println("使用默认文件路径：");
                System.out.println("输入文件: " + inputFile.getAbsolutePath());
                System.out.println("输出文件: " + outputFile.getAbsolutePath());
            }

            // 检查输入文件是否存在
            if (!inputFile.exists()) {
                System.err.println("错误：输入文件不存在");
                return;
            }

            // 执行水印添加
            addTextWatermark(inputFile, outputFile);
            System.out.println("水印添加成功！");
            System.out.println("生成文件大小: " + outputFile.length()/1024 + "KB");

        } catch (Exception e) {
            System.err.println("发生错误：");
            e.printStackTrace();
        }
    }
}
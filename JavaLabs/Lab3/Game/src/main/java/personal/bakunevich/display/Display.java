package personal.bakunevich.display;

import personal.bakunevich.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {
    private static boolean isCreated = false;
    private static JFrame window;
    private static Canvas content;

    private static BufferedImage bufferImage;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy;



    public static void create(int wight, int height, String title, int __clearColor, int numBuffers) {

        if (isCreated) return;

        window = new JFrame(title);
        content = new Canvas();

        Dimension size = new Dimension(wight, height);
        content.setPreferredSize(size);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // запрет менять размер окна
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);



        bufferImage = new BufferedImage(wight, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) bufferImage.getRaster().getDataBuffer()).getData();
        bufferGraphics = bufferImage.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = __clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        isCreated = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(bufferImage, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {
        if (!isCreated) return;
        window.dispose();
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void addInputListener (Input inputListener) {
        window.add(inputListener);
    }
    public static void addButton (JButton button) {
        window.add(button);
    }
}
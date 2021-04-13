package personal.bakunevich.display;

import personal.bakunevich.IO.Input;
import personal.bakunevich.graphics.TextureAtlas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton buttonPlayer1;
    private JButton buttonPlayer2;
    public Container container;
    public Menu(TextureAtlas atlas){

    }

    public void render(Graphics2D graphics2D){
    }

    public void update(Input input){

    }
}

class ListenerAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Нажатие кнопки! От - "+
                e.getActionCommand() + "\n");
    }
}
class ListenerChange implements ChangeListener {
    public void stateChanged(ChangeEvent e) {
        // Источник события
        Object src = e.getSource();
        System.out.println("Cообщение о смене состояния объекта : "
                + src.getClass());
    }
}

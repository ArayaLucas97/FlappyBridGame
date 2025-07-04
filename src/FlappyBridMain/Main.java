package FlappyBridMain;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Flappy Bird");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new FondoPanel();

        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER; //centrado
        
        //imagen del boton start
        ImageIcon iconoStart = new ImageIcon(getClass().getResource("/media/start.png"));
        Image img = iconoStart.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);
        iconoStart = new ImageIcon(img);

        
        JButton startButton = new JButton(iconoStart);
        //Opciones para que no sea visible el boton
        startButton.setBorderPainted(false); //contorno
        startButton.setContentAreaFilled(false); //no pinte el area
        startButton.setFocusPainted(false); //resltado de enfoque
        startButton.setOpaque(false); //el boton no es opaco
        //accion del boton
        startButton.addActionListener(e -> {
            dispose(); // Cierra el menú
            new RunGameFlappyBird(); // Llama al juego
        });

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(startButton, gbc);
        panel.add(exitButton, gbc);
             
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
    class FondoPanel extends JPanel {
        private ImageIcon fondo;

        public FondoPanel() {
            fondo = new ImageIcon(getClass().getResource("/media/background.png"));
            setLayout(new GridBagLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
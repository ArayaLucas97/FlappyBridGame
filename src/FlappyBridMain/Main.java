package FlappyBridMain;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

        JButton startButton = new JButton("Iniciar Juego");
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
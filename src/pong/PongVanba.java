package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PongVanba extends JPanel {

//    public static void main(String[] args) {
//
//        JFrame janela = new JFrame("Pong");
//
//        PongVanba pong = new PongVanba();
//        pong.setBounds(0,0,800,600);
//        pong.setVisible(true);
//        pong.setBackground(Color.BLACK);
//        janela.add(pong);
//
//
//    }

        private JTextField celsiusDegree;
        private JButton convertButton;
        private JLabel fahrenheitDeg;
        private JPanel panel;
        private JLabel celsiusLabel;


        public PongVanba(){

            convertButton = new JButton("Convert");
            celsiusDegree = new JTextField("");
            panel = new JPanel(new BorderLayout());
            fahrenheitDeg = new JLabel();

            panel.setBounds(0,0,800,600);
        panel.setVisible(true);
        panel.setBackground(Color.BLACK);

            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int fahrDegree = (int)(Double.parseDouble(celsiusDegree.getText())*1.8+32);
                    fahrenheitDeg.setText(fahrDegree+" Fahrenheit");
                }
            });

        }


//        public static void main(String[] args) {
//            JFrame frame = new JFrame("CelsiusConverterGUI");
//            frame.setContentPane(new PongVanba().panel);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setVisible(true);
//        }




//    final int WIDTH = 700, HEIGHT = 700;
//
//
//    public void init(){
//        this.resize(WIDTH,HEIGHT);
//    }
//
//    public void paint(Graphics g){
//
//    }
//
//    public void update(Graphics g){
//        paint(g);
//    }




}

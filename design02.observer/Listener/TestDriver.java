package design02.observer.Listener;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MainFramePanel extends JPanel {
    JButton button;

    MainFramePanel() {
        
    }
    
    void go() {
        button = new JButton("Yes! or No!");
        this.add(BorderLayout.CENTER, button);

        // button.addActionListener(new AngelListener());
        // button.addActionListener(new DevilListener());
        button.addActionListener((e) -> {
            System.out.println("Yes!");
        });
        button.addActionListener((e) -> {
            System.out.println("No!");
        });
    }

    // class AngelListener implements ActionListener {
    //     public void actionPerformed(ActionEvent e) {
    //         System.out.println("Yes!");
    //     }
    // }
    // class DevilListener implements ActionListener {
    //     public void actionPerformed(ActionEvent e) {
    //         System.out.println("No!");

    //     }
    // }
    
}

class MainFrame extends JFrame {

    MainFramePanel mainFramePanel;

    MainFrame() {
        
        setTitle("title");
        setLocation(0, 0);
        setSize(500, 500);

        mainFramePanel = new MainFramePanel();

        // Attach to frame
        Container mainFrameContentPane = super.getContentPane();
        mainFrameContentPane.add(mainFramePanel);

        // EXIT_ON_CLOSE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void go() {
        mainFramePanel.go();
    }
}

class TestDriver {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.go();
        mainFrame.setVisible(true);
    }
}
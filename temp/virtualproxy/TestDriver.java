import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MainFrame extends JFrame {
    MainFrame() {
        // mainframe default settings
        this.setTitle("TITLE");
        this.setVisible(true);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // fram's contentPane + mainPanel
        MainPanel mainPanel = new MainPanel();
        Container contentPane = this.getContentPane();
        contentPane.add(mainPanel);

        
    }
}

class MainPanel extends JPanel {
    
}

class TestDriver {

    public static void main(String[] args) {
        new MainFrame();
    }

}
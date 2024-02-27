import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class IntroScreen extends JPanel implements ActionListener {
    JButton testButton = new JButton();
    IntroScreen(){
        this.setBackground(Color.RED);
        this.setBounds(0, 0, 500, 500);

        testButton.setBounds(200, 200, 50, 100);
        testButton.addActionListener(this);
        this.add(testButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == testButton){
            System.out.println("Hi!");
        }
    }
}

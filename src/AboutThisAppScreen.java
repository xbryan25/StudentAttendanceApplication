import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutThisAppScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("Go back?");
    FrameHolder frame;
    GridBagConstraints gbc = new GridBagConstraints();

    AboutThisAppScreen(FrameHolder frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GREEN);
        this.setBounds(0, 0, 500, 500);

        gbc.insets = new Insets(0, 3, 3, 3);

        backButton.setPreferredSize(new Dimension(180, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(this);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            this.frame.changeToIntroScreen(1);
        }
    }
}

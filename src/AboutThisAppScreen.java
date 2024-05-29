import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutThisAppScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    FrameHolder frame;
    GridBagConstraints gbc = new GridBagConstraints();


    AboutThisAppScreen(FrameHolder frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
//        this.setBackground(Color.GREEN);
        this.setBounds(0, 0, 500, 500);

        backButton.setPreferredSize(new Dimension(65, 30));
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        gbc.insets = new Insets(10, 10, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            this.frame.changeToIntroScreen(1);
        }
    }
}

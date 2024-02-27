// TODO: Buttons stay at the top, use a Layout Manager

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class IntroScreen extends JPanel implements ActionListener{
    JButton addStudentButton = new JButton("Add Student");
    JButton aboutThisAppButton = new JButton("About this app");
    JButton adminModeButton = new JButton("Go Admin?");

    FrameHolder frame;
    IntroScreen(FrameHolder frame){
        this.frame = frame;

        this.setBackground(Color.RED);
        this.setBounds(0, 0, 500, 500);

        addStudentButton.setBounds(50, 150, 150, 50);
        addStudentButton.addActionListener(this);

        adminModeButton.setBounds(250, 150, 150, 50);
        adminModeButton.addActionListener(this);

        aboutThisAppButton.setBounds(250, 350, 150, 50);
        aboutThisAppButton.addActionListener(this);

        this.add(addStudentButton);
        this.add(aboutThisAppButton);
        this.add(adminModeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            this.hidePanel();
            this.frame.changeToAttendanceScreen();
        } else if (e.getSource() == aboutThisAppButton){
            System.out.println("aboutThisAppButton");
        } else if (e.getSource() == adminModeButton){
            System.out.println("adminModeButton");
        }
    }

    public void hidePanel(){
        this.setVisible(false);
    }

//    public void showPanel(){
//        this.setVisible(true);
//    }
}

package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroScreen extends JPanel implements ActionListener{
    JButton attendanceButton = new JButton("Attendance");
    JButton aboutThisAppButton = new JButton("About this app");
    JButton adminModeButton = new JButton("Go Admin?");

    FrameHolder frame;
    GridBagConstraints gbc = new GridBagConstraints();


    IntroScreen(FrameHolder frame){
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        this.setSize(500, 500);
//        this.setBackground(Color.RED);

        gbc.insets = new Insets(0, 3, 3, 3);

        attendanceButton.setPreferredSize(new Dimension(180, 50));
        attendanceButton.setFont(new Font("Arial", Font.BOLD, 20));
        attendanceButton.addActionListener(this);
        attendanceButton.setFocusable(false);

        aboutThisAppButton.setPreferredSize(new Dimension(180, 50));
        aboutThisAppButton.setFont(new Font("Arial", Font.BOLD, 20));
        aboutThisAppButton.addActionListener(this);
        aboutThisAppButton.setFocusable(false);

        adminModeButton.addActionListener(this);
        adminModeButton.setFocusable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(attendanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        this.add(aboutThisAppButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(50, 0, 0, 0);
        this.add(adminModeButton, gbc);

//        gbc.gridx = 3;
//        gbc.gridy = 3;
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//
//        this.add(new JLabel(" "), gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attendanceButton) {
            this.frame.changeToAttendanceScreen();
        } else if (e.getSource() == aboutThisAppButton){
            this.frame.changeToAboutThisAppScreen();

        } else if (e.getSource() == adminModeButton){
            this.frame.changeToAdminScreen();
        }
    }
}

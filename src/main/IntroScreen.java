package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroScreen extends JPanel implements ActionListener{
    JButton attendanceButton = new JButton("Attendance");
    JButton aboutThisAppButton = new JButton("About this app");
    JButton adminModeButton = new JButton("Go Admin?");

    JLabel version = new JLabel("v0 (pre v1.0)");
    FrameHolder frame;
    GridBagConstraints gbc = new GridBagConstraints();


    IntroScreen(FrameHolder frame){
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        this.setSize(500, 500);

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

        version.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.insets = new Insets(125, 25, 3, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(attendanceButton, gbc);

        gbc.insets = new Insets(125, 3, 3, 3);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(aboutThisAppButton, gbc);

        gbc.insets = new Insets(50, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(adminModeButton, gbc);

        gbc.insets = new Insets(130, 110, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(version, gbc);

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

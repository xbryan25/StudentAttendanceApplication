package main;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;

public class AboutThisAppScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JLabel header = new JLabel();
    JLabel paragraph1 = new JLabel();
    JLabel paragraph2 = new JLabel();
    JLabel paragraph3 = new JLabel();
    JLabel version = new JLabel();
    JButton githubButton = new JButton("GitHub");
    JButton facebookButton = new JButton("Facebook");
    FrameHolder frame;
    GridBagConstraints gbc = new GridBagConstraints();


    AboutThisAppScreen(FrameHolder frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
//        this.setBackground(Color.GREEN);
        this.setBounds(0, 0, 500, 500);

        backButton.setPreferredSize(new Dimension(25, 25));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setText("Student Attendance App by xbryan25");
        header.setHorizontalAlignment(JLabel.CENTER);

        paragraph1.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph1.setText("<html><p align=\"justify\">&nbsp&nbsp&nbsp&nbsp This application was made to enhance my proficiency in Java GUI." +
                           " This application supports the addition of colleges, and programs. Once the colleges and programs" +
                           " have been added, the application will accept the addition of students.</p></html>");
//        paragraph1.setPreferredSize(new Dimension(100,100));

        paragraph2.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph2.setText("<html><p align=\"justify\"><br>The application will prompt" +
                            " for the ID number, first and last name, college, and program of the student. Moreover, the data of" +
                            " the students in the list can be saved if ever the user decides to terminate the application. If the" +
                            " attendance has been ended, the result will be saved in a .pdf with contents such as the title of the event," +
                            " when it started, and when it ended.</p></html>");

        paragraph3.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph3.setText("<html><p align=\"justify\"><br>I worked on this project on and off for about 3 months." +
                " In addition, this has been my largest project so far, surpassing the GWA Application for PSHS that" +
                " I made using Python two years ago. If you ever find any errors, please don't hesitate to add an issue" +
                " to the repository of this project. If you want to reach me, my socials are pinned below.</p></html>");

        githubButton.setPreferredSize(new Dimension(100, 30));
        githubButton.setFont(new Font("Arial", Font.BOLD, 20));
        githubButton.addActionListener(this);
        githubButton.setFocusable(false);

        facebookButton.setPreferredSize(new Dimension(100, 30));
        facebookButton.setFont(new Font("Arial", Font.BOLD, 20));
        facebookButton.addActionListener(this);
        facebookButton.setFocusable(false);

        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.insets = new Insets(20, 60, 0, 0);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(header, gbc);

        gbc.insets = new Insets(20, 50, 0, 0);
        gbc.gridwidth = 2;
        gbc.ipadx = 320;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(paragraph1, gbc);

        gbc.insets = new Insets(0, 50, 0, 0);
        gbc.gridwidth = 2;
        gbc.ipadx = 320;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(paragraph2, gbc);

        gbc.insets = new Insets(0, 50, 0, 0);
        gbc.gridwidth = 2;
        gbc.ipadx = 320;
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(paragraph3, gbc);

        gbc.insets = new Insets(20, 60, 0, 0);
        gbc.gridwidth = 1;
        gbc.ipadx = 75;
        gbc.ipady = 15;
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(githubButton, gbc);

        gbc.insets = new Insets(20, 10, 0, 0);
        gbc.gridwidth = 1;
        gbc.ipadx = 55;
        gbc.ipady = 15;
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(facebookButton, gbc);

        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            this.frame.changeToIntroScreen(1);
        } else if (e.getSource() == githubButton){
            openWebpage("www.github.com/xbryan25");
        } else if (e.getSource() == facebookButton){
            openWebpage("www.facebook.com/bryanagan25p");
        }
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

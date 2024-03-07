import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttendanceScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JButton addStudentButton = new JButton("Add Student");
    FrameHolder frame;
    TableHolder tableHolder;

//    TableHolder tableHolder = new TableHolder();
    GridBagConstraints gbc = new GridBagConstraints();

    AttendanceScreen(FrameHolder frame, TableHolder tableHolder) {
        this.frame = frame;
        this.tableHolder = tableHolder;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GREEN);
        this.setBounds(500, 0, 250, 500);

//        this.add(tableHolder);
//        tableHolder.setVisible(true);

        backButton.setPreferredSize(new Dimension(65, 30));
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        addStudentButton.setPreferredSize(new Dimension(180, 30));
        addStudentButton.setFont(new Font("Arial", Font.BOLD, 20));
        addStudentButton.addActionListener(this);
        addStudentButton.setFocusable(false);

        gbc.insets = new Insets(10, 0, 0, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(addStudentButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton) {
            this.frame.changeToIntroScreen(2);
        } else if(e.getSource() == addStudentButton){
            Object[] obj = new Object[5];

            obj[0] = JOptionPane.showInputDialog("ID Number: ");
            obj[1] = JOptionPane.showInputDialog("First Name: ");
            obj[2] = JOptionPane.showInputDialog("Last Name: ");
            obj[3] = JOptionPane.showInputDialog("Program: ");
            obj[4] = JOptionPane.showInputDialog("College: ");

            // Add student using an instance of the table holder class, method defined in TableHolder.java
            this.tableHolder.addStudentInRow(obj);

            System.out.println("Student added!");
        }
    }
}

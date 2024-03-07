import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttendanceScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JButton addStudentButton = new JButton("Add Student");
    FrameHolder frame;

//    TableHolder tableHolder = new TableHolder();
    GridBagConstraints gbc = new GridBagConstraints();

    String idNum;
    String firstName;
    String lastName;
    String program;
    String college;

    AttendanceScreen(FrameHolder frame) {
        this.frame = frame;
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

        gbc.insets = new Insets(10, 10, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(addStudentButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton) {
            this.frame.changeToIntroScreen(2);
        } else if(e.getSource() == addStudentButton){
            idNum = JOptionPane.showInputDialog("ID Number: ");
            firstName = JOptionPane.showInputDialog("First Name: ");
            lastName = JOptionPane.showInputDialog("Last Name: ");

            System.out.printf("ID Number: %s\nFirst Name: %s\nLast Name: %s", idNum, firstName, lastName);
        }
    }
}

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class AttendanceScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JButton addStudentButton = new JButton("Add Student");
    JButton addProgramsButton = new JButton("Add programs");
    JButton addCollegesButton = new JButton("Add colleges");
    JButton viewCollegesAndProgramsButton = new JButton("View colleges and programs");

    FrameHolder frame;
    TableHolder tableHolder;

//    TableHolder tableHolder = new TableHolder();
    GridBagConstraints gbc = new GridBagConstraints();

    ArrayList<String> colleges = new ArrayList<>();
    ArrayList<ArrayList<String>> programsInColleges = new ArrayList<>();

    ArrayList<String[]> dataFromCSV;

    AttendanceScreen(FrameHolder frame, TableHolder tableHolder, ArrayList<String[]> dataFromCSV) {
        this.dataFromCSV = dataFromCSV;

        this.frame = frame;
        this.tableHolder = tableHolder;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GREEN);
        this.setBounds(500, 0, 250, 500);

        backButton.setPreferredSize(new Dimension(65, 30));
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        addStudentButton.setPreferredSize(new Dimension(180, 30));
        addStudentButton.setFont(new Font("Arial", Font.BOLD, 20));
        addStudentButton.addActionListener(this);
        addStudentButton.setFocusable(false);

        addCollegesButton.setPreferredSize(new Dimension(200, 30));
        addCollegesButton.setFont(new Font("Arial", Font.BOLD, 12));
        addCollegesButton.addActionListener(this);
        addCollegesButton.setFocusable(false);

        addProgramsButton.setPreferredSize(new Dimension(200, 30));
        addProgramsButton.setFont(new Font("Arial", Font.BOLD, 12));
        addProgramsButton.addActionListener(this);
        addProgramsButton.setFocusable(false);

        viewCollegesAndProgramsButton.setPreferredSize(new Dimension(200, 30));
        viewCollegesAndProgramsButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewCollegesAndProgramsButton.addActionListener(this);
        viewCollegesAndProgramsButton.setFocusable(false);

        // Positioning of buttons starts here

        gbc.insets = new Insets(10, 0, 0, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(addStudentButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(addProgramsButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(addCollegesButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 4;

        this.add(viewCollegesAndProgramsButton, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft


        // Initalize colleges ArrayList with a college that already exists in the database
        initializeCollegesAndProgramsInColleges();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            this.frame.changeToIntroScreen(2);
        }
        else if(e.getSource() == addStudentButton) {
            if (!colleges.isEmpty()) {
                preLoad2DArrayList();

                Object[] collegesObject = objectColleges(colleges);

                Object[] obj = new Object[5];

                obj[0] = JOptionPane.showInputDialog(null, "ID Number: ",
                        "Input ID Number", JOptionPane.QUESTION_MESSAGE);
                obj[1] = JOptionPane.showInputDialog(null, "First Name: ",
                        "Input First Name", JOptionPane.QUESTION_MESSAGE);
                obj[2] = JOptionPane.showInputDialog(null, "Last Name: ",
                        "Input Last Name", JOptionPane.QUESTION_MESSAGE);
                obj[3] = JOptionPane.showInputDialog(null, "Program: ",
                        "Input Program", JOptionPane.QUESTION_MESSAGE);
                obj[4] = JOptionPane.showInputDialog(null, "College: ",
                        "Input College", JOptionPane.QUESTION_MESSAGE, null, collegesObject, collegesObject[0]);

                // Add student using an instance of the table holder class, method defined in TableHolder.java
                this.tableHolder.addStudentInRow(obj);

                JOptionPane.showMessageDialog(null, "Student added successfully.",
                        "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No college added. Please add a college.",
                        "", JOptionPane.WARNING_MESSAGE);
            }

        }
        else if(e.getSource() == addProgramsButton) {
            preLoad2DArrayList();

            if (colleges.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No college added. Please add a college.",
                        "", JOptionPane.WARNING_MESSAGE);
            } else {
                String program;
                Object programsCollege;
                Object[] collegesObject = objectColleges(colleges);

                program = JOptionPane.showInputDialog(null, "Add program",
                        "", JOptionPane.QUESTION_MESSAGE);
                programsCollege = JOptionPane.showInputDialog(null, "Under what college?",
                        "", JOptionPane.QUESTION_MESSAGE, null, collegesObject, collegesObject[0]);

                // Trims both leading and trailing white spaces in String
                program = program.trim();

                for (ArrayList<String> collegeAndPrograms : programsInColleges) {
                    // If program doesn't exist in college
                    if (programsCollege.toString().equals(collegeAndPrograms.getFirst()) && !collegeAndPrograms.contains(program)) {
                        collegeAndPrograms.add(program);
                        JOptionPane.showMessageDialog(null, program + " successfully added in " +
                                programsCollege + ".","", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    // If program already exists in college
                    } else if ((programsCollege.toString().equals(collegeAndPrograms.getFirst()) && collegeAndPrograms.contains(program))) {
                        JOptionPane.showMessageDialog(null, program + " already exists in " +
                                programsCollege + ", add another program.", "", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            }
        }
        else if(e.getSource() == addCollegesButton) {
            String program;

            while(true){
                program = JOptionPane.showInputDialog(null, "Add college",
                        "", JOptionPane.QUESTION_MESSAGE);

                if (colleges.contains(program)){
                    JOptionPane.showMessageDialog(null, "College already exists",
                            "", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (program == null){
                    break;
                }
                else if (program.isEmpty()){
                    JOptionPane.showMessageDialog(null, "College cannot be blank, try again.",
                            "", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    colleges.add(program);
                    JOptionPane.showMessageDialog(null, program + " successfully added.",
                            "", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            preLoad2DArrayList();
        }
        else if(e.getSource() == viewCollegesAndProgramsButton){
            if(colleges.isEmpty()){
                JOptionPane.showMessageDialog(null, "No colleges yet. Please input a college to" +
                                " open this window.","", JOptionPane.WARNING_MESSAGE);
            } else{
                new ViewCollegesAndProgramsWindow(colleges, programsInColleges);
            }
        }

    }

    // Transfers each college in collegeList ArrayList into Object[]
    public Object[] objectColleges(ArrayList<String> collegeList){
        Object[] collegesInObject = new Object[colleges.size()];
        int objectCtr = 0;

        for(String college : collegeList){
            collegesInObject[objectCtr] = college;
            objectCtr++;
        }
        return collegesInObject;
    }

    // Loads programsInColleges with colleges from colleges ArrayList
    public void preLoad2DArrayList(){
        for(String college: colleges){
            ArrayList<String> collegeArrayList= new ArrayList<>();
            collegeArrayList.add(college);

            // If college is not in programsInColleges, then add college
            if (!programsInColleges.contains(collegeArrayList)) {
                programsInColleges.add(collegeArrayList);
            }
        }
    }

    public void initializeCollegesAndProgramsInColleges(){
        for(String[] line: dataFromCSV){
            ArrayList<String> collegeArrayList= new ArrayList<>();
            String programFromLine = line[3];
            String collegeFromLine = line[4];

            // Trims both leading and trailing white spaces in String
            programFromLine = programFromLine.trim();

            // Add to an ArrayList
            collegeArrayList.add(collegeFromLine);

            //----- Add new college in college and programsInCollege ArrayLists

            // If college is not in programsInColleges, then add college
            if (!programsInColleges.contains(collegeArrayList) && !colleges.contains(collegeFromLine)) {
                // Add in programsInColleges 2D ArrayList
                programsInColleges.add(collegeArrayList);
                // Add in colleges ArrayList
                colleges.add(collegeFromLine);
            }

            //----- Add new program in a college in programInCollege ArrayList

            // Note: collegeAndPrograms is an ArrayList in programsInColleges
            for (ArrayList<String> collegeAndPrograms: programsInColleges) {
                // Check if the current college is the same with the college that is being searched
                // AND if that college doesn't already contain the current program

                if (collegeFromLine.equals(collegeAndPrograms.getFirst()) && !collegeAndPrograms.contains(programFromLine)) {
                    collegeAndPrograms.add(programFromLine);
                    break;
                }
            }
        }
    }
}

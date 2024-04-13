import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class AdminScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
//    JButton addStudentButton = new JButton("Add Student");
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

    AdminScreen(FrameHolder frame, TableHolder tableHolder, ArrayList<String[]> dataFromCSV) {
        this.dataFromCSV = dataFromCSV;

        this.frame = frame;
        this.tableHolder = tableHolder;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.CYAN);
        this.setBounds(500, 0, 250, 500);

        backButton.setPreferredSize(new Dimension(65, 30));
        backButton.setFont(new Font("Arial", Font.BOLD, 30));
        backButton.addActionListener(this);
        backButton.setFocusable(false);

//        addStudentButton.setPreferredSize(new Dimension(180, 30));
//        addStudentButton.setFont(new Font("Arial", Font.BOLD, 20));
//        addStudentButton.addActionListener(this);
//        addStudentButton.setFocusable(false);

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

//        gbc.insets = new Insets(10, 0, 0, 0);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        this.add(addStudentButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(addProgramsButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(addCollegesButton, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 3;

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
            this.frame.changeToIntroScreen(3);
        }
        else if(e.getSource() == addProgramsButton) {
            preLoad2DArrayList();

            if (colleges.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No college added. Please add a college.",
                        "", JOptionPane.WARNING_MESSAGE);
            } else {
                String program = "";
                Object programsCollege = "";
                Object[] collegesObject = objectColleges(colleges);
                Object responseObject;

                String[] paneMessages = {"Add program", "Under what college?"};

                // This for loop and if statements are added so that when the exit button in a pane is pressed, no more
                // panes will show up

                for (int i = 0; i < 2; i++){
                    if (i == 0){
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[0],
                                "", JOptionPane.QUESTION_MESSAGE);
                    } else{
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[1],
                                "", JOptionPane.QUESTION_MESSAGE, null, collegesObject, collegesObject[0]);
                    }

                    if (responseObject == null){
                        return;
                    } else{
                        if (i == 0){
                            program = responseObject.toString();
                        } else{
                            programsCollege = responseObject;
                        }
                    }
                }

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

            // A flag that checks if an ArrayList of the respective college already exists
            boolean doesExist = false;

            // This for loop checks if the ArrayList of college already exists or not
            // If it doesn't exist, it will add collegeArrayList to programsInColleges

            // Added to avoid ConcurrentModificationException
            // Which means that you are modifying the iterable that is currently being iterated
            // What I did was separate the verifying and adding processes instead

            for (ArrayList<String> programsInCollege : programsInColleges) {
                if (programsInCollege.contains(college)) {
                    doesExist = true;
                    break;
                }
            }

            if (!doesExist){
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

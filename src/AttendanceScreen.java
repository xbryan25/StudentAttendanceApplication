import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class AttendanceScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JButton addStudentButton = new JButton("Add Student");
    JButton viewCollegesAndProgramsButton = new JButton("View colleges and programs");

    FrameHolder frame;
    TableHolder tableHolder;

//    TableHolder tableHolder = new TableHolder();
    GridBagConstraints gbc = new GridBagConstraints();

    ArrayList<String> colleges = new ArrayList<>();
    ArrayList<ArrayList<String>> programsInColleges = new ArrayList<>();

    ArrayList<String[]> dataFromCSV;

    String eventTitle;
    boolean eventTitleCancel = false;

    AttendanceScreen(FrameHolder frame, TableHolder tableHolder, ArrayList<String[]> dataFromCSV, boolean hasInitialized,
                     ArrayList<String> collegesData, ArrayList<ArrayList<String>> programsInCollegesData) {

        this.frame = frame;
        this.tableHolder = tableHolder;

        if (!frame.hasEventTitle){
            while(true){
                eventTitle = JOptionPane.showInputDialog(null, "To proceed, input a name for the event.", "",
                        JOptionPane.QUESTION_MESSAGE);

                if(eventTitle != null && !eventTitle.isEmpty()){
                    break;

                } else if(eventTitle == null){
                    eventTitleCancel = true;
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Input a name for the event to proceed.",
                            "", JOptionPane.WARNING_MESSAGE);
                }
            }

            this.dataFromCSV = new ArrayList<>();

            this.tableHolder.setTitle(eventTitle);
        } else{
            this.dataFromCSV = dataFromCSV;
        }

        if (!eventTitleCancel){
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

            this.add(viewCollegesAndProgramsButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on topleft

            if (!hasInitialized){
                // Initalize colleges ArrayList with a college that already exists in the database
                initializeCollegesAndProgramsInColleges();
            } else{
                colleges = collegesData;
                programsInColleges = programsInCollegesData;
            }
        }
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

                // obj stores the data of the added student in an Object Array
                // Its purpose is to become a storage which makes the addition of the new student in the table possible
                Object[] obj = new Object[5];
                Object responseObject;

                String[] paneMessages = {"ID Number: ", "First Name: ", "Last Name: ", "College: ", "Program: "};
                String[] paneTitles = {"Input ID Number", "Input First Name ", "Input Last Name", "Input College",
                                        "Input Program"};

                // This for loop and if statements are added so that when the exit button in a pane is pressed, no more
                // panes will show up

                for (int i = 0; i < 5; i++){
                    if (i < 3){
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[i], paneTitles[i],
                                   JOptionPane.QUESTION_MESSAGE);
                    } else if (i == 3){
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[i],
                                         paneTitles[i], JOptionPane.QUESTION_MESSAGE, null, collegesObject,
                                         collegesObject[0]);
                    } else{
                        Object tempCollege = obj[4];
                        Object[] programInCollegeObject = objectProgramInColleges(tempCollege);
//
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[i],
                                paneTitles[i], JOptionPane.QUESTION_MESSAGE, null, programInCollegeObject,
                                programInCollegeObject[0]);
                    }


                    if (responseObject == null){
                        return;
                    } else if (i == 3){
                        // This else if statement was added as the college was asked first, instead of the program
                        // This reestablishes the proper order of the inputs
                        obj[4] = responseObject;
                    } else if (i == 4){
                        obj[3] = responseObject;
                    } else{
                        obj[i] = responseObject;
                    }
                }

                // Add student using an instance of the table holder class, method defined in TableHolder.java
                this.tableHolder.addStudentInRow(obj);

                JOptionPane.showMessageDialog(null, "Student added successfully.",
                        "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No college added. Please add a college.",
                        "", JOptionPane.WARNING_MESSAGE);
            }

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

    // Transfers each program ArrayList in programsInColleges ArrayList into Object[]
    // The method will check the first element of the ArrayList to see if it matches to collegePrompt Object
    public Object[] objectProgramInColleges(Object collegePrompt){
        int collegePromptSize = 0;
        ArrayList<String> tempProgramsInCollege = new ArrayList<>();


        // Get the size of the array list of a specific college in programsInColleges

        for (ArrayList<String> programsInCollege: programsInColleges){
            if (collegePrompt.equals(programsInCollege.getFirst())){

                // Copy value of ArrayList into a temporary ArrayList
                tempProgramsInCollege.addAll(programsInCollege);

                // Remove leading college in ArrayList
                tempProgramsInCollege.removeFirst();

                collegePromptSize = tempProgramsInCollege.size();
                break;
            }
        }

        Object[] programInCollegesObject = new Object[collegePromptSize];
        int objectCtr = 0;

        for(String programs: tempProgramsInCollege){
            programInCollegesObject[objectCtr] = programs;
            objectCtr++;
        }

        return programInCollegesObject;
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

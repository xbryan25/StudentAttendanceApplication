package main;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AttendanceScreen extends JPanel implements ActionListener{
    JButton backButton = new JButton("←");
    JButton addStudentButton = new JButton("Add Student");
    JButton viewCollegesAndProgramsButton = new JButton("View colleges and programs");

    FrameHolder frame;
    TableHolder tableHolder;

    GridBagConstraints gbc = new GridBagConstraints();

    ArrayList<String> colleges = new ArrayList<>();

    ArrayList<String[]> dataFromCSV;
    ArrayList<ArrayList<ArrayList<String>>> dataFromCollegesAndProgramsCSV;
    String eventTitle;
    boolean eventTitleCancel = false;

    AttendanceScreen(FrameHolder frame, TableHolder tableHolder, ArrayList<String[]> dataFromCSV, ArrayList<ArrayList<ArrayList<String>>> dataFromCollegesAndProgramsCSV,
                boolean hasInitialized, ArrayList<String> collegesData) {

        this.frame = frame;
        this.tableHolder = tableHolder;
        this.dataFromCollegesAndProgramsCSV = dataFromCollegesAndProgramsCSV;

        if (!frame.hasEventTitle){
            while(true){
                eventTitle = JOptionPane.showInputDialog(null, "To proceed, input a name for the event.", "",
                        JOptionPane.QUESTION_MESSAGE);

                if(eventTitle != null && !eventTitle.isEmpty() && !eventTitle.isBlank() && eventTitle.length() <= 20){
                    break;
                } else if(eventTitle == null){
                    eventTitleCancel = true;
                    break;
                } else if(eventTitle.length() > 20){
                    JOptionPane.showMessageDialog(null, "The name of the event is too long (should be less than or equal to 20 characters).",
                            "", JOptionPane.WARNING_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, "Input a name for the event to proceed.",
                            "", JOptionPane.WARNING_MESSAGE);
                }
            }

            this.dataFromCSV = new ArrayList<>();

            this.tableHolder.setTitle(eventTitle);

            // Once a name of the event has been inputted, the start date will be tracked as well
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            LocalDateTime timeNow = LocalDateTime.now();
            frame.databaseStartDate = dateTimeFormat.format(timeNow);
        } else{
            this.dataFromCSV = dataFromCSV;
        }

        if (!eventTitleCancel){
            this.setLayout(new GridBagLayout());
            this.setBounds(500, 0, 250, 550);

            backButton.setPreferredSize(new Dimension(65, 30));
            backButton.setFont(new Font("Arial", Font.BOLD, 30));
            backButton.addActionListener(this);
            backButton.setFocusable(false);

            addStudentButton.setPreferredSize(new Dimension(180, 30));
            addStudentButton.setFont(new Font("Arial", Font.BOLD, 16));
            addStudentButton.addActionListener(this);
            addStudentButton.setFocusable(false);

            viewCollegesAndProgramsButton.setPreferredSize(new Dimension(200, 30));
            viewCollegesAndProgramsButton.setFont(new Font("Arial", Font.BOLD, 12));
            viewCollegesAndProgramsButton.addActionListener(this);
            viewCollegesAndProgramsButton.setFocusable(false);

            // Positioning of buttons starts here

            gbc.insets = new Insets(20, 0, 0, 20);

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(backButton, gbc);

            gbc.insets = new Insets(25, 0, 0, 20);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(addStudentButton, gbc);

            // Add blank label to add space
            gbc.insets = new Insets(0, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 2;

            this.add(new JLabel(" "), gbc);

            gbc.insets = new Insets(10, 0, 0, 20);

            gbc.gridx = 0;
            gbc.gridy = 3;

            this.add(viewCollegesAndProgramsButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.add(new JLabel(" "), gbc);  // blank JLabel, put on bottom right to put back button on top left

            if (!hasInitialized){
                // Initialize colleges ArrayList with a college that already exists in the database
                initializeCollegesAndProgramsInColleges();
            } else{
                colleges = collegesData;
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
                        // Checks if ID number already exists or not
                        if (i == 0){
                            while (true){
                                responseObject = JOptionPane.showInputDialog(null, paneMessages[i], paneTitles[i],
                                        JOptionPane.QUESTION_MESSAGE);

                                if (responseObject != null && responseObject.toString().isBlank()) {
                                    JOptionPane.showMessageDialog(null, "The ID number shouldn't be blank ",
                                            "", JOptionPane.WARNING_MESSAGE);
                                } else{
                                    boolean alreadyExists = false;

                                    int rowCount = tableHolder.table.model.getRowCount();

                                    for (int j = 0; j < rowCount; j++){
                                        String tempIDNum = tableHolder.table.model.getValueAt(j, 0).toString();
                                        if (responseObject != null && responseObject.equals(tempIDNum)){
                                            alreadyExists = true;
                                            break;
                                        }
                                    }

                                    if (!alreadyExists){
                                        break;
                                    } else{
                                        JOptionPane.showMessageDialog(null, "ID number already exists. Try again.",
                                                "", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            }
                        } else{
                            while (true){
                                responseObject = JOptionPane.showInputDialog(null, paneMessages[i], paneTitles[i],
                                        JOptionPane.QUESTION_MESSAGE);

                                if (responseObject != null && responseObject.toString().isBlank()){
                                    JOptionPane.showMessageDialog(null, "The name shouldn't be blank ",
                                            "", JOptionPane.WARNING_MESSAGE);
                                } else{
                                    break;
                                }
                            }
                        }
                    }

                    // This is different because it shows a dropdown menu
                    else if (i == 3){
                        responseObject = JOptionPane.showInputDialog(null, paneMessages[i],
                                         paneTitles[i], JOptionPane.QUESTION_MESSAGE, null, collegesObject,
                                         collegesObject[0]);
                    } else{
                        Object tempCollege = obj[4];
                        Object[] programInCollegeObject = objectProgramInColleges(tempCollege);

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

                // Add student using an instance of the table holder class, method defined in main.TableHolder.java
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
                new ViewCollegesAndProgramsWindow(colleges, dataFromCollegesAndProgramsCSV);
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
        int collegePromptSize;
        ArrayList<String> tempProgramsInCollege = new ArrayList<>();

        for(ArrayList<ArrayList<String>> aCollegeAndItsPrograms: dataFromCollegesAndProgramsCSV){
            if (collegePrompt.equals(aCollegeAndItsPrograms.getFirst().getFirst())){
                for (ArrayList<String> eachElementInACollegeAndItsPrograms: aCollegeAndItsPrograms){
                    // Add the first elements in the ArrayLists, which are the subject codes, to the temp ArrayList
                    tempProgramsInCollege.add(eachElementInACollegeAndItsPrograms.getFirst());
                }
            }
        }

        // Remove first element in ArrayList because it is the college title
        tempProgramsInCollege.removeFirst();
        collegePromptSize = tempProgramsInCollege.size();

        Object[] programInCollegesObject = new Object[collegePromptSize];
        int objectCtr = 0;

        for(String programs: tempProgramsInCollege){
            programInCollegesObject[objectCtr] = programs;
            objectCtr++;
        }

        return programInCollegesObject;
    }

    public void initializeCollegesAndProgramsInColleges(){
        for(ArrayList<ArrayList<String>> aCollegeAndItsPrograms: dataFromCollegesAndProgramsCSV){
            for(ArrayList<String> eachElementInACollegeAndItsPrograms: aCollegeAndItsPrograms){
                // Only get the first element of eachElementInACollegeAndItsPrograms, since it's always the college
                colleges.add(eachElementInACollegeAndItsPrograms.getFirst());
                break;
            }
        }
    }
}

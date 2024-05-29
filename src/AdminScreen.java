// Import from itextpdf jar file
//import com.itextpdf.text.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;


import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AdminScreen extends JPanel implements ActionListener{
    String databaseName = "src\\database.csv";
    JButton backButton = new JButton("←");
    JButton deleteStudentButtonByRow = new JButton("Delete Student (Row)");
    JButton deleteStudentButtonByID = new JButton("Delete Student (ID)");
    JButton addProgramsButton = new JButton("Add programs");
    JButton addCollegesButton = new JButton("Add colleges");
    JButton viewCollegesAndProgramsButton = new JButton("View colleges and programs");
    JButton renameEvent = new JButton("Rename event");
    JButton saveProgress = new JButton("Save progress");
    JButton endAttendance = new JButton("End attendance");

    FrameHolder frame;
    TableHolder tableHolder;

    GridBagConstraints gbc = new GridBagConstraints();

    ArrayList<String> colleges = new ArrayList<>();
    ArrayList<ArrayList<String>> programsInColleges = new ArrayList<>();

    ArrayList<String[]> dataFromCSV;

    String eventTitle;
    boolean eventTitleCancel = false;

    AdminScreen(FrameHolder frame, TableHolder tableHolder, ArrayList<String[]> dataFromCSV, boolean hasInitialized,
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

            // Once a name of the event has been inputted, the start date will be tracked as well
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            LocalDateTime timeNow = LocalDateTime.now();
            frame.databaseStartDate = dateTimeFormat.format(timeNow);
        } else{
            this.eventTitle = frame.eventTitle;
            this.dataFromCSV = dataFromCSV;
        }

        if (!eventTitleCancel){
            this.setLayout(new GridBagLayout());
            this.setBackground(Color.CYAN);
            this.setBounds(500, 0, 250, 500);

            backButton.setPreferredSize(new Dimension(65, 30));
            backButton.setFont(new Font("Arial", Font.BOLD, 30));
            backButton.addActionListener(this);
            backButton.setFocusable(false);

            deleteStudentButtonByRow.setPreferredSize(new Dimension(200, 30));
            deleteStudentButtonByRow.setFont(new Font("Arial", Font.BOLD, 16));
            deleteStudentButtonByRow.addActionListener(this);
            deleteStudentButtonByRow.setFocusable(false);

            deleteStudentButtonByID.setPreferredSize(new Dimension(200, 30));
            deleteStudentButtonByID.setFont(new Font("Arial", Font.BOLD, 16));
            deleteStudentButtonByID.addActionListener(this);
            deleteStudentButtonByID.setFocusable(false);

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

            renameEvent.setPreferredSize(new Dimension(200, 30));
            renameEvent.setFont(new Font("Arial", Font.BOLD, 12));
            renameEvent.addActionListener(this);
            renameEvent.setFocusable(false);

            saveProgress.setPreferredSize(new Dimension(200, 30));
            saveProgress.setFont(new Font("Arial", Font.BOLD, 12));
            saveProgress.addActionListener(this);
            saveProgress.setFocusable(false);

            endAttendance.setPreferredSize(new Dimension(200, 30));
            endAttendance.setFont(new Font("Arial", Font.BOLD, 12));
            endAttendance.addActionListener(this);
            endAttendance.setFocusable(false);

            // Positioning of buttons starts here

            gbc.insets = new Insets(10, 0, 0, 10);

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(backButton, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(deleteStudentButtonByRow, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.add(deleteStudentButtonByID, gbc);

            // Add blank label to add space
            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 3;

            this.add(new JLabel(" "), gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 4;
            this.add(addProgramsButton, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 5;
            this.add(addCollegesButton, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 6;

            this.add(viewCollegesAndProgramsButton, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 7;

            this.add(renameEvent, gbc);

            // Add blank label to add space
            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 8;

            this.add(new JLabel(" "), gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 9;

            this.add(saveProgress, gbc);

            gbc.insets = new Insets(10, 0, 0, 0);

            gbc.gridx = 0;
            gbc.gridy = 10;

            this.add(endAttendance, gbc);

            gbc.gridx = 0;
            gbc.gridy = 11;
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
            this.frame.changeToIntroScreen(3);
        }
        else if(e.getSource() == deleteStudentButtonByRow){
            // Typecast to DefaultTableModel since it only gives TableModel

            DefaultTableModel tableModel = (DefaultTableModel) tableHolder.table.mainTable.getModel();

            int selectedRowCount = tableHolder.table.mainTable.getSelectedRowCount();
            int selectedRow = tableHolder.table.mainTable.getSelectedRow();

            if (selectedRowCount == 1){
                tableModel.removeRow(selectedRow);
            } else if (selectedRowCount == 0){
                JOptionPane.showMessageDialog(null, "No row selected. Select a row to delete.",
                        "", JOptionPane.WARNING_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, "Multiple rows selected. Only select one to delete.",
                        "", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(e.getSource() == deleteStudentButtonByID){
            String IDToDelete;
            boolean IDIsFound = false;

            do{
                IDToDelete = JOptionPane.showInputDialog(null, "Input ID Number of the student",
                        "", JOptionPane.QUESTION_MESSAGE);

                if (IDToDelete != null && !IDToDelete.isEmpty()){
                    break;
                } else if (IDToDelete == null){
                    return;
                } else{
                    JOptionPane.showMessageDialog(null, "Input an ID Number.",
                                            "", JOptionPane.WARNING_MESSAGE);
                }
            } while(true);

            // Gets the data from the table
            DefaultTableModel tableModel = tableHolder.table.model;

            for (int count = 0; count < tableModel.getRowCount(); count++){
                String currentIDInModel = tableModel.getValueAt(count, 0).toString();
                if (IDToDelete.equals(currentIDInModel)){
                    tableModel.removeRow(count);
                    IDIsFound = true;
                    break;
                }
            }

            if (!IDIsFound){
                JOptionPane.showMessageDialog(null, "ID Number cannot be found in the table.",
                                         "", JOptionPane.INFORMATION_MESSAGE);
            }
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
        else if(e.getSource() == renameEvent){
            while(true){
                eventTitle = JOptionPane.showInputDialog(null, "Input a name to rename the event.", "",
                        JOptionPane.QUESTION_MESSAGE);

                if(eventTitle != null && !eventTitle.isEmpty()){
                    tableHolder.setTitle(eventTitle);
                    break;

                } else if(eventTitle == null){
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Name cannot be blank. Try again.",
                            "", JOptionPane.WARNING_MESSAGE);
                }
            }

            JOptionPane.showMessageDialog(null, "Event successfully renamed to " + eventTitle + ".",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == saveProgress){
            if (!frame.tableHasData){
                JOptionPane.showMessageDialog(null, "No data yet. Add students first.",
                        "", JOptionPane.WARNING_MESSAGE);
            } else{
                try (FileWriter writer = new FileWriter(databaseName)){
                    String[] tableColumns = {"ID Number", "First Name", "Last Name", "Program", "College"};

                    writer.write("Event title: " + eventTitle + "\n");

                    if (frame.databaseStartDate.isEmpty()){
                        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
                        LocalDateTime timeNow = LocalDateTime.now();
                        String dateInString = dateTimeFormat.format(timeNow);

                        writer.write("Date started: " + dateInString + "\n");
                    } else{
                        writer.write("Date started: " + frame.databaseStartDate + "\n");
                    }

                    writer.write("Date ended: \n\n");

                    writer.write(tableColumns[0] + "," + tableColumns[1] + "," + tableColumns[2] + "," + tableColumns[3] + "," + tableColumns[4] + "\n");

                    DefaultTableModel tableModel = tableHolder.table.model;

                    // Pulls data from the table
                    for (int count = 0; count < tableModel.getRowCount(); count++){
                        // Gets the data from each row of the table
                        String dataFromEachRow = tableModel.getDataVector().elementAt(count).toString();

                        // Removes the square braces in the string
                        dataFromEachRow = dataFromEachRow.substring(1, dataFromEachRow.length() - 1);

                        // Removes all the white spaces in the string
                        dataFromEachRow = dataFromEachRow.replace(" ", "");

                        writer.write(dataFromEachRow + "\n");
                    }

                    JOptionPane.showMessageDialog(null, "Progress successfully saved.",
                            "", JOptionPane.INFORMATION_MESSAGE);

                    writer.flush();
                } catch(IOException error){
                    System.out.println(error.getMessage());
                }
            }
        }
        else if (e.getSource() == endAttendance){
            if (tableHolder.table.model.getRowCount() != 0){

                // Saving data to PDF (Reference: jinu jawad m)
                try{
                    // Not included in reference - get current time
                    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
                    LocalDateTime timeNow = LocalDateTime.now();
                    String endTime = dateTimeFormat.format(timeNow);

                    // Create PDF
                    Document document = new Document(PageSize.A4);
                    String pdfFileName = eventTitle + ".pdf";
                    PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));

                    document.open();

                    // Font styles
                    com.itextpdf.text.Font pdfTitleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 15, com.itextpdf.text.Font.BOLD);
                    com.itextpdf.text.Font pdfHeadingsFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 13, com.itextpdf.text.Font.BOLD);
                    com.itextpdf.text.Font pdfCellFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.NORMAL);

                    // Add content in PDF

                    Paragraph eventTitleParagraph = new Paragraph("Event title: " + eventTitle, pdfTitleFont);

                    Paragraph eventStartedParagraph = new Paragraph("Date started: " + frame.databaseStartDate, pdfTitleFont);

                    Paragraph eventEndedParagraph = new Paragraph("Date ended: " + endTime, pdfTitleFont);

                    Paragraph newLines = new Paragraph("\n\n");

                    PdfPTable tableInPDF = new PdfPTable(5);

                    // Headers

                    String[] tableColumnsInPDF = {"ID Number", "First Name", "Last Name", "Program", "College"};

                    for(String tableColumn: tableColumnsInPDF){
                        PdfPCell tableHeadingInPDF = new PdfPCell(new Phrase(tableColumn, pdfHeadingsFont));
                        tableHeadingInPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tableInPDF.addCell(tableHeadingInPDF);
                    }

                    tableInPDF.setHeaderRows(1);

                    // Data from the table in the application
                    DefaultTableModel tableModel = tableHolder.table.model;
                    String dataInTableModel;

                    for (int countRow = 0; countRow < tableModel.getRowCount(); countRow++){
                        for (int countColumn = 0; countColumn < tableModel.getColumnCount(); countColumn++){
                            dataInTableModel = tableModel.getValueAt(countRow, countColumn).toString();

                            PdfPCell tableCellInPDF = new PdfPCell(new Phrase(dataInTableModel, pdfCellFont));
                            tableCellInPDF.setHorizontalAlignment(Element.ALIGN_CENTER);

                            tableInPDF.addCell(tableCellInPDF);
                        }
                    }

                    document.add(eventTitleParagraph);
                    document.add(eventStartedParagraph);
                    document.add(eventEndedParagraph);
                    document.add(newLines);

                    document.add(tableInPDF);

                    // Close the document
                    document.close();

                }
                catch (Exception error){
                    System.out.println(error.getMessage());
                }

                // Erase all contents in database.csv
                try (FileWriter writer = new FileWriter(databaseName)){
                    String[] tableColumns = {"ID Number", "First Name", "Last Name", "Program", "College"};

                    writer.write("Event title:\n");
                    writer.write("Date started:\n\n");

                    writer.write(tableColumns[0] + "," + tableColumns[1] + "," + tableColumns[2] + "," + tableColumns[3] + "," + tableColumns[4] + "\n");

                    writer.flush();
                } catch(IOException error){
                    System.out.println(error.getMessage());
                }

                // Clears the contents of the tableHolder
                tableHolder.table.model.setRowCount(0);

                this.frame.changeToIntroScreen(6);
            } else{
                JOptionPane.showMessageDialog(null, "Table is empty.",
                        "", JOptionPane.WARNING_MESSAGE);
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

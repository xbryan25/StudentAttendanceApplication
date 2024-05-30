import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FrameHolder extends JFrame{
    // Becomes true if the program has already read through the database
    // Wrote to remove redundancy in reading through the database
    boolean hasInitialized = false;

    // For loading data in csv; moved from attendance table so that it will have a greater scope
    String databasePath = "src\\database.csv";

    // Information about the database
    String databaseStartDate = "";

    BufferedReader reader;
    ArrayList<String[]> dataFromCSV = new ArrayList<>();
    IntroScreen introScreen;
    AttendanceScreen attendanceScreen;
    AdminScreen adminScreen;
    TableHolder tableHolder;

    // Container to hold the current data
    DefaultTableModel tableData;
    boolean tableHasData = false;

    // Containers to hold the colleges and programs data
    ArrayList<String> collegesData;
    ArrayList<ArrayList<String>> programsInCollegesData;
    AboutThisAppScreen aboutScreen = new AboutThisAppScreen(this);

    // Title of event
    String eventTitle;
    boolean eventTitleCancel = false;
    boolean hasEventTitle = false;
    FrameHolder(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Student Attendance Application by xbryan25");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);

        introScreen = new IntroScreen(this);
        this.add(introScreen);
        this.add(aboutScreen);

//        attendanceScreen.setVisible(false);
        aboutScreen.setVisible(false);

        // Fetch data from CSV
        getDataFromCSV();
    }

    public void changeToAboutThisAppScreen(){
        aboutScreen = new AboutThisAppScreen(this);

        this.remove(introScreen);
        this.add(aboutScreen);

        this.setSize(500, 550);

        this.revalidate();
        this.repaint();
    }

    public void changeToAttendanceScreen(){
        if (!eventTitleCancel){
            if (!tableHasData){
                tableHolder = new TableHolder(dataFromCSV, tableData, tableHasData);
                tableHasData = true;
            } else{
                tableHolder = new TableHolder(dataFromCSV, tableData, tableHasData);
            }
        }

        attendanceScreen = new AttendanceScreen(this, tableHolder, dataFromCSV, hasInitialized, collegesData, programsInCollegesData);

        eventTitleCancel = attendanceScreen.eventTitleCancel;

        if (!eventTitleCancel){
            this.remove(introScreen);

            // Set title of event, only executes if the database is not empty
            if (hasEventTitle){
                attendanceScreen.tableHolder.setTitle(eventTitle);
            }

            hasInitialized = true;

            this.setSize(750, 500);

            this.add(attendanceScreen);
            this.add(tableHolder);

            this.revalidate();
            this.repaint();
        } else{
            this.changeToIntroScreen(4);
        }
    }

    public void changeToAdminScreen(){
        if (!eventTitleCancel){
            if (!tableHasData){
                tableHolder = new TableHolder(dataFromCSV, tableData, tableHasData);
                tableHasData = true;
            } else{
                tableHolder = new TableHolder(dataFromCSV, tableData, tableHasData);
            }
        }

        adminScreen = new AdminScreen(this, tableHolder, dataFromCSV, hasInitialized, collegesData, programsInCollegesData);

        eventTitleCancel = adminScreen.eventTitleCancel;

        if (!eventTitleCancel) {
            this.remove(introScreen);

            // Set title of event, only executes if the database is not empty
            if (hasEventTitle) {
                adminScreen.tableHolder.setTitle(eventTitle);
            }

            hasInitialized = true;

            this.setSize(750, 500);

            this.add(adminScreen);
            this.add(tableHolder);

            this.revalidate();
            this.repaint();
        } else{
            this.changeToIntroScreen(5);
        }
    }

    public void changeToIntroScreen(int state){
        if (state != 6){
            introScreen = new IntroScreen(this);
        }


        if(state == 1){
            this.remove(aboutScreen);

            this.setSize(500, 500);
        } else if(state == 2){
            // Get data from table before tableHolder is erased
            tableData = tableHolder.table.model;

            // Get colleges data from AdminScreen class and transfer to AttendanceScreen
            collegesData = attendanceScreen.colleges;

            // Get programs data from AdminScreen class and transfer to AttendanceScreen
            programsInCollegesData = attendanceScreen.programsInColleges;


            // Get event title
            if (!hasEventTitle){
                if (attendanceScreen.eventTitle != null){
                    eventTitle = attendanceScreen.eventTitle;
                }
                hasEventTitle = true;
            }

            this.remove(attendanceScreen);
            this.remove(tableHolder);
            this.setSize(500, 500);
        } else if(state == 3){
            // Get data from table before tableHolder is erased
            tableData = tableHolder.table.model;

            // Get colleges data from AdminScreen class and transfer to AttendanceScreen
            collegesData = adminScreen.colleges;

            // Get programs data from AdminScreen class and transfer to AttendanceScreen
            programsInCollegesData = adminScreen.programsInColleges;

            if (adminScreen.eventTitle != null){
                eventTitle = adminScreen.eventTitle;
            }

            // Get event title
            if (!hasEventTitle){
                hasEventTitle = true;
            }

            this.remove(adminScreen);
            this.remove(tableHolder);
            this.setSize(500, 500);

        } else if (state == 4){
            this.remove(attendanceScreen);
        } else if (state == 5){
            this.remove(adminScreen);
        } else if (state == 6){
            this.changeToIntroScreen(3);

            JOptionPane.showMessageDialog(null, "Attendance session ended. Please check the database.",
                    "", JOptionPane.INFORMATION_MESSAGE);

            hasEventTitle = false;
        }

        if (state != 6){
            this.add(introScreen);
        }

        this.revalidate();
        this.repaint();
    }

    private void getDataFromCSV(){
        try{
            String line;

            int count = 0;

            reader = new BufferedReader(new FileReader(databasePath));

            while((line = reader.readLine()) != null){
                // This makes it so that only lines 3 onwards from the csv will be read
                if (count == 0){
                    // Get event title
                    String[] title = line.split(",");

                    if (title[0].equals("Event title:")){
                        eventTitle = "";
                        hasEventTitle = false;
                    } else{
                        eventTitle = title[0].replace("Event title: ", "");
                        hasEventTitle = true;
                    }

                } else if (count == 1){
                    // Get event start date
                    String[] startDate = line.split(",");

                    if (startDate[0].equals("Date started:")){
                        databaseStartDate = "";
                    } else{
                        databaseStartDate = startDate[0].replace("Date started: ", "");
                    }

//                } else if (count == 2){
//                    // Get event end date
//                    String[] endDate = line.split(",");
//                    databaseEndDate = endDate[0].replace("Date ended: ", "");
                } else if (count > 3){
                    // Greater than 3 because row 4 is where the data starts in the database
                    String[] row = line.split(",");
                    dataFromCSV.add(row);
                }
                count++;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FrameHolder extends JFrame{
    // Becomes true if the program has already read through the database
    // Wrote to remove redundancy in reading through the database
    boolean hasInitialized = false;

    // For loading data in csv; moved from attendance table so that it will have a greater scope
    String databaseName = "src\\database.csv";
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
        introScreen = new IntroScreen(this);

        if(state == 1){
            this.remove(aboutScreen);
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
        }

        this.add(introScreen);
        this.revalidate();
        this.repaint();
    }

    private void getDataFromCSV(){
        try{
            String line;

            reader = new BufferedReader(new FileReader(databaseName));

            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                dataFromCSV.add(row);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

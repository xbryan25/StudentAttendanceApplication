import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class FrameHolder extends JFrame{
    // For loading data in csv; moved from attendace table so that it will have a greater scope
    String databaseName = "src\\database.csv";
    BufferedReader reader;
    ArrayList<String[]> dataFromCSV;
    IntroScreen introScreen;
    AttendanceScreen attendanceScreen;
    TableHolder tableHolder;
    AboutThisAppScreen aboutScreen = new AboutThisAppScreen(this);
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
        tableHolder = new TableHolder(dataFromCSV);
        attendanceScreen = new AttendanceScreen(this, tableHolder, dataFromCSV);

        this.setSize(750, 500);

        this.remove(introScreen);

        this.add(attendanceScreen);
        this.add(tableHolder);

        this.revalidate();
        this.repaint();
    }

    public void changeToIntroScreen(int state){
        introScreen = new IntroScreen(this);

        if(state == 1){
            this.remove(aboutScreen);
        } else if(state == 2){
            this.remove(attendanceScreen);
            this.remove(tableHolder);
            this.setSize(500, 500);
        }

        this.add(introScreen);
        this.revalidate();
        this.repaint();
    }

    private void getDataFromCSV(){
        try{
            String line = "";

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

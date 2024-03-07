import javax.swing.JFrame;
import java.lang.management.GarbageCollectorMXBean;

public class FrameHolder extends JFrame{
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
    }

    public void changeToAboutThisAppScreen(){
        aboutScreen = new AboutThisAppScreen(this);

        this.remove(introScreen);

        this.add(aboutScreen);

        this.revalidate();
        this.repaint();
    }

    public void changeToAttendanceScreen(){
        attendanceScreen = new AttendanceScreen(this);
        tableHolder = new TableHolder();

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
}

import javax.swing.JFrame;

public class FrameHolder extends JFrame{
    IntroScreen introScreen;
    AttendanceScreen attendanceScreen = new AttendanceScreen(this);
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
        this.add(attendanceScreen);
        this.add(aboutScreen);

        attendanceScreen.setVisible(false);
        aboutScreen.setVisible(false);
    }

    public void changeToAttendanceScreen(){
        introScreen.setVisible(false);
        attendanceScreen.setVisible(true);
    }

    public void changeToAboutThisAppScreen(){
        introScreen.setVisible(false);
        aboutScreen.setVisible(true);
    }

    public void changeToIntroScreen(int state){
        if(state == 1){
            aboutScreen.setVisible(false);
            introScreen.setVisible(true);
        } else if(state == 2){
            attendanceScreen.setVisible(false);
            introScreen.setVisible(true);
        }
    }
}

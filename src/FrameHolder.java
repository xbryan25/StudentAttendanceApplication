import javax.swing.JFrame;

public class FrameHolder extends JFrame{
    IntroScreen introScreen;
    AttendanceScreen attendanceScreen;
    FrameHolder(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Student Attendance Application by xbryan25");
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);

        introScreen = new IntroScreen(this);
        this.add(introScreen);


    }

    public void changeToAttendanceScreen(){

        attendanceScreen = new AttendanceScreen();
        this.add(attendanceScreen);
        attendanceScreen.setVisible(true);
    }
}

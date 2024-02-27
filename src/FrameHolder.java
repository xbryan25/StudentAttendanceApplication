import javax.swing.JFrame;

public class FrameHolder extends JFrame{
    IntroScreen introScreen;
    AttendanceScreen attendanceScreen = new AttendanceScreen();

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

        attendanceScreen.setVisible(false);

    }

    public void changeToAttendanceScreen(){
        introScreen.setVisible(false);
        attendanceScreen.setVisible(true);
    }
}

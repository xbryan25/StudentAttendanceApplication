import javax.swing.*;
import java.awt.*;

public class TableHolder extends JPanel{
    AttendanceTable table = new AttendanceTable();
    TableHolder(){
        this.setBackground(Color.ORANGE);
        this.setBounds(0, 100, 500, 400);

        this.add(table.pane);
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableHolder extends JPanel{
    AttendanceTable table;

    JLabel eventTitle = new JLabel();

    TableHolder(ArrayList<String[]> dataFromCSV){
        table = new AttendanceTable(dataFromCSV);

        this.setLayout(null);
        this.setBackground(Color.ORANGE);
        this.setBounds(0, 0, 500, 500);

        eventTitle.setText("Event: Research Week");
        eventTitle.setHorizontalAlignment(JLabel.CENTER);
        eventTitle.setFont(new Font("Arial", Font.BOLD, 30));
        eventTitle.setBounds(0, 0, 500, 75);

        this.add(eventTitle);
        this.add(table.pane);
    }

    public void addStudentInRow(Object[] obj){
        table.model.addRow(obj);
    }
}

package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableHolder extends JPanel{
    AttendanceTable table;

    JLabel eventTitle = new JLabel();

    ArrayList<String[]> dataFromStudentCSV;

    TableHolder(ArrayList<String[]> dataFromCSV, DefaultTableModel tableData, boolean tableHasData){
        if (!tableHasData) {
            this.dataFromStudentCSV = dataFromCSV;

            table = new AttendanceTable(dataFromCSV, tableData, tableHasData);

            this.setLayout(null);
            this.setBounds(0, 0, 500, 500);

            eventTitle.setHorizontalAlignment(JLabel.CENTER);
            eventTitle.setFont(new Font("Arial", Font.BOLD, 30));
            eventTitle.setBounds(0, 0, 500, 75);

            this.add(eventTitle);
            this.add(table.pane);

            initializeTable();
        } else{
            table = new AttendanceTable(dataFromCSV, tableData, tableHasData);

            this.setLayout(null);
            this.setBounds(0, 0, 500, 500);

            eventTitle.setHorizontalAlignment(JLabel.CENTER);
            eventTitle.setFont(new Font("Arial", Font.BOLD, 30));
            eventTitle.setBounds(0, 0, 500, 75);

            this.add(eventTitle);
            this.add(table.pane);
        }
    }

    // This method fills the table with data from the CSV
    public void initializeTable(){
        for(String[] row: dataFromStudentCSV){
            table.model.addRow(row);
        }
    }
    public void addStudentInRow(Object[] obj){
        table.model.addRow(obj);
    }

    public void setTitle(String title){
        eventTitle.setText("Event: " + title);
    }
}

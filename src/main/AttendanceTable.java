package main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;

// This class gets called by main.TableHolder

public class AttendanceTable{
    JTable mainTable = new JTable();
    Object[] columns = {"ID Number", "First Name", "Last Name", "Program", "College"};
    DefaultTableModel model;
    JScrollPane pane = new JScrollPane(mainTable);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    ArrayList<String[]> dataFromStudentCSV;
    AttendanceTable(ArrayList<String[]> dataFromCSV, DefaultTableModel tableData, boolean tableHasData){
        if (!tableHasData) {
            model = new DefaultTableModel();
            this.dataFromStudentCSV = dataFromCSV;

        } else{
            model = tableData;
        }

        model.setColumnIdentifiers(columns);
        mainTable.setModel(model);
        mainTable.setSelectionForeground(Color.BLACK);
        mainTable.setGridColor(Color.BLACK);
        mainTable.setFont(new Font("Arial", Font.PLAIN, 12));
        mainTable.setRowHeight(15);
        mainTable.setAutoCreateRowSorter(true);
        mainTable.setDefaultEditor(Object.class, null);
        mainTable.getTableHeader().setReorderingAllowed(false);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        mainTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        mainTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        mainTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        mainTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        mainTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        pane.setForeground(Color.RED);
        pane.setBounds(10, 75, 480, 425);
    }
}

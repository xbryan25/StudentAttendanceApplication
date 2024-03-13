import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class ViewCollegesAndProgramsWindow {

    JTable collegesTable = new JTable();
    Object[] columns = {"Colleges"};
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane pane = new JScrollPane(collegesTable);
    JDialog dialog = new JDialog();

    ViewCollegesAndProgramsWindow(FrameHolder frame){
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        dialog = new JDialog(frame, "What", true);
        dialog.setSize(500, 500);
//        dialog.setLocationRelativeTo(null);
//        dialog.setBounds(0, 0, 500, 500);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setBackground(Color.RED);
        dialog.setTitle("Edited");

        model.setColumnIdentifiers(columns);
        collegesTable.setModel(model);

        collegesTable.setBackground(Color.LIGHT_GRAY);
        collegesTable.setSelectionForeground(Color.BLACK);
        collegesTable.setGridColor(Color.BLACK);
        collegesTable.setFont(new Font("Arial", Font.PLAIN, 12));
        collegesTable.setRowHeight(15);
        collegesTable.setAutoCreateRowSorter(true);

        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(0, 0, 480, 375);

        model.addRow(new Object[]{"CCS"});
        model.addRow(new Object[]{"COET"});
        model.addRow(new Object[]{"CHS"});

        dialog.add(pane);
    }
}

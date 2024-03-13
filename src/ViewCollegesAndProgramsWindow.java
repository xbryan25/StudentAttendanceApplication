import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class ViewCollegesAndProgramsWindow {

    JTable collegesTable = new JTable();
    Object[] columns = {"Colleges"};
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane pane = new JScrollPane(collegesTable);
    JDialog dialog = new JDialog();

    ViewCollegesAndProgramsWindow(ArrayList<String> colleges){
        // Set properties of the JDialog
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setBackground(Color.RED);
        dialog.setTitle("Edited");


        // Creation of table
        model.setColumnIdentifiers(columns);
        collegesTable.setModel(model);

        collegesTable.setBackground(Color.LIGHT_GRAY);
        collegesTable.setSelectionForeground(Color.BLACK);
        collegesTable.setGridColor(Color.BLACK);
        collegesTable.setFont(new Font("Arial", Font.PLAIN, 12));
        collegesTable.setRowHeight(15);
        collegesTable.setAutoCreateRowSorter(true);
        collegesTable.setDefaultEditor(Object.class, null);
        collegesTable.getTableHeader().setReorderingAllowed(false);

        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(0, 0, 480, 375);

        for(String college: colleges){
            model.addRow(new Object[]{college});
        }

        dialog.add(pane);
    }
}

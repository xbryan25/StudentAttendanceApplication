import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ViewCollegesAndProgramsWindow {

    JTable collegesTable = new JTable();
    Object[] columns = {"Colleges"};

    // Override isCellEditable of DefaultTableModel
    DefaultTableModel model = new DefaultTableModel();

//    {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return column == 0;
//        }
//    };
    JScrollPane pane = new JScrollPane(collegesTable);
    JDialog dialog = new JDialog();

    ButtonRenderer buttonRenderer = new ButtonRenderer();
    ButtonEditor buttonEditor = new ButtonEditor(new JTextField());
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    ViewCollegesAndProgramsWindow(ArrayList<String> colleges){

        // Set properties of the JDialog
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setBackground(Color.RED);
        dialog.setTitle("Edited");
        dialog.setAlwaysOnTop(true);


        // Creation of table
        model.setColumnIdentifiers(columns);
        collegesTable.setModel(model);


        //      Set font of header
        collegesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 30));

        collegesTable.setBackground(Color.LIGHT_GRAY);
        collegesTable.setSelectionForeground(Color.BLACK);
        collegesTable.setGridColor(Color.BLACK);
        collegesTable.setFont(new Font("Arial", Font.BOLD, 20));
        collegesTable.setRowHeight(30);
        collegesTable.setAutoCreateRowSorter(true);
        collegesTable.setDefaultEditor(Object.class, null);
        collegesTable.getTableHeader().setReorderingAllowed(false);


        //      Put text in center
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        collegesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // To make button in cells of a JTable (1/2)
        // Very confusing, I will study its implementation
        // Link for vid: https://www.youtube.com/watch?v=3LiSHPqbuic
        collegesTable.getColumnModel().getColumn(0).setCellRenderer(buttonRenderer);
        collegesTable.getColumnModel().getColumn(0).setCellEditor(buttonEditor);

        // Set properties of the pane
        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(0, 0, 480, 375);


        // Add each college from the ArrayList colleges to the table
        for(String college: colleges){
            model.addRow(new Object[]{college});
        }

        dialog.add(pane);

        // Show message that each button in the table has to be double-clicked
        JOptionPane.showMessageDialog(dialog, "Note: As of the current version, each college" +
                " in the table has to be double clicked.", " ", JOptionPane.INFORMATION_MESSAGE, null);
    }

}

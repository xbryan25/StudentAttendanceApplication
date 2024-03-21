import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ViewCollegesAndProgramsWindow {

    ArrayList<String> colleges;
    ArrayList<ArrayList<String>> programsInColleges;
    JTable collegesTable = new JTable();
    JTable programsTable = new JTable();
    Object[] collegesColumns = {"Colleges"};
    Object[] programsColumns = {""};

    // Override isCellEditable of DefaultTableModel
    DefaultTableModel collegesTableModel = new DefaultTableModel();
    DefaultTableModel programsTableModel = new DefaultTableModel();

//    {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return column == 0;
//        }
//    };
    JScrollPane collegesTablePane = new JScrollPane(collegesTable);
    JScrollPane programsTablePane = new JScrollPane(programsTable);
    JDialog collegesListDialog = new JDialog();
    JDialog programsListDialog = new JDialog();

    ButtonRenderer buttonRenderer = new ButtonRenderer();

    // Plan: Pass ViewCollegesAndProgramsWindow object into ButtonEditor so that ButtonEditor will have access to
    // ViewCollegesAndProgramsWindow's methods
    ButtonEditor buttonEditor = new ButtonEditor(new JTextField(), this);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    ArrayList<String> chosenCollegeAndItsPrograms;

    ViewCollegesAndProgramsWindow(ArrayList<String> colleges, ArrayList<ArrayList<String>> programsInColleges){
        this.colleges = colleges;
        this.programsInColleges = programsInColleges;

        // Set properties of the JDialog
        collegesListDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        collegesListDialog.setSize(500, 500);
        collegesListDialog.setLocationRelativeTo(null);
        collegesListDialog.setVisible(true);
        collegesListDialog.setResizable(false);
        collegesListDialog.setBackground(Color.RED);
        collegesListDialog.setTitle("Available colleges");
        collegesListDialog.setAlwaysOnTop(true);


        // Creation of table
        collegesTableModel.setColumnIdentifiers(collegesColumns);
        collegesTable.setModel(collegesTableModel);


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
        collegesTablePane.setForeground(Color.RED);
        collegesTablePane.setBackground(Color.WHITE);
        collegesTablePane.setBounds(0, 0, 480, 375);


        // Add each college from the ArrayList colleges to the table
        for(String college: this.colleges){
            collegesTableModel.addRow(new Object[]{college});
        }

        collegesListDialog.add(collegesTablePane);

        // Show message that each button in the table has to be double-clicked
        JOptionPane.showMessageDialog(collegesListDialog, "Note: As of the current version, each college" +
                " in the table has to be double clicked.", " ", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void showProgramsInCollege(String collegePrompt){
        for(ArrayList<String> collegeAndItsPrograms: this.programsInColleges){
            if(collegePrompt.equals(collegeAndItsPrograms.getFirst())){
                this.chosenCollegeAndItsPrograms = collegeAndItsPrograms;
                break;
            }
        }

        if(this.chosenCollegeAndItsPrograms.size() == 1){
            System.out.println("Hiii---");
            JOptionPane.showMessageDialog(collegesListDialog, "No programs yet. Please input a program in " +
                    collegePrompt + " to open this window.","", JOptionPane.WARNING_MESSAGE);
        } else{
            programsListDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            programsListDialog.setSize(500, 500);
            programsListDialog.setLocationRelativeTo(null);
            programsListDialog.setVisible(true);
            programsListDialog.setResizable(false);
            programsListDialog.setBackground(Color.RED);
            programsListDialog.setTitle("Available programs in " + collegePrompt);
            programsListDialog.setAlwaysOnTop(true);

            // Set table title
            programsColumns[0] = "Programs in " + collegePrompt;

            // Creation of table
            programsTableModel.setColumnIdentifiers(programsColumns);
            programsTable.setModel(programsTableModel);


            //      Set font of header
            programsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 30));

            programsTable.setBackground(Color.LIGHT_GRAY);
            programsTable.setSelectionForeground(Color.BLACK);
            programsTable.setGridColor(Color.BLACK);
            programsTable.setFont(new Font("Arial", Font.BOLD, 20));
            programsTable.setRowHeight(30);
            programsTable.setAutoCreateRowSorter(true);
            programsTable.setDefaultEditor(Object.class, null);
            programsTable.getTableHeader().setReorderingAllowed(false);


            //      Put text in center
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            programsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

            // Set properties of the pane
            programsTablePane.setForeground(Color.RED);
            programsTablePane.setBackground(Color.WHITE);
            programsTablePane.setBounds(0, 0, 480, 375);


            // Reset the table each time
            programsTableModel.setRowCount(0);

            for(String program: this.chosenCollegeAndItsPrograms){
                if(this.chosenCollegeAndItsPrograms.indexOf(program) != 0){
                    programsTableModel.addRow(new Object[]{program});
                }
            }

            programsListDialog.add(programsTablePane);

            // Create another dialog that shows another table of available programs in a colleges (last na ni nga JDialog)
        }
    }
}

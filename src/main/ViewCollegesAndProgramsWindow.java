package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ViewCollegesAndProgramsWindow {
    // Made another ArrayList for colleges to make it more accessible as opposed to traversing a nested ArrayList
    ArrayList<String> colleges;
    ArrayList<ArrayList<ArrayList<String>>> dataFromCollegesAndProgramsCSV;
    JTable collegesTable = new JTable();
    JTable programsTable = new JTable();
    Object[] collegesColumns = {"Colleges"};
    Object[] programsColumns = {"",""};

    // Override isCellEditable of DefaultTableModel
    DefaultTableModel collegesTableModel = new DefaultTableModel();
    DefaultTableModel programsTableModel = new DefaultTableModel();

    JScrollPane collegesTablePane = new JScrollPane(collegesTable);
    JScrollPane programsTablePane = new JScrollPane(programsTable);
    JDialog collegesListDialog = new JDialog();
    JDialog programsListDialog = new JDialog();
    ButtonRenderer buttonRenderer = new ButtonRenderer();

    // Plan: Pass main.ViewCollegesAndProgramsWindow object into main.ButtonEditor so that main.ButtonEditor will have access to
    // main.ViewCollegesAndProgramsWindow's methods
    ButtonEditor buttonEditor = new ButtonEditor(new JTextField(), this);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    ArrayList<ArrayList<String>> chosenCollegeAndItsPrograms;

    JLabel chosenCollegeTitle = new JLabel("");

    ViewCollegesAndProgramsWindow(ArrayList<String> colleges, ArrayList<ArrayList<ArrayList<String>>> dataFromCollegesAndProgramsCSV){
        this.colleges = colleges;
        this.dataFromCollegesAndProgramsCSV = dataFromCollegesAndProgramsCSV;

        // Set properties of the JDialog
        collegesListDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        collegesListDialog.setSize(500, 500);
        collegesListDialog.setLocationRelativeTo(null);
        collegesListDialog.setVisible(true);
        collegesListDialog.setResizable(false);
//        collegesListDialog.setBackground(Color.RED);
        collegesListDialog.setTitle("Available colleges");
        collegesListDialog.setAlwaysOnTop(true);


        // Creation of table
        collegesTableModel.setColumnIdentifiers(collegesColumns);
        collegesTable.setModel(collegesTableModel);


        //      Set font of header
        collegesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 30));

//        collegesTable.setBackground(Color.LIGHT_GRAY);
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
//        collegesTablePane.setBackground(Color.WHITE);
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
        for(ArrayList<ArrayList<String>> aCollegeAndItsPrograms: dataFromCollegesAndProgramsCSV){
            if (collegePrompt.equals(aCollegeAndItsPrograms.getFirst().getFirst())){
                this.chosenCollegeAndItsPrograms = aCollegeAndItsPrograms;
                break;
            }
        }

        if(this.chosenCollegeAndItsPrograms.size() == 1){
            JOptionPane.showMessageDialog(collegesListDialog, "No programs yet. Please input a program in " +
                    collegePrompt + " to open this window.","", JOptionPane.WARNING_MESSAGE);
        } else{
            programsListDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            programsListDialog.setSize(500, 500);
            programsListDialog.setLocationRelativeTo(null);
            programsListDialog.setVisible(true);
            programsListDialog.setResizable(false);
//            programsListDialog.setBackground(Color.RED);
            programsListDialog.setTitle("Available programs in " + collegePrompt);
            programsListDialog.setAlwaysOnTop(true);

            // Set table title
            programsColumns[0] = "Program code";
            programsColumns[1] = "Program title";


            // Creation of table
            programsTableModel.setColumnIdentifiers(programsColumns);
            programsTable.setModel(programsTableModel);

            // Set column width
            programsTable.getColumnModel().getColumn(0).setPreferredWidth(165);
            programsTable.getColumnModel().getColumn(0).setMaxWidth(165);

            //      Set font of header
            programsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));

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
            programsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);


            // Set properties of the pane
            programsTablePane.setBounds(0, 0, 480, 375);

            // Reset the table each time
            programsTableModel.setRowCount(0);

            for (ArrayList<String> eachElementInACurrentCollegeAndItsPrograms: this.chosenCollegeAndItsPrograms){
                if(this.chosenCollegeAndItsPrograms.indexOf(eachElementInACurrentCollegeAndItsPrograms) != 0){
                    // Adds the first and second elements in ArrayList
                    programsTableModel.addRow(new Object[]{eachElementInACurrentCollegeAndItsPrograms.getFirst(), eachElementInACurrentCollegeAndItsPrograms.get(1)});
                }
            }

            programsListDialog.add(programsTablePane);

            // Create another dialog that shows another table of available programs in a colleges (last na ni nga JDialog)
        }
    }
}

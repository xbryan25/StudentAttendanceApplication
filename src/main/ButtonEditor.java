package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.lang.String;


// To make button in cells of a JTable (2/2)
// Very confusing, I will study its implementation
// Link for vid: https://www.youtube.com/watch?v=3LiSHPqbuic
public class ButtonEditor extends DefaultCellEditor implements ActionListener {
    protected JButton btn;
    private String label;
    private boolean clicked;
    ViewCollegesAndProgramsWindow viewColleges;
    ButtonEditor(JTextField txt, ViewCollegesAndProgramsWindow viewColleges){
        super(txt);

        this.viewColleges = viewColleges;

        btn = new JButton();
        btn.setOpaque(true);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btn){
            fireEditingStopped();
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col){

        label = (obj==null) ? "":obj.toString();
        btn.setText(label);
        clicked = true;

        return btn;
    }

    @Override
    public Object getCellEditorValue(){
        if (clicked) {
            viewColleges.showProgramsInCollege(label);
        }

        clicked = false;

        return label;
    }

    @Override
    public boolean stopCellEditing(){
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped(){
        super.fireEditingStopped();
    }
}

package main;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


// To make button in cells of a JTable (1/2)
// Very confusing, I will study its implementation
// Link for vid: https://www.youtube.com/watch?v=3LiSHPqbuic
public class ButtonRenderer extends JButton implements TableCellRenderer{

    ButtonRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {

        setText((obj == null) ? "":obj.toString());

        return this;
    }
}

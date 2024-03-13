import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;

// This class gets called by TableHolder

public class AttendanceTable implements ActionListener{
    JTable mainTable = new JTable();
    Object[] columns = {"ID Number", "First Name", "Last Name", "Program", "College"};
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane pane = new JScrollPane(mainTable);
    AttendanceTable(){
        model.setColumnIdentifiers(columns);
        mainTable.setModel(model);

        mainTable.setBackground(Color.LIGHT_GRAY);
        mainTable.setSelectionForeground(Color.BLACK);
        mainTable.setGridColor(Color.BLACK);
        mainTable.setFont(new Font("Arial", Font.PLAIN, 12));
        mainTable.setRowHeight(15);
        mainTable.setAutoCreateRowSorter(true);

        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(10, 75, 480, 375);

        model.addRow(new Object[]{"2023-0022", "Bryan Miguel", "Agan", "BSCS", "CCS"});
        model.addRow(new Object[]{"2023-0044", "Bryle Jared", "Fantilanan", "BSCS", "CCS"});
        model.addRow(new Object[]{"2023-0079", "Keane Pharelle", "Ledesma", "BSCS", "CCS"});
        model.addRow(new Object[]{"2023-0025", "Takatsuki", "Sen", "BSANBIO", "CSM"});
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }
}

package DefaultRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CusTomTableRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JPanel cellBody = new JPanel(new GridLayout(1, 1));
        table.setRowHeight(30);

        JLabel tabledata = new JLabel(value.toString());
        tabledata.setForeground(Color.WHITE);

        if (isSelected) {
            cellBody.setBackground(new Color(20, 40, 80));
            tabledata.setForeground(Color.WHITE);
        }else{
            if ((row % 2) == 0) {
                cellBody.setBackground(new Color(200, 200, 200));
                tabledata.setForeground(Color.BLACK);
 
            }else{
                cellBody.setBackground(Color.WHITE);
                tabledata.setForeground(Color.BLACK);
        }}

        cellBody.add(tabledata);
        return cellBody;
    }

}

package DefaultRenderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class DefaultListRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object>{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        BookRecordObject book = (BookRecordObject) value;
        
        setText(book.getName());
        setIcon(book.getIcon());
        
        setIconTextGap(10);
        
        if (isSelected) {
            
            setBackground(new Color(20, 40, 80));
            setForeground(Color.WHITE);
            
        }else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        
        }
        return this;
    }
    
}
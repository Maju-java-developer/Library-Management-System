package DefaultRenderer;

import javax.swing.Icon;

public class BookRecordObject {

    String name;
    Icon icon;

    public BookRecordObject(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}

package SettingPanel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingPanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public SettingPanel(){}
    public SettingPanel(int posX, int posY, int width, int height){
        
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        
    }
    public void drawPanel(){
        intiComponends();
    }
    
    public void intiComponends(){
    
        setBounds(posX, posY, width, height);
        setLayout(null);
    
        themeLabel.setBounds(posX + 10, 20, 100, 35);
        themesBox.setBounds(themeLabel.getX() + 50 , 20, 200, 35);
        
        add(themeLabel);
        add(themesBox);
    }
    
    String[] themesStr = {"-- Choose Theme --","Theme One","Theme Two","Theme Three","Theme Four","Theme Five"};
    JLabel themeLabel = new JLabel("Theme");
    JComboBox<String> themesBox = new JComboBox<String>(themesStr);
    
}
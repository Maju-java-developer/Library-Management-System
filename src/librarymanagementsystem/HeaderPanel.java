package librarymanagementsystem;

import javax.swing.JPanel;

public class HeaderPanel extends JPanel{

    int posX;
    int posY;
    int widht;
    int height;
    
    public HeaderPanel(){}
    public HeaderPanel(int posX, int posY, int widht, int height){
        
        this.posX = posX;
        this.posY = posY;
        this.widht = widht;
        this.height = height;
    }
    public void drawPanel(){
 
        intiComponend();
    }
    public void intiComponend(){
        
        setBounds(posX, posY, widht, height);
        setLayout(null);
        
    }

}
package AboutPanel;

import javax.swing.JPanel;

public class AboutPanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public AboutPanel(){}
    public AboutPanel(int posX, int posY, int width, int height){
        
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
    }
}

package DashBaordPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashBoardDataBar extends JPanel{
    
    String title = "";
    String data = "";
    
    int posX;
    int posY;
    int width;
    int height;
    
    public DashBoardDataBar(){}
    public DashBoardDataBar(int posX, int posY, int width, int height){
    
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        
    }
    
    public void drawPanel(){
        IntiComponends();
    }
    public void IntiComponends(){

        setBounds(posX, posY, width, height);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        titlePanel.setPreferredSize(new Dimension(330, 50));
        titlePanel.setBackground(Appearances.Appearances.DashBarodDataBarColor);
        
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titleLbl.setFont(Appearances.Appearances.generalFont);
        titleLbl.setForeground(Color.WHITE);
        
        dataPanel.setPreferredSize(new Dimension(330, 140));
        dataPanel.setBackground(new Color(240, 240, 240));
        
        dataLbl.setFont(new Font("Sogue UI Light", 0, 35));
        dataLbl.setHorizontalAlignment(SwingConstants.CENTER);
        
        titleLbl.setText(title);
        dataLbl.setText(data);
    
        add(titlePanel);
        titlePanel.add(titleLbl);

        add(dataPanel);
        dataPanel.add(dataLbl);
    }

    public JPanel titlePanel = new JPanel(new GridLayout(1, 1));
    public JLabel titleLbl = new JLabel();
    
    public JPanel dataPanel = new JPanel(new GridLayout(1, 1));
    public JLabel dataLbl = new JLabel();

    public void SetTitle(String title){
        this.title = title;
    }
    
    public void SetData(String data){
        this.data = data;
    }

}
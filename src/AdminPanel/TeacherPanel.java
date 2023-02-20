package AdminPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeacherPanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public TeacherPanel(){}
    public TeacherPanel(int posX, int posY, int width, int height){
        
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
    
        teacherLbl.setBounds(20, 15, 150, 40);
        teacherLbl.setFont(new Font("Sogue UI Light", 0, 20));
        
        addTeacherBtn.setBounds(100, 20, 200, 30);
        addTeacherBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                addTeacherDialog.setTitle("Add Student:");
                addTeacherDialog.setSize(720, 600);
                addTeacherDialog.setLayout(null);
                addTeacherDialog.setLocationRelativeTo(null);
                addTeacherDialog.setVisible(true);
                
            }
        });
        
        add(teacherLbl);
        add(addTeacherBtn);
        
    }
    private JLabel teacherLbl = new JLabel("Teacher:");
    
    private JButton addTeacherBtn = new JButton("Add Teacher:");
    private JDialog addTeacherDialog = new JDialog();
    
}

package AdminPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentPanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public StudentPanel(){}
    public StudentPanel(int posX, int posY, int width, int height){
        
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
    
        studentLbl.setBounds(20, 15, 150, 40);
        studentLbl.setFont(new Font("Sogue UI Light", 0, 20));
        
        addStudentBtn.setBounds(100, 20, 200, 30);
        addStudentBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                addStudentDialog.setTitle("Add Student:");
                addStudentDialog.setSize(720, 600);
                addStudentDialog.setLayout(null);
                addStudentDialog.setLocationRelativeTo(null);
                addStudentDialog.setVisible(true);
                
            }
        });
        
        add(studentLbl);
        add(addStudentBtn);
        
    }
    
    private JLabel studentLbl = new JLabel("Student:");
    
    private JButton addStudentBtn = new JButton("Add Student");
    private JDialog addStudentDialog = new JDialog();
    
}

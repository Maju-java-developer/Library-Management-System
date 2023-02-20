package LoginForm;

import Core.Defaults;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForm extends JFrame{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public LoginForm(int posX, int posY, int widht, int height){
        
        this.posX = posX;
        this.posY = posY;
        this.width = widht;
        this.height = height;
    }
    
    public LoginForm(){
        intiComponends();
    }
    
    public void intiComponends(){
        setTitle("Login From:");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        backGroundBooksIcon.setIcon(Defaults.rescaleImage(getWidth() + 100, getHeight(), "E:/library logos/1.png"));
        backGroundBooksIcon.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                loginFormDialog.setTitle("Login Form:");
                loginFormDialog.setSize(500, 500);
                loginFormDialog.setLayout(new GridLayout(1, 1));
                loginFormDialog.setLocationRelativeTo(null);
                loginFormDialog.setVisible(true);

                LoginmainPanel.setBackground(Color.WHITE);
                
                titleLbl[0] = new JLabel("SALU LIBRARY");
                titleLbl[1] = new JLabel("MANAGEMENT SYSTEM");
                titleLbl[2] = new JLabel();
                
                titleLbl[0].setBounds(180, 50, 180, 40);
                titleLbl[0].setFont(new Font("Sogue UI Light", 0, 20));
                
                titleLbl[1].setBounds(180, 90, 230, 40);
                titleLbl[1].setFont(new Font("Sogue UI Light", 0, 20));

                //Use This Label For Set Library Books Icon
                titleLbl[2].setBounds(110, 50, 180, 40);
//                titleLbl[2].setIcon(Defaults.rescaleImage(90, 120, "E:/library logos/totolbooks.png"));

                adminNameLbl.setBounds(50, 150, 120, 40);
                adminNameTxt.setBounds(170, 150, 250, 35);
                
                adminPasswordLbl.setBounds(50, 200, 120, 40);
                adminPasswordTxt.setBounds(170, 200, 250, 35);
                
                loginBtn.setBounds(170, 240, 100, 30);
                                                                                                                    
                LoginmainPanel.add(titleLbl[0]);
                LoginmainPanel.add(titleLbl[1]);
                LoginmainPanel.add(titleLbl[2]);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        add(backGroundBooksIcon);

        loginFormDialog.add(LoginmainPanel);
        
        LoginmainPanel.add(adminNameLbl);
        LoginmainPanel.add(adminPasswordLbl);
        
        LoginmainPanel.add(adminNameTxt);
        LoginmainPanel.add(adminPasswordTxt);
        
        LoginmainPanel.add(loginBtn);
        
    }
    
    private JLabel backGroundBooksIcon = new JLabel("");
    
    private JDialog loginFormDialog = new JDialog();
    private JPanel LoginmainPanel = new JPanel(null);
    
    private JLabel titleLbl[] = new JLabel[3];
    
    private JLabel adminNameLbl = new JLabel("Name:");
    private JTextField adminNameTxt = new JTextField();
    
    private JLabel adminPasswordLbl = new JLabel("Password:");
    private JTextField adminPasswordTxt = new JTextField();
    
    private JButton loginBtn = new JButton("Login:");
    
    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}
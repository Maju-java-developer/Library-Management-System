package DashBaordPanel;

import AdminPanel.AddBookPanel;
import Connectivity.MySQLConnection;
import Connectivity.NewMySQLConnection;
import Core.Defaults;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import librarymanagementsystem.AppMain;

public class HomePanel extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public HomePanel(){}
    public HomePanel(int posX, int posY, int width, int height){
        
        this.posX= posX;
        this.posY= posY;
        this.width= width;
        this.height= height;
        
    }
    
    public void drawPanel(){
        intiComponend();
    }
    
    private void intiComponend(){
     
        setBounds(posX, posY, width, height);
        setLayout(null);
    
        StylePati.setBounds(20, 150, getWidth() - 40, 1);
        StylePati.setBackground(new Color(240, 240, 240 ));
        
        libraryLogo.setBounds(20, 20, 120, 120);
        libraryLogo.setIcon(Defaults.rescaleImage(120, 120, "E:\\library logos\\librarybook.png"));

        titleLabel[0] = new JLabel("Salu Library Managemnet System");
        titleLabel[0].setBounds(150, 50, 600, 70);
        titleLabel[0].setFont(new Font("Souge UI Light", 0, 30));
        
        titleLabel[1] = new JLabel("SLMS");
        titleLabel[1].setBounds(50, 100, 200, 50);
        titleLabel[1].setFont(new Font("Souge UI Light", 0, 20));
        titleLabel[1].setForeground(Color.RED);
        
        totalbooksDatabarPanel = new DashBoardDataBar(20, 170, 330, 200);
        totalbooksDatabarPanel.setBackground(Color.WHITE);
        totalbooksDatabarPanel.SetTitle("Total Books");
        totalbooksDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(25, 25, "E:\\library logos\\totalBooks.png"));
        totalbooksDatabarPanel.SetData(""+Defaults.getCountValue("books"));
        totalbooksDatabarPanel.drawPanel();

//        Defaults.totalbooksDataBar = totalbooksDatabarPanel; 
        
        totalMemberDatabarPanel = new DashBoardDataBar(360, 170, 330, 200);
        totalMemberDatabarPanel.setBackground(Color.WHITE);
        totalMemberDatabarPanel.SetTitle("Total Members");
        totalMemberDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(25, 25, "E:\\library logos\\account.png"));
        totalMemberDatabarPanel.SetData(""+Defaults.getCountValue("users"));
        totalMemberDatabarPanel.drawPanel();
                                                                                
        totalIssueBookDatabarPanel = new DashBoardDataBar(700, 170, 330, 200);          
        totalIssueBookDatabarPanel.setBackground(Color.WHITE);
        totalIssueBookDatabarPanel.SetTitle("Issued Books:");
        totalIssueBookDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(25, 25, "E:\\library logos\\totalBooks.png"));
        totalIssueBookDatabarPanel.SetData(""+Defaults.GetBookrentalCount("Issued"));
        totalIssueBookDatabarPanel.drawPanel();
        
        totalReturnedBookDatabarPanel = new DashBoardDataBar(20, 380, 330, 200);
        totalReturnedBookDatabarPanel.setBackground(Color.WHITE);
        totalReturnedBookDatabarPanel.SetTitle("Returned Books");
        totalReturnedBookDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(25, 25, "E:\\library logos\\returedBook.png"));
        totalReturnedBookDatabarPanel.SetData(""+Defaults.GetBookrentalCount("Returned"));
        totalReturnedBookDatabarPanel.drawPanel();

        totalLostBooksDatabarPanel = new DashBoardDataBar(360, 380, 330, 200);
        totalLostBooksDatabarPanel.setBackground(Color.WHITE);
        totalLostBooksDatabarPanel.SetTitle("Lost Books");
        totalLostBooksDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(30, 30, "E:\\library logos\\lost.png"));
        totalLostBooksDatabarPanel.SetData(""+Defaults.GetBookrentalCount("Lost"));
        totalLostBooksDatabarPanel.drawPanel();

        totalSoldBooksDatabarPanel = new DashBoardDataBar(700, 380, 330, 200);
        totalSoldBooksDatabarPanel.setBackground(Color.WHITE);
        totalSoldBooksDatabarPanel.SetTitle("Sold Books");
        totalSoldBooksDatabarPanel.titleLbl.setIcon(Defaults.rescaleImage(25, 25, "E:\\library logos\\totalBooks.png"));
        totalSoldBooksDatabarPanel.SetData(""+Defaults.GetBookrentalCount("Sold"));
        totalSoldBooksDatabarPanel.drawPanel();
        
        add(totalbooksDatabarPanel);
        add(totalMemberDatabarPanel);
        add(totalIssueBookDatabarPanel);
        add(totalReturnedBookDatabarPanel);
        add(totalLostBooksDatabarPanel);
        add(totalSoldBooksDatabarPanel);

        add(StylePati);
        add(libraryLogo);
        add(titleLabel[0]);
        add(titleLabel[1]);

    }

    private JPanel StylePati = new JPanel(null);

    private JLabel libraryLogo = new JLabel("");
    private JLabel titleLabel[] = new JLabel[2];

    public static DashBoardDataBar totalbooksDatabarPanel = null;
    public static DashBoardDataBar totalMemberDatabarPanel = null;
    public static DashBoardDataBar totalIssueBookDatabarPanel = null;
    public static DashBoardDataBar totalReturnedBookDatabarPanel = null;
    public static DashBoardDataBar totalLostBooksDatabarPanel = null;
    public static DashBoardDataBar totalSoldBooksDatabarPanel = null;

}
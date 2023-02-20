package librarymanagementsystem;

import Core.Defaults;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Appearances.*;

public class SideBar extends JPanel{
    
    int posX;
    int posY;
    int width;
    int height;
    
    public SideBar(){}
    public SideBar(int posX, int posY, int width, int height){
    
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        
    }
    public void drawPanel(){
        IntiComponend();
        booksSubPanel.setVisible(false);
        adminSubPanel.setVisible(false);
    }
    
    public void IntiComponend(){
        
        setBounds(posX, posY, width, height);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));
        
        AdminLogo.setIcon(Defaults.rescaleImage(70, 70, Defaults.iconPath("student", "png")));
        AdminLogo.setPreferredSize(new Dimension(230, 70));
        
        AdminLogo.setForeground(Color.WHITE);

        mainnavagiationPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight + 5));
        mainnavagiationPanel.setBackground(Appearances.mainNavigationPanelColor);
        
        mainnavigationLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        mainnavigationLbl.setForeground(Appearances.mainNavigationLblColor);

        homePanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight));
        homePanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
        
        homeLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("Home", "png")));
        homeLbl.setForeground(Appearances.sideBarMenuBtnLabelColor);
        homeLbl.setFont(Appearances.sideBarMenuBtnFontColor);
        homeLbl.setIconTextGap(Appearances.IconTextGap);

        //AdminPanel And adminSubPanel Ja Bounds Tho set Kaya
        AdminPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight));
        AdminPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

        adminPanelLbl.setForeground(Appearances.sideBarMenuBtnLabelColor);
        adminPanelLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("AdminPanel", "png")));
        adminPanelLbl.setFont(Appearances.sideBarMenuBtnFontColor);
        adminPanelLbl.setIconTextGap(Appearances.IconTextGap);
        
        adminSubPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, 90));
        adminSubPanel.setBackground(Appearances.sideBarSubBtnPanelColor);

        usersLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("user", "png")));
        usersLbl.setPreferredSize(new Dimension(Appearances.sideBarSubBtnWidth, Appearances.sideBarSubBtnheight));
        usersLbl.setForeground(Appearances.sideBarSubBtnLblColor);
        usersLbl.setIconTextGap(Appearances.IconTextGap);
        
        bookstockLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("addbook", "png")));
        bookstockLbl.setPreferredSize(new Dimension(Appearances.sideBarSubBtnWidth, Appearances.sideBarSubBtnheight));
        bookstockLbl.setForeground(Appearances.sideBarSubBtnLblColor);

        bookrentalLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("bookrental", "png")));
        bookrentalLbl.setPreferredSize(new Dimension(Appearances.sideBarSubBtnWidth, Appearances.sideBarSubBtnheight));
        bookrentalLbl.setForeground(Appearances.sideBarSubBtnLblColor);
        bookrentalLbl.setIconTextGap(Appearances.IconTextGap);
        //END AdminPanel And adminSubPanel Ja Bounds 

        //booksPanel And booksSubPanel Ja Bounds Tho Set Kaya
        booksPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth,Appearances.sideBarBtnMenuheight));
        booksPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

        booksLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("books", "png")));
        booksLbl.setForeground(Appearances.sideBarMenuBtnLabelColor);
        booksLbl.setFont(Appearances.sideBarMenuBtnFontColor);
        booksLbl.setIconTextGap(Appearances.IconTextGap);
        
        booksSubPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, 60));
        booksSubPanel.setBackground(Appearances.sideBarSubBtnPanelColor);

        authorsLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("user", "png")));
        authorsLbl.setPreferredSize(new Dimension(Appearances.sideBarSubBtnWidth, Appearances.sideBarSubBtnheight));
        authorsLbl.setForeground(Appearances.sideBarSubBtnLblColor);
        authorsLbl.setIconTextGap(Appearances.IconTextGap);

        categoriesLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("category", "png")));
        categoriesLbl.setPreferredSize(new Dimension(Appearances.sideBarSubBtnWidth, Appearances.sideBarSubBtnheight));
        categoriesLbl.setForeground(Appearances.sideBarSubBtnLblColor);

        reportsPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight));
        reportsPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

        reportsLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("report", "png")));
        reportsLbl.setForeground(Appearances.sideBarMenuBtnLabelColor);
        reportsLbl.setFont(Appearances.generalFont);
        reportsLbl.setIconTextGap(Appearances.IconTextGap);
        
        settingPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight));
        settingPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

        settingLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("sitting", "png")));
        settingLbl .setForeground(Appearances.sideBarMenuBtnLabelColor);
        settingLbl.setFont(Appearances.sideBarMenuBtnFontColor);
        settingLbl.setIconTextGap(Appearances.IconTextGap);
        
        aboutPanel.setPreferredSize(new Dimension(Appearances.sideBarBtnMenuWidth, Appearances.sideBarBtnMenuheight));
        aboutPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
        
        aboutLbl.setIcon(Defaults.rescaleImage(Appearances.sideBariconX, Appearances.sideBariconY, Defaults.iconPath("about", "png")));
        aboutLbl.setForeground(Appearances.sideBarMenuBtnLabelColor);
        aboutLbl.setFont(Appearances.sideBarMenuBtnFontColor);
        aboutLbl.setIconTextGap(Appearances.IconTextGap);

        add(AdminLogo);
        add(mainnavagiationPanel);
        add(homePanel);
        add(AdminPanel);
        add(adminSubPanel);
        add(booksPanel);
        add(booksSubPanel);
        add(reportsPanel);
        add(settingPanel);
        add(aboutPanel);
        
        adminSubPanel.add(usersLbl);
        adminSubPanel.add(bookstockLbl);
        adminSubPanel.add(bookrentalLbl);
        
        booksSubPanel.add(authorsLbl);
        booksSubPanel.add(categoriesLbl);

        mainnavagiationPanel.add(mainnavigationLbl);
        homePanel.add(homeLbl);
        booksPanel.add(booksLbl);
        AdminPanel.add(adminPanelLbl);
        reportsPanel.add(reportsLbl);
        settingPanel.add(settingLbl);
        aboutPanel.add(aboutLbl);
        
    }

    private JLabel AdminLogo = new JLabel("Admin");

    public JPanel mainnavagiationPanel = new JPanel(new GridLayout(1, 1));
    public JLabel mainnavigationLbl = new JLabel("MAIN NAVIGATION");
    
    public JPanel homePanel = new JPanel(new GridLayout(1, 1));
    public JLabel homeLbl = new JLabel("Home");
 
    public JPanel AdminPanel = new JPanel(new GridLayout(1, 1));
    public JLabel adminPanelLbl = new JLabel("Admin Panel");
    
    public JPanel adminSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));

    public JLabel usersLbl = new JLabel("Users");
    public JLabel bookstockLbl = new JLabel("Books");
    public JLabel bookrentalLbl = new JLabel("BookRental");
    
    public JPanel booksPanel = new JPanel(new GridLayout(1, 1));
    public JLabel booksLbl = new JLabel("Books");

    public JPanel booksSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));

    public JLabel authorsLbl = new JLabel("Authors");
    public JLabel categoriesLbl = new JLabel("Categories");
 
    public JPanel settingPanel = new JPanel(new GridLayout(1, 1));
    public JLabel settingLbl = new JLabel("Setting");
    
    public JPanel reportsPanel = new JPanel(new GridLayout(1, 1));
    public JLabel reportsLbl = new JLabel("Report");

    public JPanel aboutPanel = new JPanel(new GridLayout(1, 1));
    public JLabel aboutLbl = new JLabel("About");
}
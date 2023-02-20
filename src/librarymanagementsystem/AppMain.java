package librarymanagementsystem;

import AdminPanel.AddBookPanel;
import AdminPanel.BookRentalPanel;
import AdminPanel.StudentPanel;
import AdminPanel.TeacherPanel;
import AdminPanel.UsersPanel;
import BookSubPanels.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Connectivity.MySQLConnection;
import Core.Defaults;
import DashBaordPanel.DashBoardDataBar;
import DashBaordPanel.HomePanel;
import ReportPanel.ReportPanel;
import Appearances.Appearances;
import SettingPanel.SettingPanel;
import AboutPanel.AboutPanel;
import Constants.EnglishConstants;

public class AppMain extends JFrame{
    public AppMain(){

        IntiComponends();
    }
    public void IntiComponends(){
        
        setTitle(EnglishConstants.titleStr);
        setSize(EnglishConstants.screenSize.width, EnglishConstants.screenSize.height);
        setLayout(EnglishConstants.gridLayout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        sidebarPanel = new SideBar(0, 0, 250, getHeight());
        sidebarPanel.setBackground(Appearances.sideBarPanelColor);
        sidebarPanel.drawPanel();

        headerPanel = new HeaderPanel(sidebarPanel.getX(), 0, getWidth(), 50);
        headerPanel.setBackground(Appearances.headerPanelColor);
        headerPanel.drawPanel();
        
        bodyPanel = new BodyPanel(250, 50, 1080, getWidth() - 50);
        bodyPanel.setBackground(new Color(233, 234, 231));
        bodyPanel.drawPanel();
        
        //******************************************
        //******** Working On HomePanel *********
        //******************************************

        homeComponendsPanel = new HomePanel(10, 10, 1060, 600);
        homeComponendsPanel.setBackground(Color.WHITE);
        homeComponendsPanel.drawPanel();

        sidebarPanel.homePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(homeComponendsPanel);
                
                repaint();
                revalidate();
                
                if (sidebarPanel.booksSubPanel.isVisible()) {
                    sidebarPanel.booksSubPanel.setVisible(false);
                    
                }else if (sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.homePanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.homePanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
            }
        });

        //************************************************
        //***** Working On AdminPanel and SubPanel ****
        //************************************************

        sidebarPanel.AdminPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                if (!sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(true);
                }else {
                    sidebarPanel.adminSubPanel.setVisible(false);
                }
                
                if (sidebarPanel.booksSubPanel.isVisible()) {
                    sidebarPanel.booksSubPanel.setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.AdminPanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.AdminPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
            }
        });

        usersComponendsPanel = new UsersPanel(20, 20, 1030, 580);
        usersComponendsPanel.setBackground(Color.WHITE);
        usersComponendsPanel.drawPanel();

        sidebarPanel.usersLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(usersComponendsPanel);

                repaint();
                invalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.usersLbl.setForeground(Appearances.sideBarSubBtnPanelHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.usersLbl.setForeground(Appearances.sideBarSubBtnLblColor);
            }
        });

        addBooksComponendsPanel = new AddBookPanel(20, 20, 1030, 580);
        addBooksComponendsPanel.setBackground(Color.WHITE);
        addBooksComponendsPanel.drawPanel();
        
        sidebarPanel.bookstockLbl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(addBooksComponendsPanel);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.bookstockLbl.setForeground(Appearances.sideBarSubBtnPanelHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.bookstockLbl.setForeground(Appearances.sideBarSubBtnLblColor);
            }
        });

        bookRentalComponendsPanel = new BookRentalPanel(20, 20, 1030, 580);
        bookRentalComponendsPanel.setBackground(Color.WHITE);
        bookRentalComponendsPanel.drawPanel();

        sidebarPanel.bookrentalLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(bookRentalComponendsPanel);
                
                repaint();
                revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.bookrentalLbl.setForeground(Appearances.sideBarSubBtnPanelHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.bookrentalLbl.setForeground(Appearances.sideBarSubBtnLblColor);
            }
        });
        
        //*******************************************************
        //************ Working On booksSubPanel ***************
        //*******************************************************
        
        sidebarPanel.booksPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
           
                if (!sidebarPanel.booksSubPanel.isVisible()) {
                    sidebarPanel.booksSubPanel.setVisible(true);
                } else {
                    sidebarPanel.booksSubPanel.setVisible(false);
                }
                
                if (sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(false);
                }
                
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                sidebarPanel.booksPanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
          
            }

            @Override
            public void mouseExited(MouseEvent e) {

                sidebarPanel.booksPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

            }
        });

        authorsComponedsPanel = new AuthorPanel(20, 20, 1040, 590);
        authorsComponedsPanel.setBackground(Color.WHITE);
        authorsComponedsPanel.drawPanel();
        
        sidebarPanel.authorsLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
   
                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(authorsComponedsPanel);

                repaint();
                revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.authorsLbl.setForeground(Appearances.sideBarSubBtnPanelHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.authorsLbl.setForeground(Appearances.sideBarSubBtnLblColor);
            }
        });
        
        categoriesComponendsPanel = new CategoriesPanel(20, 20, bodyPanel.getWidth() -40, 580);
        categoriesComponendsPanel.setBackground(Color.WHITE);
        categoriesComponendsPanel.drawPanel();
                            
        sidebarPanel.categoriesLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(categoriesComponendsPanel);
                
                repaint();
                revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.categoriesLbl.setForeground(Appearances.sideBarSubBtnPanelHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.categoriesLbl.setForeground(Appearances.sideBarSubBtnLblColor);
            }
        });
        
        //working On RentalBookReport
        reportComponendsPanel = new ReportPanel(20, 20, bodyPanel.getWidth() - 40, 580);
        reportComponendsPanel.setBackground(Color.WHITE);
        reportComponendsPanel.drawPanel();
        
        sidebarPanel.reportsPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(reportComponendsPanel);

                if (sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(false);
                    
                } else if (sidebarPanel.booksSubPanel.isVisible()){
                    sidebarPanel.booksSubPanel.setVisible(false);
                }
                
                repaint();
                revalidate();
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.reportsPanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            
                sidebarPanel.reportsPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);

            }
        });
        //End work:

        settingPanel = new SettingPanel(20, 20, bodyPanel.getWidth() - 40, 580);
        settingPanel.setBackground(Color.WHITE);
        settingPanel.drawPanel();

        sidebarPanel.settingLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(aboutPanel);
                bodyPanel.add(settingPanel);
 
                if (sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(false);
                    
                } else if (sidebarPanel.booksSubPanel.isVisible()){
                    sidebarPanel.booksSubPanel.setVisible(false);
                }
               
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.settingPanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.settingPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
            }
        });
        
        aboutPanel = new AboutPanel(20, 20, bodyPanel.getWidth() - 40, 580);
        aboutPanel.setBackground(Color.WHITE);
        aboutPanel.drawPanel();

        sidebarPanel.aboutPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                bodyPanel.remove(homeComponendsPanel);
                bodyPanel.remove(addBooksComponendsPanel);
                bodyPanel.remove(authorsComponedsPanel);
                bodyPanel.remove(usersComponendsPanel);
                bodyPanel.remove(bookRentalComponendsPanel);
                bodyPanel.remove(categoriesComponendsPanel);
                bodyPanel.remove(reportComponendsPanel);
                bodyPanel.remove(settingPanel);
                bodyPanel.add(aboutPanel);
                
                if (sidebarPanel.adminSubPanel.isVisible()) {
                    sidebarPanel.adminSubPanel.setVisible(false);
                    
                } else if (sidebarPanel.booksSubPanel.isVisible()){
                    sidebarPanel.booksSubPanel.setVisible(false);
                }

                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sidebarPanel.aboutPanel.setBackground(Appearances.sideBarMenuBtnHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sidebarPanel.aboutPanel.setBackground(Appearances.sideBarMenuBtnPanelColor);
            }
        });
        
        add(mainPanel);
        mainPanel.add(sidebarPanel);
        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        bodyPanel.add(homeComponendsPanel);
       
    }

    HeaderPanel headerPanel = null;
    JPanel mainPanel = new JPanel(null);

    HomePanel homeComponendsPanel = null;
    BodyPanel bodyPanel = null;
    SideBar sidebarPanel = null;
    
    // Recieved All Classes From BookSubPanel 
    
    private CategoriesPanel categoriesComponendsPanel = null;
    private AuthorPanel authorsComponedsPanel = null;
    // Recieved All Classes From BookSubPanel 
    
    //adminSubPanel
    private UsersPanel usersComponendsPanel = null;
    private AddBookPanel addBooksComponendsPanel = null;
    private BookRentalPanel bookRentalComponendsPanel = null;
    //adminSubPanel
    
    private ReportPanel reportComponendsPanel= null;
    
    private SettingPanel settingPanel = null;
    private AboutPanel aboutPanel = null;
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new AppMain().setVisible(true);
    }
}
package AdminPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import librarymanagementsystem.AppMain;
import Connectivity.*;
import Core.Defaults;
import DashBaordPanel.HomePanel;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

public class UsersPanel extends JPanel{

    File fileUpload = null;
    File updateFile = null;
    
    int PosX;
    int posY;
    int width;
    int height;
    public UsersPanel(){}
    public UsersPanel(int posX, int posY, int width, int height){
        this.PosX=posX;
        this.posY=posY;
        this.width=width;
        this.height=height;
    }
    public void drawPanel(){
        IntiComponend();
    }
    
    public  void IntiComponend(){
        
        setBounds(PosX, posY, width, height);
        setLayout(null);
        
        usertitleLbl.setBounds(10, 10, 200, 40);
        usertitleLbl.setFont(new Font("Sogoe UI Light", 0, 20));

        displayTotalUsers.setBounds(30, 110, 200, 40);
        displayTotalUsers.setFont(new Font("Sogue UI Light", 0, 20));
        displayTotalUsers.setText("Total Users: " +Defaults.getCountValue("users"));
        
        //----------------------------------------------
        //-------- Working On Searching Table ------
        //----------------------------------------------

        searchLbl.setBounds(650, 80, 200, 30);
        searchTxt.setBounds(700, 80, 250, 30);
        searchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
           
                String searchDataStr = searchTxt.getText();
                usersTable.setModel(SearchUserModel(searchDataStr, usersTable));
        
            }
        });
        //END OF SEARCHING TABLE

        //----------------------------------------------------------
        //------------ Working On GetDataFromTable   ---------
        //----------------------------------------------------------

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"UID","UserName","Address","Contact","Email","Role","JionDate","LeftDate"}
        );
        
        usersTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        userScrollPane.setBounds(20, 150, getWidth() - 60, 400);
        userScrollPane.setViewportView(usersTable);

        usersTable.setModel(model);
        usersTable.setModel(GetUsersModel(usersTable));
        usersTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int RowID = usersTable.getSelectedRow();
     
                TableDataDialog.setTitle(""+usersTable.getValueAt(RowID, 1));
                TableDataDialog.setSize(800, 650);
                TableDataDialog.setLayout(null);
                TableDataDialog.setLocationRelativeTo(null);
                TableDataDialog.setVisible(true);
                
                mainPanelTabelData.setBounds(5, 5, TableDataDialog.getWidth(), getHeight()- 15);
                mainPanelTabelData.setBackground(Color.WHITE);
               
                JPanel BtnPanel = new JPanel(new GridLayout(1, 4));
                JButton printBtn = new JButton("Print");
                printBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Defaults.printRecord(mainPanelTabelData); 
                    }
                });
                
                JButton deleteBtn = new JButton("Delete");
                JButton editBtn = new JButton("Edit");
                JButton cancelBtn = new JButton("Cancel");
                
                displaytitlePanel.setBounds(600, 70, 150, 30);
                displaytitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
                
                displayIDpanel.setBounds(600, 110, 150, 30);
                displayIDpanel.setBackground(new Color(230, 230, 230));
                
                displayIDlbl.setHorizontalAlignment(SwingConstants.CENTER);
                displayIDlbl.setText(""+ usersTable.getValueAt(RowID, 0));
                
                displayUserImagePanel.setBounds(30, 20, 160, 160);
                displayuserImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
                
                GetImage(Integer.parseInt(""+usersTable.getValueAt(RowID, 0)));
                
                for (int i = 0; i < captionPanel.length; i++) {
                    
                    captionPanel[i] = new JPanel(new GridLayout(1, 3));
                    captionPanel[i].setBounds(0, 200 + 100 *i, mainPanelTabelData.getWidth(), 30);
                    captionPanel[i].setBackground(new Color(230, 230, 230));
                    mainPanelTabelData.add(captionPanel[i]);
                    
                }
                for (int i = 0; i < titleLbl.length; i++) {
                    
                    titleLbl[i] = new JLabel();
                    titleLbl[i].setHorizontalAlignment((SwingConstants.CENTER));
                    titleLbl[i].setText(titleArray[i]);
                }

                displayNameLbl.setBounds(100, 250, 200, 30);
                displayNameLbl.setText(""+usersTable.getValueAt(RowID, 1));

                displayAddressLbl.setBounds(360, 250, 200, 30);
                displayAddressLbl.setText(""+usersTable.getValueAt(RowID, 2));
                
                displaycontactLbl.setBounds(620, 250, 200, 30);
                displaycontactLbl.setText(""+usersTable.getValueAt(RowID, 3));
                
                displayEmailLbl.setBounds(100, 350, 300, 30);
                displayEmailLbl.setText(""+usersTable.getValueAt(RowID, 4));
                
                displayRoleLbl.setBounds(370, 350, 200, 30);
                displayRoleLbl.setText(""+usersTable.getValueAt(RowID, 5));
                
                displayJionDateLbl.setBounds(620, 350, 200, 30);
                displayJionDateLbl.setText(""+usersTable.getValueAt(RowID, 6));
                
                displayLeftDateLbl.setBounds(360, 450, 200, 30);
                displayLeftDateLbl.setText(""+usersTable.getValueAt(RowID, 7));

                TableDataDialog.add(BtnPanel);

                captionPanel[0].add(titleLbl[0]);
                captionPanel[0].add(titleLbl[1]);
                captionPanel[0].add(titleLbl[2]);
                
                captionPanel[1].add(titleLbl[3]);
                captionPanel[1].add(titleLbl[4]);
                captionPanel[1].add(titleLbl[5]);
                
                captionPanel[2].add(titleLbl[6]);

                BtnPanel.setBounds(0, 570, 785, 40);
                BtnPanel.setBackground(Color.WHITE);
                
                //Working On DeleteUserRecord
                deleteBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete it?", "Closing Window", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            DeleteRecord(Integer.parseInt(displayIDlbl.getText()));

                            GetUsersModel(usersTable);
                            displayTotalUsers.setText("Total Users: " +Defaults.getCountValue("users"));
                            HomePanel.totalMemberDatabarPanel.dataLbl.setText(""+Defaults.getCountValue("users"));

                            TableDataDialog.dispose();
                        }else {
                            JOptionPane.showMessageDialog(null, "Cancelled user deleting!");
                        }
                    }
                });
                cancelBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        TableDataDialog.dispose();
                        
                    }
                });
                
                //Working On UpdateUserData
                updateUserImageLbl.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        JFileChooser openfile = new JFileChooser();

                        int choise = openfile.showOpenDialog(null);

                        if (choise == JFileChooser.APPROVE_OPTION) {

                            updateFile = openfile.getSelectedFile();

                            String filepath = updateFile.getAbsolutePath();

                            updateUserImageLbl.setIcon(Defaults.rescaleImage(
                                    updateUserImageLbl.getWidth(), 
                                    updateUserImageLbl.getHeight(), 
                                    filepath));
                        }else {
                            JOptionPane.showMessageDialog(null, "Sorry Please Load Image First!");
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
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                editBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        editDialog.setTitle("");
                        editDialog.setSize(700, 600);
                        editDialog.setLayout(null);
                        editDialog.setLocationRelativeTo(null);
                        editDialog.setVisible(true);
                        
                        JPanel btnPanel = new JPanel(new GridLayout(1, 3));
                        JButton updateBtn = new JButton("Update");
                        JButton resetBtn = new JButton("Reset");
                        JButton cancelBtn = new JButton("Cancel");

                        updateUserImagePanel.setBounds(30, 20, 160, 160);
                        updateUserImagePanel.setBackground(Color.WHITE);

                        updateUserImageLbl.setHorizontalAlignment(SwingConstants.RIGHT);
                        updateUserImageLbl.setIcon(displayuserImageLbl.getIcon());

                        updateNameLbl.setBounds(240, 60, 200, 30);
                        updateNameTxt.setBounds(340, 60, 250, 30);
                        updateNameTxt.setText(displayNameLbl.getText());
                        
                        updateAddressLbl.setBounds(240, 110, 200, 30);
                        updateAddressTxt.setBounds(340, 110, 250, 30);
                        updateAddressTxt.setText(displayAddressLbl.getText());
                   
                        updateContactLbl.setBounds(240, 160, 200, 30);
                        updateContactTxt.setBounds(340, 160, 250, 30);
                        updateContactTxt.setText(displaycontactLbl.getText());
                        
                        updateEmailLbl.setBounds(240, 210, 200, 30);
                        updateEmailTxt.setBounds(340, 210, 250, 30);
                        updateEmailTxt.setText(displayEmailLbl.getText());
                        
                        updateRoleLbl.setBounds(240, 260, 200, 30);
                        updateRoleComboBox.setBounds(340, 260, 250, 30);
                        updateRoleComboBox.setSelectedItem(""+displayRoleLbl.getText());
                        
                        btnPanel.setBounds(0, 520, editDialog.getWidth() - 15, 40);
                        editDialog.add(btnPanel);

                        btnPanel.add(updateBtn);
                        updateBtn.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                           
                                String NameStr = updateNameTxt.getText();
                                String addressStr = updateAddressTxt.getText();
                                String contactStr = updateContactTxt.getText();
                                String roleStr = roleComboBox.getSelectedItem().toString();
                                
                                UpdateRecord(Integer.parseInt(displayIDlbl.getText()),NameStr, addressStr, contactStr, roleStr, updateFile);

                                displayNameLbl.setText(updateNameTxt.getText());
                                displaycontactLbl.setText(updateContactTxt.getText());
                                displayAddressLbl.setText(updateAddressTxt.getText());
                                displayEmailLbl.setText(updateEmailTxt.getText());
                                displayRoleLbl.setText(updateRoleComboBox.getSelectedItem().toString());
                                displayuserImageLbl.setIcon(updateUserImageLbl.getIcon());

                                updateNameTxt.setText("");
                                updateContactTxt.setText("");
                                updateAddressTxt.setText("");
                                updateEmailTxt.setText("");
                                updateRoleComboBox.setSelectedItem("Admin");
                                updateUserImageLbl.setIcon(null);

                                GetUsersModel(usersTable);
                                editDialog.dispose();

                            }
                        });
                        btnPanel.add(resetBtn);
                        resetBtn.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                           
                                updateUserImageLbl.setIcon(null);
                                updateNameTxt.setText("");
                                updateAddressTxt.setText("");
                                updateContactTxt.setText("");
                                updateRoleComboBox.setSelectedItem("Admin");
                                updateEmailTxt.setText("");
                            }
                        });
                        btnPanel.add(cancelBtn);
                        cancelBtn.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                           
                                editDialog.dispose();
                            }
                        });
                        
                    }
                });
                BtnPanel.add(printBtn);
                BtnPanel.add(deleteBtn);
                BtnPanel.add(editBtn);
                BtnPanel.add(cancelBtn);
                
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        //End Work of GetUserData Form Table:

        //Use FileChooser For GetImage of new Users With Mouselistener
        userImageLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        userImageLbl.setFont(new Font("Sogoe UI Light", 0, 15));
        userImageLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser openfile = new JFileChooser();

                int choice =openfile.showOpenDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {

                    fileUpload = openfile.getSelectedFile();

                    String filepath = fileUpload.getAbsolutePath();

                    userImageLbl.setIcon(Defaults.rescaleImage(userImageLbl.getWidth(), userImageLbl.getWidth(), filepath));
                } else {
                    JOptionPane.showMessageDialog(null, "Image cannot be loaded!");
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        //End MouselistneerAction Of FileChooser
        
        // *************************************************
        // *********** Working On AddNewUsers *******
        // *************************************************
        
        adduserBtn.setBounds(80, 15, 180, 30);
        adduserBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                adduserDialog.setTitle("INSET NEW USER:");
                adduserDialog.setSize(700, 550);
                adduserDialog.setLayout(null);
                adduserDialog.setLocationRelativeTo(null);
                adduserDialog.setVisible(true);
        
                userImagePanel.setBounds(40, 40, 180, 180);
                userImagePanel.setBackground(Color.WHITE);
                
                usernameLbl.setBounds(240, 50, 200, 30);
                usernameTxt.setBounds(340, 50, 250, 30);
                
                emailLbl.setBounds(240, 100, 200, 30);
                emailTxt.setBounds(340, 100, 250, 30);
                
                passwordLbl.setBounds(240, 150, 200, 30);
                passwordTxt.setBounds(340, 150, 250, 30);
                
                conformPswrdLbl.setBounds(240, 200, 200, 30);
                conformpswrdTxt.setBounds(340, 200, 250, 30);
                
                addressLbl.setBounds(240, 250, 200, 30);
                addressTxt.setBounds(340, 250, 250, 30);
                
                contactLbl.setBounds(240, 300, 200, 30);
                contactTxt.setBounds(340, 300, 250, 30);
                
                roleLbl.setBounds(240, 350, 200, 30);
                roleComboBox.setBounds(340, 350, 250, 30);
                
            }
        });
        
        //Working On AddNewUsers  
        btnPanel.setBounds(0, 470, 685, 40);
        saveRecordBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String usernameStr = usernameTxt.getText();
                String emailStr = emailTxt.getText();
                String passwordStr = passwordTxt.getText();
                String addressStr = addressTxt.getText();
                String roleStr = roleComboBox.getSelectedItem().toString();
                String contactStr = contactTxt.getText();
                if (fileUpload != null) {
                    if (conformpswrdTxt.getText().equals(passwordStr)) {
                        
                        InsertNewUser(
                            usernameStr,
                            emailStr, 
                            passwordStr, 
                            addressStr, 
                            roleStr, 
                            contactStr, 
                            fileUpload);

                            adduserDialog.dispose();
                            usernameTxt.setText("");
                            emailTxt.setText("");
                            passwordTxt.setText("");
                            conformpswrdTxt.setText("");
                            addressTxt.setText("");
                            contactTxt.setText("");
                            roleComboBox.setSelectedItem("Admin");
                            userImageLbl.setIcon(null);
                            
                            GetUsersModel(usersTable);
                            displayTotalUsers.setText("Total Users:" +Defaults.getCountValue("users"));
                            HomePanel.totalMemberDatabarPanel.dataLbl.setText(""+Defaults.getCountValue("users"));

                    } else {
                        JOptionPane.showMessageDialog(null, ""
                            + "Password: "+passwordStr
                            +"\nComform: "+conformpswrdTxt.getText()
                            + "\nPlz Recheck Your Password:");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Sorry Please Load Image First!");
                }
            }
        });
        
        //End ResetAction
        resetBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                usernameTxt.setText("");
                emailTxt.setText("");
                passwordTxt.setText("");
                conformpswrdTxt.setText("");
                addressTxt.setText("");
                contactTxt.setText("");
                roleComboBox.setSelectedItem("Admin");
                userImageLbl.setIcon(null);
            }
        });
        
        //End CancelAction
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                adduserDialog.dispose();
                usernameTxt.setText("");
                emailTxt.setText("");
                passwordTxt.setText("");
                conformpswrdTxt.setText("");
                addressTxt.setText("");
                contactTxt.setText("");
                roleComboBox.setSelectedItem("Admin");
                userImageLbl.setIcon(null);

            }
        });
        
        //END Work Of Add New Users
        TableDataDialog.add(mainPanelTabelData);

        mainPanelTabelData.add(displayNameLbl);
        mainPanelTabelData.add(displayAddressLbl);
        mainPanelTabelData.add(displaycontactLbl);
        mainPanelTabelData.add(displayEmailLbl);
        mainPanelTabelData.add(displayRoleLbl);
        mainPanelTabelData.add(displayJionDateLbl);
        mainPanelTabelData.add(displayLeftDateLbl);

        mainPanelTabelData.add(displayUserImagePanel);
        mainPanelTabelData.add(displaytitlePanel);
        mainPanelTabelData.add(displayIDpanel);

        displayUserImagePanel.add(displayuserImageLbl);
        displaytitlePanel.add(displaytitleLbl);
        displayIDpanel.add(displayIDlbl);

        editDialog.add(updateUserImagePanel);
        updateUserImagePanel.add(updateUserImageLbl);
        
        editDialog.add(updateNameLbl);
        editDialog.add(updateAddressLbl);
        editDialog.add(updateContactLbl);
        editDialog.add(updateEmailLbl);
        editDialog.add(updateRoleLbl);
        
        editDialog.add(updateNameTxt);
        editDialog.add(updateAddressTxt);
        editDialog.add(updateContactTxt);
        editDialog.add(updateEmailTxt);
        editDialog.add(updateRoleComboBox);
        
        adduserDialog.add(usernameLbl);
        adduserDialog.add(passwordLbl);
        adduserDialog.add(conformPswrdLbl);
        adduserDialog.add(emailLbl);
        adduserDialog.add(addressLbl);
        adduserDialog.add(contactLbl);
        adduserDialog.add(roleLbl);
        
        adduserDialog.add(usernameTxt);
        adduserDialog.add(passwordTxt);
        adduserDialog.add(conformpswrdTxt);
        adduserDialog.add(emailTxt);
        adduserDialog.add(addressTxt);
        adduserDialog.add(contactTxt);
        adduserDialog.add(roleComboBox);
        
        adduserDialog.add(userImagePanel);
        userImagePanel.add(userImageLbl);
        
        adduserDialog.add(btnPanel);
        btnPanel.add(saveRecordBtn);
        btnPanel.add(resetBtn);
        btnPanel.add(cancelBtn);

        add(usertitleLbl);
        add(adduserBtn);
        
        add(userScrollPane);
        add(displayTotalUsers);
                
        add(searchLbl);
        add(searchTxt);

    }
    
    private JLabel usertitleLbl = new JLabel("Users:");

    private JLabel displayTotalUsers = new JLabel();
            
    private JPanel userImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel userImageLbl = new JLabel("PHOTO");
    
    private JLabel usernameLbl = new JLabel("UserName:");
    private JTextField usernameTxt = new JTextField();
    
    private JLabel passwordLbl = new JLabel("Password:");
    private JTextField passwordTxt = new JTextField();
    
    private JLabel conformPswrdLbl = new JLabel("Conform:");
    private JTextField conformpswrdTxt = new JTextField();
    
    private JLabel emailLbl = new JLabel("Email:");
    private JTextField emailTxt = new JTextField();
    
    private JLabel addressLbl = new JLabel("Address:");
    private JTextField addressTxt = new JTextField();
    
    private JLabel contactLbl = new JLabel("Contact");
    private JTextField contactTxt = new JTextField();
            
    private JLabel roleLbl = new JLabel("Role:");
    private JComboBox roleComboBox = new JComboBox(new Object[]{"Admin","user","Student","Teacher"});
    
    private JPanel btnPanel = new JPanel(new GridLayout(1, 3));
    private JButton saveRecordBtn = new JButton("Save");
    private JButton resetBtn = new JButton("Reset");
    private JButton cancelBtn = new JButton("Cancel");
    
    private JButton adduserBtn = new JButton("Add User:");
    private JDialog adduserDialog = new JDialog();
    
    private JLabel searchLbl = new JLabel("Search:");
    private JTextField searchTxt = new JTextField();
    
    private JTable usersTable = new JTable();
    private JScrollPane userScrollPane = new JScrollPane();
    
    private JDialog TableDataDialog = new JDialog();
    private JPanel mainPanelTabelData = new JPanel(null);
    
    private JPanel displayUserImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayuserImageLbl = new JLabel("Photo: ");

    private JPanel displaytitlePanel = new JPanel(new GridLayout(1, 1));
    private JLabel displaytitleLbl = new JLabel("User ID");
                
    private JPanel displayIDpanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayIDlbl = new JLabel();

    private JPanel captionPanel[] = new JPanel[3];
    private JLabel titleLbl[] = new JLabel[7];
    private String titleArray[] = {"Name:","Address:","Contact:","Email:","Role:","JionDate","LeftDate"};
    
    private JLabel displayNameLbl = new JLabel();
    private JLabel displayAddressLbl = new JLabel();
    private JLabel displaycontactLbl = new JLabel();
    private JLabel displayRoleLbl = new JLabel();
    private JLabel displayEmailLbl = new JLabel();
    private JLabel displayJionDateLbl = new JLabel();
    private JLabel displayLeftDateLbl = new JLabel();

    private JDialog editDialog = new JDialog();
    
    private JPanel updateUserImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel updateUserImageLbl = new JLabel();
    
    private JLabel updateEmailLbl = new JLabel("Email:");
    private JTextField updateEmailTxt = new JTextField();
    
    private JLabel updateNameLbl = new JLabel("User Name:");
    private JTextField updateNameTxt = new JTextField();
    
    private JLabel updateAddressLbl = new JLabel("Address:");
    private JTextField updateAddressTxt = new JTextField();
    
    private JLabel updateContactLbl = new JLabel("Contact:");
    private JTextField updateContactTxt = new JTextField();
    
    private JLabel updateRoleLbl = new JLabel("Role:");
    private JComboBox updateRoleComboBox = new JComboBox(new Object[]{"Admin","User","Student","Teacher"});
    
    private void GetImage(int ID){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT UserImage FROM users WHERE UID ="+ID;
                
                Statement statement = MyConn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
               
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                       
                
                        displayuserImageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        displayUserImagePanel.getWidth(),
                                         displayUserImagePanel.getHeight(),
                                        resultSet.getBytes("UserImage"))
                        );
                    }
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
    }
    private void UpdateRecord(int ID, String NameStr, String AddressStr, String contactStr, String roleStr, File file){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = "UPDATE users SET "
                        + "UserName = ?,"
                        + "Address =?,"
                        + "Contact = ?,"
                        + "Role = ?,"
                        + "UserImage = ? WHERE UID = ?";
        
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setString(1, NameStr);
                preparedStatement.setString(2, AddressStr);
                preparedStatement.setString(3, contactStr);
                preparedStatement.setString(4, roleStr);
                
                if (file != null) {
                    InputStream inputStream = new FileInputStream(file);
                    if (inputStream != null) {
                        preparedStatement.setBlob(5, inputStream);
                        JOptionPane.showMessageDialog(null, "Your Data has been Processed into binary!");
                    }
                }
                
                preparedStatement.setInt(6, ID);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your Record has been Updated successfully! ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    }
    
    private void InsertNewUser(String userNameStr, String emailStr, String passwordStr, String addressStr, String roleStr, String contactStr ,File file){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                
                String query = "INSERT INTO users(UserName,Email,Password,Address,Role,Contact,JionDate,LeftDate,LastSession,RID,UserImage)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
                
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                preparedStatement.setString(1, userNameStr);
                preparedStatement.setString(2, emailStr);
                preparedStatement.setString(3, passwordStr);
                preparedStatement.setString(4, addressStr);
                preparedStatement.setString(5, roleStr);
                preparedStatement.setString(6, contactStr);
                preparedStatement.setString(7, Defaults.currentDate());
                preparedStatement.setString(8, Defaults.currentDate());
                preparedStatement.setString(9, Defaults.currentTimestamp());
                preparedStatement.setInt(10, Defaults.generateRalationID());
                
                if (file != null) {
                    InputStream inputStream = new FileInputStream(file);
                    if (inputStream != null) {
                        preparedStatement.setBlob(11, inputStream);
                        JOptionPane.showMessageDialog(null, "Data has been processed into binary!");
                    }
                }
                preparedStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Your Record has been inserted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
    }
    private void DeleteRecord(int ID){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "DELETE FROM users WHERE UID = "+ID;
                
                Statement statement = MyConn.createStatement();
                statement.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Your Record has been deleted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
        }
    }
    private DefaultTableModel SearchUserModel(String username, JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String searchquery = "SELECT * FROM users WHERE UserName LIKE ('%"+ username +"%')";
                
                Statement statement = MyConn.createStatement();
                ResultSet resultSet = statement.executeQuery(searchquery);
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object searchObject[] = {
                    
                            resultSet.getString("UID"),
                            resultSet.getString("UserName"),
                            resultSet.getString("Address"),
                            resultSet.getString("Contact"),
                            resultSet.getString("Email"),
                            resultSet.getString("Role"),
                            resultSet.getDate("JionDate"),
                            resultSet.getDate("LeftDate")
                                
                        };
                        model.addRow(searchObject);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
        
        return model;
    }
    
    private DefaultTableModel GetUsersModel(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM users";
                
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                       
                        Object userobject[] = {
                            resultSet.getString("UID"),
                            resultSet.getString("UserName"),
                            resultSet.getString("Address"),
                            resultSet.getString("Contact"),
                            resultSet.getString("Email"),
                            resultSet.getString("Role"),
                            resultSet.getDate("JionDate"),
                            resultSet.getDate("LeftDate")
                        };
                        model.addRow(userobject);
                    }
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
        return model;
    }
}
package BookSubPanels;

import AdminPanel.AddBookPanel;
import static AdminPanel.AddBookPanel.displayBookImageLbl;
import AdminPanel.BookDialog;
import Connectivity.MySQLConnection;
import Connectivity.NewMySQLConnection;
import Core.Defaults;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AuthorPanel extends JPanel{
    int posX;
    int posY;
    int width;
    int height;
    
    public AuthorPanel(){}
    public AuthorPanel(int posX, int posY, int width, int height){
 
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        
    }
    public void drawPanel(){
        intiComponend();
    }
    File uploadAuthorFile = null;
    
    public void intiComponend(){
        
        setBounds(posX, posY, width, height);
        setLayout(null);

        authorTitleLbl.setBounds(10, 10, 200, 40);
        authorTitleLbl.setFont(new Font("Souge UI", 0, 20));
        
        authorImageLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                JFileChooser openfile = new JFileChooser();
                
                int choise = openfile.showOpenDialog(null);
                
                if (choise == JFileChooser.APPROVE_OPTION) {
                    
                    uploadAuthorFile  = openfile.getSelectedFile();
                    
                    String filepath = uploadAuthorFile.getAbsolutePath();
                    
                    authorImageLbl.setIcon(
                            Defaults.rescaleImage(
                                    authorImagePanel.getWidth(), 
                                    authorImagePanel.getHeight(), 
                                    filepath)
                    );

                }else {
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
        addAuthorBtn.setBounds(80, 15, 150, 30);
        addAuthorBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
    
                addauthorDialog.setTitle("Add New Author:");
                addauthorDialog.setSize(700, 400);
                addauthorDialog.setLayout(null);
                addauthorDialog.setLocationRelativeTo(null);
                addauthorDialog.setVisible(true);
                
                authorImagePanel.setBounds(40, 40, 180, 180);
                authorImagePanel.setBackground(Color.WHITE);
                
                authorImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
                
                authorNameLbl.setBounds(260, 50, 200, 30);
                authorNameTxt.setBounds(340, 50, 250, 30);
                
                authorAddressLbl.setBounds(260, 100, 200, 30);
                authorAddressTxt.setBounds(340, 100, 250, 30);
                
                authorContactLbl.setBounds(260, 150, 200, 30);
                authorContactTxt.setBounds(340, 150, 250, 30);
                
                saveAuthorBtn.setBounds(0, 320, addauthorDialog.getWidth() - 15, 40);
                saveAuthorBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        String nameStr = authorNameTxt.getText();
                        String addressStr = authorAddressTxt.getText();
                        String contactStr = authorContactTxt.getText();
    
                        if (uploadAuthorFile != null ) {
                                BookDialog.InsertAuthorRecord(nameStr, addressStr, contactStr, uploadAuthorFile);

                                authorImageLbl.setIcon(null);
                                authorNameTxt.setText("");
                                authorAddressTxt.setText("");
                                authorContactTxt.setText("");
                                
                                GetAuthorsModel(authorTable);
                                addauthorDialog.dispose();
                                displayTotalAuthorLbl.setText("Total Authors: "+Defaults.getCountValue("Authors"));
                                                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry Please Load Image First!");
                        }
                    }
                });
            }
        });
        
        //Working On Search Author From Author Table:
        searchLbl.setBounds(620, 60, 200, 30);
        searchTxt.setBounds(700, 60, 250, 30);
        searchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
           
                authorTable.setModel(SearchAuthorsModel(searchTxt.getText(), authorTable));
            }
        });
        
        displayTotalAuthorLbl.setBounds(25, 120, 200, 30);
        displayTotalAuthorLbl.setFont(new Font("Sogue UI Bold", 0, 20));
        displayTotalAuthorLbl.setText("Total Authors: "+Defaults.getCountValue("Authors"));
        
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"#ID","AuthorName","Contact","Address","SubmitDate"}
        );
        authorTableScrollPane.setBounds(20, 150, getWidth() - 40, 400);
        authorTableScrollPane.setViewportView(authorTable);

        authorTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        authorTable.setModel(model);
        authorTable.setModel(GetAuthorsModel(authorTable));
        authorTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
           
                int AuthorRow = authorTable.getSelectedRow();
                
                tableDataDialog.setTitle("Author Name: " + authorTable.getValueAt(AuthorRow, 1));
                tableDataDialog.setSize(600, 550);
                tableDataDialog.setLayout(new GridLayout(1, 1));
                tableDataDialog.setLocationRelativeTo(null);
                tableDataDialog.setVisible(true);
                 
                mainPanelOfTableData.setBackground(Color.WHITE);

                displayImagePanel.setBounds(30, 30, 160, 160);
                
                displayImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
                displayImageLbl.setFont(new Font("Sogue UI Bold", 0, 15));
                
                displayTitleIDPanel.setBounds(getHeight() - 180, 80, 150, 30);
                displayTitleIDPanel.setBackground(new Color(230, 230, 230));
                
                displayTitleIDLbl.setHorizontalAlignment(SwingConstants.CENTER);
                
                displayIDPanel.setBounds(getHeight() - 180, 120, 150, 30);
                displayIDPanel.setBackground(new Color(240, 240, 240));
                
                displayIDLbl.setHorizontalAlignment(SwingConstants.CENTER);
                displayIDLbl.setText(authorTable.getValueAt(AuthorRow, 0).toString());

                GetAuthorImage(Integer.parseInt(displayIDLbl.getText().toString()));
 
                for (int i = 0; i < captionPanel.length; i++) {

                    captionPanel[i] = new JPanel(new GridLayout(1, 1));
                    captionPanel[i].setBounds(0, 210 + 90 * i, mainPanelOfTableData.getWidth(), 30);
                    mainPanelOfTableData.add(captionPanel[i]);
                
                }
                for (int i = 0; i < titleLbl.length; i++) {
                    
                    titleLbl[i] = new JLabel(TitleArray[i]);
                    titleLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
                }

                displayNameDataLbl.setBounds(100, 250, 200, 30);
                displayNameDataLbl.setText(authorTable.getValueAt(AuthorRow, 1).toString());
                displayNameDataLbl.setFont(new Font("Sogue UI Bold", 0, 15));

                displayContactDataLbl.setBounds(100, 350, 200, 30);
                displayContactDataLbl.setText(authorTable.getValueAt(AuthorRow, 2).toString());
                displayContactDataLbl.setFont(new Font("Sogue UI Bold", 0, 15));

                displayaddressDataLbl.setBounds(400, 250, 200, 30);
                displayaddressDataLbl.setText(authorTable.getValueAt(AuthorRow, 3).toString());
                displayaddressDataLbl.setFont(new Font("Sogue UI Bold", 0, 15));

                displaySumbitDateDataLbl.setBounds(400, 350, 200, 30);
                displaySumbitDateDataLbl.setText(authorTable.getValueAt(AuthorRow, 4).toString());
                displaySumbitDateDataLbl.setFont(new Font("Sogue UI Bold", 0, 15));

                TablebtnPanel.setBounds(0, 470, mainPanelOfTableData.getWidth(), 40);
                TablebtnPanel.setBackground(Color.WHITE);
                
                captionPanel[0].add(titleLbl[0]);
                captionPanel[0].add(titleLbl[1]);
                
                captionPanel[1].add(titleLbl[2]);
                captionPanel[1].add(titleLbl[3]);

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
        
        updateImageLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser openFile = new JFileChooser();
                
                int result = openFile.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    
                    uploadAuthorFile = openFile.getSelectedFile();
                    
                    String filepath = uploadAuthorFile.getAbsolutePath();
                    
                    updateImageLbl.setIcon(
                            Defaults.rescaleImage(
                                    updateImagePanel.getWidth(), 
                                    updateImagePanel.getHeight(),
                                    filepath)
                    );
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Image can not be loaded!");
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
           
                editDialog.setTitle("Update Author:");
                editDialog.setSize(700, 400);
                editDialog.setLayout(null);
                editDialog.setLocationRelativeTo(null);
                editDialog.setVisible(true);

                updateImagePanel.setBounds(40, 40, 180, 180);
                updateImagePanel.setBackground(Color.WHITE);
                
                updateImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
                updateImageLbl.setIcon(displayImageLbl.getIcon());
                
                updateNameLbl.setBounds(260, 50, 200, 30);
                updateNameTxt.setBounds(340, 50, 250, 30);
                updateNameTxt.setText(displayNameDataLbl.getText());
                
                updateAddressLbl.setBounds(260, 100, 200, 30);
                updateAddressTxt.setBounds(340, 100, 250, 30);
                updateAddressTxt.setText(displayaddressDataLbl.getText());
                
                updateContactLbl.setBounds(260, 150, 200, 30);
                updateContactTxt.setBounds(340, 150, 250, 30);
                updateContactTxt.setText(displayContactDataLbl.getText());
                
                updateBtn.setBounds(0, 320, editDialog.getWidth() - 15, 40);
                updateBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                   
                        String nameStr = updateNameTxt.getText();
                        String addressStr = updateAddressTxt.getText();
                        String contactStr = updateContactTxt.getText();
    
                        if (uploadAuthorFile != null ) {
                            
                            updateAuthor(Integer.parseInt(displayIDLbl.getText()), uploadAuthorFile);
                            
                                updateImageLbl.setIcon(null);
                                updateNameTxt.setText("");
                                updateAddressTxt.setText("");
                                updateContactTxt.setText("");
                                
                                GetAuthorsModel(authorTable);
                                editDialog.dispose();
                                                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry Please Load Image First!");
                        }
                    }
                });
    
            }
        });
        
        deleteBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are You Sure it?", "Delete Window", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    DeleteAuthor(Integer.parseInt(displayIDLbl.getText()));
                    GetAuthorsModel(authorTable);
                    tableDataDialog.dispose();
                    displayTotalAuthorLbl.setText("Total Authors: "+Defaults.getCountValue("Authors"));
      
                } else {
                    JOptionPane.showMessageDialog(null, "cancel Deleting author!");

                }
            }
        });

        editDialog.add(updateImagePanel);
        updateImagePanel.add(updateImageLbl);
        
        editDialog.add(updateNameLbl);
        editDialog.add(updateAddressLbl);
        editDialog.add(updateContactLbl);
        
        editDialog.add(updateNameTxt);
        editDialog.add(updateAddressTxt);
        editDialog.add(updateContactTxt);
        
        editDialog.add(updateBtn);

        tableDataDialog.add(mainPanelOfTableData);
        
        mainPanelOfTableData.add(displayImagePanel);
        displayImagePanel.add(displayImageLbl);
        
        mainPanelOfTableData.add(displayTitleIDPanel);
        displayTitleIDPanel.add(displayTitleIDLbl);
        
        mainPanelOfTableData.add(displayIDPanel);
        displayIDPanel.add(displayIDLbl);
        
        mainPanelOfTableData.add(displayNameDataLbl);
        mainPanelOfTableData.add(displayContactDataLbl);
        mainPanelOfTableData.add(displayaddressDataLbl);
        mainPanelOfTableData.add(displaySumbitDateDataLbl);
        
        mainPanelOfTableData.add(TablebtnPanel);
        
        TablebtnPanel.add(printBtn);
        TablebtnPanel.add(deleteBtn);
        TablebtnPanel.add(editBtn);
        
        addauthorDialog.add(authorImagePanel);
        authorImagePanel.add(authorImageLbl);
        
        addauthorDialog.add(authorNameLbl);
        addauthorDialog.add(authorContactLbl);
        addauthorDialog.add(authorAddressLbl);
        
        addauthorDialog.add(authorNameTxt);
        addauthorDialog.add(authorAddressTxt);
        addauthorDialog.add(authorContactTxt);
    
        addauthorDialog.add(saveAuthorBtn);

        add(authorTitleLbl);
        add(addAuthorBtn);
        add(displayTotalAuthorLbl);
        
        add(searchLbl);
        add(searchTxt);

        add(authorTableScrollPane);
    }
    
    private JLabel authorTitleLbl = new JLabel("Author:");
    
    private JButton addAuthorBtn = new JButton("Add Author:");
    private JDialog addauthorDialog = new JDialog();
    
    private JPanel authorImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel authorImageLbl = new JLabel("PHOTO:");
    
    private JLabel authorNameLbl = new JLabel("Name:");
    private JTextField authorNameTxt = new JTextField();
    
    private JLabel authorContactLbl = new JLabel("Contact");
    private JTextField authorContactTxt = new JTextField();
    
    private JLabel authorAddressLbl = new JLabel("Address:");
    private JTextField authorAddressTxt = new JTextField();

    private JButton saveAuthorBtn = new JButton("Add");
    
    private JLabel searchLbl = new JLabel("Search:");
    private JTextField searchTxt = new JTextField();
                                                                                    
    private JScrollPane authorTableScrollPane = new JScrollPane();
    private JTable  authorTable = new JTable();
                                                
    private JDialog tableDataDialog = new JDialog();
    private JPanel mainPanelOfTableData = new JPanel(null);
    
    private JPanel captionPanel[] = new JPanel[2];
    private JLabel titleLbl[] = new JLabel[4];
    private String TitleArray[] = {"Author Name:","Addrees:","Contact:","SubmitDate:"};
    
    private JPanel displayImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayImageLbl = new JLabel("Photo:");
    
    private JPanel displayTitleIDPanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayTitleIDLbl = new JLabel("Author ID:");
    
    private JPanel displayIDPanel = new JPanel(new GridLayout(1, 1));
    private JLabel displayIDLbl = new JLabel();

    private JLabel displayNameDataLbl = new JLabel();
    private JLabel displayContactDataLbl = new JLabel();
    private JLabel displayaddressDataLbl = new JLabel();
    private JLabel displaySumbitDateDataLbl = new JLabel();

    private JLabel displayTotalAuthorLbl = new JLabel("Total Author: ");
    
    private JPanel TablebtnPanel = new JPanel(new GridLayout(1, 4));
    private JButton printBtn = new JButton("Print");
    private JButton deleteBtn = new JButton("Delete");
    private JButton editBtn = new JButton("Edit");
    
    private JDialog editDialog = new JDialog();
    
    private JPanel updateImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel updateImageLbl = new JLabel("Photo:");
    
    private JLabel updateNameLbl = new JLabel("Name:");
    private JTextField updateNameTxt = new JTextField();
    
    private JLabel updateContactLbl = new JLabel("Contact:");
    private JTextField updateContactTxt = new JTextField();
    
    private JLabel updateAddressLbl = new JLabel("Address:");
    private JTextField updateAddressTxt = new JTextField();
    
    private JButton updateBtn = new JButton("Update:");
    
    private void DeleteAuthor(int AID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String deleteAuthor = "DELETE FROM Authors WHERE AID = "+AID;
                
                Statement statement = myConn.createStatement();
                statement.executeUpdate(deleteAuthor);
                JOptionPane.showMessageDialog(null, "Your Author has been Deleted successfully!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
    }

    private void updateAuthor(int AID, File file){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                
                String query = "UPDATE authors SET "
                        + " AuthorName = ?,"
                        + " Address = ?, "
                        + " Contact = ? ,"
                        + " AuthorImage = ? WHERE AID = "+ AID;
    
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setString(1, updateNameTxt.getText());
                preparedStatement.setString(2, updateAddressTxt.getText());
                preparedStatement.setString(3, updateContactTxt.getText());
                
                if (file != null ) {
                    
                    InputStream inputStream = new FileInputStream(file);
                    if (inputStream != null) {
                        
                        preparedStatement.setBlob(4, inputStream);
                        JOptionPane.showMessageDialog(null, "Your Data has been proccessed into binary!");
                    }
                }
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your record has been updated Successfully!");
                
                displayImageLbl.setIcon(updateImageLbl.getIcon());
                displayNameDataLbl.setText(updateNameTxt.getText());
                displayaddressDataLbl.setText(updateAddressTxt.getText());
                displayContactDataLbl.setText(updateContactTxt.getText());
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    
    }   

    private void GetAuthorImage(int AID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String query = " SELECT AuthorImage FROM authors WHERE AID = "+AID;
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        displayImageLbl.setIcon(
                                Defaults.rescaleImageFromBytes(
                                        displayImagePanel.getWidth(), 
                                        displayImagePanel.getHeight()
                                        , resultSet.getBytes("AuthorImage")));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
        }
    }

    private DefaultTableModel GetAuthorsModel(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        try {
            Connection Myconn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM Authors";
                
                PreparedStatement preparedStatement = Myconn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object categoriesObject[] = {
                            resultSet.getInt("AID"),
                            resultSet.getString("AuthorName"),
                            resultSet.getString("Contact"),
                            resultSet.getString("Address"),
                            resultSet.getDate("SubmitDate")
                            
                        };
                        model.addRow(categoriesObject);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
        return model;
    }
    private DefaultTableModel SearchAuthorsModel(String AuthorName, JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        try {
            Connection Myconn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM authors WHERE AuthorName LIKE ('%" + AuthorName + "%')";
                
                PreparedStatement preparedStatement = Myconn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object categoriesObject[] = {
                            
                            resultSet.getInt("AID"),
                            resultSet.getString("AuthorName"),
                            resultSet.getString("Contact"),
                            resultSet.getString("Address"),
                            resultSet.getDate("SubmitDate")
                        };
                        model.addRow(categoriesObject);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
        return model;
    }

}
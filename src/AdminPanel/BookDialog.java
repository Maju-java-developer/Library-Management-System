package AdminPanel;

import Core.Defaults;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import Connectivity.MySQLConnection;
import DashBaordPanel.HomePanel;
import DefaultRenderer.CusTomTableRenderer;

public class BookDialog extends JDialog{

    int posX;
    int posY;
    int width;
    int height;
    
    public BookDialog(){}
    public BookDialog(int posX, int posY, int width, int height){
        
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    private String authorName = "";
    private String authorAddress = "";
    private String authorContact = "";
    
    private File bookfileUpload = null;
    private File authorFileUpload = null;
    public int gotRelationIDOfAuthor;
   
    public void drawPanel(){
        
        intiComponend();
    }
    public void intiComponend(){
        
        btnPanel.setBounds(0, 640, 700 - 15, 40);

        //Add Mouselistner For GetImage Of Book
        bookImagePanel.setBounds(40, 40, 160, 160);
        bookImagePanel.setBackground(Color.WHITE);

        bookImageLbl.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        bookImageLbl.setFont(new Font("Sogoe UI Light", 0, 20));
        bookImageLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser openfile = new JFileChooser();

                int choise = openfile.showOpenDialog(null);

                if (choise == JFileChooser.APPROVE_OPTION) {

                    bookfileUpload = openfile.getSelectedFile();

                    String filepath = bookfileUpload.getAbsolutePath();

                    bookImageLbl.setIcon(
                            Defaults.rescaleImage(
                                    bookImageLbl.getWidth(), 
                                    bookImageLbl.getHeight(),
                                    filepath)
                    );
                    
                } else {
                    JOptionPane.showMessageDialog(null, " Cover Image cannot be loaded!");
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
        
        booktitlePanel.setBounds(0, 2, 700, 30);
        booktitlePanel.setBackground(Color.WHITE);

        BookTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BookTitleLabel.setFont(new Font("BOLD", 0, 20));

        booktitleLbl.setBounds(250, 50, 200, 30);
        booktitleTxt.setBounds(350, 50, 250, 30);

        booktypeLbl.setBounds(250, 100, 200, 30);
        booktypeBox.setBounds(350, 100, 250, 30);
        booktypeBox.setModel(GetCategoriesModel(booktypeBox));

        copiesLbl.setBounds(250, 150, 200, 30);
        copiesTxt.setBounds(350, 150, 250, 30);

        editionLbl.setBounds(250, 200, 200, 30);
        editionTxt.setBounds(350, 200, 250, 30);

        descLbl.setBounds(250, 250, 200, 30);
        descScrollPane.setBounds(350, 250, 250, 90);
        descScrollPane.setViewportView(descTxt);

        //----------------------------------------------
        //------- Working On Add New Category -----
        //----------------------------------------------
        
        addCategoriesBtn.setBounds(600, 100, 50, 30);
        addCategoriesBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                addcategoriesDialog.setTitle("Add new Catogories:");
                addcategoriesDialog.setSize(400, 300);
                addcategoriesDialog.setLayout(null);
                addcategoriesDialog.setLocationRelativeTo(null);
                addcategoriesDialog.setVisible(true);

                addcategoriesLbl.setBounds(20, 20, 150, 30);
                addcategoriesTxt.setBounds(150, 20, 200, 30);

                adddescLbl.setBounds(20, 60, 150, 30);
                adddescScrollPane.setBounds(150, 60, 200, 90);
                adddescScrollPane.setViewportView(adddescTxt);

                saveCategoriesBtn.setBounds(150, 160, 200, 30);
                saveCategoriesBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String categoryNameStr = addcategoriesTxt.getText();
                        String descriptionStr = adddescTxt.getText();

                        if (categoryNameStr.equals("")) {
                            JOptionPane.showMessageDialog(null, "Sorry Write Category Name First!");
                        }else {

                            addCategory(categoryNameStr, descriptionStr);
                            GetCategoriesModel(booktypeBox);
                            addcategoriesTxt.setText("");
                            adddescTxt.setText("");
                            addcategoriesDialog.dispose();
                        }
                    }
                });

            }
        }); 
        //End Work On AddNewCategory:
        
        //Working On Search Author From Table:
        displayAuthorSelectLbl.setBounds(60, 600, 200, 30);

        searchAuthorLbl.setBounds(50, 350, 200, 30);
        searchAuthorTxt.setBounds(140, 350, 700 - 300, 30);
        searchAuthorTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                AuthorTable.setModel(searchAuthorModel(searchAuthorTxt.getText(), AuthorTable));
            }
        });

        //Work On AuthorTable For Get RelationID OF Author From Table
        AuthorTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"#ID","AuthorName","Contact","Address"}
        );

        AuthorTableScrollPane.setBounds(40, 390, 700 - 100, 200);
        AuthorTableScrollPane.setViewportView(AuthorTable);
        AuthorTable.setModel(model);
        AuthorTable.setModel(GetAuthorsModel(AuthorTable));
        
        AuthorTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int RowID = AuthorTable.getSelectedRow();

                String AID = ""+AuthorTable.getValueAt(RowID, 0);
                
                authorName = ""+AuthorTable.getValueAt(RowID, 1);
                authorContact = ""+AuthorTable.getValueAt(RowID, 2);
                authorAddress = ""+AuthorTable.getValueAt(RowID, 3);

                int RID = GetAuthorRelationID(Integer.parseInt(AID), authorName, authorAddress, authorContact);

                gotRelationIDOfAuthor = RID;

                displayAuthorSelectLbl.setText("Selected Author: " + authorName + ":");

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

        //AddMouseListener For To Get Author Image
        authorImagePanel.setBounds(40, 50, 180, 180);
        authorImagePanel.setBackground(Color.WHITE);

        authorImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
        authorImageLbl.setFont(new Font("Sogoe UI Light", 0, 20));
        authorImageLbl.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser openFile = new JFileChooser();

                int choise = openFile.showOpenDialog(null);

                if (choise == JFileChooser.APPROVE_OPTION) {

                    authorFileUpload = openFile.getSelectedFile();

                    String filePath = authorFileUpload.getAbsolutePath();

                    authorImageLbl.setIcon(
                            Defaults.rescaleImage(
                                    authorImageLbl.getWidth(),
                                    authorImageLbl.getHeight(), 
                                    filePath)
                    );
                    
                }else {
                    JOptionPane.showMessageDialog(null, "Author Image cannot be loaded!");
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

        addAuthorBtn.setBounds(540, 350, 100, 30);
        addAuthorBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                addAuthorDialog.setTitle("Add New Author:");
                addAuthorDialog.setSize(700, 400);
                addAuthorDialog.setLayout(null);
                addAuthorDialog.setLocationRelativeTo(null);
                addAuthorDialog.setVisible(true);

                authorNameLbl.setBounds(250, 70, 150, 30);
                authorNameTxt.setBounds(350, 70, 250, 30);

                authorContactLbl.setBounds(250, 120, 150, 30);
                authorContactTxt.setBounds(350, 120, 250, 30);

                authoraddressLbl.setBounds(250, 170, 150, 30);
                authoraddressTxt.setBounds(350, 170, 250, 30);
        
                SaveAuthorBtn.setBounds(0, 320, addAuthorDialog.getWidth() - 15, 40);
                SaveAuthorBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (authorFileUpload != null) {
                            
                            InsertAuthorRecord(
                                    authorNameTxt.getText(),
                                    authoraddressTxt.getText(), 
                                    authorContactTxt.getText(),
                                    authorFileUpload);

                            authorNameTxt.setText("");
                            authoraddressTxt.setText("");
                            authorContactTxt.setText("");
                            authorImageLbl.setIcon(null);

                            AuthorTable.setModel(GetAuthorsModel(AuthorTable));
                            addAuthorDialog.dispose();

                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry Please Load Image First!");
                        }
                    }
                });
            }
        }); 
                                                                                                                        
        //SaveBook Action:               
        saveBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                String bookTitleStr = booktitleTxt.getText();
                String bookTypeStr = booktypeBox.getSelectedItem().toString();
                String copies = copiesTxt.getText();
                String editionStr = editionTxt.getText();
                String descriptionStr = descTxt.getText();

                    if (bookfileUpload != null) {
                        if (!booktypeBox.getSelectedItem().toString().equals("-- Select Type --")) {
                            if (gotRelationIDOfAuthor != 0) {
                                InsertBook(
                                        bookTitleStr,
                                        bookTypeStr,
                                        Integer.parseInt(copies),
                                        editionStr, 
                                        descriptionStr, 
                                        bookfileUpload, 
                                        gotRelationIDOfAuthor);
                                
                                booktitleTxt.setText("");
                                booktypeBox.setSelectedItem("");
                                descTxt.setText("");
                                copiesTxt.setText("");
                                bookImageLbl.setIcon(null);
                                AddBookPanel.displayTotalBooksLbl.setText("Total Books: " +Defaults.getCountValue("books"));
                                HomePanel.totalbooksDatabarPanel.dataLbl.setText(""+Defaults.getCountValue("books"));
                                
                                displayAuthorSelectLbl.setText(""+Defaults.totalbooksDataBar);

                                AddBookPanel.GetBookModel(Defaults.bookTable);
                                dispose();

                            }else {
                                JOptionPane.showMessageDialog(null, "Please select author first!");
                            }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please Select BookType!");
                    }                    
                }else {
                    JOptionPane.showMessageDialog(null, "Please Load Book Image First!");
                }
            }
        });
        //End SaveBook Action
        
        //Update ToBook Action
        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (bookfileUpload != null) {
                    if (gotRelationIDOfAuthor !=0) {
                        
                        UpdateBook(Integer.parseInt(AddBookPanel.displayDataLbl.getText()), bookfileUpload, gotRelationIDOfAuthor);

                        booktitleTxt.setText("");
                        booktypeBox.setSelectedItem("");
                        descTxt.setText("");
                        copiesTxt.setText("");
                        bookImageLbl.setIcon(null);

                        AddBookPanel.GetBookModel(Defaults.bookTable);
                        dispose();

                    }else {
                        JOptionPane.showMessageDialog(null, "Please Select Author First!");
            
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select Image First!");
                            
                }
            }
        });
        
        cancelBtn.addActionListener(new ActionListener() {
       
            @Override
            public void actionPerformed(ActionEvent e) {

                booktitleTxt.setText("");
                booktypeBox.setSelectedItem("");
                descTxt.setText("");
                copiesTxt.setText("");
                editionTxt.setText("");
                bookImageLbl.setIcon(null);
                dispose();
            }
        });

        resetBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                booktitleTxt.setText("");
                booktypeBox.setSelectedItem("");
                descTxt.setText("");
                copiesTxt.setText("");
                editionTxt.setText("");
                bookImageLbl.setIcon(null);

            }
        });
        //End Work of AddNewBook

        add(bookImagePanel);
        bookImagePanel.add(bookImageLbl);

        add(booktitlePanel);
        booktitlePanel.add(BookTitleLabel);
        
        add(booktitleLbl);
        add(booktypeLbl);
        add(copiesLbl);
        add(editionLbl);
        add(descLbl);

        add(booktitleTxt);
        add(booktypeBox);
        add(copiesTxt);
        add(editionTxt);
        add(descScrollPane);

        add(addCategoriesBtn);

        addcategoriesDialog.add(addcategoriesLbl);
        addcategoriesDialog.add(addcategoriesTxt);
        addcategoriesDialog.add(adddescLbl);
        addcategoriesDialog.add(adddescScrollPane);
        addcategoriesDialog.add(saveCategoriesBtn);
 
        add(searchAuthorLbl);
        add(searchAuthorTxt);

        add(AuthorTableScrollPane);
        add(displayAuthorSelectLbl);
        
        add(addAuthorBtn);

        addAuthorDialog.add(authorImagePanel);
        authorImagePanel.add(authorImageLbl);
      
        addAuthorDialog.add(authorNameLbl);
        addAuthorDialog.add(authoraddressLbl);
        addAuthorDialog.add(authorContactLbl);
        
        addAuthorDialog.add(authorNameTxt);
        addAuthorDialog.add(authoraddressTxt);
        addAuthorDialog.add(authorContactTxt);
        
        addAuthorDialog.add(SaveAuthorBtn);
        
        add(btnPanel);
    }

    private JPanel btnPanel = new JPanel(new GridLayout(1, 3));
    
    private JButton saveBtn = new JButton("Save");
    private JButton updateBtn = new JButton("Update");
    private JButton resetBtn = new JButton("Reset");
    private JButton cancelBtn = new JButton("Cancel");

    private JPanel bookImagePanel = new JPanel(new GridLayout(1, 1));
    public JLabel bookImageLbl = new JLabel("Photo");
    
    private JLabel booktitleLbl = new JLabel("BookTitle:");
    public JTextField booktitleTxt = new JTextField();

    private JPanel booktitlePanel = new JPanel(new GridLayout(1, 1));
    public JLabel BookTitleLabel = new JLabel("Please Fill The Book Information:");

    private JLabel booktypeLbl = new JLabel("BookType:");
    public JComboBox booktypeBox = new JComboBox();

    private JLabel copiesLbl  = new JLabel("Copies");
    public JTextField copiesTxt = new JTextField();

    private JLabel editionLbl = new JLabel("Edition:");
    public JTextField editionTxt = new JTextField();
    
    private JLabel descLbl = new JLabel("Description:");
    public JTextArea descTxt = new JTextArea();
    private JScrollPane descScrollPane = new JScrollPane();

    private JButton addCategoriesBtn = new JButton("+");
    private JDialog addcategoriesDialog = new JDialog();

    private JLabel addcategoriesLbl = new JLabel("Categories:");
    private JTextField addcategoriesTxt = new JTextField();
    
    private JLabel adddescLbl = new JLabel("Description:");
    private JTextArea adddescTxt = new JTextArea();
    private JScrollPane adddescScrollPane = new JScrollPane();
    
    private JButton saveCategoriesBtn = new JButton("Add Categories");
    
    private JLabel searchAuthorLbl = new JLabel("Search Author:");
    public JTextField searchAuthorTxt = new JTextField();
    
    private JTable AuthorTable = new JTable();
    private JScrollPane AuthorTableScrollPane = new JScrollPane();

    public JLabel displayAuthorSelectLbl = new JLabel();

    private JButton addAuthorBtn = new JButton("+");
    private JDialog addAuthorDialog = new JDialog();
    
    private JPanel authorImagePanel = new JPanel(new GridLayout(1, 1));
    private JLabel authorImageLbl = new JLabel("Photo:");
    
    private JLabel authorNameLbl = new JLabel("AuthorName: ");
    private JTextField authorNameTxt = new JTextField();
    
    private JLabel authorContactLbl = new JLabel("Contact:");
    private JTextField authorContactTxt = new JTextField();
 
    private JLabel authoraddressLbl = new JLabel("Address:");
    private JTextField authoraddressTxt = new JTextField();

    private JButton SaveAuthorBtn = new JButton("Add");

    public void SetBtnName(String btnName){

        if (saveBtn.getText().equals(btnName)) {
            
            btnPanel.add(saveBtn);
            btnPanel.add(resetBtn);
            btnPanel.add(cancelBtn);
            System.out.println("Your If Candition has been Successfully!");
            
        } else if (updateBtn.getText().equals(btnName)) {
            
            btnPanel.add(updateBtn);
            btnPanel.add(resetBtn);
            btnPanel.add(cancelBtn);
            System.out.println("Your else If Candition has been Successfully!");

        }
    }
    
    private void InsertBook(String BookTitleStr, String bookTypeStr, int copies, String editionStr ,String descritpionStr, File bookfile, int authorRID){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String bookquery = "INSERT INTO books(BookTitle,BookType,copies,Edition,Description,SubmitDate,RID,BookImage)VALUES(?,?,?,?,?,?,?,?)";
       
                PreparedStatement bookpreparedStatement = MyConn.prepareStatement(bookquery);
                bookpreparedStatement.setString(1, BookTitleStr);
                bookpreparedStatement.setString(2, bookTypeStr);
                bookpreparedStatement.setInt(3, copies);
                bookpreparedStatement.setString(4, editionStr);
                bookpreparedStatement.setString(5, descritpionStr);
                bookpreparedStatement.setString(6, Defaults.currentDate());
                bookpreparedStatement.setInt(7, authorRID);

                if (bookfile != null) {
                    
                    InputStream bookinputStream = new FileInputStream(bookfile);
                    
                    if (bookinputStream != null) {
                       
                        bookpreparedStatement.setBlob(8, bookinputStream);
                        JOptionPane.showMessageDialog(null, "Your Images has been processed into binary!");
                    }
                }

                bookpreparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Your Books has been Inserted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
    }
    
    public void UpdateBook(int BID, File file, int RID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {

                String updatequery = "UPDATE books SET "
                        
                        + " BookTitle = ?, "
                        + " BookType = ?, "
                        + " Copies = ?, "
                        + " Edition = ?, "
                        + " Description = ?, "
                        + " RID = ?, "
                        + " BookImage = ? WHERE BID = " +BID;
                
                PreparedStatement preparedStatement = myConn.prepareStatement(updatequery);
                preparedStatement.setString(1, booktitleTxt.getText());
                preparedStatement.setString(2, booktypeBox.getSelectedItem().toString());
                preparedStatement.setString(3, copiesTxt.getText());
                preparedStatement.setString(4, editionTxt.getText());
                preparedStatement.setString(5, descTxt.getText());
                preparedStatement.setInt(6, RID);
             
                if(file != null){
                    InputStream inputStream = new FileInputStream(file);

                    if (inputStream != null) {
                        preparedStatement.setBlob(7, inputStream);
                        JOptionPane.showMessageDialog(null, "Your data has been processed into binary!");
                    }
                }
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your Record has been Updated Successfully!");
                
                AddBookPanel.displayBookImageLbl.setIcon(bookImageLbl.getIcon());
                AddBookPanel.displaybookTitleLbl.setText(booktitleTxt.getText());
                AddBookPanel.displaybookTypeLbl.setText(booktypeBox.getSelectedItem().toString());
                AddBookPanel.displayCopiesLbl.setText(copiesTxt.getText());
                AddBookPanel.displayEditionLbl.setText(editionTxt.getText());
                AddBookPanel.displayAuthorNameLbl.setText(authorName);
                AddBookPanel.displayContactLbl.setText(authorContact);
                AddBookPanel.displayAddressLbl.setText(authorAddress);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    }
    
    private DefaultComboBoxModel GetCategoriesModel(JComboBox comboBox){
        DefaultComboBoxModel model = (DefaultComboBoxModel)comboBox.getModel();
        model.removeAllElements();
        model.addElement("-- Select Type --");
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "SELECT CategoryName FROM categories;";
                
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object CategoryName = resultSet.getString("CategoryName");
                        model.addElement(CategoryName);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Erorr: "+e.getMessage());
        }
        
        return model;
    }
    
    public static void addCategory(String categoryNameStr, String descritpionStr){
        try {
            Connection MyConn = MySQLConnection.getConnection();
            try {
                String query = "INSERT INTO Categories(CategoryName,Description,SubmitDate,RID)VALUES(?,?,?,?)";
                
                PreparedStatement preparedStatement = MyConn.prepareStatement(query);
                preparedStatement.setString(1, categoryNameStr);
                preparedStatement.setString(2, descritpionStr);
                preparedStatement.setString(3, Defaults.currentDate());
                preparedStatement.setInt(4, Defaults.generateRalationID());
                
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your Catogory has been Added Successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: "+e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: "+e.getMessage());
        }
    }
    
    public static void InsertAuthorRecord(String AuthorName, String addressStr, String contactStr, File file){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {

                String query = "INSERT INTO authors (AuthorName,Address,Contact,SubmitDate,RID,AuthorImage)VALUES(?,?,?,?,?,?);";
                
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setString(1, AuthorName);
                preparedStatement.setString(2, addressStr);
                preparedStatement.setString(3, contactStr);
                preparedStatement.setString(4, Defaults.currentDate());
                preparedStatement.setInt(5, Defaults.generateRalationID());
                
                if (file != null) {
                    InputStream inputStream = new FileInputStream(file);
                    if (inputStream != null ) {
                        preparedStatement.setBlob(6, inputStream);
                        JOptionPane.showMessageDialog(null, "Your data has been processed into binary!");
                    }
                }
                preparedStatement.executeUpdate();
       
                JOptionPane.showMessageDialog(null, "Your author has been inserted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
        }
    }
    public int GetAuthorRelationID(int AID, String AuthorName, String Address, String contact){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                
                String query = "SELECT RID FROM authors WHERE AID = ? "
                    + " AND "
                    + " AuthorName = ?"
                    + " AND "
                    + " Address = ?"
                    + " AND "
                    + " Contact = ?;";
               
                PreparedStatement preparedStatement = myConn.prepareStatement(query);
                preparedStatement.setInt(1, AID);
                preparedStatement.setString(2, AuthorName);
                preparedStatement.setString(3, Address);
                preparedStatement.setString(4, contact);
    
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        return resultSet.getInt("RID");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error: " +e.getMessage());
            return 0;
        }
        return 0;
    }
    
    public static DefaultTableModel GetAuthorsModel(JTable table){
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
    
    public static DefaultTableModel searchAuthorModel(String AuthorName, JTable table){
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
package BookSubPanels;

import Connectivity.NewMySQLConnection;
import DefaultRenderer.CusTomTableRenderer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import AdminPanel.AddBookPanel;
import AdminPanel.BookDialog;
import Connectivity.MySQLConnection;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Statement;
import javax.swing.JTextArea;
public class CategoriesPanel extends JPanel{
    int posX;
    int posY;
    int width;
    int height;
    
    public CategoriesPanel(){}
    public CategoriesPanel(int posX, int posY, int width, int height){
        
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        
    }
    public void drawPanel(){
        inttiComponends();
    }
    
    int CategoryID = 0;
    String CatNameStr = "";
    
    public void inttiComponends(){
        
        setBounds(posX, posY, width, height);
        setLayout(null);

        titleLbl.setBounds(10, 10, 200, 40);
        titleLbl.setFont(new Font("", 0, 20));
        
        AddCategoriesBtn.setBounds(120, 15, 200, 30);
        AddCategoriesBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
           
                addCategoriesDialog.setTitle("Add New Categories:");
                addCategoriesDialog.setSize(400, 300);
                addCategoriesDialog.setLocationRelativeTo(null);
                addCategoriesDialog.setLayout(null);
                addCategoriesDialog.setVisible(true);
                
                categoriesNameLbl.setBounds(50, 20, 200, 30);
                categoriesNameTxt.setBounds(150, 20, 200, 30);
                
                descriptionLbl.setBounds(50, 70, 200, 30);
                
                descriptionScrollPane.setBounds(150, 70, 200, 120);
                descriptionScrollPane.setViewportView(descriptionTxt);
                
                saveCategoryBtn.setBounds(0, 220, addCategoriesDialog.getWidth() - 15, 40);
                saveCategoryBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                
                        if (categoriesNameTxt.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please Write Category Name First!");
                        }else {
                            BookDialog.addCategory(categoriesNameTxt.getText(), descriptionTxt.getText());
                            categoriesNameTxt.setText("");
                            descriptionTxt.setText("");
                            GetCategoriesModel(categoriesTable);
                            addCategoriesDialog.dispose();
                        }
                    }
                });
            }
        });
        
        SearchLbl.setBounds(660, 60, 200, 30);
        SearchTxt.setBounds(720, 60, 250, 30);
        SearchTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
           
                categoriesTable.setModel(SearchCategoriesModel(SearchTxt.getText(), categoriesTable));
            }
        });
        
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                },
                new Object[]{"#ID","CategoryName","Description","SubmitDate"}
        );
        
        categoriesTable.setDefaultRenderer(Object.class, new CusTomTableRenderer());
        CategoriesTableScrollPane.setBounds(20, 120, 980, 440);
        CategoriesTableScrollPane.setViewportView(categoriesTable);
        categoriesTable.setModel(model);
        categoriesTable.setModel(GetCategoriesModel(categoriesTable));
        categoriesTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int RowID = categoriesTable.getSelectedRow();
           
                editDialog.setTitle("Update Category" + categoriesTable.getValueAt(RowID, 1).toString() +" :");
                editDialog.setSize(450, 350);
                editDialog.setLayout(null);
                editDialog.setLocationRelativeTo(null);
                editDialog.setVisible(true);
        
                CategoryID = Integer.parseInt(categoriesTable.getValueAt(RowID, 0).toString());

                updateCatTxt.setText(categoriesTable.getValueAt(RowID, 1).toString());
                updateDesTxt.setText(categoriesTable.getValueAt(RowID, 3).toString());
                
                updateCatLbl.setBounds(100, 50, 200, 30);
                updateCatTxt.setBounds(200, 50, 200, 30);
                
                updateDesLbl.setBounds(100, 100, 200, 30);
                updateDesTxt.setBounds(200, 100, 200, 120   );
                
                btnPanel.setBounds(0, 270, editDialog.getWidth() - 15, 40);
                
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
        
        deleteBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are you sure delete it?", "Delete Category:", JOptionPane.YES_NO_CANCEL_OPTION);
                
                if (result == JOptionPane.YES_OPTION) {
                    DeleteCategory(CategoryID);
                    
                    updateCatTxt.setText("");
                    updateDesTxt.setText("");

                    GetCategoriesModel(categoriesTable);
                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Delete Canceling Category!");
                }
            }
        });
        
        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                updateCategory(CategoryID);
                
                updateCatTxt.setText("");
                updateDesTxt.setText("");
                
                GetCategoriesModel(categoriesTable);
                editDialog.dispose();
                
                
            }
        });
        
        editDialog.add(updateCatLbl);
        editDialog.add(updateCatTxt);
        
        editDialog.add(updateDesLbl);
        editDialog.add(updateDesTxt);
       
        editDialog.add(btnPanel);
        btnPanel.add(deleteBtn);
        btnPanel.add(updateBtn);
        
        addCategoriesDialog.add(categoriesNameLbl);
        addCategoriesDialog.add(categoriesNameTxt);
       
        addCategoriesDialog.add(descriptionLbl);
        addCategoriesDialog.add(descriptionScrollPane);
        
        addCategoriesDialog.add(saveCategoryBtn);
        
        add(titleLbl);
        add(AddCategoriesBtn);
        add(CategoriesTableScrollPane);
        add(SearchTxt);
        add(SearchLbl);
        
    }
    
    private JLabel titleLbl = new JLabel("Categories:");

    private JLabel SearchLbl = new JLabel("Search:");
    private JTextField SearchTxt = new JTextField();
    
    private JButton AddCategoriesBtn = new JButton("Add Categories");
    private JDialog addCategoriesDialog = new JDialog();
    
    private JLabel categoriesNameLbl = new JLabel("Categories:");
    private JTextField categoriesNameTxt = new JTextField();
    
    private JLabel descriptionLbl = new JLabel("Description:");
    private JTextArea descriptionTxt = new JTextArea();
    private JScrollPane descriptionScrollPane = new JScrollPane();
    
    private JButton saveCategoryBtn = new JButton("Add");
    private JScrollPane CategoriesTableScrollPane = new JScrollPane();
    private JTable categoriesTable = new JTable();
        
    private JDialog editDialog = new JDialog();
    
    private JLabel updateCatLbl = new JLabel("Category:");
    private JTextField updateCatTxt = new JTextField();
    
    private JLabel updateDesLbl = new JLabel("Description:");
    private JTextArea updateDesTxt = new JTextArea();
    
    private JPanel btnPanel = new JPanel(new GridLayout(1, 1));
    private JButton updateBtn = new JButton("Update");
    private JButton deleteBtn = new JButton("Delete");
    
    private void DeleteCategory(int CID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String deletequery = "DELETE FROM categories WHERE CID = " + CID + ";";

                Statement statement = myConn.createStatement();
                statement.executeUpdate(deletequery);
                
                JOptionPane.showMessageDialog(null, "Your Category has been deleted Updated Successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connectoin Error: " +e.getMessage());
        }
    }
    
    private void updateCategory(int CID){
        try {
            Connection myConn = MySQLConnection.getConnection();
            try {
                String updateQuery = "UPDATE categories SET "
                        + " CategoryName = '" + updateCatTxt.getText() + "',"
                        + " Description = '" + updateDesTxt.getText() + "'"
                        + " WHERE CID = "+CID;
                
                Statement statement = myConn.createStatement();
                statement.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Your Category has been updated successfully!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Error: " +e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connectoin Error: " +e.getMessage());
        }
    }
    
    private DefaultTableModel GetCategoriesModel(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        try {
            Connection Myconn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM categories";
                
                PreparedStatement preparedStatement = Myconn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object categoriesObject[] = {
                            resultSet.getInt("CID"),
                            resultSet.getString("CategoryName"),
                            resultSet.getDate("SubmitDate"),
                            resultSet.getString("Description")
                                
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
    
    private DefaultTableModel SearchCategoriesModel(String CategoryName, JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        
        try {
            Connection Myconn = MySQLConnection.getConnection();
            try {
                String query = "SELECT * FROM categories WHERE CategoryName LIKE ('%" + CategoryName + "%')";
                
                PreparedStatement preparedStatement = Myconn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {                        
                        
                        Object categoriesObject[] = {
                            resultSet.getInt("CID"),
                            resultSet.getString("CategoryName"),
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
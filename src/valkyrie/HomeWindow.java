package valkyrie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.UIManager;

public class HomeWindow {

    private final ImageIcon icon, poster;

    private final JFrame frameHome = new JFrame("Valkyrie");

    private final JMenuBar menuBar;
    private final JMenu menuAbout, menuContact, menuHelp;

    private final JPanel topPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();

    private final JButton btnSearch, btnAdd, btnViewProfile, btnDelete, btnRefresh;

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrDarkRed = new Color(128, 0, 0);
    private final Color clrBloodRed = new Color(95, 2, 31);
    private final Color clrBlack = new Color(44, 47, 51);
    private final Color clrYellow = new Color(218,165,32);
    private final Color clrWhite = new Color(192, 192, 192);
    private final Color clrGrey = new Color(119, 136, 153);
    private final Color clrBlue = new Color(0, 156, 255);
    private final Color clrGreen = new Color(40, 155, 15);

    private final String[] column = {"NO", "ID", "First Name", "Last Name", "Gender", "Age", "Weight", "Blood Type", "Mobile No", "Landline No", "E-Mail", "Address"};

    private final JTable tblData;
    private final DefaultTableModel tblModel;

    public HomeWindow() {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/home_icon.png"));
        frameHome.setIconImage(icon.getImage());                                //setting window icon

        //setting up the frame
        frameHome.setSize(1000, 600);
        frameHome.getContentPane().setLayout(new BorderLayout());                                //frame layout: Border Layout
        frameHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);         //setting default close operation
        frameHome.setLocationRelativeTo(null);                                  //to center the window

        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(128, 0, 0));
        menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        menuBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frameHome.setJMenuBar(menuBar);

        menuAbout = new JMenu("About");
        menuAbout.setForeground(Color.WHITE);
        menuBar.add(menuAbout);

        menuContact = new JMenu("Contact Us");
        menuContact.setForeground(Color.WHITE);
        menuBar.add(menuContact);

        menuHelp = new JMenu("Help");
        menuHelp.setForeground(Color.WHITE);
        menuBar.add(menuHelp);
        
        menuAbout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new About();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        menuContact.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new Contact();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        menuHelp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new Help();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        frameHome.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frameHome,
                        "Are you sure you want to exit?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //setting up the topPanel
        /* divided topPanel horizontally into two part
        left part will contain an image as poster
        right panel will show blood type amount
         */
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1000, 180));                    //setting panel size
        topPanel.setBackground(clrBlack);                                         //background color: Black

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();

        //left panel work
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setBackground(clrBlack);                                        //background color: Black

        poster = new ImageIcon(getClass().getResource("images/poster.png"));           //create imageIcon for poster
        JLabel lblPoster = new JLabel();
        lblPoster.setIcon(poster);                                              //set image for poster

        panelLeft.add(lblPoster, BorderLayout.PAGE_START);
        //left panel work done

        //right panel work
        panelRight.setBorder(new EmptyBorder(50, 50, 50, 50));                  //setting panel border
        panelRight.setPreferredSize(new Dimension(350, 180));                   //setting panel size
        panelRight.setBackground(clrBlack);
        //right panel work done

        topPanel.add(panelLeft, BorderLayout.WEST);                             //add panelLeft to topPanel
        topPanel.add(panelRight, BorderLayout.CENTER);
        panelRight.setLayout(null);
        
        JEditorPane dtrpnOPos = new JEditorPane();
        dtrpnOPos.setEditable(false);
        dtrpnOPos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnOPos.setBackground(clrGrey);
        dtrpnOPos.setForeground(clrBloodRed);
        dtrpnOPos.setBounds(70, 11, 80, 75);
        dtrpnOPos.setText("O+" + "\n" + getBlood("O+"));
        dtrpnOPos.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnOPos);
        
        JEditorPane dtrpnONeg = new JEditorPane();
        dtrpnONeg.setEditable(false);
        dtrpnONeg.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnONeg.setBackground(clrGrey);
        dtrpnONeg.setForeground(clrBloodRed);
        dtrpnONeg.setBounds(190, 11, 80, 75);
        dtrpnONeg.setText("O-" + "\n" + getBlood("O-"));
        dtrpnONeg.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnONeg);
        
        JEditorPane dtrpnAPos = new JEditorPane();
        dtrpnAPos.setEditable(false);
        dtrpnAPos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnAPos.setBackground(clrGrey);
        dtrpnAPos.setForeground(clrBloodRed);
        dtrpnAPos.setBounds(313, 11, 80, 75);
        dtrpnAPos.setText("A+" + "\n" + getBlood("A+"));
        dtrpnAPos.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnAPos);
        
        JEditorPane dtrpnANeg = new JEditorPane();
        dtrpnANeg.setEditable(false);
        dtrpnANeg.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnANeg.setBackground(clrGrey);
        dtrpnANeg.setForeground(clrBloodRed);
        dtrpnANeg.setBounds(432, 11, 80, 75);
        dtrpnANeg.setText("A-" + "\n" + getBlood("A-"));
        dtrpnANeg.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnANeg);
        
        JEditorPane dtrpnBPos = new JEditorPane();
        dtrpnBPos.setEditable(false);
        dtrpnBPos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnBPos.setBackground(clrGrey);
        dtrpnBPos.setForeground(clrBloodRed);
        dtrpnBPos.setBounds(70, 97, 80, 75);
        dtrpnBPos.setText("B+" + "\n" + getBlood("B+"));
        dtrpnBPos.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnBPos);
        
        JEditorPane dtrpnBNeg = new JEditorPane();
        dtrpnBNeg.setEditable(false);
        dtrpnBNeg.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnBNeg.setBackground(clrGrey);
        dtrpnBNeg.setForeground(clrBloodRed);
        dtrpnBNeg.setBounds(190, 97, 80, 75);
        dtrpnBNeg.setText("B-" + "\n" + getBlood("B-"));
        dtrpnBNeg.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnBNeg);
        
        JEditorPane dtrpnABPos = new JEditorPane();
        dtrpnABPos.setEditable(false);
        dtrpnABPos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnABPos.setBackground(clrGrey);
        dtrpnABPos.setForeground(clrBloodRed);
        dtrpnABPos.setBounds(313, 97, 80, 75);
        dtrpnABPos.setText("AB+" + "\n" + getBlood("AB+"));
        dtrpnABPos.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnABPos);
        
        JEditorPane dtrpnABNeg = new JEditorPane();
        dtrpnABNeg.setEditable(false);
        dtrpnABNeg.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
        dtrpnABNeg.setBackground(clrGrey);
        dtrpnABNeg.setForeground(clrBloodRed);
        dtrpnABNeg.setBounds(432, 97, 80, 75);
        dtrpnABNeg.setText("AB-" + "\n" + getBlood("AB-"));
        dtrpnABNeg.setMargin(new Insets(5, 25, 20, 20));
        panelRight.add(dtrpnABNeg);
        //topPanel work done

        //setting up the midPanel
        /*divide mid panel vertically into three part
        upper part will contain search and sort button
        middle part will contain donor's info
        and lower part will contain some buttons
         */
        midPanel.setBackground(clrDarkRed);                                    //background color: Dark Red
        midPanel.setLayout(new BorderLayout());

        JPanel panelUpper = new JPanel();
        JPanel panelLower = new JPanel();

        //upper panel work
        panelUpper.setLayout(null);
        panelUpper.setPreferredSize(new Dimension(1000, 45));                   //setting panel size        
        panelUpper.setBackground(clrDarkRed);                                  //background color: Dark Red

        JLabel lblSearch = new JLabel("Search By: ");
        lblSearch.setFont(new Font("Sans", Font.BOLD, 14));                     //font: Sans, Style: Bold, Size: 20
        lblSearch.setForeground(Color.WHITE);                                   //font color: black
        lblSearch.setBounds(20, 15, 100, 20);                                   //setting location and size for search label

        String search[] = {"Blood Type", "First Name", "Last Name", "Address"};
        JComboBox cbSearch = new JComboBox(search);                             //combobox for search options
        cbSearch.setBounds(120, 13, 150, 25);                                   //setting location and size for search combobox

        JTextField tfSearch = new JTextField();
        tfSearch.setFont(new Font("Sans", Font.PLAIN, 14));                     //setting font for search TextField
        tfSearch.setBounds(280, 13, 200, 25);                                   //setting location and size of TextField

        btnSearch = new JButton("Search");
        btnSearch.setBounds(490, 13, 100, 25);                                  //setting location and size of search button
        btnSearch.setBackground(clrRed);                               			//button background color: Red
        btnSearch.setForeground(Color.WHITE);                                   //button text color: White
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int option = cbSearch.getSelectedIndex();
                String value = tfSearch.getText();

                switch (option) {
                    case 0:
                        value = value.trim();
                        value = "Blood_Type LIKE '%" + value + "%'";
                        break;
                    case 1:
                        value = value.trim();
                        value = "First_Name LIKE '%" + value + "%'";
                        break;
                    case 2:
                        value = value.trim();
                        value = "Last_Name LIKE '%" + value + "%'";
                        break;
                    case 3:
                        value = value.trim();
                        value = "Address LIKE '%" + value + "%'";
                        break;
                }
                showSearchedData(value);
            }
        });

        panelUpper.add(lblSearch);
        panelUpper.add(cbSearch);
        panelUpper.add(tfSearch);
        panelUpper.add(btnSearch);
        //upper panel work done

        //lower panel work
        panelLower.setLayout(new BorderLayout());
        panelLower.setBackground(clrDarkRed);                                  //background color: Dark Red
        panelLower.setBorder(new EmptyBorder(5, 15, 5, 15));                    //setting panel border

        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(column);

        tblData = new JTable();                                                 //created a jTable
        tblData.setModel(tblModel);
        tblData.setBackground(clrWhite);                                       //table background color: Yellow
        tblData.setRowHeight(30);                                               //set row height
        tblData.setFont(new Font("Sans", Font.PLAIN, 14));                      //font: Sans Style: Plain Size: 14
        tblData.setDefaultEditor(Object.class, null);                           //set cell non-editable
        tblData.setAutoCreateRowSorter(true);

        //setting column width of tblData
        tblData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblData.getColumnModel().getColumn(0).setMinWidth(50);
        tblData.getColumnModel().getColumn(0).setMaxWidth(50);
        tblData.getColumnModel().getColumn(1).setMinWidth(60);
        tblData.getColumnModel().getColumn(1).setMaxWidth(60);
        tblData.getColumnModel().getColumn(2).setMinWidth(80);
        tblData.getColumnModel().getColumn(3).setMinWidth(80);
        tblData.getColumnModel().getColumn(3).setMaxWidth(80);
        tblData.getColumnModel().getColumn(4).setMinWidth(80);
        tblData.getColumnModel().getColumn(4).setMaxWidth(80);
        tblData.getColumnModel().getColumn(5).setMinWidth(80);
        tblData.getColumnModel().getColumn(5).setMaxWidth(80);
        tblData.getColumnModel().getColumn(6).setMinWidth(80);
        tblData.getColumnModel().getColumn(6).setMaxWidth(80);
        tblData.getColumnModel().getColumn(7).setMinWidth(80);
        tblData.getColumnModel().getColumn(7).setMaxWidth(80);
        tblData.getColumnModel().getColumn(8).setMinWidth(100);
        tblData.getColumnModel().getColumn(9).setMinWidth(100);
        tblData.getColumnModel().getColumn(10).setMinWidth(180);
        tblData.getColumnModel().getColumn(11).setMinWidth(180);

        //setting table data in center alignment
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblData.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane sp = new JScrollPane(tblData);                              //added table into a scroll pane

        panelLower.add(sp, BorderLayout.CENTER);
        //lower panel work done

        //here we add an extra panel to put buttons into bottom right corner
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));                   //anchoring buttons into lower right side
        panelBtn.setBackground(clrDarkRed);                                    //background color: Dark Red
        panelBtn.setBorder(new EmptyBorder(0, 0, 5, 30));                       //setting panel border

        btnAdd = new JButton("Add");
        btnAdd.setBackground(clrGreen);                                         //button background color: Green
        btnAdd.setForeground(Color.WHITE);                                      //button text color: White
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));                       //set button cursor

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewDonor();
            }

        });

        btnViewProfile = new JButton("Profile");
        btnViewProfile.setBackground(clrBlue);                                  //button background color: Blue
        btnViewProfile.setForeground(Color.white);                              //button text color: White
        btnViewProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));               //set button cursor
        btnViewProfile.setVisible(false);                                       //set button invisible

        btnViewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = tblData.getSelectedRow();                             //getting view index of selected row
                row = tblData.convertRowIndexToModel(row);                      //getting model index of selected row
                int id = Integer.parseInt(tblData.getModel().getValueAt(row, 1).toString());
                System.out.println("ID: " + id + " Selected");
                
                new ViewProfile(id);
            }
        });

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(clrRed);                                        //button background color: Red
        btnDelete.setForeground(Color.white);                                   //button text color: White
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor
        btnDelete.setVisible(false);                                            //set button invisible

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (JOptionPane.showConfirmDialog(frameHome,
                        "Are you sure you want to delete?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    int row = tblData.getSelectedRow();                         //getting view index of selected row
                    row = tblData.convertRowIndexToModel(row);                  //getting model index of selected row
                    int id = Integer.parseInt(tblData.getModel().getValueAt(row, 1).toString());
                    System.out.println("ID: " + id + " Selected");

                    Database db = new Database();
                    db.delete(id);
                    showTableData();
                }
            }
        });

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(clrYellow);                                    //button background color: Black
        btnRefresh.setForeground(Color.WHITE);                                  //button text color: White
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));                   //set button cursor

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showTableData();
            }
        });

        tblData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int[] indexs = tblData.getSelectedRows();

                //set btnViewProfile & btnDelete visible if exactly 1 row is selected
                if (indexs.length == 1) {
                    btnViewProfile.setVisible(true);
                    btnDelete.setVisible(true);
                } else {
                    btnViewProfile.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });

        panelBtn.add(btnViewProfile);
        panelBtn.add(btnDelete);
        panelBtn.add(btnRefresh);
        panelBtn.add(btnAdd);
        //button panel work done

        midPanel.add(panelUpper, BorderLayout.NORTH);
        midPanel.add(panelLower, BorderLayout.CENTER);
        midPanel.add(panelBtn, BorderLayout.SOUTH);
        //midPanel work done

        //setting up the bottomPanel
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));                    //setting border for bottom panel
        bottomPanel.setPreferredSize(new Dimension(1000, 50));                  //setting panel size
        bottomPanel.setBackground(clrBlack);                                      //background color: Black

        JLabel lblFooter = new JLabel();

        String footer = "Valkyrie - Developed by Bachar Sabra.";
        lblFooter.setText(footer);                                              //set text of footnote
        lblFooter.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 18));                  //font: Serif, Style: Italic, Size: 18
        lblFooter.setForeground(clrBlue);                                   	//foot color: Blue

        bottomPanel.add(lblFooter);                                             //add footnote to panel
        //bottomPanel work done                

        //adding panels into frame
        frameHome.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frameHome.getContentPane().add(midPanel, BorderLayout.CENTER);
        frameHome.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);

        //make frame visible
        frameHome.setVisible(true);

        //show table data
        showTableData();
    }

    //this method retrieves data from database and shows them in table
    public void showTableData() {
        ArrayList<Donor> donor = new ArrayList();
        try {
            Database db = new Database();
            donor = db.getDataForTable();

            int slNo = 1;
            tblModel.setRowCount(0);
            for (int i = 0; i < donor.size(); ++i) {
                Vector newRow = new Vector();
                newRow.addElement(slNo);
                newRow.addElement(donor.get(i).getID());
                newRow.addElement(donor.get(i).getFirstName());
                newRow.addElement(donor.get(i).getLastName());
                newRow.addElement(donor.get(i).getGender());
                newRow.addElement(donor.get(i).getAge());
                newRow.addElement(donor.get(i).getWeight());
                newRow.addElement(donor.get(i).getBloodType());
                newRow.addElement(donor.get(i).getMobileNo());
                newRow.addElement(donor.get(i).getLandlineNo());
                newRow.addElement(donor.get(i).getEMail());
                newRow.addElement(donor.get(i).getAddress());
                tblModel.addRow(newRow);

                ++slNo;
            }
            if (slNo == 1) {
                JOptionPane.showMessageDialog(frameHome, "No Data Found!", "NO DATA!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException ex) {
            System.out.println("HomeWindow.showTableData(): " + ex);
            ex.printStackTrace();
        }
    }

    //this method show search result on jtable
    public void showSearchedData(String value) {
        ArrayList<Donor> donor = new ArrayList();
        try {
            Database db = new Database();
            donor = db.search(value);

            int slNo = 1;
            tblModel.setRowCount(0);
            for (int i = 0; i < donor.size(); ++i) {
                Vector newRow = new Vector();
                newRow.addElement(slNo);
                newRow.addElement(donor.get(i).getID());
                newRow.addElement(donor.get(i).getFirstName());
                newRow.addElement(donor.get(i).getLastName());
                newRow.addElement(donor.get(i).getGender());
                newRow.addElement(donor.get(i).getAge());
                newRow.addElement(donor.get(i).getWeight());
                newRow.addElement(donor.get(i).getBloodType());
                newRow.addElement(donor.get(i).getMobileNo());
                newRow.addElement(donor.get(i).getLandlineNo());
                newRow.addElement(donor.get(i).getEMail());
                newRow.addElement(donor.get(i).getAddress());
                tblModel.addRow(newRow);

                ++slNo;
            }
            if (slNo == 1) {
                JOptionPane.showMessageDialog(frameHome, "No Data Found!", "NO DATA!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException ex) {
            System.out.println("HomeWindow.showTableData(): " + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        new HomeWindow();
    }
    
    private String getBlood(String bloodType) {
        Database db = new Database();
        String count = "";
        try {
        	count = db.bloodCount(bloodType);

        } catch (HeadlessException ex) {
            System.out.println("bloodCount.getBlood(String bloodType): " + ex);
            ex.printStackTrace();
        }
		return count;
    }
}

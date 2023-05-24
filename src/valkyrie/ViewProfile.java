package valkyrie;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

public class ViewProfile {

    private final ImageIcon icon;

    private final JFrame frameViewProfile = new JFrame("View Profile");
    private final CardLayout cardLayout = new CardLayout();

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrBlue = new Color(0, 156, 255);
    private final Color clrBlack = new Color(44, 47, 51);
    private final Color clrGreen = new Color(40, 155, 15);

    private final Border margin = new EmptyBorder(10, 10, 10, 10);
    private final Border blueline = BorderFactory.createLineBorder(clrBlue);

    private final JPanel mainPanle = new JPanel();
    private final JPanel cardView = new JPanel();
    private final JPanel cardEdit = new JPanel();

    private final JPanel c1leftPanel = new JPanel();
    private final JPanel c2leftPanel = new JPanel();
    private final JPanel c1rightPanel = new JPanel();
    private final JPanel c2rightPanel = new JPanel();

    private JButton btnEdit, btnBack, btnCancel, btnUpdate, btnChoose;

    private JLabel c1lblFirstName, c1lblLastName, c1lblFatherName, c1lblGender, c1lblBloodType, c1lblAge, c1lblWeight, c1lblIDType, c1lblIDNo, c1lblLastDonation, c1lblMedical, c1lblMobile, c1lblLandline, c1lblEMail, c1lblAddress, c1lblImage;
    private JLabel lblFirstName, lblLastName, lblFatherName, lblGender, lblBloodType, lblAge, lblWeight, lblIDType, lblIDNo, lblStatement, lblLastDonation, lblMedical, lblMobile, lblLandline, lblEMail, lblAddress, lblImage;

    private JTextField c1tfFirstName, c1tfLastName, c1tfFatherName, c1tfGender, c1tfAge, c1tfWeight;
    private JTextField c1tfBloodType, c1tfIDType, c1tfIDNo, c1tfLastDonation;
    private JTextField c1tfMobile, c1tfLandline, c1tfEMail;
    private JTextArea c1taMedical, c1taAddress;

    private JTextField tfFirstName, tfLastName, tfFatherName, tfWeight, tfEMail ;
    private JTextArea taMedical, taAddress;
    private JComboBox cbGender, cbBloodType, cbIDType;
    private JSpinner spAge;
    private SpinnerNumberModel ages;
    private JCheckBox chbSttmnt;
    private JDateChooser dcLstDonation;
    private JFormattedTextField tfMobile, tfLandline, tfIDNo;

    private final String bloodTypes[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private final String genders[] = {"Male", "Female"};
    private final String idTypes[] = {"National ID", "Civil Status Extract", "Passport"};

    private String imagePath;
    private boolean imageChanged;

    private String firstName, lastName, fatherName, gender, bloodType, weight, idType, idNo, medical, mobile, landline, eMail, address;
    private InputStream isImage;
    private Date date;
    private int age, id;

    public ViewProfile(int id) {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/view_profile_icon.png"));
        frameViewProfile.setIconImage(icon.getImage());                         //set frame icon

        //setting up the frame
        frameViewProfile.setSize(600, 540);
        frameViewProfile.getContentPane().setLayout(new BorderLayout());                         //frame layout: Border Layout
        frameViewProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     //setting default close operation
        frameViewProfile.setResizable(false);                                   //disable resize button
        frameViewProfile.setLocationRelativeTo(null);                           //to center the window

        mainPanle.setLayout(cardLayout);
        cardView.setLayout(new BorderLayout());
        cardEdit.setLayout(new BorderLayout());

        this.id = id;

        getProfile(id);
        initCard1();
        initCard2();

        mainPanle.add(cardView, "view");
        mainPanle.add(cardEdit, "edit");

        frameViewProfile.getContentPane().add(mainPanle);

        //make frame visible
        frameViewProfile.setVisible(true);
    }

    private void initCard1() {
        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        c1leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));       //set border(outer border: margin, inner border: title)
        c1leftPanel.setBackground(clrBlack);                                   //background color: Black
        c1leftPanel.setPreferredSize(new Dimension(300, 540));                  //setting panel size
        c1leftPanel.setLayout(null);

        c1lblFirstName = new JLabel("First Name:");
        c1lblFirstName.setForeground(Color.WHITE);
        c1lblFirstName.setBounds(30, 30, 100, 20);
        c1leftPanel.add(c1lblFirstName);

        c1tfFirstName = new JTextField(firstName);
        c1tfFirstName.setEditable(false);
        c1tfFirstName.setBounds(30, 50, 240, 25);
        c1leftPanel.add(c1tfFirstName);

        c1lblLastName = new JLabel("Last Name:");
        c1lblLastName.setForeground(Color.WHITE);
        c1lblLastName.setBounds(30, 80, 150, 20);
        c1leftPanel.add(c1lblLastName);

        c1tfLastName = new JTextField(lastName);
        c1tfLastName.setEditable(false);
        c1tfLastName.setBounds(30, 100, 240, 25);
        c1leftPanel.add(c1tfLastName);

        c1lblFatherName = new JLabel("Father's Name:");
        c1lblFatherName.setForeground(Color.WHITE);
        c1lblFatherName.setBounds(30, 130, 150, 20);
        c1leftPanel.add(c1lblFatherName);

        c1tfFatherName = new JTextField(fatherName);
        c1tfFatherName.setEditable(false);
        c1tfFatherName.setBounds(30, 150, 240, 25);
        c1leftPanel.add(c1tfFatherName);

        c1lblGender = new JLabel("Gender:");
        c1lblGender.setForeground(Color.WHITE);
        c1lblGender.setBounds(30, 180, 80, 20);
        c1leftPanel.add(c1lblGender);

        c1tfGender = new JTextField(gender);
        c1tfGender.setEditable(false);
        c1tfGender.setBounds(30, 200, 80, 25);
        c1leftPanel.add(c1tfGender);

        c1lblAge = new JLabel("Age:");
        c1lblAge.setForeground(Color.WHITE);
        c1lblAge.setBounds(120, 180, 50, 20);
        c1leftPanel.add(c1lblAge);

        c1tfAge = new JTextField(String.valueOf(age));
        c1tfAge.setEditable(false);
        c1tfAge.setBounds(120, 200, 50, 25);
        c1leftPanel.add(c1tfAge);

        c1lblWeight = new JLabel("Weight (KG):");
        c1lblWeight.setForeground(Color.WHITE);
        c1lblWeight.setBounds(180, 180, 90, 20);
        c1leftPanel.add(c1lblWeight);

        c1tfWeight = new JTextField(weight);
        c1tfWeight.setEditable(false);
        c1tfWeight.setBounds(180, 200, 90, 25);
        c1leftPanel.add(c1tfWeight);

        c1lblBloodType = new JLabel("Blood Type:");
        c1lblBloodType.setForeground(Color.WHITE);
        c1lblBloodType.setBounds(30, 230, 100, 20);
        c1leftPanel.add(c1lblBloodType);

        c1tfBloodType = new JTextField(bloodType);
        c1tfBloodType.setEditable(false);
        c1tfBloodType.setBounds(30, 250, 100, 25);
        c1leftPanel.add(c1tfBloodType);

        c1lblIDType = new JLabel("Identification Type:");
        c1lblIDType.setForeground(Color.WHITE);
        c1lblIDType.setBounds(160, 230, 110, 20);
        c1leftPanel.add(c1lblIDType);

        c1tfIDType = new JTextField(idType);
        c1tfIDType.setEditable(false);
        c1tfIDType.setBounds(160, 250, 110, 25);
        c1leftPanel.add(c1tfIDType);

        c1lblIDNo = new JLabel("ID Number:");
        c1lblIDNo.setForeground(Color.WHITE);
        c1lblIDNo.setBounds(30, 280, 100, 20);
        c1leftPanel.add(c1lblIDNo);

        c1tfIDNo = new JTextField(idNo);
        c1tfIDNo.setEditable(false);
        c1tfIDNo.setBounds(30, 300, 240, 25);
        c1leftPanel.add(c1tfIDNo);

        c1lblLastDonation = new JLabel("Last Donation Date:");
        c1lblLastDonation.setForeground(Color.WHITE);
        c1lblLastDonation.setBounds(30, 330, 200, 20);
        c1leftPanel.add(c1lblLastDonation);

        c1tfLastDonation = new JTextField("");
        if (date != null) {
            c1tfLastDonation.setText(date.toString());
        }
        c1tfLastDonation.setEditable(false);
        c1tfLastDonation.setBounds(30, 350, 240, 25);
        c1leftPanel.add(c1tfLastDonation);

        c1lblMedical = new JLabel("Medical Conditions:");
        c1lblMedical.setForeground(Color.WHITE);
        c1lblMedical.setBounds(30, 380, 250, 20);
        c1leftPanel.add(c1lblMedical);

        c1taMedical = new JTextArea(medical);
        c1taMedical.setBounds(30, 419, 238, 150);
        c1taMedical.setEditable(false);
        c1taMedical.setLineWrap(true);

        JScrollPane scrlPaneDss = new JScrollPane();
        scrlPaneDss.setBounds(30, 400, 240, 80);
        scrlPaneDss.setViewportView(c1taMedical);
        c1leftPanel.add(scrlPaneDss);
        //left panel work done

        //setting up right panel
        c1rightPanel.setBackground(clrBlack);                                  //background color: Black
        c1rightPanel.setPreferredSize(new Dimension(300, 540));                 //setting panel size
        c1rightPanel.setLayout(new BorderLayout());

        //we divided right panel into three part vertically
        //upper panel will contain donor's picture
        //mid panel will contain contact info
        //and lower panel will contain buttons
        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //upper panel work
        TitledBorder ttlPicture = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlPicture.setTitle(" Donor's Picture ");                               //set title
        ttlPicture.setTitleColor(clrBlue);                                      //title color: Blue

        upperPanel.setBorder(new CompoundBorder(margin, ttlPicture));           //set border(outer border: margin, inner border: title)
        upperPanel.setBackground(clrBlack);                                    //background color: Black
        upperPanel.setPreferredSize(new Dimension(300, 275));                   //setting panel size
        upperPanel.setLayout(null);

        c1lblImage = new JLabel();
        c1lblImage.setBounds(25, 30, 250, 225);
        c1lblImage.setBorder(blueline);

        BufferedImage bf = null;
        Database db = new Database();
        isImage = db.getImage(id);
        try {
            bf = ImageIO.read(isImage);
        } catch (IOException ex) {
            System.out.println("ViewProfile.initCard1():");
            ex.printStackTrace();
        }
        c1lblImage.setIcon(resizeImage(bf));
        upperPanel.add(c1lblImage);
        //upper panel work done

        //mid panel work
        TitledBorder ttlContctInfo = BorderFactory.createTitledBorder(blueline);//create titled border

        ttlContctInfo.setTitle(" Contact Information ");                        //set title
        ttlContctInfo.setTitleColor(clrBlue);                                   //title color: Blue

        midPanel.setBorder(new CompoundBorder(
                new EmptyBorder(0, 10, 0, 10), ttlContctInfo));                 //set border(outer border: margin, inner border: title)
        midPanel.setBackground(clrBlack);                                      //background color: Black
        midPanel.setPreferredSize(new Dimension(300, 165));                     //setting panel size
        midPanel.setLayout(null);

        c1lblMobile = new JLabel("Mobile Number:");
        c1lblMobile.setForeground(Color.WHITE);
        c1lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(c1lblMobile);

        c1tfMobile = new JTextField(mobile);
        c1tfMobile.setEditable(false);
        c1tfMobile.setBounds(30, 40, 100, 25);
        midPanel.add(c1tfMobile);

        c1tfLandline = new JTextField(landline);
        c1tfLandline.setEditable(false);
        c1tfLandline.setBounds(165, 40, 100, 25);
        midPanel.add(c1tfLandline);

        c1lblLandline = new JLabel("Landline Number:");
        c1lblLandline.setForeground(Color.WHITE);
        c1lblLandline.setBounds(165, 20, 120, 20);
        midPanel.add(c1lblLandline);
        
        c1lblEMail = new JLabel("E-Mail:");
        c1lblEMail.setForeground(Color.WHITE);
        c1lblEMail.setBounds(30, 70, 120, 20);
        midPanel.add(c1lblEMail);

        c1tfEMail = new JTextField(eMail);
        c1tfEMail.setEditable(false);
        c1tfEMail.setBounds(30, 90, 235, 25);
        midPanel.add(c1tfEMail);

        c1lblAddress = new JLabel("Address:");
        c1lblAddress.setForeground(Color.WHITE);
        c1lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(c1lblAddress);

        c1taAddress = new JTextArea(address);
        c1taAddress.setEditable(false);
        c1taAddress.setLineWrap(true);

        JScrollPane scrlPaneAdrs = new JScrollPane();
        scrlPaneAdrs.setBounds(30, 140, 240, 35);
        scrlPaneAdrs.setViewportView(c1taAddress);

        midPanel.add(scrlPaneAdrs);
        //mid panel work done

        //lower panel work
        lowerPanel.setBackground(clrBlack);                                    //background color: Black
        lowerPanel.setPreferredSize(new Dimension(300, 50));                    //setting panel size
        lowerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        lowerPanel.setLayout(new BorderLayout());

        btnEdit = new JButton("Edit Profile");
        btnEdit.setBackground(clrBlue);                                         //button background color: Blue
        btnEdit.setForeground(Color.WHITE);                                     //button text color: White
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));                      //set button cursor

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(mainPanle, "edit");
            }
        });

        btnBack = new JButton("Back");
        btnBack.setBackground(clrRed);                                       //button background color: Red
        btnBack.setForeground(Color.WHITE);                                   //button text color: White
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameViewProfile.dispose();
            }

        });

        lowerPanel.add(btnEdit, BorderLayout.WEST);
        lowerPanel.add(btnBack, BorderLayout.EAST);
        //lower panel work done

        c1rightPanel.add(upperPanel, BorderLayout.NORTH);
        c1rightPanel.add(midPanel, BorderLayout.CENTER);
        c1rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //right panel work done

        cardView.add(c1leftPanel, BorderLayout.WEST);
        cardView.add(c1rightPanel, BorderLayout.EAST);
    }

    private void initCard2() {
        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        c2leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));       //set border(outer border: margin, inner border: title)
        c2leftPanel.setBackground(clrBlack);                                   //background color: Black
        c2leftPanel.setPreferredSize(new Dimension(300, 540));                  //setting panel size
        c2leftPanel.setLayout(null);

        lblFirstName = new JLabel("First Name:");
        lblFirstName.setForeground(Color.WHITE);
        c1lblFirstName.setForeground(Color.WHITE);
        lblFirstName.setBounds(30, 30, 100, 20);
        c2leftPanel.add(lblFirstName);

        tfFirstName = new JTextField(firstName);
        tfFirstName.setBounds(30, 50, 240, 25);
        c2leftPanel.add(tfFirstName);

        lblLastName = new JLabel("Last Name:");
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(30, 80, 150, 20);
        c2leftPanel.add(lblLastName);

        tfLastName = new JTextField(lastName);
        tfLastName.setBounds(30, 100, 240, 25);
        c2leftPanel.add(tfLastName);

        lblFatherName = new JLabel("Father's Name:");
        lblFatherName.setForeground(Color.WHITE);
        lblFatherName.setBounds(30, 130, 150, 20);
        c2leftPanel.add(lblFatherName);

        tfFatherName = new JTextField(fatherName);
        tfFatherName.setBounds(30, 150, 240, 25);
        c2leftPanel.add(tfFatherName);

        lblGender = new JLabel("Gender:");
        lblGender.setForeground(Color.WHITE);
        lblGender.setBounds(30, 180, 80, 20);
        c2leftPanel.add(lblGender);

        cbGender = new JComboBox(genders);
        cbGender.setBounds(30, 200, 80, 25);
        if (!gender.trim().equals("")) {
            cbGender.setSelectedItem(gender);
        }
        c2leftPanel.add(cbGender);

        lblAge = new JLabel("Age:");
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(120, 180, 50, 20);
        c2leftPanel.add(lblAge);

        ages = new SpinnerNumberModel(18, 18, 65, 1);
        spAge = new JSpinner(ages);
        spAge.setBounds(120, 200, 50, 25);
        if (!String.valueOf(age).trim().equals("")) {
            spAge.setValue(age);
        }
        c2leftPanel.add(spAge);

        lblWeight = new JLabel("Weight (KG):");
        lblWeight.setForeground(Color.WHITE);
        lblWeight.setBounds(180, 180, 90, 20);
        c2leftPanel.add(lblWeight);

        tfWeight = new JTextField(weight);
        tfWeight.setBounds(180, 200, 90, 25);
        c2leftPanel.add(tfWeight);

        lblBloodType = new JLabel("Blood Group:");
        lblBloodType.setForeground(Color.WHITE);
        lblBloodType.setBounds(30, 230, 100, 20);
        c2leftPanel.add(lblBloodType);

        cbBloodType = new JComboBox(bloodTypes);
        cbBloodType.setBounds(30, 250, 100, 25);
        if (!bloodType.trim().equals("")) {
            cbBloodType.setSelectedItem(bloodType);
        }
        c2leftPanel.add(cbBloodType);

        lblIDType = new JLabel("Identification Type:");
        lblIDType.setForeground(Color.WHITE);
        lblIDType.setBounds(160, 230, 110, 20);
        c2leftPanel.add(lblIDType);

        cbIDType = new JComboBox(idTypes);
        cbIDType.setModel(new DefaultComboBoxModel(new String[] {"National ID", "Civil Status Extract", "Passport"}));
        cbIDType.setBounds(160, 250, 110, 25);
        if (!idType.trim().equals("")) {
            cbIDType.setSelectedItem(idType);
        }
        c2leftPanel.add(cbIDType);

        lblIDNo = new JLabel("ID Number:");
        lblIDNo.setForeground(Color.WHITE);
        lblIDNo.setBounds(30, 280, 100, 20);
        c2leftPanel.add(lblIDNo);

        tfIDNo = new JFormattedTextField(idNo);
        tfIDNo.setText(idNo);
        tfIDNo.setBounds(30, 300, 240, 25);
        c2leftPanel.add(tfIDNo);

        chbSttmnt = new JCheckBox();
        chbSttmnt.setBounds(30, 330, 20, 20);
        chbSttmnt.setBackground(clrBlack);
        c2leftPanel.add(chbSttmnt);

        lblStatement = new JLabel("I have donated blood earlier.");
        lblStatement.setBounds(55, 330, 300, 20);
        lblStatement.setForeground(clrBlue);
        c2leftPanel.add(lblStatement);

        lblLastDonation = new JLabel("Last Donation Date:*");
        lblLastDonation.setBounds(30, 350, 200, 20);
        lblLastDonation.setEnabled(false);
        lblLastDonation.setForeground(Color.WHITE);
        c2leftPanel.add(lblLastDonation);

        dcLstDonation = new JDateChooser();
        dcLstDonation.setBounds(30, 370, 240, 25);
        dcLstDonation.setDateFormatString("dd/MM/yyyy");
        dcLstDonation.setMaxSelectableDate(new Date());
        dcLstDonation.setEnabled(false);
        c2leftPanel.add(dcLstDonation);

        lblMedical = new JLabel("Medical Conditions:");
        lblMedical.setForeground(Color.WHITE);
        lblMedical.setBounds(30, 400, 250, 20);
        c2leftPanel.add(lblMedical);

        taMedical = new JTextArea(medical);
        taMedical.setBounds(30, 419, 238, 38);
        taMedical.setLineWrap(true);

        JScrollPane scrlPaneDss = new JScrollPane();
        scrlPaneDss.setBounds(30, 440, 240, 40);
        scrlPaneDss.setViewportView(taMedical);
        c2leftPanel.add(scrlPaneDss);

        if (date != null) {
            chbSttmnt.setSelected(true);
            dcLstDonation.setDate(date);
            dcLstDonation.setEnabled(true);
        }

        chbSttmnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chbSttmnt.isSelected()) {
                    lblLastDonation.setEnabled(true);
                    dcLstDonation.setEnabled(true);
                } else {
                    lblLastDonation.setEnabled(false);
                    dcLstDonation.setDate(null);
                    dcLstDonation.setEnabled(false);
                }
            }
        });

        //left panel work done
        //setting up right panel
        c2rightPanel.setBackground(clrBlack);                                  //background color: Black
        c2rightPanel.setPreferredSize(new Dimension(300, 540));                 //setting panel size
        c2rightPanel.setLayout(new BorderLayout());

        //we divided right panel into three part vertically
        //upper panel will contain donor's picture
        //mid panel will contain contact info
        //and lower panel will contain buttons
        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //upper panel work
        TitledBorder ttlChoosePic = BorderFactory.createTitledBorder(blueline); //create titled border
        ttlChoosePic.setTitle(" Choose Picture ");                              //set title
        ttlChoosePic.setTitleColor(clrBlue);                                    //title color: Blue

        upperPanel.setBorder(new CompoundBorder(margin, ttlChoosePic));         //set border(outer border: margin, inner border: title)
        upperPanel.setBackground(clrBlack);                                    //background color: Black
        upperPanel.setPreferredSize(new Dimension(300, 275));                   //setting panel size
        upperPanel.setLayout(null);

        lblImage = new JLabel();
        lblImage.setBounds(40, 30, 220, 205);
        lblImage.setBorder(blueline);
        
        BufferedImage bf = null;
        Database db = new Database();
        isImage = db.getImage(id);
        try {
            bf = ImageIO.read(isImage);
        } catch (IOException ex) {
            System.out.println("ViewProfile.initCard2()");
            ex.printStackTrace();
        }
        lblImage.setIcon(resizeImage1(bf));
        upperPanel.add(lblImage);

        btnChoose = new JButton("Select Picture");
        btnChoose.setBounds(80, 240, 150, 20);
        btnChoose.setBackground(clrBlue);
        btnChoose.setForeground(Color.WHITE);
        upperPanel.add(btnChoose);

        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //create a file chooser to select image
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                fileChooser.addChoosableFileFilter(filter);

                imagePath = null;
                imageChanged = false;
                int result = fileChooser.showSaveDialog(null);
                //if the user click on save in Jfilechooser
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageChanged = true;
                    imagePath = selectedFile.getAbsolutePath();
                    lblImage.setIcon(resizeImage(imagePath));
                }//if the user click on cancel in Jfilechooser
                else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No File Selected");
                }
            }

        });
        //upper panel work done

        //mid panel work
        TitledBorder ttlContctInfo = BorderFactory.createTitledBorder(blueline);//create titled border

        ttlContctInfo.setTitle(" Contact Information ");                        //set title
        ttlContctInfo.setTitleColor(clrBlue);                                   //title color: Blue

        midPanel.setBorder(new CompoundBorder(
                new EmptyBorder(0, 10, 0, 10), ttlContctInfo));                 //set border(outer border: margin, inner border: title)
        midPanel.setBackground(clrBlack);                                      //background color: Black
        midPanel.setPreferredSize(new Dimension(300, 165));                     //setting panel size
        midPanel.setLayout(null);

        lblMobile = new JLabel("Mobile Number:");
        lblMobile.setForeground(Color.WHITE);
        lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(lblMobile);

        tfMobile = new JFormattedTextField(mobile);
        tfMobile.setText(mobile);
        tfMobile.setBounds(30, 40, 100, 25);
        midPanel.add(tfMobile);

        lblLandline = new JLabel("Landline Number:");
        lblLandline.setForeground(Color.WHITE);
        lblLandline.setBounds(165, 20, 120, 20);
        midPanel.add(lblLandline);

        tfLandline = new JFormattedTextField(landline);
        tfLandline.setText(landline);
        tfLandline.setBounds(165, 40, 100, 25);
        midPanel.add(tfLandline);

        lblEMail = new JLabel("E-Mail:");
        lblEMail.setForeground(Color.WHITE);
        lblEMail.setBounds(30, 70, 120, 20);
        midPanel.add(lblEMail);
        
        tfEMail = new JTextField(eMail);
        tfEMail.setBounds(30, 90, 235, 25);
        midPanel.add(tfEMail);
        c2rightPanel.add(lowerPanel, BorderLayout.SOUTH);

        lblAddress = new JLabel("Address:");
        lblAddress.setForeground(Color.WHITE);
        lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(lblAddress);

        taAddress = new JTextArea(address);
        taAddress.setLineWrap(true);

        JScrollPane scrlPaneAdrs = new JScrollPane();
        scrlPaneAdrs.setBounds(30, 140, 240, 35);
        scrlPaneAdrs.setViewportView(taAddress);

        midPanel.add(scrlPaneAdrs);
        //mid panel work done

        //lower panel work
        lowerPanel.setBackground(clrBlack);                                    //background color: Black
        lowerPanel.setPreferredSize(new Dimension(300, 50));                    //setting panel size
        lowerPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        lowerPanel.setLayout(new BorderLayout());

        btnUpdate = new JButton("Update Profile");
        btnUpdate.setBackground(clrGreen);                                       //button background color: Green
        btnUpdate.setForeground(Color.WHITE);                                   //button text color: White
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean ok = true;

                String ufName = tfFirstName.getText();
                String ulName = tfLastName.getText();
                String ufaName = tfFatherName.getText();
                String ugender = cbGender.getSelectedItem().toString();
                String ubloodType = cbBloodType.getSelectedItem().toString();
                String uweight = tfWeight.getText();
                String uidType = cbIDType.getSelectedItem().toString();
                String uidNo = tfIDNo.getText();
                String umedical = taMedical.getText();
                String umobile = tfMobile.getText();
                String ulandline = tfLandline.getText();
                String ueMail = tfEMail.getText();
                String uaddress = taAddress.getText();
                Date udate = dcLstDonation.getDate();
                int uage = (int) spAge.getModel().getValue();

                InputStream uisImage = null;
                if (!imageChanged) {
                    uisImage = db.getImage(id);
                } else {
                    try {
                        uisImage = Files.newInputStream(Paths.get(imagePath));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frameViewProfile, "'Picture' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.initCard2.actionPerformed()" + ex);
                        ok = false;
                    }
                }

                if (!tfWeight.getText().isEmpty()) {
                    try {
                        Float.parseFloat(tfWeight.getText());
                    } catch (NumberFormatException ex) {
                        ok = false;
                        tfWeight.setText("");
                        JOptionPane.showMessageDialog(frameViewProfile, "'Weight' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.tfWeight.getText(): " + ex);
                    }
                }

                if (chbSttmnt.isSelected() && udate == null) {
                    ok = false;
                    JOptionPane.showMessageDialog(frameViewProfile, "'Last Donation Date' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                }

                if (!tfMobile.getText().isEmpty()) {
                    try {
                        Float.parseFloat(tfMobile.getText());
                    } catch (NumberFormatException ex) {
                        ok = false;
                        tfMobile.setText("");
                        JOptionPane.showMessageDialog(frameViewProfile, "'Mobile number' is invalid.",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("AddNewDonor.tfMobile.getText(): " + ex);
                    }
                }
                
                if (!tfLandline.getText().isEmpty()) {
                    try {
                        Float.parseFloat(tfLandline.getText());
                    } catch (NumberFormatException ex) {
                        ok = false;
                        tfLandline.setText("");
                        JOptionPane.showMessageDialog(frameViewProfile, "'Landline number' is invalid.",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("AddNewDonor.tfLandline.getText(): " + ex);
                    }
                }
                
                if (ufName.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'First Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ufName.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'First Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ulName.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Last Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ulName.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Last Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ufaName.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Father's Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ufaName.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Father's Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ugender.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Gender' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ubloodType.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Blood Type' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uweight.trim().equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Weight' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uidType.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Identification Type' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uidNo.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'ID Number' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uidNo.length() > 20) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'ID Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (umobile.trim().equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Mobile Number' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (umobile.length() > 15) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Mobile Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ulandline.length() > 15) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Landline Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ueMail.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'E-Mail' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ueMail.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'E-Mail' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uaddress.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Address' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ok == true) {
                    Donor donor = new Donor();
                    try {
                        donor.setID(id);
                        donor.setFirstName(ufName);
                        donor.setLastName(ufaName);
                        donor.setFathersName(ulName);
                        donor.setAge(uage);
                        donor.setWeight(uweight);
                        donor.setGender(ugender);
                        donor.setBloodType(ubloodType);
                        donor.setLastDonation(udate);
                        donor.setMedical(umedical);
                        donor.setIdentificationType(uidType);
                        donor.setIDNo(uidNo);
                        donor.setMobileNo(umobile);
                        donor.setLandlineNo(ulandline);
                        donor.setEMail(ueMail);
                        donor.setAddress(uaddress);
                        donor.setPicture(uisImage);

                        Database db = new Database();
                        db.update(donor);

                        JOptionPane.showMessageDialog(frameViewProfile, "Profile Updated Successfully.", "Message.", JOptionPane.INFORMATION_MESSAGE);
                        btnCancel.setText("Done");


                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frameViewProfile, "Something Went Wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.update():");
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(clrRed);                                        //button background color: clrRed
        btnCancel.setForeground(Color.WHITE);                                   //button text color: White
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanle, "view");
            }
        });

        lowerPanel.add(btnUpdate, BorderLayout.WEST);
        lowerPanel.add(btnCancel, BorderLayout.EAST);
        //lower panel work done

        c2rightPanel.add(upperPanel, BorderLayout.NORTH);
        c2rightPanel.add(midPanel, BorderLayout.CENTER);
        c2rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //right panel work done

        cardEdit.add(c2leftPanel, BorderLayout.WEST);
        cardEdit.add(c2rightPanel, BorderLayout.EAST);
    }

    public ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public ImageIcon resizeImage(BufferedImage bf) {
        ImageIcon imageIcon = new ImageIcon(bf);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(c1lblImage.getWidth(), c1lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    public ImageIcon resizeImage1(BufferedImage bf) {
        ImageIcon imageIcon = new ImageIcon(bf);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    private void getProfile(int id) {
        Donor donor;
        Database db = new Database();
        try {
            donor = db.viewProfile(id);

            firstName = donor.getFirstName();
            lastName = donor.getFathersName();
            fatherName = donor.getLastName();
            gender = donor.getGender();
            age = donor.getAge();
            weight = donor.getWeight();
            bloodType = donor.getBloodType();
            idType = donor.getIdentificationType();
            idNo = donor.getIDNo();
            date = donor.getLastDonation();
            medical = donor.getMedical();
            mobile = donor.getMobileNo();
            landline = donor.getLandlineNo();
            eMail = donor.getEMail();
            address = donor.getAddress();
            isImage = donor.getPicture();
        } catch (HeadlessException ex) {
            System.out.println("ViewProfile.getProfile(int id): " + ex);
            ex.printStackTrace();
        }
    }

    //this method refresh the profile during view mode
    private void refreshProfile(int id) {
        getProfile(id);
        try {
            c1tfFirstName.setText(firstName);
            c1tfLastName.setText(lastName);
            c1tfFatherName.setText(fatherName);
            c1tfGender.setText(gender);
            c1tfAge.setText(String.valueOf(age));
            c1tfWeight.setText(weight);
            c1tfBloodType.setText(bloodType);
            c1tfIDType.setText(idType);
            c1tfIDNo.setText(idNo);
            if (date != null) {
                c1tfLastDonation.setText(date.toString());
            } else if (date == null) {
                c1tfLastDonation.setText("");
            }
            c1taMedical.setText(medical);
            c1tfMobile.setText(mobile);
            c1tfLandline.setText(landline);
            c1tfEMail.setText(eMail);
            c1taAddress.setText(address);
            BufferedImage bf = null;
            try {
                bf = ImageIO.read(isImage);
            } catch (IOException ex) {
                System.out.println("ViewProfile.refreshProfile(int id)");
                ex.printStackTrace();
            }
            c1lblImage.setIcon(resizeImage(bf));
        } catch (HeadlessException ex) {
            System.out.println("ViewProfile.refreshProfile(int id):");
            ex.printStackTrace();
        }
    }

}

package valkyrie;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import javax.swing.BorderFactory;
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
import javax.swing.DefaultComboBoxModel;

public class AddNewDonor {

    private final ImageIcon icon;

    private final JFrame frameAddNewEntry = new JFrame("Add New Donor");

    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();

    private final JButton btnCancel, btnAdd, btnChoose;

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrBlue = new Color(0, 156, 255);
    private final Color clrBlack = new Color(44, 47, 51);
    private final Color clrGreen = new Color(40, 155, 15);

    private final JLabel lblFirstName, lblLastName, lblFatherName, lblGender, lblBloodType, lblAge, lblWeight, lblIDType, lblIDNo, lblStatement, lblLastDonation, lblMedical, lblMobile,  lblLandline, lblAddress, lblImage;
    private final JTextField tfFirstName, tfLastName, tfFatherName, tfWeight;
    private final JTextArea taMedical, taAddress;
    private final JComboBox cbGender, cbBloodType, cbIDType;


    private final JSpinner spAge;
    private final SpinnerNumberModel age;
    private final JCheckBox chbSttmnt;
    private final JDateChooser dcLstDonation;
    private final JFormattedTextField tfMobile, tfLandline, tfIDNo;

    private final String bloodTypes[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private final String idTypes[]={"National ID", "Civil Status Extract", "Passport"};
    private final String gender[] = {"Male", "Female"};
    private String imagePath;

    private Donor donor = new Donor();
    private JLabel lblEMail;
    private JTextField tfEMail;

    public AddNewDonor() {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/add_donor_icon.png"));
        frameAddNewEntry.setIconImage(icon.getImage());                         //set frame icon

        //setting up the frame
        frameAddNewEntry.setSize(600, 540);
        frameAddNewEntry.getContentPane().setLayout(new BorderLayout());                         //frame layout: Border Layout
        frameAddNewEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     //setting default close operation
        frameAddNewEntry.setResizable(false);                                   //disable resize button
        frameAddNewEntry.setLocationRelativeTo(null);                           //to center the window

        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border

        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));         //set border(outer border: margin, inner border: title)
        leftPanel.setBackground(clrBlack);                                     //background color: Yellow
        leftPanel.setPreferredSize(new Dimension(300, 540));                    //setting panel size
        leftPanel.setLayout(null);

        lblFirstName = new JLabel("First Name:");
        lblFirstName.setForeground(Color.WHITE);
        lblFirstName.setBounds(30, 30, 100, 20);
        leftPanel.add(lblFirstName);

        tfFirstName = new JTextField();
        tfFirstName.setBounds(30, 50, 240, 25);
        leftPanel.add(tfFirstName);

        lblLastName = new JLabel("Last Name:");
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(30, 80, 150, 20);
        leftPanel.add(lblLastName);

        tfLastName = new JTextField();
        tfLastName.setBounds(30, 100, 240, 25);
        leftPanel.add(tfLastName);

        lblFatherName = new JLabel("Father's Name:");
        lblFatherName.setForeground(Color.WHITE);
        lblFatherName.setBounds(30, 130, 150, 20);
        leftPanel.add(lblFatherName);

        tfFatherName = new JTextField();
        tfFatherName.setBounds(30, 150, 240, 25);
        leftPanel.add(tfFatherName);

        lblGender = new JLabel("Gender:");
        lblGender.setForeground(Color.WHITE);
        lblGender.setBounds(30, 180, 80, 20);
        leftPanel.add(lblGender);

        cbGender = new JComboBox(gender);
        cbGender.setBounds(30, 200, 80, 25);
        leftPanel.add(cbGender);

        lblAge = new JLabel("Age:");
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(120, 180, 50, 20);
        leftPanel.add(lblAge);

        age = new SpinnerNumberModel(18, 18, 65, 1);
        spAge = new JSpinner(age);
        spAge.setBounds(120, 200, 50, 25);
        leftPanel.add(spAge);

        lblWeight = new JLabel("Weight (KG):");
        lblWeight.setForeground(Color.WHITE);
        lblWeight.setBounds(178, 180, 95, 20);
        leftPanel.add(lblWeight);

        tfWeight = new JTextField();
        tfWeight.setBounds(180, 200, 90, 25);
        leftPanel.add(tfWeight);

        lblBloodType = new JLabel("Blood Type:");
        lblBloodType.setForeground(Color.WHITE);
        lblBloodType.setBounds(30, 230, 100, 20);
        leftPanel.add(lblBloodType);

        cbBloodType = new JComboBox(bloodTypes);
        cbBloodType.setBounds(30, 250, 100, 25);
        leftPanel.add(cbBloodType);

        lblIDType = new JLabel("Identification Type:");
        lblIDType.setForeground(Color.WHITE);
        lblIDType.setBounds(160, 230, 110, 20);
        leftPanel.add(lblIDType);

        cbIDType = new JComboBox(idTypes);
        cbIDType.setBounds(160, 250, 110, 25);
        leftPanel.add(cbIDType);

        lblIDNo = new JLabel("ID Number:");
        lblIDNo.setForeground(Color.WHITE);
        lblIDNo.setBounds(30, 280, 100, 20);
        leftPanel.add(lblIDNo);

        tfIDNo = new JFormattedTextField();
        tfIDNo.setBounds(30, 300, 240, 25);
        leftPanel.add(tfIDNo);

        chbSttmnt = new JCheckBox();
        chbSttmnt.setBounds(30, 330, 20, 20);
        chbSttmnt.setBackground(clrBlack);
        leftPanel.add(chbSttmnt);

        lblStatement = new JLabel("I have donated blood earlier.");
        lblStatement.setBounds(55, 330, 300, 20);
        lblStatement.setForeground(clrBlue);
        leftPanel.add(lblStatement);

        lblLastDonation = new JLabel("Last Donation Date:");
        lblLastDonation.setForeground(Color.WHITE);
        lblLastDonation.setBounds(30, 350, 200, 20);
        lblLastDonation.setEnabled(false);
        leftPanel.add(lblLastDonation);

        dcLstDonation = new JDateChooser();
        dcLstDonation.setBounds(30, 370, 240, 25);
        dcLstDonation.setDateFormatString("dd/MM/yyyy");
        dcLstDonation.setMaxSelectableDate(new Date());
        dcLstDonation.setEnabled(false);
        leftPanel.add(dcLstDonation);

        lblMedical = new JLabel("Medical Conditions:");
        lblMedical.setForeground(Color.WHITE);
        lblMedical.setBounds(30, 400, 250, 20);
        leftPanel.add(lblMedical);

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
        rightPanel.setBackground(clrBlack);                                    //background color: Yellow
        rightPanel.setPreferredSize(new Dimension(300, 540));                   //setting panel size
        rightPanel.setLayout(new BorderLayout());

        //divided right panel into three part vertically
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
        upperPanel.setBackground(clrBlack);                                    //background color: Yellow
        upperPanel.setPreferredSize(new Dimension(300, 275));                   //setting panel size
        upperPanel.setLayout(null);

        lblImage = new JLabel();
        lblImage.setBounds(40, 30, 220, 205);
        lblImage.setBorder(blueline);
        lblImage.setIcon(resizeImage(new ImageIcon(getClass().getResource("images/user.png"))));
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
                int result = fileChooser.showSaveDialog(null);
                //if the user clicks on save in Jfilechooser
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath = selectedFile.getAbsolutePath();
                    lblImage.setIcon(resizeImage(imagePath));
                }//if the user clicks on cancel in Jfilechooser
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
        midPanel.setBackground(clrBlack);                                      //background color: Yellow
        midPanel.setPreferredSize(new Dimension(300, 165));                     //setting panel size
        midPanel.setLayout(null);

        lblMobile = new JLabel("Mobile Number:");
        lblMobile.setForeground(Color.WHITE);
        lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(lblMobile);

        tfMobile = new JFormattedTextField();
        tfMobile.setBounds(30, 40, 100, 25);
        midPanel.add(tfMobile);
        
        lblLandline = new JLabel("Landline Number:");
        lblLandline.setForeground(Color.WHITE);
        lblLandline.setBounds(165, 20, 120, 20);
        midPanel.add(lblLandline);

        tfLandline = new JFormattedTextField();
        tfLandline.setBounds(165, 40, 100, 25);
        midPanel.add(tfLandline);

        lblAddress = new JLabel("Address:");
        lblAddress.setForeground(Color.WHITE);
        lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(lblAddress);

        taAddress = new JTextArea();
        taAddress.setLineWrap(true);

        JScrollPane scrlPaneAdrs = new JScrollPane();
        scrlPaneAdrs.setBounds(30, 140, 240, 35);
        scrlPaneAdrs.setViewportView(taAddress);

        midPanel.add(scrlPaneAdrs);
        
        lblEMail = new JLabel("E-Mail:");
        lblEMail.setForeground(Color.WHITE);
        lblEMail.setBounds(30, 70, 120, 20);
        midPanel.add(lblEMail);
        
        tfEMail = new JTextField();
        tfEMail.setBounds(30, 90, 235, 25);
        midPanel.add(tfEMail);
        rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //mid panel work done

        //lower panel work
        lowerPanel.setBackground(clrBlack);                                    //background color: Yellow
        lowerPanel.setPreferredSize(new Dimension(300, 50));                    //setting panel size
        lowerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        lowerPanel.setLayout(new BorderLayout());

        btnAdd = new JButton("Add Donor");
        btnAdd.setBackground(clrGreen);                                          //button background color: Green
        btnAdd.setForeground(Color.WHITE);                                      //button text color: White
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));                       //set button cursor

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                insertData();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(clrRed);                                        //button background color: clrRed
        btnCancel.setForeground(Color.WHITE);                                   //button text color: White
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameAddNewEntry.dispose();
            }

        });

        lowerPanel.add(btnAdd, BorderLayout.WEST);
        lowerPanel.add(btnCancel, BorderLayout.EAST);
        //lower panel work done

        rightPanel.add(upperPanel, BorderLayout.NORTH);
        rightPanel.add(midPanel, BorderLayout.CENTER);
        //right panel work done

        frameAddNewEntry.getContentPane().add(leftPanel, BorderLayout.WEST);
        
                taMedical = new JTextArea();
                taMedical.setBounds(30, 419, 238, 38);
                leftPanel.add(taMedical);
                taMedical.setLineWrap(true);
        frameAddNewEntry.getContentPane().add(rightPanel, BorderLayout.EAST);

        //make frame visible
        frameAddNewEntry.setVisible(true);
    }

    private ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    private ImageIcon resizeImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    private void insertData() {
        boolean ok = true;

        String fName = tfFirstName.getText();
        String lName = tfLastName.getText();
        String faName = tfFatherName.getText();
        String gender = cbGender.getSelectedItem().toString();
        String bloodType = cbBloodType.getSelectedItem().toString();
        String weight = tfWeight.getText();
        String idType = cbIDType.getSelectedItem().toString();
        String idNo = tfIDNo.getText();
        String medical = taMedical.getText();
        String mobile = tfMobile.getText();
        String landline = tfLandline.getText();
        String eMail = tfEMail.getText();
        String address = taAddress.getText();
        
        Date date = dcLstDonation.getDate();
        int age = (int) spAge.getModel().getValue();

        InputStream isImage = null;
        if (imagePath == null) {
            isImage = getClass().getResourceAsStream("images/user.png");
        } else {
            try {
                isImage = Files.newInputStream(Paths.get(imagePath));
            } catch (IOException ex) {
                ok = false;
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Picture' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!tfWeight.getText().isEmpty()) {
            try {
                Float.parseFloat(tfWeight.getText());
            } catch (NumberFormatException ex) {
                ok = false;
                tfWeight.setText("");
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Weight' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                System.out.println("AddNewDonor.tfWeight.getText(): " + ex);
            }
        }
        
        if (!tfIDNo.getText().isEmpty()) {
            try {
                Float.parseFloat(tfIDNo.getText());
            } catch (NumberFormatException ex) {
                ok = false;
                tfIDNo.setText("");
                JOptionPane.showMessageDialog(frameAddNewEntry, "'ID number' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                System.out.println("AddNewDonor.tfIDNo.getText(): " + ex);
            }
        }
        
        if (chbSttmnt.isSelected() && date == null) {
            ok = false;
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Last Donation Date' is invalid.",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!tfMobile.getText().isEmpty()) {
            try {
                Float.parseFloat(tfMobile.getText());
            } catch (NumberFormatException ex) {
                ok = false;
                tfMobile.setText("");
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Mobile number' is invalid.",
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
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Landline number' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                System.out.println("AddNewDonor.tfLandline.getText(): " + ex);
            }
        }
        
        if (fName.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'First Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (fName.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'First Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (lName.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Last Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (lName.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Last Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (faName.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Father's Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (faName.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Father's Name' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (gender.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Gender' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (bloodType.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Blood Type' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (weight.trim().equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Weight' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (idType.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Identification Type' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (idNo.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'ID Number' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (idNo.length() > 20) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'ID Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (mobile.trim().equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Mobile Number' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (mobile.length() > 15) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Mobile Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (landline.length() > 15) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Landline Number' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (eMail.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'E-Mail' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (eMail.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'E-Mail' is too long (MAX 30 characters).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (address.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Address' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (ok == true) {
            try {
                donor.setFirstName(fName);
                donor.setLastName(lName);
                donor.setFathersName(faName);
                donor.setGender(gender);
                donor.setAge(age);
                donor.setWeight(weight);
                donor.setBloodType(bloodType);
                donor.setIdentificationType(idType);
                donor.setIDNo(idNo);
                donor.setLastDonation(date);
                donor.setMedical(medical);
                donor.setPicture(isImage);
                donor.setMobileNo(mobile);
                donor.setLandlineNo(landline);
                donor.setEMail(eMail);
                donor.setAddress(address);

                Database db = new Database();
                db.insert(donor);
                int choice = JOptionPane.showConfirmDialog(frameAddNewEntry,
                        "Data Added Successfully.\nDo you want to add another?", "Message.",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                //if user choose NO then dispose this window
                //otherwise clear all the field                        
                if (choice == JOptionPane.NO_OPTION) {
                    frameAddNewEntry.dispose();
                } else {
                    tfFirstName.setText("");
                    tfLastName.setText("");
                    tfFatherName.setText("");
                    tfWeight.setText("");
                    tfIDNo.setText("");
                    tfMobile.setText("");
                    tfLandline.setText("");
                    tfEMail.setText("");
                    taMedical.setText("");
                    taAddress.setText("");
                    chbSttmnt.setSelected(false);
                    dcLstDonation.setDate(null);
                    dcLstDonation.setEnabled(false);
                    cbGender.setSelectedIndex(0);
                    cbBloodType.setSelectedIndex(0);
                    cbIDType.setSelectedIndex(0);
                    spAge.setValue(18);
                    lblImage.setIcon(resizeImage(new ImageIcon(getClass().getResource("images/user.png"))));
                }
            } catch (Exception ex) {
                System.out.println("AddNewEntry.getData():");
                ex.printStackTrace();
            }
        }
    }
}

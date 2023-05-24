package valkyrie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Contact {
    JFrame frameContact = new JFrame("Contact Information");
    
    private final ImageIcon icon;    
    private final JPanel panelMain;    
    private final JButton btnOk;
    private final JLabel lblContact;
    
    private final Color clrBlack = new Color(44, 47, 51);
    private final Color clrBlue = new Color(0, 156, 255);

    public Contact() {
        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/info_icon.png"));
        frameContact.setIconImage(icon.getImage());                          //setting window icon

        //setting up the frame
        frameContact.setSize(400, 300);
        frameContact.getContentPane().setLayout(new BorderLayout());                          //frame layout: Border Layout
        frameContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      //setting default close operation
        frameContact.setLocationRelativeTo(null);                            //to center the window
        frameContact.setResizable(false);                                    //disable window resize
        
        //setting up the panel 
        
        panelMain = new JPanel();
        panelMain.setBackground(clrBlack);                                     //background color: Yellow
        panelMain.setLayout(null);
        
        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border
        
        TitledBorder ttlDevInfo = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlDevInfo.setTitle(" Contact Us ");                                    //set title
        ttlDevInfo.setTitleColor(clrBlue);                                      //title color: Blue

        panelMain.setBorder(new CompoundBorder(margin, ttlDevInfo));            //set border(outer border: margin, inner border: title)
        panelMain.setBackground(clrBlack);                                     //background color: Yellow
        panelMain.setPreferredSize(new Dimension(600, 400));                    //setting panel size
        
        lblContact = new JLabel("Contact Information:");
        lblContact.setForeground(Color.WHITE);
        lblContact.setBounds(70, 41, 118, 20);
        panelMain.add(lblContact);
        
        btnOk = new JButton("OK");
        btnOk.setBackground(clrBlue);                                           //button background color: Red
        btnOk.setForeground(Color.white);                                       //button text color: White
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));                        //set button cursor
        btnOk.setBounds(144, 222, 80, 25);
        panelMain.add(btnOk);
        
        //after clicking this button this window will be closed
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frameContact.dispose();
            }
        });
        
        //panel work done
        
        frameContact.getContentPane().add(panelMain, BorderLayout.CENTER);
        
        JTextArea taContact = new JTextArea();
        taContact.setEditable(false);
        taContact.setText("E-Mail: help.valkyrie@hotmail.com\r\nPhone: +961 1/234 567\r\nHQ: Beirut, Lebanon.");
        taContact.setFont(new Font("Tahoma", Font.PLAIN, 12));
        taContact.setBounds(70, 65, 250, 150);
        panelMain.add(taContact);
          //make frame visible
        frameContact.setVisible(true);
    }
}

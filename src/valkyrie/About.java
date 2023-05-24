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
import java.awt.SystemColor;

public class About {

    JFrame frameAbout = new JFrame("About");

    private final ImageIcon icon;
    private final JPanel panelMain;
    private final JLabel lblAbout, lblFeatures;
    private final JTextArea taAbout, taFeatures;
    private final JButton btnOk;

    private final Color clrBlack = new Color(44, 47, 51);
    private final Color clrBlue = new Color(0, 156, 255);

    public About() {
        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/about_icon.png"));
        frameAbout.setIconImage(icon.getImage());                               //setting window icon

        //setting up the frame
        frameAbout.setSize(400, 300);
        frameAbout.getContentPane().setLayout(new BorderLayout());                               //frame layout: Border Layout
        frameAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);           //setting default close operation
        frameAbout.setLocationRelativeTo(null);                                 //to center the window
        frameAbout.setResizable(false);                                         //disable window resize

        //setting up the panel 
        panelMain = new JPanel();
        panelMain.setBackground(clrBlack);                                     //background color: Yellow
        panelMain.setLayout(null);

        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border

        TitledBorder ttlDevInfo = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlDevInfo.setTitle(" About ");                                         //set title
        ttlDevInfo.setTitleColor(clrBlue);                                      //title color: Blue

        panelMain.setBorder(new CompoundBorder(margin, ttlDevInfo));            //set border(outer border: margin, inner border: title)
        panelMain.setBackground(clrBlack);                                     //background color: Yellow
        panelMain.setPreferredSize(new Dimension(400, 300));                    //setting panel size

        lblAbout = new JLabel("Description:");
        lblAbout.setForeground(Color.WHITE);
        lblAbout.setBounds(25, 30, 340, 20);
        panelMain.add(lblAbout);

        taAbout = new JTextArea();
        taAbout.setText("Application to store information about blood donors.");
        taAbout.setBounds(25, 50, 340, 30);
        taAbout.setBorder(blueline);
        taAbout.setEditable(false);
        panelMain.add(taAbout);

        lblFeatures = new JLabel("Features:");
        lblFeatures.setForeground(Color.WHITE);
        lblFeatures.setBounds(25, 90, 340, 20);
        panelMain.add(lblFeatures);

        taFeatures = new JTextArea();
        taFeatures.setText("Add new donors\n"
                + "Update existing donors\n"
                + "Delete donors\n"
                + "Filtered search\n"
                + "Sort donor's list");
        taFeatures.setBounds(25, 110, 340, 100);
        taFeatures.setBorder(blueline);
        taFeatures.setEditable(false);
        panelMain.add(taFeatures);

        btnOk = new JButton("OK");
        btnOk.setBackground(clrBlue);
        btnOk.setForeground(Color.white);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.setBounds(150, 220, 80, 25);
        panelMain.add(btnOk);

        //after clicking this button this window will be closed
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frameAbout.dispose();
            }
        });

        //panel work done
        frameAbout.getContentPane().add(panelMain, BorderLayout.CENTER);
        //make frame visible
        frameAbout.setVisible(true);
    }

}

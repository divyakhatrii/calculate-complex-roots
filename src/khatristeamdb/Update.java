/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khatristeamdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author divyak
 */

public class Update extends JFrame implements ActionListener
{
    //declaring variables
    private JButton updateButton;
    private JComboBox<String> setBox;
    private JComboBox<String> whereBox;
    private JTextField setField;
    private JTextField whereField;
    private JLabel setLabel;
    private JLabel toLabel;
    private JLabel whereLabel;
    private JLabel equalsLabel;
    private JPanel updatePanel;
    private JButton doneButton;
    private JPanel donePanel;
    
    private String[] tableHeaders;
    Output gFrame; //global frame of type class ButtonsWdisplay, it is global, it is an attribute
    //objects are ADRESSES pointing to a copy of the class with certain values, attributes and properties

    public Update(Output pFrame, String[] tableHeaders) //object as parameter, parameters a local, cannot use in other methodsl but we can use global frame to do that
    {
       //constructing the frame
        super("Update");
        this.setBounds(250, 350, 950, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.ORANGE);
        this.setLayout(new BorderLayout());
        
        this.tableHeaders = tableHeaders;
        this.gFrame = pFrame;
        
        //construct labels
        this.setLabel = new JLabel("Set ");
        this.setLabel.setFont(Welcome.SMALL_FONT);
        this.toLabel = new JLabel(" to ");
        this.toLabel.setFont(Welcome.SMALL_FONT);
        this.whereLabel = new JLabel(" where ");
        this.whereLabel.setFont(Welcome.SMALL_FONT);
        this.equalsLabel = new JLabel(" = ");
        this.equalsLabel.setFont(Welcome.SMALL_FONT);
        
        //construct textFields
        this.setField = new JTextField(20);
        this.whereField = new JTextField(20);
        
        //construct Buttons
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.updateButton.setFont(Welcome.SMALL_FONT);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        this.doneButton.setFont(Welcome.SMALL_FONT);
        
         //construct comboBox
        this.setBox = new JComboBox<>(tableHeaders);
        this.setBox.setFont(Welcome.SMALL_FONT);
        this.whereBox = new JComboBox<>(tableHeaders);
        this.whereBox.setFont(Welcome.SMALL_FONT);
        
        //construct and add stuff to panels
        this.updatePanel = new JPanel(new FlowLayout());
        this.updatePanel.setBackground(Welcome.DARK_BLUE);
        this.updatePanel.add(setLabel);
        this.updatePanel.add(setBox);
        this.updatePanel.add(toLabel);
        this.updatePanel.add(setField);
        this.updatePanel.add(whereLabel);
        this.updatePanel.add(whereBox);
        this.updatePanel.add(equalsLabel);
        this.updatePanel.add(whereField);
        this.updatePanel.add(updateButton);
        this.donePanel = new JPanel();
        this.donePanel.setBackground(Welcome.TINTED_WHITE);
        this.donePanel.add(doneButton);
        
        //add everything to the frame
        this.add(updatePanel, BorderLayout.CENTER);
        this.add(donePanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };
        //connect to db
        JavaDb objDb = new JavaDb(dbName);
        Connection myDbConn = objDb.getDbConn();//we made the Dbconn when making the object, so now we access it, get connection

        //variables to be added instead of ?
        String setColumn;
        int setTo; 
        String whereColumn;
        int whereCondition;
        
        if (command.equals("Update"))
        {
            //get the select Column based on the aelected value
            setColumn = tableHeaders[setBox.getSelectedIndex()];
            setTo = Integer.parseInt(setField.getText());
            whereColumn = tableHeaders[whereBox.getSelectedIndex()];
            whereCondition = Integer.parseInt(whereField.getText());
            setField.setText("");
            whereField.setText("");
            String dbQuery = "UPDATE " + tableName + " SET " + setColumn + " = ? WHERE " + whereColumn + " = ?";
        
            try //inserting the data
            {
                //prepare statement
                PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
                //enter data into query
                ps.setInt(1, setTo);//first  question mark replaced by that variable
                ps.setInt(2, whereCondition);

                //execute the query
                ps.executeUpdate();
                System.out.println("Data updated successfully");
                
                //dispose old frame and bring new one
                gFrame.dispose();
                gFrame = new Output(dbName, tableName, tableHeaders);
                //to make sure we see it, we bring it to the front
                this.toFront();
            }
            catch (SQLException se)
            {
                System.out.println("Error inserting data");  
            }
        }
        else if (command.equals("Done"))
        {
            //close connection and dispose frame
            objDb.closeDbConn();
            this.dispose();
        }
    }

    public static void main(String[] args)
    {
        String dbName = "imaginary";
        String tableName = "RectangularForm";
        String[] tableHeaders =
        {
            "InputNo", "RectForm", "PolarForm"
        };
        
        Output mainObj = new Output(dbName, tableName, tableHeaders);
        new Update(mainObj, tableHeaders);
    }
}

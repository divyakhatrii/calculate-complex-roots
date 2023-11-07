/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khatristeamdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaDb
{

    private String dbName;
    private Connection dbConn;
    private ArrayList<ArrayList<String>> data;

    public JavaDb()
    {
        dbName = "";
        dbConn = null;
        data = null;
    }

    public JavaDb(String dbName)
    {
        setDbName(dbName);
        setDbConn();
        data = null;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public Connection getDbConn()
    {
        return dbConn;
    }

    public void setDbConn()//deleted the parameter here so no errors 
    {
        String connectionURL = "jdbc:derby:" + this.dbName;
        this.dbConn = null;//dbconn must be set inside a try and catch
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//connects to the library and databse
            this.dbConn = DriverManager.getConnection(connectionURL);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not found, check Library"); //when you type class for name wrong 
        }
        catch (SQLException se) //fix imports for the exceptions 
        {
            System.out.println("SQL connection error!");
        }
    }

    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();

        //read the data
        try
        {
            //send the query and receive data
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery);

            //read the data using rs and stroe in ArrayList data
            while (rs.next())
            {
                //row object to hold one row data
                ArrayList<String> row = new ArrayList<>();
                //go trhoguh the row and read each cell
                for (int i = 0; i < columnCount; i++)
                {
                    //read the cell
                    //example: String cell = rs.getString("Name");
                    //reads the cell in column "Name"
                    //table header = {"Name","Price","Color"};`
                    String cell = rs.getString(tableHeaders[i]);//rs points to row, git goes through the column that we are one, like "anme for example" it needs column header as paramenter. It reads the rowwithin the caolumn that the rs is pointing to
                    //add the cell to the row
                    row.add(cell);
                }
                //add the row to the data, adding the arraylist row to the arraylist of multiple rows
                this.data.add(row);
            }
            try
            {
                this.dbConn.close();
            }
            catch (Exception err)
            {
                System.out.println("DB closing error.");
            }
        }

        catch (SQLException se)
        {
            System.out.println("SQL Error: Not able to get data");
        }
        return data;
    }


    public void setData(ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }

    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch (Exception err)
        {
            System.out.println("DB closing error.");
        }
    }

    public void createDb(String newDbName)//should only be run once, not like insert db
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";//checks if the database doesn't exist then it creates one
        this.dbConn = null;//copy and pasted here down from the setDb Conn method
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//connects to the library and databse
            this.dbConn = DriverManager.getConnection(connectionURL);
            System.out.println("New Database " + this.dbName + " created!");//meaningful tells us if it worked
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not found, check Library"); //when you type class for name wrong 
        }
        catch (SQLException se) //fix imports for the exceptions 
        {
            System.out.println("SQL connection error, Db was not created!");//but we change it here
        }
    }

    public void createTable(String newTable, String dbName)//two paramenters table info/query name and dbName
    {
        System.out.println(newTable);
        setDbName(dbName);
        setDbConn();
        Statement s;

        try
        {
            s = this.dbConn.createStatement();//balnk statement and pass it to our s
            s.execute(newTable);
            System.out.println("New Table created!");
        }
        catch (SQLException se)
        {
            System.out.println("SQL connection error, or syntax error!");
        }
    }

    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        if (data.size() == 0)
        {
            Object[][] dataList = new Object[0][0];
            return dataList;
        }
        else
        {
            int columnCount = data.get(0).size();
            Object[][] dataList = new Object[data.size()][columnCount];

            for (int i = 0; i < data.size(); i++)
            {
                ArrayList<String> row = data.get(i);
                for (int j = 0; j < columnCount; j++)
                {
                    dataList[i][j] = row.get(j);
                }
            }
            return dataList;
        }
    }

    //main method
    public static void main(String[] args)
    {

        //db info
        String dbName = "Mcv";//must be same name as db name we are testing 
        String tableName = "Cars";
        String[] columnNames =
        {
            "Name", "Price", "Color"
        };
        String dbQuery = "INSERT INTO Cars VALUES (?,?,?)";

        //connect to db
        JavaDb objDb = new JavaDb(dbName);//go up it creates a dbConn already
        Connection myDbConn = objDb.getDbConn();//we made the Dbconn when making the object, so now we access it, get connection

        String name = "yasss";
        int price = 50000;
        String color = "orange";
        try
        {
            //prepare statement
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery); //blank check
            //enter data into query
            ps.setString(1, name); //first  question mark replaced by that variable
            ps.setInt(2, price);
            ps.setString(3, color);

            //execute the query
            ps.executeUpdate();
            System.out.println("Data inserted successfully");
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
        }

        //read the data from database
        ArrayList<ArrayList<String>> myData = objDb.getData(tableName, columnNames);
        System.out.println(myData);
    }

}

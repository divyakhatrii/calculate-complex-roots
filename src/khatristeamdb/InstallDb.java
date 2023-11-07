package khatristeamdb;

import khatristeamdb.JavaDb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author divyak
 */
public class InstallDb
{
    public static void main(String[] args)
    {
        String dbName = "imaginary";
        //creating an object of DB class
        JavaDb objDb = new JavaDb();// no paramenters since we havent made a database yet
        //create a new database
        objDb.createDb(dbName);//dont need to run this a second time,only the first time so good practice to comment it out

        //creating a new table
        String newTable = "CREATE TABLE RectangularForm (InputNo int, RectForm varchar(30), PolarForm varchar(30))";
        objDb.createTable(newTable,dbName);
    }
}

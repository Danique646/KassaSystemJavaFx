package com.kjellvos.school.databaseConnectivityTest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by kjevo on 2/21/17.
 */
public class DatabaseStuffForDatabaseConnectivityTest {
    MainForDatabaseConnectivityTest main;

    Connection connection;
    Statement statement;

    static final String DB_URL = "jdbc:mysql://localhost/DatabaseConnectivityTestDatabase?autoReconnect=true&useSSL=false";
    static final String USER = "DatabaseConnectivityTest";
    static final String PASS = "password123321";

    private ObservableList<ValuesForDatabaseConnectivityTest> data;

    public DatabaseStuffForDatabaseConnectivityTest(MainForDatabaseConnectivityTest main){
        this.main = main;
        refreshData();
    }


    public void insertValueToDatabase(int value) {
        try{
            statement = connection.createStatement();
            statement.execute("INSERT INTO DatabaseJavaValues SET value='" + value + "';");
        }catch (SQLException SQLex){
            SQLex.printStackTrace();
        }
        refreshData();
        main.getSceneForDatabaseConnectivityTest().refreshTableView(data);
    }

    private void refreshData() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "SELECT ID, Value FROM DatabaseJavaValues;";

            ResultSet resultSet = statement.executeQuery(sql);


            data = FXCollections.observableArrayList();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                int value = resultSet.getInt("Value");
                data.add(new ValuesForDatabaseConnectivityTest(id, value));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<ValuesForDatabaseConnectivityTest> getData() {
        return data;
    }

    /*
    SQL to create database:
--
-- Database: `DatabaseConnectivityTestDatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `DatabaseJavaValues`
--

CREATE TABLE `DatabaseJavaValues` (
  `ID` int(11) NOT NULL,
  `Value` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DatabaseJavaValues`
--
ALTER TABLE `DatabaseJavaValues`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `DatabaseJavaValues`
--
ALTER TABLE `DatabaseJavaValues`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
    */
}

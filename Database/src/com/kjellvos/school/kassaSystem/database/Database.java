package com.kjellvos.school.kassaSystem.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;

/**
 * Created by kjevo on 2/24/17.
 */
public class Database {
    Connection connection;
    PreparedStatement statement;

    static final String DB_URL = "jdbc:mysql://localhost/KassaSystem?autoReconnect=true&useSSL=false";
    static final String USER = "KassaSystem";
    static final String PASS = "password123321";

    public void uploadToItemsAndImages(File file, String name, String description, double price, boolean defaultPrice, Date validFrom, Date validTill){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO Items SET Name=?, Description=?;";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();
            int itemsID = 0;
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                itemsID = resultSet.getInt(1);
            }

            FileInputStream fis = new FileInputStream(file);

            sql = "INSERT INTO Images SET ItemsID=?, Image=?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, itemsID);
            statement.setBinaryStream(2, fis, file.length());
            statement.executeUpdate();

            sql = "INSERT INTO Prices SET ItemsID=?, price=?, defaultPrice=?, ValidFrom=?, ValidTill=?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, itemsID);
            statement.setDouble(2, price);
            statement.setBoolean(3, defaultPrice);
            statement.setDate(4, validFrom);
            statement.setDate(5, validTill);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<Item> getData() {
        ObservableList<Item> data = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM Items;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            data = FXCollections.observableArrayList();
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");

                sql = "SELECT * FROM Images WHERE ItemsID='" + id + "';";
                statement = connection.prepareStatement(sql);

                ResultSet resultSet1 = statement.executeQuery(sql);
                while (resultSet1.next()){
                    InputStream img = resultSet1.getBinaryStream("Image");

                    ImageView imageView = new ImageView();
                    imageView.setImage(new Image(img));
                    sql = "SELECT * FROM Prices WHERE ItemsID='" + id + "';";
                    statement = connection.prepareStatement(sql);

                    ResultSet resultSet2 = statement.executeQuery(sql);
                    while (resultSet2.next()) {
                        double price = resultSet2.getDouble("price");
                        boolean defaultPrice = resultSet2.getBoolean("defaultPrice");
                        CheckBox defaultPriceCheckBox = new CheckBox("Ja");
                        defaultPriceCheckBox.setSelected(defaultPrice);
                        Date validFrom = resultSet2.getDate("ValidFrom");
                        Date validTill = resultSet2.getDate("ValidTill");

                        data.add(new Item(id, name, description, imageView, new Button("Show image"), price, defaultPriceCheckBox, validFrom, validTill));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }
}

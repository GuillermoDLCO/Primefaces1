/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo
 */
public class DAO {
    
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mitocode?useSSL=false","root","admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void cerrar(){
        try{
        if(conn!=null){
            if(conn.isClosed()==false){
                conn.close();
            }
        }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
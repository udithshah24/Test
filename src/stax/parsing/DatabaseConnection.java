/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stax.parsing;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Locale;

/**
 *
 * @author udit
 */
public class DatabaseConnection {
    String username="root",password="udit24";
    Connection con=null;
    Statement stmt=null;
    PreparedStatement ps=null;
    
    
    public DatabaseConnection(){
        try{
      Class.forName("com.mysql.jdbc.Driver");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/XMLTesting", username, password);
      stmt= con.createStatement();
     
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    public void insertFeed(FeedMessage fm){
        String sql="INSERT INTO article(title,description,link,guid,pubDate) VALUES( ?, ?, ?, ?, ? )";
        int articleid=0;
        String tempdate="";
       Date d=null;
       try{       
        DateFormat formatter=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        //Wed, 10 Oct 2012 01:16:48 +0000
        d=formatter.parse(fm.getPubDate());//parsing string of Rcs 822 date format to date object.
        formatter=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");//Add new format which is compitible with mysql datetime
        tempdate=formatter.format(d);//format that PubDate to the required format.
        System.out.println(tempdate);
        }catch(ParseException pe){
            System.out.println(pe.toString());
        }
        try{
        ps=con.prepareStatement(sql);
        ps.setString( 1, fm.getTitle().toString());
        ps.setString( 2, fm.getDescription().toString());
        ps.setString( 3, fm.getLink().toString());
        ps.setString( 4, fm.getGuid().toString());
        ps.setString( 5, tempdate);
        ps.executeUpdate();
        ResultSet rs=stmt.executeQuery("SELECT last_insert_id()");//To insert categories/tags to categories table,articles primary key is fetched. 
        while(rs.next()){
            articleid=rs.getInt(1);
        }
        System.out.println(articleid);
        ArrayList<String> categories=fm.getCategories();//Arraylist of all the categories of this article.
        sql="Insert into categories(categoryname,articleid) values";
        for(int i=0;i<categories.size();i++){
            sql+="('"+categories.get(i)+"','"+articleid+"')";
            if(i==(categories.size()-1)){
                
            }else
            {
                sql+=",";
            }
        }
        stmt.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

}

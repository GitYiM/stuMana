import model.Student;

import javax.swing.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sql {
    private static final int ROWs=37;
    private static String sql;
    private static String searchKeySno;
    private static String searchKeyName;

    public Sql(){
    }
    public Sql(String searchKeySno,String searchKeyName){
        this.searchKeySno = searchKeySno;
        this.searchKeyName = searchKeyName;
    }


    public  Object[][]  searchBySno(){
        sql = "SELECT name,sno,math,linemath,decremath,physics,history,C,mental,togec,(math+linemath+decremath+physics+history+C+mental+togec) FROM stu WHERE sno="+
                "\""+searchKeySno+"\"" +"ORDER by (math+linemath+decremath+physics+history+C+mental+togec) DESC";
        Connection con =JDBCConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            int i=0;
            while (rs.next()){
                Student student = new Student(rs.getString(1),rs.getString(2),null,rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(9));
                String[] data = student.toDatas();
                datas[i] = data;

                i++;
            }
            System.out.println(datas.length);
            rs.close();
            psmt.close();
            con.close();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Object[][] searchAll(){
        sql = "SELECT name,sno,math,linemath,decremath,physics,history,C,mental,togec,(math+linemath+decremath+physics+history+C+mental+togec) FROM " +
                "stu ORDER by (math+linemath+decremath+physics+history+C+mental+togec) DESC";
        Connection con =JDBCConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            int i=0;
            while (rs.next()){
                Student student = new Student(rs.getString(1),rs.getString(2),null,rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(9));
                String[] data = student.toDatas();
                datas[i] = data;
                i++;
            }
            rs.close();
            psmt.close();
            con.close();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] searchByName(){
        sql = "SELECT name,sno,math,linemath,decremath,physics,history,C,mental,togec,(math+linemath+decremath+physics+history+C+mental+togec) FROM stu WHERE name="
                +"\""+searchKeyName+"\"" +"ORDER by (math+linemath+decremath+physics+history+C+mental+togec) DESC";
        Connection con =JDBCConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            int i=0;
            while (rs.next()){
                Student student = new Student(rs.getString(1),rs.getString(2),null,rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(9));
                String[] data = student.toDatas();
                datas[i] = data;
                i++;
            }
            rs.close();
            psmt.close();
            con.close();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] searchByNameAndSno(){
        sql = "SELECT name,sno,math,linemath,decremath,physics,history,C,mental,togec,(math+linemath+decremath+physics+history+C+mental+togec) FROM stu where name="
                +"\""+searchKeyName+"\""+"AND sno="+"\""+searchKeySno+"\""+"ORDER by (math+linemath+decremath+physics+history+C+mental+togec) DESC";
        Connection con =JDBCConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            int i=0;
            while (rs.next()){
                Student student = new Student(rs.getString(1),rs.getString(2),null,rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(9));
                String[] data = student.toDatas();
                datas[i] = data;
                i++;
            }
            rs.close();
            psmt.close();
            con.close();
            return datas;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int deleRow(String sno){
        System.out.println(sno);
        sql = " DELETE from stu where sno="+"\""+sno+"\"";
        Connection con =JDBCConnection.getConnection();
        int rs;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);
            rs = psmt.executeUpdate();
            psmt.close();
            con.close();
            System.out.println(rs);
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int changeGrade(String sno,String chosed,String value){
        sql = "UPDATE stu set "+chosed+"="+"\""+value+"\""+"where sno="+"\""+sno+"\"";
        String sql2 = "select ?,? from stu where sno = ?";
        String sql3 = "update stu set total = ? where sno=?";
        Connection con =JDBCConnection.getConnection();
        int rs;
        PreparedStatement psmt= null;
        Object[][] datas= new Object[ROWs][];
        try{
            psmt = con.prepareStatement(sql);

            rs = psmt.executeUpdate();

            psmt.close();
            con.close();
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public String login(String usrName){
        sql = "SELECT password from usr where usrName=?";
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt=null;
        ResultSet rs=null;
        String password = null;
        try{
            psmt = con.prepareStatement(sql);
            psmt.setString(1,usrName);
            rs = psmt.executeQuery();
            while(rs.next()){
                password = rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return password;
    }

}

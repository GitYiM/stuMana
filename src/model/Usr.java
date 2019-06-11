package model;
public class Usr{
    String usrName;
    String password;
    public Usr(){
    }
    public Usr(String usrName,String password){
        this.usrName = usrName;
        this.password = password;
    }
    void setUsrName(String usrName){
        this.usrName = usrName;
    }
    void setPassword(String password){
        this.password = password;
    }
    String getUsrName(){
        return usrName;
    }
    String getPassword(){
        return password;
    }
}

package model;

public class Student {
       private String stuName;
       private String sno;
       private String stuPhone;
       private String mathGrade;
       private String lineMathGrade;
       private String decreMathGrade;
       private String physicsGrade;
       private String historyGrade;
       private String Cgrade;
       private String togeCGrade;
       private String mentalGrade;
       private String totalGrade;
       private Double total;
       public Student(){
       }

    public Student(String stuName, String sno, String stuPhone, String mathGrade, String lineMathGrade, String decreMathGrade, String physicsGrade, String historyGrade, String cgrade, String togeCGrade, String mentalGrade) {
        this.stuName = stuName;
        this.sno = sno;
        this.stuPhone = stuPhone;
        this.mathGrade = mathGrade;
        this.lineMathGrade = lineMathGrade;
        this.decreMathGrade = decreMathGrade;
        this.physicsGrade = physicsGrade;
        this.historyGrade = historyGrade;
        this.Cgrade = cgrade;
        this.togeCGrade = togeCGrade;
        this.mentalGrade = mentalGrade;
         this.total=Double.parseDouble(mathGrade)+Double.parseDouble(lineMathGrade)+Double.parseDouble(decreMathGrade)+Double.parseDouble(physicsGrade)+Double.parseDouble(historyGrade)+Double.parseDouble(cgrade)+Double.parseDouble(togeCGrade)+Double.parseDouble(mentalGrade);
        this.totalGrade = total.toString();
       }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(String mathGrade) {
        this.mathGrade = mathGrade;
    }

    public String getLineMathGrade() {
        return lineMathGrade;
    }

    public void setLineMathGrade(String lineMathGrade) {
        this.lineMathGrade = lineMathGrade;
    }

    public String getDecreMathGrade() {
        return decreMathGrade;
    }

    public void setDecreMathGrade(String decreMathGrade) {
        this.decreMathGrade = decreMathGrade;
    }

    public String getPhysicsGrade() {
        return physicsGrade;
    }

    public void setPhysicsGrade(String physicsGrade) {
        this.physicsGrade = physicsGrade;
    }

    public String getHistoryGrade() {
        return historyGrade;
    }

    public void setHistoryGrade(String historyGrade) {
        this.historyGrade = historyGrade;
    }

    public String getCgrade() {
        return Cgrade;
    }

    public void setCgrade(String cgrade) {
        Cgrade = cgrade;
    }

    public String getTogeCGrade() {
        return togeCGrade;
    }

    public void setTogeCGrade(String togeCGrade) {
        this.togeCGrade = togeCGrade;
    }

    public String getMentalGrade() {
        return mentalGrade;
    }

    public void setMentalGrade(String mentalGrade) {
        this.mentalGrade = mentalGrade;
    }

    public String getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(String totalGrade) {
        this.totalGrade = totalGrade;
    }
    public String[] toDatas(){
           String[] datas =  {this.stuName,this.sno,this.mathGrade,this.lineMathGrade,this.decreMathGrade,this.physicsGrade,this.historyGrade,this.Cgrade,this.mentalGrade,this.togeCGrade,this.totalGrade};
            return datas;
       }
}

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PutInPanel extends JPanel {

    private Font myFont, font2;
    private JLabel stuNumber, stuName; //学生信息
    private JTextField stuNumInput, stuNameInput; //信息输入
    private JLabel math, linerMath, physics, history, cLan, discreteMath, mentalHealth, cOnline;//各门成绩
    private JTextField mathInput, linerMathInput, physicsInput, historyInput, cLanInput, discreteMathInput, mentalHealthInput, cOnlineInput;//成绩输入
    private JButton save, cancel;//按钮
    private String sno, name;
    private double mathGrade, lineMahtGrade, physicsGrade, historyGrade, cLanGrade, discreteGrade, mentalGrade, cOlineGrade,totalGrade;


//    public static  void showChooseDialog(Frame owner,Component parentComponent){
//        final JDialog dialog = new JDialog(owner,"提示",true);
//        JPanel diaPane = new JPanel();
//        dialog.setLocationRelativeTo(parentComponent);
//        dialog.setResizable(false);
//        dialog.setSize(300,300);
//        JButton conti = new JButton("继续录入");
//
//        conti.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        JButton cancel = new JButton("取消");
//        cancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        dialog.setVisible(true);
//        dialog.setContentPane(diaPane);
//    }


    public PutInPanel(JTabbedPane parent) {
        setLayout(null);
        font2 = new Font("楷体", Font.BOLD, 15);
        //学生信息
        myFont = new Font("楷体", Font.BOLD, 20);

        stuNumber = new JLabel("学号：");
        stuNumber.setFont(myFont);
        stuNumber.setBounds(200, 100, 150, 50);

        stuNumInput = new JTextField(15);
        stuNumInput.setBounds(300, 110, 150, 30);
        stuNumInput.setFont(myFont);

        stuName = new JLabel("姓名：");
        stuName.setFont(myFont);
        stuName.setBounds(200, 160, 150, 50);

        stuNameInput = new JTextField(15);
        stuNameInput.setFont(myFont);
        stuNameInput.setBounds(300, 170, 150, 30);

        add(stuNumber);
        add(stuNumInput);
        add(stuName);
        add(stuNameInput);


        //成绩信息
        math = new JLabel("高等数学：");
        math.setFont(myFont);
        math.setBounds(500, 100, 150, 50);
        linerMath = new JLabel("线性代数：");
        linerMath.setFont(myFont);
        linerMath.setBounds(500, 160, 150, 50);
        discreteMath = new JLabel("离散数学：");
        discreteMath.setFont(myFont);
        discreteMath.setBounds(500, 220, 150, 50);
        mentalHealth = new JLabel("心理健康：");
        mentalHealth.setFont(myFont);
        mentalHealth.setBounds(500, 280, 150, 50);
        physics = new JLabel("大学物理：");
        physics.setFont(myFont);
        physics.setBounds(800, 100, 150, 50);
        cLan = new JLabel("C语言：");
        cLan.setFont(myFont);
        cLan.setBounds(800, 160, 150, 50);
        history = new JLabel("近代史：");
        history.setFont(myFont);
        history.setBounds(800, 220, 150, 50);
        cOnline = new JLabel("集中上机：");
        cOnline.setFont(myFont);
        cOnline.setBounds(800, 280, 150, 50);

        mathInput = new JTextField();
        mathInput.setFont(myFont);
        mathInput.setBounds(600, 110, 150, 30);
        linerMathInput = new JTextField();
        linerMathInput.setFont(myFont);
        linerMathInput.setBounds(600, 170, 150, 30);
        physicsInput = new JTextField();
        physicsInput.setFont(myFont);
        physicsInput.setBounds(900, 110, 150, 30);
        cLanInput = new JTextField();
        cLanInput.setFont(myFont);
        cLanInput.setBounds(900, 170, 150, 30);
        discreteMathInput = new JTextField();
        discreteMathInput.setFont(myFont);
        discreteMathInput.setBounds(600, 230, 150, 30);
        historyInput = new JTextField();
        historyInput.setFont(myFont);
        historyInput.setBounds(900, 230, 150, 30);
        mentalHealthInput = new JTextField();
        mentalHealthInput.setFont(myFont);
        mentalHealthInput.setBounds(600, 290, 150, 30);
        cOnlineInput = new JTextField();
        cOnlineInput.setFont(myFont);
        cOnlineInput.setBounds(900, 290, 150, 30);
        add(math);
        add(linerMath);
        add(physics);
        add(cLan);
        add(discreteMath);
        add(history);
        add(mentalHealth);
        add(cOnline);

        add(mathInput);
        add(linerMathInput);
        add(physicsInput);
        add(cLanInput);
        add(discreteMathInput);
        add(historyInput);
        add(mentalHealthInput);
        add(cOnlineInput);

        //操作按钮
        save = new JButton("录入");
        save.setFont(font2);
        save.setBounds(500, 400, 70, 30);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = JDBCConnection.getConnection();
                PreparedStatement psmt = null;
                String sql = "INSERT INTO stu(name,sno,math,linemath,decremath,physics,history,C,mental,togec) VALUES(?,?,?,?,?,?,?,?,?,?)";
                sno = stuNumInput.getText();
                name = stuNameInput.getText();
                mathGrade = Double.parseDouble(mathInput.getText());
                lineMahtGrade = Double.parseDouble(linerMathInput.getText());
                physicsGrade = Double.parseDouble(physicsInput.getText());
                historyGrade =Double.parseDouble(historyInput.getText());
                cLanGrade = Double.parseDouble(cLanInput.getText());
                discreteGrade = Double.parseDouble(discreteMathInput.getText());
                mentalGrade = Double.parseDouble(mentalHealthInput.getText());
                cOlineGrade = Double.parseDouble(cOnlineInput.getText());
                totalGrade = mathGrade+lineMahtGrade+physicsGrade+historyGrade+cLanGrade+discreteGrade+mentalGrade+cOlineGrade;
                try {
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, name);
                    psmt.setString(2, sno);
                    psmt.setDouble(3, mathGrade);
                    psmt.setDouble(4, lineMahtGrade);
                    psmt.setDouble(5, discreteGrade);
                    psmt.setDouble(6, physicsGrade);
                    psmt.setDouble(7, historyGrade);
                    psmt.setDouble(8, cLanGrade);
                    psmt.setDouble(9, mentalGrade);
                    psmt.setDouble(10, cOlineGrade);
//                    psmt.setDouble(11,totalGrade);
                    Object[] options = new Object[]{"继续录入", "查看列表"};
                    if (psmt.executeUpdate()!= 0) {
                        UIManager.put("OptionPane.messageFont",new FontUIResource(new Font("楷体",Font.BOLD,20)));
                        UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("楷体",Font.ITALIC,15)));
                        int optionSelected = JOptionPane.showOptionDialog(parent, "                         录入成功，接下来您要？                         ", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (optionSelected == 0) {
                            stuNameInput.setText("");
                            stuNumInput.setText("");
                            mathInput.setText("");
                            linerMathInput.setText("");
                            physicsInput.setText("");
                            historyInput.setText("");
                            cLanInput.setText("");
                            discreteMathInput.setText("");
                            mentalHealthInput.setText("");
                            cOnlineInput.setText("");
                        } else if (optionSelected == 1) {
                            stuNameInput.setText("");
                            stuNumInput.setText("");
                            mathInput.setText("");
                            linerMathInput.setText("");
                            physicsInput.setText("");
                            historyInput.setText("");
                            cLanInput.setText("");
                            discreteMathInput.setText("");
                            mentalHealthInput.setText("");
                            cOnlineInput.setText("");
                            parent.setSelectedIndex(1);
                        }
                    }
                    psmt.close();
                    con.close();
                } catch (Exception E) {
                    E.printStackTrace();
                }


            }
        });
        cancel = new JButton("返回");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListPanel.refreshData();
                parent.setSelectedIndex(1);
            }
        });
        cancel.setFont(font2);
        cancel.setBounds(700, 400, 70, 30);

        add(save);
        add(cancel);
    }

}



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField usrName;
    private JPasswordField password;
    private JButton submit;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    double windowWidth ;
    double windowHeight;
    int windowX;
    int windowY;
    private String inputUsrname;
    private String inputPassword;
    public LoginWindow() {
    }

    public LoginWindow(String title, int x, int y, int width, int height) {
        init(title);
        windowWidth = width;
        windowHeight = height;
        windowX = x;
        windowY = y;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(x, y, width, height);
    }

    public void init(String title) {
//        setLayout(new FlowLayout());
        setLayout(null);
        setTitle(title);
        JLabel welComeLable = new JLabel("Student Grade Management System");
        Font font = new Font("Serief",Font.ITALIC+Font.BOLD,30);
        welComeLable.setFont(font);
        welComeLable.setBounds(210,10,700,200);
        add(welComeLable);
        JLabel usrLable = new JLabel("用户名: ");

        usrLable.setBounds(320,220,100,30);
        add(usrLable);
        usrName = new JTextField("admin", 11);
        usrName.setBounds(360,220,230,30);
        add(usrName);
        JLabel pwdLabel = new JLabel("密码: ");
        pwdLabel.setBounds(320,270,100,30);
        add(pwdLabel);
        password = new JPasswordField("123456", 11);
        password.setBounds(360,270,230,30);
        add(password);
        submit = new JButton("登录");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==submit){
                    inputUsrname = usrName.getText();
                    inputPassword =String.copyValueOf(password.getPassword());
                    Sql  sql = new Sql();
                    String rightPwd =  sql.login(inputUsrname);
                    System.out.println(password.getPassword());
                    if(rightPwd.equals(inputPassword)){
                        dispose();
                        new MainWindow("学生成绩管理系统",windowX-200,windowY-100,(int)(windowWidth*1.5),(int)(windowHeight*1.5));
                    }else{
                        JOptionPane.showMessageDialog(getParent(),"密码错误，请确认你的密码");
                        password.setText("");
                    }

                }
            }
        });
        submit.setBounds(430,330,80,30);
        add(submit);
    }
}

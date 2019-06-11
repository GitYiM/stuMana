import javax.swing.*;
import java.awt.*;



public class swingIndex {
    public static void main(String[] args){
//        JFrame ManaFrame = new JFrame("学生课程管理系统");
//        ManaFrame.setVisible(true);
//
//        ManaFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double windowWidth = dimension.getWidth();
        double windowHeight = dimension.getHeight();
        double x  = windowWidth/4;
        double y = windowHeight/4;
        double width = windowWidth/2;
        double height  = windowHeight/2;
        String title  = "学生成绩管理系统";
        LoginWindow loginWindow = new LoginWindow(title,(int)x,(int)y,(int)width,(int)height);
    }

}

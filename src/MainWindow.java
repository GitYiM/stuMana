import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow extends JFrame {
     private JMenuBar jMenuBar;
    private JMenu menu1,subMenu;
     private JMenuItem addStuInfo,queryStuInfo;
     private JTabbedPane mainTab;
    public MainWindow(){}
    public MainWindow(String s, int x, int y, int width, int height){
        init(s);
        setLocation(x,y);
        setSize(width,height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void init(String s) {
        setTitle(s);
        mainTab = new JTabbedPane(JTabbedPane.LEFT);
//        setLayout(null);
//        jMenuBar= new JMenuBar();
//        menu1 = new JMenu("功能选项");
//        addStuInfo = new JMenuItem("添加学生信息");
//        queryStuInfo  = new JMenuItem("信息查询界面");
//        menu1.add(addStuInfo);
//        menu1.add(queryStuInfo);
//        jMenuBar.add(menu1);
//        setJMenuBar(jMenuBar);
        mainTab.addTab("录入成绩",new PutInPanel(mainTab));
        mainTab.addTab("成绩列表",new ListPanel(mainTab));
        mainTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane jTabbedPane =(JTabbedPane)e.getSource();
                int selectedIndex = jTabbedPane.getSelectedIndex();
                switch (selectedIndex){
                    case 1:ListPanel.refreshData();
                    break;
                    default:
                        break;
                }
            }
        });
        mainTab.validate();
        add(mainTab);
        validate();


    }
}

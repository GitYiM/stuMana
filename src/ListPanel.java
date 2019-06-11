import com.mysql.cj.protocol.Resultset;
import com.sun.deploy.panel.JHighDPITable;
import com.sun.deploy.panel.JreTableModel;
import model.Student;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListPanel extends JPanel {
    private static final int ROWs=37;
    private JLabel nameSearch, phoneSearch, snoSearch;
    private JTextField nameSearchInput, phoneSearchInput, snoSearchInput;
    private JButton searchButton,clearButton;
    private Font font1, font2;
    private static String[] titles = {"姓名", "学号", "高等数学", "线性代数", "离散数学", "大学物理", "近代史", "C语言", "心理健康", "集中上机", "总分"};
    private  static Object[][]  tableDatas = new Object[ROWs][];
    private  static JTable listTable;
    private static DefaultTableModel listTableModel;
    private static JScrollPane scrollPane;
    private static int toDeleRowIndex;
    private static JTabbedPane parentS;
    private static String[] chooseLable = {"高等数学","线性代数","离散数学","大学物理","近代史","C语言","心理健康","集中上机"};
    private static String[] chooseOptions ={"math","linemath","decremath","physics","history","C","mental","togeC"};

    private static JPopupMenu rightClick;



    public static  void refreshData(){
        System.out.println("执行刷新数据");
        initData();
        listTableModel =new DefaultTableModel(tableDatas, titles);
        listTable.setModel(listTableModel);
    }
    private static void createPopupMenu(JTabbedPane parent){
        rightClick = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem();
        JMenuItem changeItem = new JMenuItem();
        changeItem.setText("  修改成绩  ");
        changeItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sno = (String) tableDatas[toDeleRowIndex][1];
                        int seleced =  JOptionPane.showOptionDialog(parent,"请选择修改项","修改",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,chooseLable,chooseLable[0]);
                        if(seleced>-1){
                            String chosed = chooseOptions[seleced];
                            String value = JOptionPane.showInputDialog("修改"+chosed+"成绩为?");
                            Sql sql = new Sql();
                            if(sql.changeGrade(sno,chosed,value)>0){
                              JOptionPane.showMessageDialog(parent,"修改成功");
                              refreshData();
                            }
                        }
                    }
                }
        );
        deleteItem.setText("  删除  ");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            //右键删除事件
            public void actionPerformed(ActionEvent e) {
                String deleSno = (String) tableDatas[toDeleRowIndex][1];  //学号
                Sql sql = new Sql();
                if(sql.deleRow(deleSno)!=0){
                    System.out.println(toDeleRowIndex);
                    listTableModel.removeRow(toDeleRowIndex);
                    listTable.setModel(listTableModel);
                    JOptionPane.showMessageDialog(parent,"删除成功");
                }

            }
        });
        rightClick.add(deleteItem);
        rightClick.add(changeItem);
    }

    private void jtableMouseClicked(MouseEvent e){
        mouseRightButtonClicked(e);
    }
    private void mouseRightButtonClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            int fouceIndex = listTable.rowAtPoint(e.getPoint());
            if(fouceIndex==-1){
                return;
            }
            listTable.setRowSelectionInterval(fouceIndex,fouceIndex);
            toDeleRowIndex = fouceIndex;
            rightClick.show(listTable,e.getX(),e.getY());
        }
    }

    public static void initData(){
        tableDatas=new Object[ROWs][];
        Connection con = JDBCConnection.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "SELECT name,sno,math,linemath,decremath,physics,history,C,mental,togec,(math+linemath+decremath+physics+history+C+mental+togec) FROM stu ORDER by (math+linemath+decremath+physics+history+C+mental+togec) DESC";

        try {
            psmt = con.prepareStatement(sql);
            rs= psmt.executeQuery();
            int i = 0;
            while(rs.next()){

                Student student = new Student(rs.getString(1),rs.getString(2),null,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(10),rs.getString(9));
//                String[] datas = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7)
//                        ,rs.getString(8),rs.getString(10),rs.getString(9),rs.getString(11)};
                String[] datas = student.toDatas();
//                System.out.println(datas.length);
               tableDatas[i] = datas;
               i++;
            }
            rs.close();
            psmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ListPanel(JTabbedPane parent) {
        createPopupMenu(parent);
        initData();
        setLayout(null);
        font1 = new Font("楷体", Font.BOLD, 20);
        font2 = new Font("楷体", Font.BOLD, 15);
        nameSearch = new JLabel("姓名：");
        nameSearch.setFont(font1);
        nameSearch.setBounds(50, 50, 150, 50);
//        phoneSearch = new JLabel("电话号码：");
//        phoneSearch.setBounds(650, 50, 150, 50);
//        phoneSearch.setFont(font1);
        snoSearch = new JLabel("学号：");
        snoSearch.setFont(font1);
        snoSearch.setBounds(350, 50, 150, 50);


        nameSearchInput = new JTextField();
        nameSearchInput.setFont(font1);
        nameSearchInput.setBounds(150, 60, 150, 30);
//        phoneSearchInput = new JTextField();
//        phoneSearchInput.setBounds(750, 60, 150, 30);
//        phoneSearchInput.setFont(font1);
        snoSearchInput = new JTextField();
        snoSearchInput.setFont(font1);
        snoSearchInput.setBounds(450, 60, 150, 30);
        add(nameSearch);
        add(nameSearchInput);
//        add(phoneSearch);
        add(snoSearch);
//        add(phoneSearchInput);
        add(snoSearchInput);

        searchButton = new JButton("查询");
        searchButton.setFont(font2);
        searchButton.setBounds(1220, 60, 70, 30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(snoSearchInput.getText()+nameSearchInput.getText());

                if(!snoSearchInput.getText().equals("")&&!nameSearchInput.getText().equals("")){
                    System.out.println(snoSearchInput.getText()+1);
                    Sql sql = new Sql(snoSearchInput.getText(),nameSearchInput.getText());
                    tableDatas = sql.searchByNameAndSno();
                }else if(!nameSearchInput.getText().equals("")){
                    System.out.println(2);
                    Sql  sql= new Sql(null,nameSearchInput.getText());
                    tableDatas = sql.searchByName();
                }else if (!snoSearchInput.getText().equals("")){
                    System.out.println(3);

                    Sql  sql= new Sql(snoSearchInput.getText(),null);
                    tableDatas = sql.searchBySno();
                }else{
                    System.out.println(4);

                    Sql sql = new Sql();
                    tableDatas = sql.searchAll();
                }
                listTableModel =new DefaultTableModel(tableDatas, titles);
                listTable.setModel(listTableModel);
            }
        });

        add(searchButton);
        clearButton = new JButton("清空列表");
        clearButton.setFont(font2);
        clearButton.setBounds(1100, 60, 100, 30);
        add(clearButton);

        String[] options = new String[]{"确认清空"};

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont",new FontUIResource(new Font("楷体",Font.BOLD,20)));
                UIManager.put("OptionPane.buttonFont",new FontUIResource(new Font("楷体",Font.ITALIC,15)));
               int selected =  JOptionPane.showOptionDialog(parent,"是否确认清空列表（清空后，目前暂无恢复功能）","清空列表",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                if(selected>=0) {
                    Connection con = JDBCConnection.getConnection();
                    PreparedStatement psmt =null;

                    String sql = "DELETE FROM stu";
                    try{
                        psmt = con.prepareStatement(sql);
                        if(psmt.executeUpdate()!=0){
                            listTableModel.setRowCount(0);
                            JOptionPane.showMessageDialog(parent,"清空列表成功");
                        }

                    }catch (Exception E){
                        E.printStackTrace();
                    }
                }

            }
        });
        listTableModel = new DefaultTableModel(tableDatas, titles);
        listTable = new JTable(listTableModel);
        listTable.setBounds(50,100,1250,600);
        listTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtableMouseClicked(e);
            }
        });
        scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(50, 100, 1250, 600);
        add(scrollPane);

    }

}


package student.modle;

import student.view.StudentFrame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class openStudentFrame extends JFrame {

    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;

    public openStudentFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 王康
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setForeground(Color.cyan);
        setBackground(Color.cyan);
        setTitle("\u5b66\u751f\u7ba1\u7406\u7cfb\u7edf\u767b\u9646\u754c\u9762");
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0),
                    "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", TitledBorder.CENTER,
                    TitledBorder.BOTTOM, new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12),
                    Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062or\u0064er".equals(e.getPropertyName()))
                        throw new RuntimeException();
                }
            });

            //---- label1 ----
            label1.setText("\u5b66\u751f\u7ba1\u7406\u7cfb\u7edf");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 8f));

            //---- label2 ----
            label2.setText("\u8d26\u53f7");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));

            //---- label3 ----
            label3.setText("\u5bc6\u7801");

            //---- button1 ----
            button1.setText("\u767b\u9646");
            button1.addActionListener(new ActionListener() { // 登录按钮
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginButtonActionPerformed(); // 调用登录方法
                }
            });

            //---- button2 ----
            button2.setText("\u6ce8\u518c");
            button2.addActionListener(new ActionListener() { // 注册按钮
                @Override
                public void actionPerformed(ActionEvent e) {
                    openRegisterFrame(); // 调用注册方法
                }
            });


            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(label3)
                                            .addComponent(label2))
                                    .addGap(51, 51, 51)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 133,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 144,
                                                    Short.MAX_VALUE)
                                            .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 144,
                                                    Short.MAX_VALUE))
                                    .addContainerGap(121, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup().addGap(93, 93, 93)
                                    .addComponent(button1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                            56, Short.MAX_VALUE)
                                    .addComponent(button2).addGap(87, 87, 87)));
            panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup().addGroup(panel1Layout
                    .createSequentialGroup().addGap(34, 34, 34).addComponent(label1, GroupLayout.PREFERRED_SIZE, 30,
                            GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2).addComponent(textField1, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(35, 35, 35)
                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3).addComponent(passwordField1, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18).addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1).addComponent(button2))
                    .addContainerGap(32, Short.MAX_VALUE)));

        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup().addContainerGap()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        contentPaneLayout.setVerticalGroup(contentPaneLayout.createParallelGroup().addGroup(contentPaneLayout
                .createSequentialGroup().addContainerGap()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void openStudentFrame() {
        StudentFrame studentFrame = new StudentFrame();
        studentFrame.setVisible(true);
        this.dispose();
    }

    // 打开注册窗体并保存用户信息到文件
    private void openRegisterFrame() {
        String username = JOptionPane.showInputDialog(this, "请输入用户名", "");
        if (username == null) { // 用户点击了取消按钮或关闭了对话框
            return;
        }

        String password = JOptionPane.showInputDialog(this, "请输入密码", "");
        if (password == null) { // 用户点击了取消按钮或关闭了对话框
            return;
        }

        String filePath = "users.txt";
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            writer.flush();

            JOptionPane.showMessageDialog(this, "注册成功");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "注册失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 添加检查用户名和密码的逻辑并调用openStudentFrame()方法
    private void loginButtonActionPerformed() {
        String username = textField1.getText().trim();
        String password = new String(passwordField1.getPassword());
        String filePath = "users.txt";
        File file = new File(filePath);

        try {
            if (file.exists() && file.isFile()) {
                boolean found = false;
                String line;
                try (java.util.Scanner scanner = new java.util.Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        if (line.startsWith(username + ":")) {
                            found = true;
                            String pwd = line.substring(username.length() + 1);
                            if (password.equals(pwd)) {
                                openStudentFrame();
                                break;
                            }
                        }
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "用户名不存在", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "密码正确", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "未找到用户信息文件", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "发生异常: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}


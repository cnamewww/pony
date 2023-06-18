package student.view;

import student.DAO.StudentDAO;
import student.student.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentFrame extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField idField;
    private JComboBox<String> majorBox;
    private JComboBox<String> genderBox;
    private JSpinner ageSpinner;
    private JTable table;
    private DefaultTableModel model;

    public StudentFrame() {
        super("学生管理系统");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("姓名"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("学号"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("专业"));
        majorBox = new JComboBox<>(new String[]{"软件技术", "物联网技术", "教育传媒", "宝石鉴定", "食品检测"});
        inputPanel.add(majorBox);
        inputPanel.add(new JLabel("性别"));
        genderBox = new JComboBox<>(new String[]{"男", "女"});
        inputPanel.add(genderBox);
        inputPanel.add(new JLabel("年龄"));
        ageSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        inputPanel.add(ageSpinner);
        JButton addButton = new JButton("添加");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        inputPanel.add(addButton);
        JButton updateButton = new JButton("更新");
        updateButton.setActionCommand("update");
        updateButton.addActionListener(this);
        inputPanel.add(updateButton);

        model = new DefaultTableModel(new String[]{"姓名", "学号", "专业", "性别", "年龄"}, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteButton = new JButton("删除");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(deleteButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void refreshData() {
        model.setRowCount(0);
        List<student> students = StudentDAO.queryStudents();
        for (student student : students) {
            model.addRow(new Object[]{student.getName(), student.getId(), student.getMajor(), student.getGender(), student.getAge()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("add".equals(command)) {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String major = (String) majorBox.getSelectedItem();
            String gender = (String) genderBox.getSelectedItem();
            Integer age = (Integer) ageSpinner.getValue();
            if (!name.isEmpty() && !id.isEmpty() && !major.isEmpty() && !gender.isEmpty()) {
                student student = new student(name, id, major, gender, age);
                boolean result = StudentDAO.addStudent(student);
                if (result) {
                    JOptionPane.showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(this, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "请填写完整信息", "错误", JOptionPane.ERROR_MESSAGE);
            }
            nameField.setText("");
            idField.setText("");
            majorBox.setSelectedIndex(0);
            genderBox.setSelectedIndex(0);
            ageSpinner.setValue(0);
        } else if ("update".equals(command)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String id = (String) model.getValueAt(row, 1);
                String name = nameField.getText().trim();
                String major = (String) majorBox.getSelectedItem();
                String gender = (String) genderBox.getSelectedItem();
                Integer age = (Integer) ageSpinner.getValue();
                if (!name.isEmpty() && !major.isEmpty() && !gender.isEmpty()) {
                    student student = new student(name, id, major, gender, age);
                    boolean result = StudentDAO.updateStudent(student);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "更新成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        refreshData();
                    } else {
                        JOptionPane.showMessageDialog(this, "更新失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请填写完整信息", "错误", JOptionPane.ERROR_MESSAGE);
                }
                nameField.setText("");
                idField.setText("");
                majorBox.setSelectedIndex(0);
                genderBox.setSelectedIndex(0);
                ageSpinner.setValue(0);
                table.clearSelection();
            } else {
                JOptionPane.showMessageDialog(this, "请选择要更新的学生信息", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if ("delete".equals(command)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String id = (String) model.getValueAt(row, 1);
                int option = JOptionPane.showConfirmDialog(this, "确定删除该学生信息吗?", "确认删除", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    boolean result = StudentDAO.deleteStudent(id);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        refreshData();
                    } else {
                        JOptionPane.showMessageDialog(this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
                nameField.setText("");
                idField.setText("");
                majorBox.setSelectedIndex(0);
                genderBox.setSelectedIndex(0);
                ageSpinner.setValue(0);
                table.clearSelection();
            } else {
                JOptionPane.showMessageDialog(this, "请选择要删除的学生信息", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // 设置UI风格
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 运行学生管理系统
        new StudentFrame().setVisible(true);
    }
}
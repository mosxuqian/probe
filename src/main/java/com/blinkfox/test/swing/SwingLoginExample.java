package com.blinkfox.test.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Swing登录示例
 * Created by blinkfox on 2017-02-08.
 */
public class SwingLoginExample {

    /**
     * 创建和设置登录面板
     * @param panel
     */
    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("账 户:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(20, 20, 50, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(70, 20, 220, 25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密 码:");
        passwordLabel.setBounds(20, 60, 50, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(70, 60, 220, 25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("登 录");
        loginButton.setBounds(70, 110, 80, 25);
        panel.add(loginButton);

        // 创建重置按钮
        JButton resetBtn = new JButton("重 置");
        resetBtn.setBounds(180, 110, 80, 25);
        panel.add(resetBtn);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("登录示例");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null); // 居中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
    }

}
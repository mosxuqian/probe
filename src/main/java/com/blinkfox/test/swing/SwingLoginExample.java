package com.blinkfox.test.swing;

import com.blinkfox.utils.Log;
import freeseawind.lf.LittleLuckLookAndFeel;
import org.apache.commons.lang3.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Swing登录示例
 * Created by blinkfox on 2017-02-08.
 */
public class SwingLoginExample {

    private static final Log log = Log.get(SwingLoginExample.class);

    //使用静态代码块加载界面皮肤
    static {
        try {
            EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(LittleLuckLookAndFeel.class.getName());
                    } catch (Exception e) {
                        log.error("设置界面风格失败", e);
                    }
                }
            });
        } catch (Exception e) {
            log.error("页面设置风格失败", e);
        }
    }

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

        // 提示信息组件
        JLabel msgLabel = new JLabel();
        msgLabel.setBounds(70, 90, 150, 20);
        msgLabel.setForeground(Color.RED);
        panel.add(msgLabel);

        // 密码框的回车监听登录事件
        passwordText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            /**
             * 如果松开了回车键，则进行登录
             * @param e
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    validAndLogin(userText, passwordText, msgLabel);
                }
            }
        });

        // 创建登录按钮
        JButton loginButton = new JButton("登 录");
        loginButton.setBounds(70, 120, 80, 25);
        // 登录单击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validAndLogin(userText, passwordText, msgLabel);
            }
        });
        panel.add(loginButton);

        // 创建重置按钮
        JButton resetBtn = new JButton("重 置");
        resetBtn.setBounds(190, 120, 80, 25);
        // 重置的单击事件
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset(userText, passwordText, msgLabel);
            }
        });
        panel.add(resetBtn);
    }

    /**
     * 实现登录判断的方法
     * @param userText
     * @param passwordText
     */
    private static void validAndLogin(JTextField userText, JPasswordField passwordText, JLabel msgLabel) {
        String userName = userText.getText();
        String password = new String(passwordText.getPassword()).trim();

        if (StringUtils.isBlank(userName)) {
            msgLabel.setText("用户账户不能为空！");
            return;
        }

        if (StringUtils.isBlank(password)) {
            msgLabel.setText("账户密码不能为空！");
            return;
        }

        if ("blinkfox".equals(userName) && "123456".equals(password)) {
            msgLabel.setText("登录成功！");
            msgLabel.setForeground(Color.GREEN);
            return;
        } else {
            msgLabel.setText("登录失败！");
            msgLabel.setForeground(Color.RED);
            passwordText.setFocusable(true);
            passwordText.setText("");
            return;
        }
    }

    /**
     * 重置输入框内容
     * @param userText
     * @param passwordText
     */
    private static void reset(JTextField userText, JPasswordField passwordText, JLabel msgLabel) {
        userText.setText("");
        passwordText.setText("");
        msgLabel.setText("");
        msgLabel.setForeground(Color.RED);
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
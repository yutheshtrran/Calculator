import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private double num1, num2, result;
    private String operator;
    private JTextField jTextField1;

    public Calculator() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        JPanel buttonPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 36));
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("CALCULATOR");

        jTextField1.setBackground(new Color(51, 51, 51));
        jTextField1.setFont(new Font("Tahoma", Font.BOLD, 18));
        jTextField1.setForeground(new Color(102, 255, 102));
        jTextField1.setHorizontalAlignment(JTextField.RIGHT);
        jTextField1.setText("0");
        jTextField1.setEditable(false);

        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "Clear", "=", "+",
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Tahoma", Font.BOLD, 18));
            buttonPanel.add(button);
            if (label.matches("\\d")) {
                button.addActionListener(this::numberButtonActionPerformed);
            } else if (label.equals("Clear")) {
                button.addActionListener(this::clearButtonActionPerformed);
            } else if (label.equals("=")) {
                button.addActionListener(this::equalsButtonActionPerformed);
            } else {
                button.addActionListener(this::operatorButtonActionPerformed);
            }
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void numberButtonActionPerformed(ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        String currentText = jTextField1.getText();
        if (currentText.equals("0")) {
            jTextField1.setText(source.getText());
        } else {
            jTextField1.setText(currentText + source.getText());
        }
    }

    private void operatorButtonActionPerformed(ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        num1 = Double.parseDouble(jTextField1.getText());
        operator = source.getText();
        jTextField1.setText("0");
    }

    private void equalsButtonActionPerformed(ActionEvent evt) {
        num2 = Double.parseDouble(jTextField1.getText());
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        jTextField1.setText(String.valueOf(result));
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        jTextField1.setText("0");
        num1 = 0;
        num2 = 0;
        operator = "";
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new Calculator().setVisible(true));
    }

    private JLabel jLabel1;
}

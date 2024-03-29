import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame {

    private JTextField displayField;
    private String currentInput = "";
    private double result = 0;
    private String operation = "";

    public CalculatorUI() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> {
                    currentInput += command;
                    displayField.setText(currentInput);
                }
                case "+", "-", "*", "/" -> {
                    if (!currentInput.isEmpty()) {
                        if (!operation.isEmpty())
                            calculate();
                        result = Double.parseDouble(currentInput);
                        operation = command;
                        currentInput = "";
                    }
                }
                case "=" -> {
                    calculate();
                    operation = "";
                }
                default -> throw new IllegalArgumentException("Invalid operation: " + command);
            }
        }
    }

    private void calculate() {
        double input = Double.parseDouble(currentInput);
        switch (operation) {
            case "+" -> result += input;
            case "-" -> result -= input;
            case "*" -> result *= input;
            case "/" -> result /= input;
        }
        displayField.setText(String.valueOf(result));
        currentInput = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calculator = new CalculatorUI();
            calculator.setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private JTextField passcodeField;
    private JLabel statusLabel;

    private String password = "123"; // Predefined password
    private boolean passwordSet = false;

    public Main() {
        setTitle("Locker Application");
        setSize(300, 250); // Increased height to accommodate the new layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for passcode input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // Create numeric buttons 1-9 in a 3x3 grid
        JPanel numericPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(this);
            numericPanel.add(button);
        }

        inputPanel.add(numericPanel);

        // Text field for passcode input
        passcodeField = new JTextField(10);
        passcodeField.setMaximumSize(passcodeField.getPreferredSize()); // Set maximum size to preferred size
        passcodeField.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(Box.createVerticalStrut(10)); // Vertical spacing
        inputPanel.add(passcodeField);

        // Panel for Clear and Enter buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        buttonPanel.add(enterButton);

        inputPanel.add(Box.createVerticalStrut(10)); // Vertical spacing
        inputPanel.add(buttonPanel);

        add(inputPanel, BorderLayout.CENTER);

        // Status label to show messages
        statusLabel = new JLabel("Enter passcode to set or verify.");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Enter")) {
            String inputPasscode = passcodeField.getText();

            if (!passwordSet) {
                // If password has not been set, check against predefined password
                if (inputPasscode.equals(password)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            } else {
                // Password has been set, verify entered password
                if (inputPasscode.equals(password)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }
        } else if (command.equals("Clear")) {
            // Clear passcode field
            passcodeField.setText("");
        } else {
            // Append numeric button press to passcode field
            String currentPasscode = passcodeField.getText();
            passcodeField.setText(currentPasscode + command);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class EscalaFuncionariosApp extends JFrame {

    private Map<String, String> escala;
    private JTextArea displayArea;

    public EscalaFuncionariosApp() {
        setTitle("Escala de Funcionários");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar a janela na tela

        escala = new HashMap<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Escala de Funcionários");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        String[] diasDaSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        for (String dia : diasDaSemana) {
            JLabel label = new JLabel(dia);
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            centerPanel.add(label);
            JTextField textField = new JTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            centerPanel.add(textField);
            escala.put(dia, "");
        }
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Corrigido: Movido aqui

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 16));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setBackground(new Color(50, 150, 50)); // Cor verde
        salvarButton.setFocusPainted(false);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < centerPanel.getComponentCount(); i += 2) {
                    JLabel label = (JLabel) centerPanel.getComponent(i);
                    JTextField textField = (JTextField) centerPanel.getComponent(i + 1);
                    escala.put(label.getText(), textField.getText());
                }
                exibirEscala();
                JOptionPane.showMessageDialog(null, "Escala salva com sucesso!");
            }
        });
        bottomPanel.add(salvarButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.NORTH); // Alterado: Aqui
        add(mainPanel);
        setVisible(true);
    }

    private void exibirEscala() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : escala.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EscalaFuncionariosApp();
            }
        });
    }
}


package JogoDoMarciano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JogoDoMarcianoGUI extends JFrame {
    private int numeroMarciano;
    private int tentativas;
    private JTextField campoPalpite;
    private JLabel mensagemLabel;
    private JLabel imagemLabel;
    private Image fundo;

    public JogoDoMarcianoGUI() {
        System.out.println("Diretório de trabalho: " + System.getProperty("user.dir"));

        fundo = new ImageIcon("C:\\Users\\Sassaki\\Desktop\\java\\JogoDoMarciano\\space.jpg").getImage();

        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelFundo.setLayout(new GridBagLayout());

        setTitle("Jogo do Marciano");
        setSize(500, 500);
        setResizable(true); // Agora a tela pode ser maximizada
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarJogo();

        ImageIcon icon = new ImageIcon("C:\\Users\\Sassaki\\Desktop\\java\\JogoDoMarciano\\marciano.jpg");

        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Imagem do marciano não carregada!");
        } else {
            System.out.println("Imagem do marciano carregada com sucesso!");
        }

        Image imagemRedimensionada = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));

        mensagemLabel = new JLabel("Adivinhe a árvore (1-100):");
        mensagemLabel.setForeground(Color.WHITE);
        mensagemLabel.setFont(new Font("Arial", Font.BOLD, 18));

        campoPalpite = new JTextField(10);
        campoPalpite.setForeground(Color.WHITE);
        campoPalpite.setBackground(Color.DARK_GRAY);
        campoPalpite.setFont(new Font("Arial", Font.BOLD, 16));

        JButton botaoEnviar = new JButton("Tentar");
        botaoEnviar.setForeground(Color.WHITE);
        botaoEnviar.setBackground(Color.BLACK);
        botaoEnviar.setFont(new Font("Arial", Font.BOLD, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelFundo.add(imagemLabel, gbc);

        gbc.gridy = 1;
        painelFundo.add(mensagemLabel, gbc);

        gbc.gridy = 2;
        painelFundo.add(campoPalpite, gbc);

        gbc.gridy = 3;
        painelFundo.add(botaoEnviar, gbc);

        setContentPane(painelFundo);

        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarPalpite();
            }
        });

        setVisible(true);
    }

    private void iniciarJogo() {
        Random random = new Random();
        numeroMarciano = random.nextInt(100) + 1;
        tentativas = 0;
    }

    private void verificarPalpite() {
        try {
            int palpite = Integer.parseInt(campoPalpite.getText());
            tentativas++;

            if (palpite < numeroMarciano) {
                mensagemLabel.setText("O marciano está em uma árvore maior!");
            } else if (palpite > numeroMarciano) {
                mensagemLabel.setText("O marciano está em uma árvore menor!");
            } else {
                JOptionPane.showMessageDialog(this, "Parabéns! Encontrou em " + tentativas + " tentativas!");
                iniciarJogo();
                mensagemLabel.setText("Adivinhe a árvore (1-100):");
            }
            campoPalpite.setText("");

        } catch (NumberFormatException ex) {
            mensagemLabel.setText("Digite um número válido!");
        }
    }

    public static void main(String[] args) {
        new JogoDoMarcianoGUI();
    }
}
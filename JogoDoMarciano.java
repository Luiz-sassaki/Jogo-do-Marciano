import java.util.Random;
import java.util.Scanner;

public class JogoDoMarciano {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int numeroMarciano = random.nextInt(100) + 1;
        int tentativa = 0;
        int chute;

        System.out.println("Bem-vindo ao Jogo do Marciano!");
        System.out.println("Tente adivinhar em qual árvore o marciano está escondido (1 a 100).");

        do {
            System.out.print("Digite seu palpite: ");
            chute = scanner.nextInt();
            tentativa++;

            if (chute < numeroMarciano) {
                System.out.println("O marciano está em uma árvore com número maior.");
            } else if (chute > numeroMarciano) {
                System.out.println("O marciano está em uma árvore com número menor.");
            } else {
                System.out.println("Parabéns! Você encontrou o marciano em " + tentativa + " tentativas.");
            }
        } while (chute != numeroMarciano);

        scanner.close();
    }
}

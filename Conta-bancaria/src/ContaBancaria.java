import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaBancaria {
    private double saldo;
    private final Scanner scanner;

    public ContaBancaria() {
        this.saldo = 0.0;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> exibirSaldo();
                case 2 -> realizarDeposito();
                case 3 -> realizarSaque();
                case 4 -> {
                    System.out.println("Obrigado por usar nosso sistema. Até logo!");
                    executando = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1 - Ver saldo");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpa entrada errada
            return -1; // opção inválida
        }
    }

    private void exibirSaldo() {
        System.out.printf("Seu saldo atual é: R$ %.2f%n", saldo);
    }

    private void realizarDeposito() {
        System.out.print("Digite o valor para depósito: R$ ");
        try {
            double valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Valor inválido. Depósito deve ser positivo.");
            } else {
                saldo += valor;
                System.out.println("Depósito realizado com sucesso!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Informe um número válido.");
            scanner.nextLine();
        }
    }

    private void realizarSaque() {
        System.out.print("Digite o valor para saque: R$ ");
        try {
            double valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Valor inválido. Saque deve ser positivo.");
            } else if (valor > saldo) {
                System.out.println("Saldo insuficiente.");
            } else {
                saldo -= valor;
                System.out.println("Saque realizado com sucesso!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Informe um número válido.");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria();
        conta.iniciar();
    }
}

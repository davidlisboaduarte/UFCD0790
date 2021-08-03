//User input validation
package mainprojeto;

import java.util.Scanner;

public class Valida {

    Scanner sc = new Scanner(System.in);

    //para int
    public static int validar1(int valida) {
        boolean isNumber;
        Scanner sc = new Scanner(System.in);
        valida = 0;
        do {
            if (sc.hasNextInt()) {
                valida = sc.nextInt();
                isNumber = true;
            } else {
                System.out.println("Dados Inválidos!");
                System.out.println("Introduza de novo os dados para a última pergunta: ");
                isNumber = false;
                sc.next();
            }
        } while (!(isNumber));
        return valida;
    }

    //para double
    public void validar2() {
        boolean isNumber2;

        do {
            if (sc.hasNextDouble()) {
                sc.nextDouble();
                isNumber2 = true;
            } else {
                System.out.println("Dados Inválidos!");
                System.out.println("Introduza de novo os dados para a última pergunta: ");
                isNumber2 = false;
                sc.next();
            }
        } while (!(isNumber2));
    }

    //para String
    public static String validar3(String input) {
        input = "";
        boolean isText;

        do {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.matches("[a-zA-Z\\s\'\"]+")) {
                isText = false;
            } else {
                System.out.println("Dados Inválidos!");
                System.out.println("Introduza de novo os dados para a última pergunta: ");
                isText = true;

            }
        } while (isText);
        return input;
    }

    //para boleano
    public static boolean validar4(boolean input) {
        Scanner sc = new Scanner(System.in);
        boolean dirigent = false;
        String digi = "";
        do {
            //System.out.println("\n (false/true)");

            if (sc.hasNextBoolean()) {
                input = sc.nextBoolean();

                digi = String.valueOf(input);

                if (digi.equalsIgnoreCase("true")) {
                    input = Boolean.parseBoolean(digi);
                    dirigent = true;

                } else if (digi.equalsIgnoreCase("false")) {
                    input = Boolean.parseBoolean(digi);
                    dirigent = true;
                }
            } else {
                dirigent = false;
                System.out.println("Tem de escrever false ou true");
                sc.next();
            }
        } while (!(dirigent));
        return input;
    }

    public static int validar5(int maximo) {
        do {
            Scanner sc = new Scanner(System.in);
            maximo = sc.nextInt();
            if (maximo <= 0 || maximo >= 41) {
                System.out.println("Maximo de aulas, escreva um numero de 1 a 40");
            }

        } while (maximo <= 0 || maximo >= 41);
        return maximo;
    }
}

package multiplayertcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Bruno Bencke
 */

public class Cliente{
    
    public static Socket socket;
    public static BufferedReader in;
    public static PrintStream out;
    public static final int PORTA = 6000;
    public static final String IP = "localhost";
    public static int coluna = 0;
    public static int linha = 0;
    //declara um novo scanner
    public static Scanner scanner = new Scanner(System.in);
    
    //construtor Cliente que recebe IP e porta
    public Cliente(String ip, int porta) throws Exception {
        socket = new Socket(ip, porta);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }
    
    public static void main(String[] args) throws Exception {

        //iniciando cliente
        System.out.println("Iniciando Cliente/Conectando com o Servidor ...");
        Cliente cliente = new Cliente(IP, PORTA);
        System.out.println("Conexão Estabelecida...");

        String respServidor = "";
        String mandarServidor = "";

        respServidor = in.readLine();
        System.out.println("Digite iniciar para começar o jogo: ");
        out.println("mat_x");
        coluna = Integer.parseInt(in.readLine());

        out.println("mat_y");
        linha = Integer.parseInt(in.readLine());

            do {
                mandarServidor = scanner.nextLine();
                out.println(mandarServidor);
                respServidor = in.readLine();

                if (mandarServidor.equalsIgnoreCase("iniciar")) {
                    
                    out.println("matriz");
                    respServidor = in.readLine();
                    
                    
                    if(linha == 0){
                        System.out.println(respServidor+"\n\n");
                    } else {
                        int size = 0;//linha * coluna
                        for (int i = 0; i < linha; i++) {
                            for (int j = 0; j < coluna; j++) {
                                System.out.print("  " + respServidor.charAt(size));
                                size++;
                            }
                            System.out.println("");
                        }
                    }
                } else if (mandarServidor.equalsIgnoreCase("mat_x")) {
                    linha = Integer.parseInt(respServidor);
                    System.out.println("O servidor disse: " + respServidor);
                } else if (mandarServidor.equalsIgnoreCase("mat_y")) {
                    coluna = Integer.parseInt(respServidor);
                    System.out.println("O servidor disse: " + respServidor);
                } else {
                    System.out.println("O servidor disse: " + respServidor);
                }

            } while (!mandarServidor.equalsIgnoreCase("sair"));

            System.out.println("Conexão Finalizada");
    }
}

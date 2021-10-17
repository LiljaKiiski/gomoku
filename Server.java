package lilja.kiiski.gomoku;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
	private ClientHandler[] clients = new ClientHandler[2];
	
	public String[][] grid = new String[19][19];
	public Lock gridLock = new ReentrantLock();

	public static void main(String[] args) throws IOException, InterruptedException{
		Server server = new Server();
		server.startProgram();
	}

	public void startProgram() throws IOException, InterruptedException{
	       	ServerSocket listener = new ServerSocket(1234);
                System.out.println("SEARCHING FOR CLIENTS");

                Socket socket = listener.accept();
                System.out.println("FOUND CLIENT");
                clients[0] = new ClientHandler("x", this, socket);

                Socket socket2 = listener.accept();
                System.out.println("FOUND CLIENT 2");
                clients[1] = new ClientHandler("y", this, socket2);

                //GAME STARTS
                clients[0].clients = clients;
                clients[1].clients = clients;

                clients[0].thread.start();
                clients[1].thread.start();

                clients[0].sendMessage("TURN");
	}
}

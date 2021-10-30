import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
	private ArrayList<Game> games = new ArrayList<>();

	public static void main(String[] args) throws IOException, InterruptedException{
		Server server = new Server();
	}

	public Server() throws IOException, InterruptedException{
		ServerSocket listener = new ServerSocket(1234);

		while (true) {
			Game game = new Game(new ClientHandler[2]);
                	Socket socket = listener.accept();
                	game.clients[0] = new ClientHandler("black", game, socket);
  
			socket = listener.accept();
			game.clients[1] = new ClientHandler("white", game, socket);
		      	game.startGame();
               		games.add(game);
		}
	}
}

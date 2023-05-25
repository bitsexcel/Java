import java.io.*;
import java.net.*;
import java.util.*;



public class EchoServer {
	
	public static void main(String[] args) throws IOException{
		
		try(ServerSocket ss = new ServerSocket(8189)){
			
			try(Socket s = ss.accept()){
				
				InputStream  inputStream  = s.getInputStream();
				OutputStream outputStream = s.getOutputStream();
				
				try(Scanner scanner = new Scanner(inputStream)){
					
					PrintWriter printWriter = new PrintWriter(outputStream,
							true /* autoflush */);
					
					printWriter.println("Bienvenido, enter bye to exit");
					
					boolean done = false;
					
					while(!done && scanner.hasNextLine()){
						
						String line = scanner.nextLine();
						printWriter.println("echo: "+ line);
						if(line.trim().equals("bye")) done = true;
					}
				}
			}
		}
	}

}

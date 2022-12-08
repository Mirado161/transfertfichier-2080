import java.io.File;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Serveur {

    
    private ServerSocket serverSocket;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    
    public Serveur(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }

    private static void sendFile(String path) throws Exception{
    int bytes = 0;
    // Open the File where he located in your pc
    File file = new File(path);
    FileInputStream fileInputStream= new FileInputStream(file);

    // Here we send the File to Server
    dataOutputStream.writeLong(file.length());
    // Here we  break file into chunks
    byte[] buffer = new byte[(int)file.length()];
    while ((bytes = fileInputStream.read(buffer))!= -1) {
      // Send the file to Server Socket 
      dataOutputStream.write(buffer, 0, bytes);
        dataOutputStream.flush();
    }
    // close the file here
    fileInputStream.close();
}

public void startServer(){
    try {
        
          Socket socket=serverSocket.accept();
          dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println( "Sending the File to the Client");
          // Call SendFile Method
          sendFile( "C:/Users/Mirado/Desktop/lesona/y2meta.com - NANTENAINA ft Rotsy - fitia tsy tafita (128 kbps).mp3");
            /* Thread thread=new Thread(distributeur);
            thread.start(); */
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public static void main(String[] args)
    {
        try {
            ServerSocket serverSocket=new ServerSocket(900);
            Serveur serveur=new Serveur(serverSocket);
            serveur.startServer();
            dataInputStream.close();
            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
 
   
   

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Client {

    private static Socket socket;
    private static DataOutputStream dataOutputStream ;
    private static DataInputStream dataInputStream = null;
 
    public Client(Socket socket){
        try {
            this.socket=socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName)
    throws Exception
{
    
        int bytes = 0;
    FileOutputStream fileOutputStream= new FileOutputStream(fileName);

    long size= dataInputStream.readLong(); // read file size
    byte[] buffer = new byte[(int)size];
    while (size > 0
&& (bytes = dataInputStream.read(
                   buffer, 0,
                   (int)Math.min(buffer.length, size)))
                  != -1) {
        // Here we write the file using write method
        fileOutputStream.write(buffer, 0, bytes);
        size -= bytes; // read upto file size
    }
    // Here we received file
    System.out.println("File is Received");
    fileOutputStream.close();
    
   
}
    public static void main(String[] args)
    {   
        try {
            Socket socket=new Socket("localhost",900);
            Client client=new Client(socket);
            receiveFile("NewFile5.mp3");
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

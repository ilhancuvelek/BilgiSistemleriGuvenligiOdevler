
import java.net.*;
import java.io.*;
public class Client {
 
	public static void main(String[] args) throws IOException {
 
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
 
		try {
			socket = new Socket("localhost", 1998); // "localhost" ya da sunucu IP adresi
                        // input stream ve output stream yarat�l�yor...
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Sunucu bulunamad�");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("I/O exception:" + e.getMessage());
			System.exit(1);
		}
		System.out.println("Sunucuya baglanildi.");
		// klavyeden girdi: stdIn
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput,encryptedMessage;
		System.out.println("�ifrelenecek Mesaj� Giriniz  ...");
		while (!(userInput = stdIn.readLine()).equals("q")) {
			Encryption enc=new Encryption();
			encryptedMessage=Encryption.encrypt(userInput);
			out.println(encryptedMessage);
			System.out.println("Sunucudan gelen: " +"(Orginal Message : "+userInput+") ==> "+ in.readLine()+"\n"+"(baglantiyi kesmek icin: q)"+"\n");
		}
		System.out.println("Baglanti kesiliyor...");
 
		out.close();
		in.close();
		stdIn.close();
		socket.close();
	}
}

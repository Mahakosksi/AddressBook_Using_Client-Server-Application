package folder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ServerAddressBook extends Application {

    private int x = 0;
    private Double n = 0.0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        TextArea ta = new TextArea();

        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        RandomAccessFile inout = new RandomAccessFile("AdressBookServer.dat", "rw");

        new Thread(() -> {
            try {
                ServerSocket severSocket1 = new ServerSocket(8000);
                Platform.runLater(()
                        -> ta.appendText("Server started at " + new Date() + '\n'));
                Socket socket = severSocket1.accept();
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {

                    String name = inputFromClient.readUTF();
                    String street = inputFromClient.readUTF();
                    String city = inputFromClient.readUTF();
                    String state = inputFromClient.readUTF();
                    String zip = inputFromClient.readUTF();
                    Double n = inputFromClient.readDouble();
                    if (name != "" && street != "" && city != "" && state != "" && zip != "" && n == 1.0) {
                        add(inout, name, street, city, state, zip);
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText(" La donnée est entrée avec succès " + '\n');

                        });

                    }
                    if ((n == 2.0)) {

                        x = 0;
                        inout.seek(x);
                        
                        int pos;
                        byte[] Name = new byte[23];
                        pos = inout.read(Name);
                        outputToClient.writeUTF(new String(Name));
                        byte[] Street = new byte[23];
                        pos = inout.read(Street);
                        outputToClient.writeUTF(new String(Street));
                        byte[] City = new byte[8];
                        pos = inout.read(City);
                        outputToClient.writeUTF(new String(City));
                        byte[] State = new byte[2];
                        pos = inout.read(State);
                        outputToClient.writeUTF(new String(State));
                        byte[] Zip = new byte[4];
                        pos = inout.read(Zip);
                        outputToClient.writeUTF(new String(Zip));
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText(" Le premier est affiché " + '\n');

                        });

                    }
                    if ((n == 3.0)) {
                        if (x < inout.length() - 60) {
                            x = x + 60;
                        }
                        inout.seek(x);

                        int pos;
                        byte[] Name = new byte[23];
                        pos = inout.read(Name);
                        outputToClient.writeUTF(new String(Name));
                        byte[] Street = new byte[23];
                        pos = inout.read(Street);
                        outputToClient.writeUTF(new String(Street));
                        byte[] City = new byte[8];
                        pos = inout.read(City);
                        outputToClient.writeUTF(new String(City));
                        byte[] State = new byte[2];
                        pos = inout.read(State);
                        outputToClient.writeUTF(new String(State));
                        byte[] Zip = new byte[4];
                        pos = inout.read(Zip);
                        outputToClient.writeUTF(new String(Zip));
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText(" Le suivant est affiché " + '\n');

                        });

                    }

                    if ((n == 4.0)) {
                        if (x > 0) {
                            x = x - 60;
                        }
                        inout.seek(x);

                        int pos;
                        byte[] Name = new byte[23];
                        pos = inout.read(Name);
                        outputToClient.writeUTF(new String(Name));
                        byte[] Street = new byte[23];
                        pos = inout.read(Street);
                        outputToClient.writeUTF(new String(Street));
                        byte[] City = new byte[8];
                        pos = inout.read(City);
                        outputToClient.writeUTF(new String(City));
                        byte[] State = new byte[2];
                        pos = inout.read(State);
                        outputToClient.writeUTF(new String(State));
                        byte[] Zip = new byte[4];
                        pos = inout.read(Zip);
                        outputToClient.writeUTF(new String(Zip));
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText(" Le previous est affiché " + '\n');

                        });

                    }
                    if ((n == 5.0)) {
                        x = (int) (inout.length() - 60);
                        inout.seek(x);

                        int pos;
                        byte[] Name = new byte[23];
                        pos = inout.read(Name);
                        outputToClient.writeUTF(new String(Name));
                        byte[] Street = new byte[23];
                        pos = inout.read(Street);
                        outputToClient.writeUTF(new String(Street));
                        byte[] City = new byte[8];
                        pos = inout.read(City);
                        outputToClient.writeUTF(new String(City));
                        byte[] State = new byte[2];
                        pos = inout.read(State);
                        outputToClient.writeUTF(new String(State));
                        byte[] Zip = new byte[4];
                        pos = inout.read(Zip);
                        outputToClient.writeUTF(new String(Zip));
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText(" Le dernier est affiché " + '\n');

                        });

                    }
                    if ((name != "" && street != "" && city != "" && state != "" && zip != "" && n == 6.0)) {

                        inout.seek(x);
                        write(inout, name, street, city, state, zip);
                        
                       
                        n = 0.0;
                        Platform.runLater(() -> {
                            ta.appendText("La modification est prise en compte avec succès" + '\n');

                        });

                    }

                }

            } catch (IOException ex) {
                System.err.println("the error");
            }
        }).start();
    }

    byte[] fixeBytes(byte[] textBytes, int maxBytes) {
        byte[] fixedTextBytes = new byte[maxBytes];
        for (int i = 0; i < textBytes.length; i++) {
            fixedTextBytes[i] = textBytes[i];

        }
        return fixedTextBytes;
    }

    private void write(RandomAccessFile inout, String name, String street, String city, String state, String zip) throws IOException {

        inout.write(fixeBytes(name.getBytes(), 23));
        inout.write(fixeBytes(street.getBytes(), 23));
        inout.write(fixeBytes(city.getBytes(), 8));
        inout.write(fixeBytes(state.getBytes(), 2));
        inout.write(fixeBytes(zip.getBytes(), 4));

    }

    private void add(RandomAccessFile inout, String name, String street, String city, String state, String zip) throws IOException {

        inout.seek(inout.length());
        write(inout, name, street, city, state, zip);
    }

}

package folder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientAddressBook extends Application {

    protected Label lab1 = new Label("Name");
    public TextField Name = new TextField();
    protected Label lab2 = new Label("Street");
    protected TextField Street = new TextField();
    protected Label lab3 = new Label("City");
    protected TextField City = new TextField();
    protected Label lab4 = new Label("State");
    protected TextField State = new TextField();
    protected Label lab5 = new Label("Zip");
    protected TextField Zip = new TextField();
    protected Button add = new Button("Add");
    protected Button first = new Button("First");
    protected Button next = new Button("Next");
    protected Button previous = new Button("Previous");
    protected Button last = new Button("Last");
    protected Button update = new Button("Update");
    protected HBox pane1 = new HBox(5);
    protected HBox pane2 = new HBox(5);
    protected HBox pane3 = new HBox(5);
    protected HBox pane4 = new HBox(5);
    protected VBox pane = new VBox(5);
    private int x = 0;

    private double n = 0.0;

    DataOutputStream toServer;
    DataInputStream fromServer;

    @Override
    public void start(Stage primaryStage) {

        Name.setPrefColumnCount(23);
        Street.setPrefColumnCount(23);
        City.setPrefColumnCount(8);
        State.setPrefColumnCount(2);
        Zip.setPrefColumnCount(4);
        pane1.getChildren().addAll(lab1, Name);
        pane2.getChildren().addAll(lab2, Street);
        pane3.getChildren().addAll(lab3, City, lab4, State, lab5, Zip);
        pane3.setAlignment(Pos.CENTER);
        pane4.getChildren().addAll(add, first, next, previous, last, update);
        pane4.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(pane1, pane2, pane3, pane4);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            Socket socket = new Socket("localHost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {

            System.err.println("the error");

        }

        add.setOnAction(e -> {
            try {

                String name = Name.getText();
                String street = Street.getText();
                String city = City.getText();
                String state = State.getText();
                String zip = Zip.getText();

                toServer.writeUTF(name);
                toServer.writeUTF(street);
                toServer.writeUTF(city);
                toServer.writeUTF(state);
                toServer.writeUTF(zip);
                toServer.writeDouble(1.0);
                toServer.flush();
            } catch (IOException ex) {
                Logger.getLogger(MainClassAddressBookPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        first.setOnAction(e -> {

            try {

                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeDouble(2.0);
                toServer.flush();

                String name = fromServer.readUTF();
                String street = fromServer.readUTF();
                String city = fromServer.readUTF();
                String state = fromServer.readUTF();
                String zip = fromServer.readUTF();

                Name.setText(name);
                Street.setText(street);
                City.setText(city);
                State.setText(state);
                Zip.setText(zip);

            } catch (IOException ex) {
                Name.setText("erreur");
            }
        });
        next.setOnAction(e -> {

            try {

                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeDouble(3.0);
                toServer.flush();

                String name = fromServer.readUTF();
                String street = fromServer.readUTF();
                String city = fromServer.readUTF();
                String state = fromServer.readUTF();
                String zip = fromServer.readUTF();

                Name.setText(name);
                Street.setText(street);
                City.setText(city);
                State.setText(state);
                Zip.setText(zip);

            } catch (IOException ex) {
                Name.setText("erreur");
            }
        });
        previous.setOnAction(e -> {

            try {

                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeDouble(4.0);
                toServer.flush();

                String name = fromServer.readUTF();
                String street = fromServer.readUTF();
                String city = fromServer.readUTF();
                String state = fromServer.readUTF();
                String zip = fromServer.readUTF();

                Name.setText(name);
                Street.setText(street);
                City.setText(city);
                State.setText(state);
                Zip.setText(zip);

            } catch (IOException ex) {
                Name.setText("erreur");
            }
        });
        last.setOnAction(e -> {

            try {

                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeUTF("");
                toServer.writeDouble(5.0);
                toServer.flush();

                String name = fromServer.readUTF();
                String street = fromServer.readUTF();
                String city = fromServer.readUTF();
                String state = fromServer.readUTF();
                String zip = fromServer.readUTF();

                Name.setText(name);
                Street.setText(street);
                City.setText(city);
                State.setText(state);
                Zip.setText(zip);

            } catch (IOException ex) {
                Name.setText("erreur");
            }
        });
        update.setOnAction(e -> {

            try {

               String name = Name.getText();
                String street = Street.getText();
                String city = City.getText();
                String state = State.getText();
                String zip = Zip.getText();

                toServer.writeUTF(name);
                toServer.writeUTF(street);
                toServer.writeUTF(city);
                toServer.writeUTF(state);
                toServer.writeUTF(zip);
                toServer.writeDouble(6.0);
                toServer.flush();

                

            } catch (IOException ex) {
                Name.setText("erreur");
            }
        });

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

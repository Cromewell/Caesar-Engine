package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.Y;

public class Main extends Application {

    TextArea output;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Caesar-Engine");
        Group root = new Group();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        primaryStage.setScene(new Scene(root, 500, 410));
        root.getChildren().add(layout);

        /*----------*/
        /*-TEXTAREA-*/
        /*----------*/
        output = new TextArea("output...");
        output.setMaxHeight(100);
        output.setEditable(false);
        layout.getChildren().add(output);

        /*------------*/
        /*-TEXTFIELD--*/
        /*----AND-----*/
        /*--TEXTAREA--*/
        /*------------*/
        TextField changingValue = new TextField("Shifting value");
        layout.getChildren().add(changingValue);

        TextArea input = new TextArea("Text to encrypt. (Only lowercase!)");
        input.setMaxHeight(200);
        layout.getChildren().add(input);

        /*---------*/
        /*-BUTTONS-*/
        /*---------*/
        Button decrypt = new Button("Decrypt");
        decrypt.setMaxWidth(100);
        decrypt.setOnAction(e->{
            try {
                output.setText(decrypt(input.getText(), Integer.parseInt(changingValue.getText())));
            }catch (IllegalArgumentException iae){
                output.setText("Illegal argument.");
                iae.printStackTrace();
            }
        });
        Button encrypt = new Button("Encrypt");
        encrypt.setMaxWidth(100);
        encrypt.setOnAction(e->{
            try {
                output.setText(encrypt(input.getText(), Integer.parseInt(changingValue.getText())));
            }catch (IllegalArgumentException iae){
                output.setText("Illegal argument.");
                iae.printStackTrace();
            }
        });
        layout.getChildren().addAll(decrypt, encrypt);
        layout.setAlignment(Pos.BOTTOM_CENTER);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private String encrypt(String text, int shift){
        String result = "";

        try{
            char[] chars = new char[100];
            for (int i = 0; i < text.length(); i++) {
                chars[i] = text.charAt(i);
            }
            int count = 0;
            for (char c : chars) {
                if (c != '\u0000') {
                    count++;
                }
            }
            if(count < 100){
                char[] tmp = new char[count];
                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = chars[i];
                }
                chars = tmp.clone();
            }

            for (char c: chars){
                int pos = (int) c;
                if(c >= 'a' && c <= 'z'){
                    pos = (pos - 'a' + shift)%26 + 'a';
                    System.out.println(pos);
                    c = (char) pos;
                    result += c;
                }else{
                    output.setText("Wrong input.");
                }
            }

        }catch (IllegalArgumentException iae){
            output.setText("Max characters = 100");
            iae.printStackTrace();
        }

        return result;

    }

    private String decrypt(String text, int shift){

        return encrypt(text, -shift);

    }

}
package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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

            for (int i = 0; i < text.length(); i++){
                char c = text.charAt(i);
                int pos = (int) c;
                if(c >= 'a' && c <= 'z'){

                    pos = (pos - 'a' + shift)%26 + 'a';
                    c = (char) pos;
                    result += c;

                }else if(c >= 'A' && c <= 'Z'){

                    pos =  (pos - 'A' + shift)%26 + 'A';
                    c = (char) pos;
                    result +=  c;

                }else if(c == ' '){

                    result += ' ';

                } else {
                    output.setText("Wrong input.");
                }
            }

        return result;

    }

    private String decrypt(String text, int shift){

        String result = "";

            for (int i = 0; i < text.length(); i++){
                char c = text.charAt(i);
                int pos = (int) c;
                if(c >= 'a' && c <= 'z'){

                    pos = pos - (shift%26);
                    if(pos < 'a'){
                        pos += 26;
                    }
                    c = (char) pos;
                    result += c;

                }else if(c >= 'A' && c <= 'Z'){

                    pos =  pos - (shift%26);
                    if(pos < 'A'){
                        pos += 26;
                    }
                    c = (char) pos;
                    result +=  c;

                }else if(c == ' '){

                    result += ' ';

                } else {
                    output.setText("Wrong input.");
                }
            }

        return result;

    }

}

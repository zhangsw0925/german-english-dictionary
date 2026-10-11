package controller;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import viewmodel.Mainviewmodel;

public class MainController {
    @FXML
    private TextField germanField;

    @FXML
    private TextField englishField;

    @FXML
    private TextArea wordsArea;

    @FXML
    private Label messagelabel;

    @FXML
    private TextField deleteField;

    @FXML
    private Label deletemessagelabel;

    @FXML
    private TextField filenameField;

    @FXML
    private Label filemessagelabel;

    @FXML
    private Tab trainingtab;

    @FXML
    private TextField showwordField;

    @FXML
    private Label checklabel;

    @FXML
    private TextField typewordField;
    
    @FXML
    private Label directionlabel;

    private Mainviewmodel viewmodel = new Mainviewmodel();

    @FXML
    public void handleAddwords(){
        String german = germanField.getText();
        String english = englishField.getText();
        String message = viewmodel.addWords(german,english);
        messagelabel.setText(message);
        if(message.equals("Wordpair added successfully.")){
            germanField.clear();
            englishField.clear();
        }
    }

    @FXML
    public void handleDeletewords(){
        String word = deleteField.getText();
        String message = viewmodel.deleteWords(word);
        deletemessagelabel.setText(message);
        if(message.equals("Wordpair deleted successfully.")){
            deleteField.clear();
        }
    }

    @FXML
    public void handleSaveFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dictionary");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            String message = viewmodel.saveFile(file.getAbsolutePath());
            filemessagelabel.setText(message);
        }else{
            filemessagelabel.setText("Save cancelled.");
        }
    }

    @FXML
    public void handleLoadFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Dictionary");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            String message = viewmodel.loadFile(file.getAbsolutePath());
            filemessagelabel.setText(message);
        }else{
            filemessagelabel.setText("Load cancelled.");
        }
    }        

    @FXML
    public void handleShowallwords(){        
        wordsArea.setText(viewmodel.showAllwords());
    }

    @FXML
    public void initialize(){
        trainingtab.setOnSelectionChanged(event -> {            
            if(trainingtab.isSelected()){
                showwordField.setText(viewmodel.initialize()); 
                checklabel.setText("");
                typewordField.clear();               
            }
        });
    }

    @FXML
    public void handlenewword(){
        showwordField.setText(viewmodel.newWord());
        typewordField.clear();
        checklabel.setText("");        
    }

    @FXML
    public void handleConfirm(){
        String answer = typewordField.getText();            
        checklabel.setText(viewmodel.confirm(answer));
    }

    @FXML
    public void handleswitch(){
        try{
            String directionText = viewmodel.switchDirection();
            String word = viewmodel.getDirectionnewWord();
            showwordField.setText(word);
            directionlabel.setText(directionText);
            checklabel.setText("");
            typewordField.clear();
        }catch(IllegalStateException e){
            checklabel.setText(e.getMessage());
        }
    }    
}

package viewmodel;

import java.io.IOException;
import model.Dictionary;
import model.DictionaryDAO;
import model.Wordpair;
import model.Training;
import model.Direction;

public class Mainviewmodel{
    public Dictionary dictionary;
    public Training training;
    public DictionaryDAO dao;

    public Mainviewmodel(){
        dictionary = new Dictionary();
        training = new Training(dictionary);
        dao = new DictionaryDAO();
    }

    public String addWords(String german,String english){
        try{        
            Wordpair Wordpair = new Wordpair(german,english);
            dictionary.addWords(Wordpair);
            return("Wordpair added successfully.");
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public String deleteWords(String word){
        try{
            dictionary.deleteWords(word);
            return("Wordpair deleted successfully.");
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }catch(IllegalStateException e){
            return e.getMessage();
        }
    }

    public String showAllwords(){
        try{
            return dictionary.showallWords();
        }catch(IllegalStateException e){
            return e.getMessage();
        }
    }

    public String saveFile(String filepath){
        try{
            dao.save(dictionary,filepath);
            return("Dictionary saved successfully.");
        }catch(IOException e){
            return("Dictionary can't be saved.");
        }
    }

    public String loadFile(String filepath){
        try{
            dao.load(dictionary,filepath);
            return("Dictionary loaded successfully.");
        }catch(IOException e){
            return("Dictionary can't be found or loaded.");
        }catch(IllegalArgumentException e){
            return e.getMessage();        
        }
    }

    public String initialize(){
        try{
            return training.getRandomWord();                
        }catch(IllegalStateException e){
            return ("Dictionary is empty.");
        }
    }

    public String newWord(){
        try{
            return training.getRandomWord();
        }catch(IllegalStateException e){
            return e.getMessage();
        }
    }

    public String confirm(String answer){
        try{
            boolean result = training.checkAnswer(answer);
            if(result){
               return("Correct!");
            }else{
               return("Flase!");
            }
        }catch(IllegalStateException e){
            return e.getMessage();
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public String switchDirection(){
        training.switchDirection();
        if(training.getDirection() == Direction.DE_TO_EN){
            return("German to English");            
        }else{
            return("English to German");            
        } 
    }

    public String getDirectionnewWord(){
        if(training.getDirection() == Direction.DE_TO_EN){
            return(training.getRandomWord());            
        }else{
            return(training.getRandomWord());            
        } 
    }
    
}

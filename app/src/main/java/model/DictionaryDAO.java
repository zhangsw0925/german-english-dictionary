package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DictionaryDAO{
    public void save(Dictionary dictionary,String filename) throws IOException{
        PrintWriter writer = new PrintWriter(filename);
        for(int i = 0; i< dictionary.getSize(); i++){
            writer.println(dictionary.get(i).getGerman() + ":" +dictionary.get(i).getEnglish());
        }
        writer.close();
    }

    public void load(Dictionary dictionary,String filename) throws IOException{
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
        while(scanner.hasNextLine()){         
            String line = scanner.nextLine();      
            String[] parts = line.split(":");
            if(parts.length == 2){                  
                String german = parts [0].trim();   
                String english = parts [1].trim();
                Wordpair words = new Wordpair(german, english);
                if(german.isEmpty() || english.isEmpty()){
                    throw new IllegalArgumentException("Invalid format.");
                }
                try{
                dictionary.addWords(words);
                }catch(IllegalArgumentException e){
                    if(!e.getMessage().equals("Wordpair already exists.")){
                        throw e;
                    }
                }                
            }else{                                 
                throw new IllegalArgumentException("Invalid format.");
            }            
        }        
    }
    }
}

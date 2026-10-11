package model;
import java.util.ArrayList;

public class Dictionary{
    private ArrayList<Wordpair> pair;

    public Dictionary(){
        pair = new ArrayList<>();
    }
    
    public void addWords(Wordpair wordPair){
        String german = wordPair.getGerman().trim();
        String english = wordPair.getEnglish().trim();
        if(wordPair.getGerman() == null || wordPair.getGerman().isEmpty()){
            if(wordPair.getEnglish() == null || wordPair.getEnglish().isEmpty()){
                throw new IllegalArgumentException("Words are empty.");
            }
            throw new IllegalArgumentException("German is empty.");
        }
        if(wordPair.getEnglish() == null || wordPair.getEnglish().isEmpty()){
            throw new IllegalArgumentException("English is empty.");
        }        
        if(german.contains(":") || english.contains(":")){
            throw new IllegalArgumentException("Words must not contain ':'.");
        }
        Wordpair cleanWordpair = new Wordpair(german, english);
        for(int i = 0; i < pair.size(); i++){
            if((pair.get(i).getGerman().trim()).equals(cleanWordpair.getGerman()) 
                ||((pair.get(i).getEnglish().trim()).equals(cleanWordpair.getEnglish()))){
                throw new IllegalArgumentException("Wordpair already exists.");
            }
        }
        pair.add(cleanWordpair);
    }

    public void deleteWords(String word){
        if(word == null || word.isEmpty()){
            throw new IllegalArgumentException("Word is empty.");
        }
        if(pair.isEmpty()){
            throw new IllegalStateException("Dictionary is empty.");
        }        
        for(int i = 0; i < pair.size(); i++){
            if((pair.get(i).getGerman()).equals(word)
                ||(pair.get(i).getEnglish()).equals(word)){
                pair.remove(i);
                return;
            }                
        }
        throw new IllegalArgumentException(word + " not found.");
    }

    public String showallWords(){
        if(pair.isEmpty()){
            throw new IllegalStateException("Dictionary is empty.");
        }
        String result = "";
        for(int i = 0; i< pair.size(); i++){
            result += (pair.get(i).getGerman() + "-" +
            pair.get(i).getEnglish() + "\n" );
        }
        return result;
    }   

    public int getSize(){
        return pair.size();
    }

    public Wordpair get(int i){
        return pair.get(i);
    }
}

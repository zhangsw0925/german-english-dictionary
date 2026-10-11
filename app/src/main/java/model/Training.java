package model;
import java.util.Random;

public class Training {
    private Dictionary dictionary;
    private Wordpair currentword;
    private Direction direction;

    public Training(Dictionary dictionary){
        this.dictionary = dictionary;  
        direction = Direction.DE_TO_EN;      
    }

    public String getRandomWord(){
        if(dictionary.getSize() == 0){
            throw new IllegalStateException("Dictionary is empty."); 
        }
        Random random = new Random();
        int i = random.nextInt(dictionary.getSize());
        currentword = dictionary.get(i);
        if(direction == Direction.DE_TO_EN){
            return currentword.getGerman();
        }else{
            return currentword.getEnglish();
        }
    }

    public boolean checkAnswer(String answer){        
        if(currentword == null){
            throw new IllegalStateException("No current word.");
        }
        if(answer == null){
            throw new IllegalArgumentException("Answer is empty.");
        }
        answer = answer.trim();
        if(answer.isEmpty()){
            throw new IllegalArgumentException("Answer is empty.");
        }        
        if(direction == Direction.DE_TO_EN){
            return answer.equalsIgnoreCase(currentword.getEnglish());
        }        
            return answer.equalsIgnoreCase(currentword.getGerman());
    }

    public String switchDirection(){
        if(direction == Direction.DE_TO_EN){
            direction = Direction.EN_TO_DE;
        }else{
            direction = Direction.DE_TO_EN;
        } 
        return getRandomWord();
    }

    public Direction getDirection(){
        return direction;
    }    
}

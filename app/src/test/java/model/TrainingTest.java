package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TrainingTest {
    @Test
    void testgetRandomWordDtoE() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        assertEquals("Hund", training.getRandomWord());
    }

    @Test
    void testgetRandomWordEtoD() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        training.switchDirection();
        assertEquals("dog", training.getRandomWord());
    }

    @Test
    void testgetRandomWordEmpty() throws IOException{
        Dictionary dictionary = new Dictionary();
        Training training = new Training(dictionary);
        assertThrows(IllegalStateException.class, ()-> training.getRandomWord());
    }

    @Test
    void testCheckAnswerDtoE() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        training.getRandomWord();
        assertTrue(training.checkAnswer("dog"));
    }

    @Test
    void testCheckAnswerEtoD() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        training.switchDirection();
        training.getRandomWord();
        assertTrue(training.checkAnswer("Hund"));
    }

    @Test
    void testCheckAnswerWrong() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        training.getRandomWord();
        assertFalse(training.checkAnswer("cat"));
    }

    @Test
    void testCheckAnswerNOword(){
        Dictionary dictionary = new Dictionary();
        Training training = new Training(dictionary);
        assertThrows(IllegalStateException.class, ()-> training.checkAnswer("dog"));
    }

    @Test
    void testCheckEmptyAnswer() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        training.getRandomWord();
        assertThrows(IllegalArgumentException.class, ()-> training.checkAnswer(""));
        assertThrows(IllegalArgumentException.class, ()-> training.checkAnswer(null));
    }

    @Test
    void testSwitchDirection() throws IOException{
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        Training training = new Training(dictionary);
        assertEquals(Direction.DE_TO_EN, training.getDirection());
        training.switchDirection();
        assertEquals(Direction.EN_TO_DE, training.getDirection());
        training.switchDirection();        
        assertEquals(Direction.DE_TO_EN, training.getDirection());
    }








        
        

    
}

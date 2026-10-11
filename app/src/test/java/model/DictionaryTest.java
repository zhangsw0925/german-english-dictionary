package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DictionaryTest {
    @Test
    void testAddWordssuccessfully(){
        Dictionary dictionary = new Dictionary();
        Wordpair pair = new Wordpair("Hund", "dog");
        dictionary.addWords(pair);
        assertEquals(1, dictionary.getSize());
    }

    @Test
    void testAddWordsNull(){
        Dictionary dictionary = new Dictionary();
        Wordpair pair = new Wordpair("","dog");
        assertThrows(IllegalArgumentException.class, () -> dictionary.addWords(pair));
    }
    
    @Test
    void testAddWordsExist(){
        Dictionary dictionary = new Dictionary();
        Wordpair pair = new Wordpair("Katze","cat");
        Wordpair pair2 = new Wordpair("Katze","cat");
        dictionary.addWords(pair);
        assertThrows(IllegalArgumentException.class, () -> dictionary.addWords(pair2));
        assertEquals(1, dictionary.getSize());
    }

    @Test
    void testAddWordsWithColon(){
        Dictionary dictionary = new Dictionary();
        Wordpair pair = new Wordpair("Katze","cat:dog");
        assertThrows(IllegalArgumentException.class, () -> dictionary.addWords(pair));
    }

    @Test
    void testDeleteWordssuccessfully(){
        Dictionary dictionary = new Dictionary();
        Wordpair  wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        String word = "Hund";
        dictionary.deleteWords(word);
        assertEquals(0, dictionary.getSize());
    }

    @Test
    void testDeleteWordsNull(){
        Dictionary dictionary = new Dictionary();
        assertThrows(IllegalArgumentException.class, () -> dictionary.deleteWords(null));
        assertThrows(IllegalArgumentException.class, () -> dictionary.deleteWords(""));
    }

    @Test
    void testDeleWordsNotExist(){
        Dictionary dictionary = new Dictionary();
        Wordpair  wordpair = new Wordpair("Hund","dog");
        dictionary.addWords(wordpair);
        assertThrows(IllegalArgumentException.class,() -> dictionary.deleteWords("Katze"));
        assertEquals(1, dictionary.getSize());
    }

    @Test
    void testShowallWords(){
        Dictionary dictionary = new Dictionary();
        Wordpair wordpair1 = new Wordpair("Hund","dog");
        Wordpair wordpair2 = new Wordpair("Katze","cat");
        dictionary.addWords(wordpair1);
        dictionary.addWords(wordpair2);
        assertEquals("Hund-dog\nKatze-cat\n", dictionary.showallWords());
    }

    @Test
    void testShowallWordsEmpty(){
        Dictionary dictionary = new Dictionary();
        assertThrows(IllegalStateException.class,() -> dictionary.showallWords());
    }
}















    


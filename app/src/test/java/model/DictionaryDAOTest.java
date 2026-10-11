package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

public class DictionaryDAOTest {
    @Test
    void testSavetoFile() throws IOException{
        DictionaryDAO dao = new DictionaryDAO();
        Dictionary dictionary1 = new Dictionary();
        Dictionary dictionary2 = new Dictionary();
        Wordpair pair1 = new Wordpair("Hund","dog");
        Wordpair pair2 = new Wordpair("Katze","cat");
        dictionary1.addWords(pair1);
        dictionary1.addWords(pair2);
        dao.save(dictionary1,"dictionarytest.txt");        
        dao.load(dictionary2,"dictionarytest.txt");
        assertEquals(2,dictionary2.getSize());
        assertEquals("Hund", dictionary2.get(0).getGerman());
        assertEquals("Katze", dictionary2.get(1).getGerman());
        assertEquals("dog", dictionary2.get(0).getEnglish());
        assertEquals("cat", dictionary2.get(1).getEnglish());    
    }

    @Test
    void testSaveEmptyFile() throws IOException{
        Dictionary dictionary1 = new Dictionary();
        Dictionary dictionary2 = new Dictionary();
        DictionaryDAO dao = new DictionaryDAO();
        dao.save(dictionary1,"Empty Dictionary.txt");
        dao.load(dictionary2,"Empty Dictionary.txt");
        assertEquals(0, dictionary2.getSize());
    }

    @Test
    void testLoadOneWord() throws IOException{
        Dictionary dictionary1 = new Dictionary();
        Dictionary dictionary2 = new Dictionary();
        DictionaryDAO dao = new DictionaryDAO();
        Wordpair pair = new Wordpair("Hund", "dog");
        dictionary1.addWords(pair);
        dao.save(dictionary1,"onewordpair.txt");
        dao.load(dictionary2,"onewordpair.txt");
        assertEquals(1, dictionary2.getSize());   
    }

    @Test
    void testLoadMultipleWord() throws IOException{
        Dictionary dictionary1 = new Dictionary();
        Dictionary dictionary2 = new Dictionary();
        DictionaryDAO dao = new DictionaryDAO();
        Wordpair pair1 = new Wordpair("Hund", "dog");
        Wordpair pair2 = new Wordpair("Katze", "cat");
        dictionary1.addWords(pair1);
        dictionary1.addWords(pair2);
        dao.save(dictionary1,"multiwords.txt");
        dao.load(dictionary2,"multiwords.txt");
        assertEquals(2,dictionary2.getSize());
        assertEquals("Hund", dictionary2.get(0).getGerman());
        assertEquals("dog", dictionary2.get(0).getEnglish());
        assertEquals("Katze", dictionary2.get(1).getGerman());
        assertEquals("cat", dictionary2.get(1).getEnglish());
    }

    @Test
    void testLoadEmptyFile() throws IOException{
        Dictionary dictionary1 = new Dictionary();
        Dictionary dictionary2 = new Dictionary();
        DictionaryDAO dao = new DictionaryDAO();
        dao.save(dictionary1,"Empty Dictionary.txt");
        dao.load(dictionary2,"Empty Dictionary.txt");
        assertEquals(0, dictionary2.getSize());
    }

    @Test
    void testLoadinvalidFormat() throws IOException{
        PrintWriter printWriter = new PrintWriter("Invalidform.txt");
        printWriter.println("Hund;dog;cat");
        printWriter.close();
        DictionaryDAO dao = new DictionaryDAO();
        Dictionary dictionary = new Dictionary();
        assertThrows(IllegalArgumentException.class, () -> dao.load(dictionary,"Invalidform.txt"));   
    }
}



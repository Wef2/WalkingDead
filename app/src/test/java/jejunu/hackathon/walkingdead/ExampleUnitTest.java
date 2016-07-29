package jejunu.hackathon.walkingdead;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void random_test(){
        for (int i = 1; i <= 100; i++) {
            double n = (double) ((int)(Math.random() * 10) + 1)/ 1000000;
            System.out.println(n);
        }
    }
}
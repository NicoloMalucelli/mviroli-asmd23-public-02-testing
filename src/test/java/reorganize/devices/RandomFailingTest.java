package reorganize.devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RandomFailingTest {
    private FailingPolicy failingPolicy;

    @BeforeEach
    public void init(){
        failingPolicy = new RandomFailing();
    }

    @Test
    @DisplayName("After one fail it keep failing")
    public void testAfterOneFailKeepFailing(){
        do{
        }while(failingPolicy.attemptOn());
        for(int i = 0; i < 10; i++){
            assertFalse(failingPolicy.attemptOn());
        }
    }

}

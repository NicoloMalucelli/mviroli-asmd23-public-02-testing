package devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class RandomFailingTest {
    private FailingPolicy failingPolicy;

    @BeforeEach
    public void init(){
        failingPolicy = new RandomFailing();
    }

    @Test
    @DisplayName("After one fail it keep failing")
    public void AfterOneFailKeepFailing(){
        do{
        }while(failingPolicy.attemptOn());
        for(int i = 0; i < 10; i++){
            assertFalse(failingPolicy.attemptOn());
        }
    }

}

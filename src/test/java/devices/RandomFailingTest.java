package devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class RandomFailingTest {
    private FailingPolicy failingPolicy;

    @BeforeEach
    public void init(){
        failingPolicy = mock(FailingPolicy.class);
    }


}

package devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class StandardDeviceMyTest {

    @Test
    @DisplayName("StandardDevice creation withou failing policy")
    public void testNullFailingPolicy(){
        assertThrows(NullPointerException.class, () -> new StandardDevice(null));
    }

    @Test
    @DisplayName("StandardDevice creation with failing policy")
    public void testDeviceCreationWithFailingPolicy(){
        final FailingPolicy dummyFailingPolicy = mock(FailingPolicy.class);
        assertDoesNotThrow(() -> new StandardDevice(dummyFailingPolicy));
    }



}

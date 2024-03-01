package devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class StandardDeviceMyTest {

    @Test
    @DisplayName("Can't create a device without a failing policy")
    public void testNullFailingPolicy(){
        assertThrows(NullPointerException.class, () -> new StandardDevice(null));
    }



}

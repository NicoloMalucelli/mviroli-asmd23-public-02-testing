package devices;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class StandardDeviceMyTest {

    private FailingPolicy dummyFailingPolicy;
    private StandardDevice device;

    @BeforeEach
    public void createDummyFailingPolicy(){
        this.dummyFailingPolicy = mock(FailingPolicy.class);
        this.device = new StandardDevice(dummyFailingPolicy);
    }

    @Test
    @DisplayName("StandardDevice creation without failing policy")
    public void testNullFailingPolicy(){
        assertThrows(NullPointerException.class, () -> new StandardDevice(null));
    }

    @Test
    @DisplayName("StandardDevice creation with failing policy")
    public void testDeviceCreationWithFailingPolicy(){
        assertDoesNotThrow(() -> new StandardDevice(this.dummyFailingPolicy));
    }

    @Test
    @DisplayName("StandardDevice is off after creation")
    public void testIsOffAtCreation(){
        assertFalse(this.device.isOn());
    }



}

package reorganize.devices;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class StandardDeviceMyTest {

    @Mock
    private FailingPolicy failingPolicy;
    private StandardDevice device;

    @BeforeEach
    public void createDummyFailingPolicy(){
        MockitoAnnotations.openMocks(this);
        //this.failingPolicy = mock(FailingPolicy.class);
        this.device = new StandardDevice(failingPolicy);
    }

    @Test
    @DisplayName("StandardDevice creation without failing policy")
    public void testNullFailingPolicy(){
        assertThrows(NullPointerException.class, () -> new StandardDevice(null));
    }

    @Test
    @DisplayName("StandardDevice creation with failing policy")
    public void testDeviceCreationWithFailingPolicy(){
        assertDoesNotThrow(() -> new StandardDevice(this.failingPolicy));
    }

    @Test
    @DisplayName("StandardDevice is off after creation")
    public void testIsOffAtCreation(){
        assertFalse(this.device.isOn());
    }

    @Test
    @DisplayName("StandardDevice turn on successfully when FailingPolicy allows it")
    public void testTurnOnSuccessfully(){
        when(this.failingPolicy.attemptOn()).thenReturn(true);
        assertDoesNotThrow(() -> this.device.on());
        assertTrue(this.device.isOn());
        verify(this.failingPolicy, times(1)).attemptOn();
    }

    @Test
    @DisplayName("StandardDevice doesn't turn on when FailingPolicy fails")
    public void testTurnOnFails(){
        when(this.failingPolicy.attemptOn()).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.device.on());
        assertFalse(this.device.isOn());
        verify(this.failingPolicy, times(1)).attemptOn();
    }

    @Test
    @DisplayName("StandardDevice successfully turn off")
    public void testTurnOff(){
        this.testTurnOnSuccessfully();
        this.device.off();
        assertFalse(this.device.isOn());
    }

    @Test
    @DisplayName("StandardDevice multiple turn on and off")
    public void testMultipleTurnOnAndOff(){
        when(this.failingPolicy.attemptOn()).thenReturn(true, true, false);
        this.device.on();
        this.device.off();
        this.device.on();
        this.device.off();
        assertThrows(IllegalStateException.class, () -> this.device.on());
        verify(this.failingPolicy, times(3)).attemptOn();
    }

    @Test
    @DisplayName("StandardDevice test reset")
    public void testReset(){
        device.reset();
        verify(this.failingPolicy, times(1)).reset();
     }


}

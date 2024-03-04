package reengineer;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogIntegrationTest {

    private Logger log;
    private String lastPrint;
    private int numOfPrints;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private String consumeStream(ByteArrayOutputStream stream){
        final String str = stream.toString().replaceAll("\\r\\n?", "\n");
        stream.reset();
        return str;
    }

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("test logger behaviour at application start")
    public void testLoggerBehaviourAtApplicationStart(){
        final Logics logic = new LogicsImpl(10);
        assertEquals("Log: grid of size 10 created\n", consumeStream(output));
    }



}

package reengineer;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class LogIntegrationTest {

    private Logics logic;
    private ByteArrayOutputStream output;

    private String consumeStream(ByteArrayOutputStream stream){
        final String str = stream.toString().replaceAll("\\r\\n?", "\n");
        stream.reset();
        return str;
    }

    @BeforeEach
    public void setUp(){
        this.output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        this.logic = new LogicsImpl(10);
    }

    @Test
    @DisplayName("test logger behaviour at application start")
    public void testLoggerBehaviourAtApplicationStart(){
        assertEquals("Log: grid of size 10 created\n", consumeStream(output));
    }

    @Test
    @DisplayName("test logger behaviour at the first hit")
    public void  testLoggerBehaviourAtTheFirstHit(){
        consumeStream(output);
        this.logic.hit(0, 0);
        assertEquals("Log: (0,0)\n", consumeStream(output));
    }

    @Test
    @DisplayName("test logger behaviour at the second first hit")
    public void  testLoggerBehaviourAtTheSecondHit(){
        this.testLoggerBehaviourAtTheFirstHit();
        this.logic.hit(2, 3);
        assertEquals("Log: (2,3)\nLog: 12 selected cells\n", consumeStream(output));
    }
}

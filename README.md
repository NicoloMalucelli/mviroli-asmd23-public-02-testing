# Advanced testing, mocking, integration

## Task 1: REORGANIZE

### description

The goal of this task was to reorganize the unit tests for the class _StandardDevice_ using the Mockito framework in the best possible way.  

### observations

The most interesting part was to handle the behaviour of the device's _FailingPolicy_ by using a mock and not a complete implementation.
By simulating the failing policy it's been possible to test the device behaviour independently from the failing policy implementation. In order to follow the DRY principle also in the test, 
the following organization has been chosen:

```java
@Mock private FailingPolicy failingPolicy;

private StandardDevice device;

@BeforeEach
public void createDummyFailingPolicy(){
    MockitoAnnotations.openMocks(this);
    this.device = new StandardDevice(failingPolicy);
}
```

Then, inside each test, the desired _FailingPolicy_'s desired behaviour it's been defined:

```java
when(this.failingPolicy.attemptOn()).thenReturn(true);
```
```java
when(this.failingPolicy.attemptOn()).thenReturn(false);
```
Some tests also required to check how many times a _FailingPolicy_'s method was called, so the _verify_ method has been used:
```java
verify(this.failingPolicy, times(1)).reset();
```

## Task 3: REENGINEERING

### description

The goal of this task was to write integration tests for an application composed of GUI, Logic and Logger.
Since the original application had not a logger, the first step consisted of creating a simple Logger and print some useful information to show the progress of the game. 
In particular, the following information has been printed through the logger:
- grid creation and game starting
- the user click a clickable cell
- a rectangle has been selected
- the end of the game

### observations

Since the GUI has not public methods, the written integration tests cover the interaction between the _Logic_ and the _Logger_, by verifying that the logged information was the expected ones
when calling a particular method of the logic in a specific moment of the game.

To do so, the standard output has been redirected to a ByteArray stream, and its content compared to the expected value using Junit assertions.

```java
@Test
@DisplayName("test logger behaviour at the second hit")
public void  testLoggerBehaviourAtTheSecondHit(){
    this.testLoggerBehaviourAtTheFirstHit();
    this.logic.hit(2, 3);
    assertEquals("Log: (2,3)\nLog: 12 selected cells\n", consumeStream(output));
}
```

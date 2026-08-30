import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void constructorWithNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "  "})
    void constructorWithBlankNameThrowsException(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeSpeedThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("TestHorse", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWithNegativeDistanceThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("TestHorse", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameReturnsCorrectName() {
        Horse horse = new Horse("TestHorse", 1.0, 1.0);
        assertEquals("TestHorse", horse.getName());
    }

    @Test
    void getSpeedReturnsCorrectSpeed() {
        Horse horse = new Horse("TestHorse", 1.5, 1.0);
        assertEquals(1.5, horse.getSpeed());
    }

    @Test
    void getDistanceReturnsCorrectDistance() {
        Horse horse = new Horse("TestHorse", 1.0, 2.5);
        assertEquals(2.5, horse.getDistance());
    }

    @Test
    void getDistanceReturnsZeroByDefault() {
        Horse horse = new Horse("TestHorse", 1.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void moveCallsGetRandomDoubleWithCorrectParams() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("TestHorse", 1.0, 0.0);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.5, 0.7, 0.9})
    void moveUpdatesDistanceCorrectly(double randomValue) {
        double expected = 1.0 + 2.0 * randomValue;

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            Horse horse = new Horse("TestHorse", 2.0, 1.0);
            horse.move();

            assertEquals(expected, horse.getDistance());
        }
    }
}
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void constructorWithNullListThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorWithEmptyListThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesReturnsSameListInSameOrder() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, 1.0, 0.0));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returned = hippodrome.getHorses();

        assertEquals(horses, returned);
    }

    @Test
    void moveCallsCorrectlyForAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse1", 1.0, 1.0);
        Horse horse2 = new Horse("Horse2", 1.0, 2.0);
        Horse horse3 = new Horse("Horse3", 1.0, 1.5);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertEquals(horse2, hippodrome.getWinner());
    }
}
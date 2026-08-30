import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Disabled because of long running time, enable manually if needed")
    void mainExecutionDoesNotExceedTimeout() throws Exception {
        Main.main(new String[0]);
    }
}
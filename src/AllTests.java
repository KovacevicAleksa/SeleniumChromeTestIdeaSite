import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JunitTest.class })
public class AllTests {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AllTests.class);
        Logger log = Logger.getLogger(AllTests.class.getName());

        log.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        log.addHandler(consoleHandler);
        
        for (Failure f : result.getFailures()) {
            log.warning(f.toString());
        }
        
        log.info("Broj izvrsenih testova: "+result.getRunCount());
        log.info("Vreme izvrsenih tesova: "+result.getRunTime());
        log.info("Broj preskocenih testova:"+result.getIgnoreCount());
        log.info("Broj uspesno izvrsenih testova: "+
        (result.getRunCount()-result.getIgnoreCount()-result.getFailureCount()-
        result.getAssumptionFailureCount()));
        log.info("Broj neuspesnih testova:"+result.getFailureCount());
        log.info("Broj dinamicki preskocenih testova:"+result.getAssumptionFailureCount());
        if(result.wasSuccessful()) {
        log.info("Svi testovi uspesno izvrseni");
        }
        else {
        log.info("Neki testovi nisu uspesno izvrseni");
        }
    }
}

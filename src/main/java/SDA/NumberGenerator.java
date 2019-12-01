package SDA;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by krzysztof on 12.11.17.
 */
@Singleton
@Startup
public class NumberGenerator{

    private static final Logger logger = Logger.getLogger(NumberGenerator.class);

    private Set<Integer> winningNumbers;

    /**
     * W implementacji tej metody można posłużyć się klasą java.util.Random
     * @return
     */
    private Set<Integer> drawWinningNumbers(){
        return new Random().ints(1 ,50)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toSet());

    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    @PostConstruct
    public void generateNumbersOnStart() {
        winningNumbers = drawWinningNumbers();
        logger.info("Liczby wygrywające to: " + winningNumbers);

    }



}

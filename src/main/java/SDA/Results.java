package SDA;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by krzysztof on 12.11.17.
 */
public class Results {

    @Inject
    private  NumberGenerator numberGenerator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLotteryResults(Set<Integer> userNumbers) {

        boolean success = isSuccess(userNumbers, numberGenerator.getWinningNumbers() );
        if (success) {

            return Response.ok("Brawo Wygrałeś")
                    .build();
        }
        return Response.ok("Niestety nie udało się")
                .build();

    }

    /**
     * Implmentację tej metody można oprzeć na zwykłej pętli, lub API JAVA8 (stream, filter, collect, Collectors, toList())
     * W obu przypadkach przyda się metoda contains z interfejsu List
     * @param numbers
     * @param winningNumbers
     * @return
     */
    private boolean isSuccess(Set<Integer> numbers, Set<Integer> winningNumbers) {
        return numbers.stream()
                .anyMatch(winningNumbers::contains);
    }

}

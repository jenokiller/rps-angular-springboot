package rpsserver;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RpsServiceTest {
    private RpsService rpsService;

    Map<String, String> requestDummy;

    @Before
    public void setUp() {
        rpsService = new RpsService();

        requestDummy = new HashMap<>();
    }

    @Test
    public void checkWinner_p1HandIsRock_p2HandIsScissors() {
        requestDummy.put("p1Name", "さとう");
        requestDummy.put("p1ThrowHand", "ROCK");
        requestDummy.put("p2Name", "武藤");
        requestDummy.put("p2ThrowHand", "SCISSORS");

        GameResult gameResult = rpsService.checkWinner(requestDummy);

        assertEquals(gameResult.getResult(), "さとう wins!");
    }

    @Test
    public void checkWinner_p1HandIsScissors_p2HandIsPaper() {
        requestDummy.put("p1Name", "さとう");
        requestDummy.put("p1ThrowHand", "SCISSORS");
        requestDummy.put("p2Name", "武藤");
        requestDummy.put("p2ThrowHand", "PAPER");

        GameResult gameResult = rpsService.checkWinner(requestDummy);

        assertEquals(gameResult.getResult(), "さとう wins!");
    }

    @Test
    public void checkWinner_p1HandIsPaper_p2HandIsRock() {
        requestDummy.put("p1Name", "さとう");
        requestDummy.put("p1ThrowHand", "PAPER");
        requestDummy.put("p2Name", "武藤");
        requestDummy.put("p2ThrowHand", "ROCK");

        GameResult gameResult = rpsService.checkWinner(requestDummy);

        assertEquals(gameResult.getResult(), "さとう wins!");
    }

    @Test
    public void checkWinner_p1HandIsRock_p2HandIsPaper() {
        requestDummy.put("p1Name", "さとう");
        requestDummy.put("p1ThrowHand", "ROCK");
        requestDummy.put("p2Name", "武藤");
        requestDummy.put("p2ThrowHand", "PAPER");

        GameResult gameResult = rpsService.checkWinner(requestDummy);

        assertEquals(gameResult.getResult(), "武藤 wins!");
    }

    @Test
    public void checkWinner_p1HandIsRock_p2HandIsRock() {
        requestDummy.put("p1Name", "さとう");
        requestDummy.put("p1ThrowHand", "ROCK");
        requestDummy.put("p2Name", "武藤");
        requestDummy.put("p2ThrowHand", "ROCK");

        GameResult gameResult = rpsService.checkWinner(requestDummy);

        assertEquals(gameResult.getResult(), "tie!");
    }
}
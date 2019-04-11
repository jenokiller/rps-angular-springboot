package rpsserver;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RpsService {
    public GameResult checkWinner(Map<String, String> params) {
        if (params.get("p1ThrowHand").equals(params.get("p2ThrowHand"))) {
            return new GameResult("tie!");
        } else if (params.get("p1ThrowHand").equals("ROCK") && params.get("p2ThrowHand").equals("SCISSORS")
                || params.get("p1ThrowHand").equals("SCISSORS") && params.get("p2ThrowHand").equals("PAPER")
                || params.get("p1ThrowHand").equals("PAPER") && params.get("p2ThrowHand").equals("ROCK")) {
            return new GameResult(params.get("p1Name") + " wins!");
        }
        return new GameResult(params.get("p2Name") + " wins!");
    }
}

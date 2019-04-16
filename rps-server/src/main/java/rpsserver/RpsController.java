package rpsserver;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/rps")
public class RpsController {
    private RpsService rpsService;

    public RpsController(RpsService rpsService) {
        this.rpsService = rpsService;
    }

    @GetMapping
    public GameResult getResult(@RequestParam Map<String, String> params) {
        if (params.get("p1Name").equals("")
            || params.get("p1ThrowHand").equals("")
            || params.get("p2Name").equals("")
            || params.get("p2ThrowHand").equals(""))
        {
            return new GameResult("");
        }

        return rpsService.checkWinner(params);
    }
}

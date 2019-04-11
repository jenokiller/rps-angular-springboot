package rpsserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class RpsControllerTest {

    private MockMvc mockController;

    @InjectMocks
    RpsController rpsController;

    @Mock
    RpsService rpsService;

    @Captor
    ArgumentCaptor<Map<String, String>> argCaptorGamePlayer;

    MultiValueMap<String, String> requestDummy;

    @Before
    public void setUp() {
        mockController = standaloneSetup(rpsController).build();

        requestDummy = new LinkedMultiValueMap<>();
        requestDummy.add("p1Name", "さとう");
        requestDummy.add("p1ThrowHand", "SCISSORS");
        requestDummy.add("p2Name", "武藤");
        requestDummy.add("p2ThrowHand", "PAPER");
    }

    @Test
    public void getResult_wasCalled() throws Exception {
        mockController.perform(get("/api/rps")
                .params(requestDummy));
        Mockito.verify(rpsService, Mockito.times(1)).checkWinner(anyMap());
    }

    @Test
    public void getResult_getArgumentsValue() throws Exception {
        mockController.perform(get("/api/rps")
                .params(requestDummy));
        Mockito.verify(rpsService).checkWinner(argCaptorGamePlayer.capture());
        Map<String, String> getResultArg = argCaptorGamePlayer.getValue();


        assertEquals("さとう", getResultArg.get("p1Name"));
        assertEquals("SCISSORS", getResultArg.get("p1ThrowHand"));
        assertEquals("武藤", getResultArg.get("p2Name"));
        assertEquals("PAPER", getResultArg.get("p2ThrowHand"));
    }

    @Test
    public void getResult_checkReturnValue() throws Exception {
        GameResult gameResult = new GameResult("さとうwins!");
        Mockito.when(rpsService.checkWinner(anyMap())).thenReturn(gameResult);

        ResultActions test = mockController.perform(get("/api/rps")
                .params(requestDummy));
        MockHttpServletResponse response = test
                .andReturn()
                .getResponse();

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(gameResult);
        System.out.println(jsonInString);


        assertEquals(response.getContentAsString(), jsonInString);
    }

    @Test
    public void getResult_checkReturnValue_containEmptyData() throws Exception {
        MultiValueMap<String, String> emptyDataDummy = new LinkedMultiValueMap<>();

        emptyDataDummy.add("p1Name", "");
        emptyDataDummy.add("p1ThrowHand", "");
        emptyDataDummy.add("p2Name", "");
        emptyDataDummy.add("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "p2ThrowHand");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "p2Name");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "");
        emptyDataDummy.set("p1ThrowHand", "p1ThrowHand");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());

        emptyDataDummy.set("p1Name", "p1Name");
        emptyDataDummy.set("p1ThrowHand", "");
        emptyDataDummy.set("p2Name", "");
        emptyDataDummy.set("p2ThrowHand", "");

        mockController.perform(get("/api/rps")
                .params(emptyDataDummy));


        Mockito.verify(rpsService, Mockito.times(0)).checkWinner(anyMap());
    }
}
import {RpsService} from './rps.service'
import {HttpClientService} from '../http-client.service'
import {GamePlayer} from './game-player'
import SpyObj = jasmine.SpyObj
import {of} from 'rxjs';
import {GameResult} from './game-result';

describe('RpsService', () => {
  let httpClientServiceSpyStub: SpyObj<HttpClientService>;
  let gamePlayer1: GamePlayer;
  let gamePlayer2: GamePlayer;

  beforeEach(() => {
    httpClientServiceSpyStub = jasmine.createSpyObj('HttpClientService', ['get']);
    gamePlayer1 = new GamePlayer('さとう', 'SCISSORS');
    gamePlayer2 = new GamePlayer('武藤', 'PAPER');
  });

  describe('getResultメソッド', () => {
    it('引数がHttpClientServiceに渡されていること', () => {
      const rpsService = new RpsService(httpClientServiceSpyStub);
      rpsService.getResult(gamePlayer1, gamePlayer2);
      expect(httpClientServiceSpyStub.get).toHaveBeenCalledWith('/rps', gamePlayer1, gamePlayer2);
    });

    it('HttpClientService', () => {
      const returnValue = of(new GameResult('武藤wins'));
      httpClientServiceSpyStub.get.and.returnValue(returnValue);

      let value;
      returnValue.subscribe(res => {
        value = res;
      });

      const rpsService = new RpsService(httpClientServiceSpyStub);

      rpsService.getResult(gamePlayer1, gamePlayer2).subscribe(getResultRes => {
          expect(getResultRes).toEqual(value);
      });
    });
  });
});

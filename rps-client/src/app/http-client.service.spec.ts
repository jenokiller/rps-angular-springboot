import {HttpClientService} from './http-client.service'
import {HttpClient, HttpParams} from '@angular/common/http'
import {of} from 'rxjs'
import {GamePlayer} from './rps/game-player'
import SpyObj = jasmine.SpyObj;

const testData1 = new GamePlayer('さとう', 'SCISSORS');
const testData2 = new GamePlayer('武藤', 'PAPER');

describe('HttpClientService', () => {
  let httpClientSpyStub: SpyObj<HttpClient>;
  let service: HttpClientService;
  beforeEach(() => {
    httpClientSpyStub = jasmine.createSpyObj('HttpClient', ['get']);
    httpClientSpyStub.get.and.returnValue(of({result: 'tie'}));

    service = new HttpClientService(httpClientSpyStub);
  });

  describe('get', () => {
    it('HttpClientを使って指定された環境変数に入っているパスに対してgetのHTTPリクエストを飛ばす', () => {
      service.get('/rps', testData1, testData2);
      expect(httpClientSpyStub.get).toHaveBeenCalledWith('http://localhost:8080/api/rps',
          {
            params: new HttpParams()
              .set('p1Name', 'さとう')
              .set('p1ThrowHand', 'SCISSORS')
              .set('p2Name', '武藤')
              .set('p2ThrowHand', 'PAPER')
          }
        )
    });

    it('HttpClientから返ってきたHTTPレスポンスをそのまま返す', () => {
      service.get('/rps', testData1, testData2).subscribe((result) => {
        expect(result).toEqual({result: 'tie'});
      });
    });
  });
});


import {Injectable} from '@angular/core'
import {HttpClientService} from '../http-client.service';
import {Observable} from 'rxjs';
import {GamePlayer} from './game-player';

@Injectable({
  providedIn: 'root'
})
export class RpsService {
  constructor(private httpClientService: HttpClientService) {
  }

  getResult(gamePlayer1: GamePlayer, gamePlayer2: GamePlayer): Observable<any> {
    return this.httpClientService.get('/rps', gamePlayer1, gamePlayer2);
  }
}

import {Injectable} from '@angular/core'
import {Observable} from 'rxjs'
import {environment} from '../environments/environment'
import {HttpClient, HttpParams} from '@angular/common/http'

@Injectable({providedIn: 'root'})
export class HttpClientService {

  constructor(private httpClient: HttpClient) {
  }

  get(path: string, data1: any, data2: any): Observable<any> {
    const params = new HttpParams()
      .set('p1Name', data1.name)
      .set('p1ThrowHand', data1.throwHand)
      .set('p2Name', data2.name)
      .set('p2ThrowHand', data2.throwHand);
    const url = environment.apiUrl + path;
    return this.httpClient.get(url, {params});
  }
}

import {of} from 'rxjs'
import {Injectable} from '@angular/core'

@Injectable({providedIn: 'root'})
export class HttpClient {
  get<T>(url: string, options: {} = {}) {
    if (url === 'http://localhost:8080/api/rps') {
      return of({result: 'Test User'});
    }
  }
}

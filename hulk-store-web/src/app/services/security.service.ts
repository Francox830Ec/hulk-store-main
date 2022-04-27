import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {ProductResponse} from "../model/product-response";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  baseurl = '/security';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-type': 'application/json',
      Accept: 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  registerUser(data: any): Observable<any> {
    return this.http.post<ProductResponse>(
      this.baseurl + '/users/create',
      data,
      this.httpOptions)
      .pipe(retry(1), catchError(this.errorHandle));
  }

  errorHandle(error: { error: { message: string; }; status: any; message: any; }) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {ProductResponse} from "../model/product-response";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseurl = '/products';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-type': 'application/json',
      Accept: 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getInventory(): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(
      this.baseurl + '/management/inventory',
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

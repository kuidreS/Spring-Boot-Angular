import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Exam} from './exam';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  private examUrl = 'http://localhost:8888/monolith/exam';

  constructor(private http: HttpClient) { }

  create(exam: Exam): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.post(`${this.examUrl}/create`, exam, {headers: headers}).subscribe(
        data => {
          console.log('POST Request is successful ', data);
          resolve();
        },
        error => {
          console.log('Error ', error);
          reject();
        }
      );
    }));
  }

}

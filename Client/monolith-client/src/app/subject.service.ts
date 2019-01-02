import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Subject } from './subject';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  private subjectUrl = 'http://localhost:8888/monolith/subject';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.subjectUrl}/all`);
  }

  create(subject: Subject): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.post(`${this.subjectUrl}/create`, subject, {headers: headers}).subscribe(
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

  delete(id: number): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.delete(`${this.subjectUrl}/delete/` + `${id}`).subscribe(
        data => {
          console.log('DELETE Request is successful ', data);
          resolve();
        },
        error => {
          console.log('Error ', error);
          reject();
        }
      );
    }));
  }

  deleteAll(): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.delete(`${this.subjectUrl}/deleteAll`).subscribe(
        data => {
          console.log('DELETE Request is successful ', data);
          resolve();
        },
        error => {
          console.log('Error ', error);
          reject();
        }
      );
    }));
  }

  update(subject: Subject): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.put(`${this.subjectUrl}/update`, subject, {headers: headers}).subscribe(
        data => {
          console.log('PUT Request is successful ', data);
          resolve();
        },
        error => {
          console.log('Error ', error);
          reject();
        }
      );
    }));
  }

  search(name: string): Observable<Subject> {
    return this.http.get<Subject>(`${this.subjectUrl}/search?name=${name}`);
  }
}

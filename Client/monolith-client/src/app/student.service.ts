import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private studentUrl = 'http://localhost:8888/monolith/student';

  constructor(private http: HttpClient) {

  }

  create(student: Student): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.post(`${this.studentUrl}/create`, student, {headers: headers}).subscribe(
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

  update(student: Student): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.put(`${this.studentUrl}/update`, student, {headers: headers}).subscribe(
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

  getAll(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.studentUrl}/all`);
  }

  search(firstName: string, lastName: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.studentUrl}/search/?firstName=${firstName}&lastName=${lastName}`);
  }

  delete(id: number): any {
    return new Promise(((resolve, reject) => {
      const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=UTF-8');
      headers.append('Access-Control-Allow-Methods', 'POST, GET, OPTIONS');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Access-Control-Allow-Headers', 'Authorization,DNT,X-Mx- ReqToken,Keep-Alive,User-Agent,' +
        'X-Requested-With,If-Modified-Since,content-type');

      this.http.delete(`${this.studentUrl}/delete/` + `${id}`).subscribe(
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

      this.http.delete(`${this.studentUrl}/deleteAll`).subscribe(
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
}

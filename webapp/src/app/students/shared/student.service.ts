import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';

import {Student} from './student.model';

import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


@Injectable()
export class StudentService {
  private studentsUrl = 'http://localhost:8080/api/students';

  constructor(private httpClient: HttpClient) {
  }

  getStudents(): Observable<Student[]> {
    console.log(this.httpClient
      .get<Array<Student>>(this.studentsUrl));
    return this.httpClient
      .get<Array<Student>>(this.studentsUrl);
  }

  getStudent(id: number): Observable<Student> {
    return this.getStudents()
      .pipe(
        map(students => students.find(student => student.id === id))
      );
  }

  update(student): Observable<Student> {
    const url = `${this.studentsUrl}/${student.id}`;
    return this.httpClient
      .put<Student>(url, student);
  }

}

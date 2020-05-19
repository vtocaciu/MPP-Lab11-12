import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';

import {Student} from './student.model';

import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';


@Injectable()
export class StudentService {
  private studentsUrl = 'http://localhost:8080/api/students';
  private gradeUrl = 'http://localhost:8080/api/grade/student';

  constructor(private httpClient: HttpClient) {
  }

  getStudentsPage(pageSize, pageNumber): Observable<Student[]> {
    const url = `${this.studentsUrl}/${pageSize}/${pageNumber}`;
    return this.httpClient.get<Array<Student>>(url);
  }

  getStudents(): Observable<Student[]> {
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

  delete(id: number): Observable<Student> {
    const url = `${this.studentsUrl}/${id}`;
    const grUrl = `${this.gradeUrl}/${id}`;
    this.httpClient.delete(grUrl).subscribe();
    return this.httpClient.delete<Student>(url);
  }

  add(student: Student): Observable<Student> {
    return this.httpClient.post<Student>(this.studentsUrl, student);
  }
}

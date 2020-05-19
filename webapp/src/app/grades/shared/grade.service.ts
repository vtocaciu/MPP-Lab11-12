import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Grade} from "./grade.model";
import {Student} from "../../students/shared/student.model";
import {LabProblem} from "../../labproblems/shared/labproblems.model";

@Injectable()
export class GradeService {
  private gradeUrl = 'http://localhost:8080/api/grade';
  private studentUrl = 'http://localhost:8080/api/students';
  private labUrl = 'http://localhost:8080/api/lab';

  constructor(private httpClient: HttpClient) {
  }

  getGradesPage(pageSize, pageNumber): Observable<Grade[]> {
    const url = `${this.gradeUrl}/${pageSize}/${pageNumber}`;
    return this.httpClient.get<Array<Grade>>(url);
  }

  getIdStudent(): Observable<Student[]> {
    return this.httpClient.get<Array<Student>>(this.studentUrl);
  }

  getIdLab(): Observable<LabProblem[]> {
    return this.httpClient.get<Array<LabProblem>>(this.labUrl);
  }

  getGrades(): Observable<Grade[]> {
    return this.httpClient
      .get<Array<Grade>>(this.gradeUrl);
  }

  getGrade(id: number): Observable<Grade> {
    return this.getGrades()
      .pipe(
        map(grades => grades.find(grade => grade.id === id))
      );
  }

  update(grade): Observable<Grade> {
    let url = this.gradeUrl+"/update";
    url = `${url}/${grade.id}/${grade.grade}`;
    // @ts-ignore
    return this.httpClient.put<Grade>(url);
  }

  delete(id: number): Observable<Grade> {
    const url = `${this.gradeUrl}/${id}`;
    return this.httpClient.delete<Grade>(url);
  }

  add(grade: Grade): Observable<Grade> {
    const url = this.gradeUrl+'/assg';
    return this.httpClient.post<Grade>(url, grade);
  }
}

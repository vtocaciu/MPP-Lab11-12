import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../students/shared/student.model";
import {LabProblem} from "../labproblems/shared/labproblems.model";
import {Grade} from "../grades/shared/grade.model";

@Injectable()
export class SortService {
  private studentUrl = 'http://localhost:8080/api/students/sort';
  private labUrl = 'http://localhost:8080/api/lab/sort';
  private gradeUrl = 'http://localhost:8080/api/grade/sort';

  constructor(private httpClient: HttpClient) {
  }

  sortStudent(dir: String, field: String): Observable<Student[]> {
    const url = `${this.studentUrl}/${dir}/${field}`;
    return this.httpClient.get<Array<Student>>(url);
  }
  sortLabs(dir: String, field: String): Observable<LabProblem[]> {
    const url = `${this.labUrl}/${dir}/${field}`;
    return this.httpClient.get<Array<LabProblem>>(url);
  }
  sortGrades(dir: String, field: String): Observable<Grade[]> {
    const url = `${this.gradeUrl}/${dir}/${field}`;
    return this.httpClient.get<Array<Grade>>(url);
  }
}

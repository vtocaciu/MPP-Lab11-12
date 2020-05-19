import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../students/shared/student.model";
import {LabProblem} from "../labproblems/shared/labproblems.model";

@Injectable()
export class FilterService {
  private studentUrl = 'http://localhost:8080/api/students';
  private labUrl = 'http://localhost:8080/api/lab';

  constructor(private httpClient: HttpClient) {
  }

  filterByName(name: string): Observable<Student[]> {
    const url = `${this.studentUrl}/nameFilter/${name}`;
    return this.httpClient.get<Array<Student>>(url);
  }

  filterByGroup(group: string): Observable<Student[]> {
    const url = `${this.studentUrl}/groupFilter/${group}`;
    return this.httpClient.get<Array<Student>>(url);
  }

  filterByStmt(stmt: string): Observable<LabProblem[]> {
    const url = `${this.labUrl}/stmtFilter/${stmt}`;
    return this.httpClient.get<Array<LabProblem>>(url);
  }
  filterByNumber(number: string): Observable<LabProblem[]> {
    const url = `${this.labUrl}/numberFilter/${number}`;
    return this.httpClient.get<Array<LabProblem>>(url);
  }
}

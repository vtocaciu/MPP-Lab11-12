import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LabProblem} from "./labproblems.model";
import {map} from "rxjs/operators";
import {Student} from "../../students/shared/student.model";


@Injectable()
export class LabproblemsService {
  private labsUrl = 'http://localhost:8080/api/lab';
  private gradeUrl = 'http://localhost:8080/api/grade/lab';


  constructor(private httpClient: HttpClient) {
  }

  getLabsPage(pageSize, pageNumber): Observable<LabProblem[]> {
    const url = `${this.labsUrl}/${pageSize}/${pageNumber}`;
    return this.httpClient.get<Array<LabProblem>>(url);
  }

  getLabs(): Observable<LabProblem[]> {
    return this.httpClient
      .get<Array<LabProblem>>(this.labsUrl);
  }

  getLab(id: number): Observable<LabProblem> {
    return this.getLabs()
      .pipe(
        map(labp => labp.find(lab => lab.id === id))
      );
  }

  update(lab): Observable<LabProblem> {
    const url = `${this.labsUrl}/${lab.id}`;
    return this.httpClient
      .put<LabProblem>(url, lab);
  }

  delete(id: number): void {
    const url = `${this.labsUrl}/${id}`;
    const grUrl = `${this.gradeUrl}/${id}`;
    this.httpClient.delete(grUrl).subscribe();
    this.httpClient.delete(url).subscribe(data => console.log(data));
  }

  add(lab: LabProblem): void {
    this.httpClient.post(this.labsUrl, lab).subscribe();
  }
}

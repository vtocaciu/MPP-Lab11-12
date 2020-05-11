import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LabProblem} from "./labproblems.model";


@Injectable()
export class LabproblemsService {
  private labsUrl = 'http://localhost:8080/api/lab';

  constructor(private httpClient: HttpClient) {
  }

  getLabs(): Observable<LabProblem[]> {
    return this.httpClient
      .get<Array<LabProblem>>(this.labsUrl);
  }
}

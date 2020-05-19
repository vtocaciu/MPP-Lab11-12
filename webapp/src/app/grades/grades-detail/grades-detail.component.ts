import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {Grade} from "../shared/grade.model";
import {GradeService} from "../shared/grade.service";

@Component({
  selector: 'app-grades-detail',
  templateUrl: './grades-detail.component.html',
  styleUrls: ['./grades-detail.component.css']
})
export class GradesDetailComponent implements OnInit {

  @Input() grade: Grade;

  constructor(private gradeService: GradeService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.gradeService.getGrade(+params['id'])))
      .subscribe(gr => this.grade = gr);
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    this.gradeService.update(this.grade)
      .subscribe(_ => this.goBack());
  }

}

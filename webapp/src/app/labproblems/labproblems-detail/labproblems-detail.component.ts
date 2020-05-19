import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {LabProblem} from "../shared/labproblems.model";
import {LabproblemsService} from "../shared/labproblems.service";

@Component({
  selector: 'ubb-labproblems-detail',
  templateUrl: './labproblems-detail.component.html',
  styleUrls: ['./labproblems-detail.component.css']
})
export class LabproblemsDetailComponent implements OnInit {

  @Input() lab: LabProblem;

  constructor(private labProblemService: LabproblemsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.labProblemService.getLab(+params['id'])))
      .subscribe(lab => this.lab = lab);
  }

  goBack(): void {
    this.location.back();
  }

  update(): void {
    this.labProblemService.update(this.lab)
      .subscribe(_ => this.goBack());
  }

}

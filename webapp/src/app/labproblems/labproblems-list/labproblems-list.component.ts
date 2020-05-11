import { Component, OnInit } from '@angular/core';

import {Router} from "@angular/router";
import {LabproblemsService} from "../shared/labproblems.service";
import {LabProblem} from "../shared/labproblems.model";

@Component({
  selector: 'ubb-labproblems-list',
  templateUrl: './labproblems-list.component.html',
  styleUrls: ['./labproblems-list.component.css']
})
export class LabproblemsListComponent implements OnInit {
  errorMessage: string;
  labs: Array<LabProblem>;
  selectedLab: LabProblem;
  addLab: LabProblem;

  constructor(private labProblemService: LabproblemsService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getLabs();
  }

  getLabs() {
    this.labProblemService.getLabs()
      .subscribe(
        students => this.labs = students,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(lab): void{
    this.selectedLab = lab;
  }
}

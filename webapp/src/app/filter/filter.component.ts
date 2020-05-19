import { Component, OnInit } from '@angular/core';
import {GradeService} from "../grades/shared/grade.service";
import {FilterService} from "./filter.service";
import {Student} from "../students/shared/student.model";
import {LabProblem} from "../labproblems/shared/labproblems.model";

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  constructor(private filterService: FilterService) { }

  selectedOption: string;
  selectedField: string;
  selectedValue: string;
  errorMessage: string;
  allStudents: Student[];
  allLabs: LabProblem[];


  ngOnInit(): void {
  }

  filter(): void {
    if (this.selectedValue !== '' && this.selectedValue !== undefined) {
      if (this.selectedOption === 'student') {
        if (this.selectedField === 'studentGroup') {
          this.filterService.filterByGroup(this.selectedValue)
            .subscribe(data => {this.allStudents = data; this.allLabs = undefined;}, error => this.errorMessage = <any>error);
        } else {
          this.filterService.filterByName(this.selectedValue)
            .subscribe(data => {this.allStudents = data; this.allLabs = undefined;}, error => this.errorMessage = <any>error);
        }
      } else {
        if (this.selectedField === 'labnumber') {
          this.filterService.filterByNumber(this.selectedValue)
            .subscribe(data => {this.allLabs = data; this.allStudents = undefined;}, error => this.errorMessage = <any>error);
        } else {
          this.filterService.filterByStmt(this.selectedValue)
            .subscribe(data => {this.allLabs = data; this.allStudents = undefined;}, error => this.errorMessage = <any>error);
        }
      }
    }else{
      alert("invalid value");
    }
  }
}

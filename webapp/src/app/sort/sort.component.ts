import { Component, OnInit } from '@angular/core';
import {LabProblem} from "../labproblems/shared/labproblems.model";
import {Grade} from "../grades/shared/grade.model";
import {Student} from "../students/shared/student.model";
import {FilterService} from "../filter/filter.service";
import {SortService} from "./sort.service";
import set = Reflect.set;

@Component({
  selector: 'app-sort',
  templateUrl: './sort.component.html',
  styleUrls: ['./sort.component.css']
})
export class SortComponent implements OnInit {

  constructor(private sortService: SortService) { }

  selectedOption: string;
  dir: string;
  fields: string;
  labs: LabProblem[];
  grades: Grade[];
  students: Student[];
  errorMessage: string;
  private valid: boolean;

  ngOnInit(): void {
  }

  validateInput(): void {
    this.valid = true;
    if(this.dir === undefined) {
      this.valid = false;
      alert("Invalid dir!");
    }
    if (this.fields === undefined) {
      this.valid = false;
      alert("Invalid fields!");
    }
    if (this.dir.split(',').length !== this.fields.split(',').length) {
      alert("Different size for input!");
      this.valid = false;
    }
  }

  sort() : void {
    this.validateInput();
    if (this.valid === true) {
      if (this.selectedOption === "student") {
        this.sortService.sortStudent(this.dir, this.fields).subscribe(
          data => {
            this.students = data;
            this.labs = undefined;
            this.grades = undefined;
          },
          error => {
            this.errorMessage = <any>error; alert("Wrong fields!");
          });
      }
      if (this.selectedOption === "labproblem") {
        this.sortService.sortLabs(this.dir, this.fields).subscribe(
          data => {
            this.labs = data;
            this.students = undefined;
            this.grades = undefined;
          },
          error => {
            this.errorMessage = <any>error; alert("Wrong fields!");
          });
      }
      if (this.selectedOption === "grade") {
        this.sortService.sortGrades(this.dir, this.fields).subscribe(
          data => {
            this.grades = data;
            this.labs = undefined;
            this.students = undefined;
          },
          error => {
            this.errorMessage = <any>error; alert("Wrong fields!");
          });
      }
    }
  }
}

import {Component, OnInit, ViewChild} from '@angular/core';
import {Grade} from '../shared/grade.model';
import {Router} from '@angular/router';
import {GradeService} from '../shared/grade.service';
import {MatTableDataSource} from "@angular/material/table";
import {MatTable} from "@angular/material/table";
import {DataSource} from "@angular/cdk/collections";
import "@angular/material/prebuilt-themes/indigo-pink.css"
import {MatSort} from "@angular/material/sort";
import {Student} from "../../students/shared/student.model";
import {LabProblem} from "../../labproblems/shared/labproblems.model";

@Component({
  selector: 'ubb-grades-list',
  templateUrl: './grades-list.component.html',
  styleUrls: ['./grades-list.component.css']
})


export class GradesListComponent implements OnInit {



  errorMessage: string;
  grades: Grade[];
  selectedGrade: Grade;
  addGrade: Grade;
  displayedColumns: string[] = ['id','grade','idStudent','idLabProblem'];
  dataSource = new MatTableDataSource<Grade>();
  totalPages: number;
  pageSize: number;
  students: Student[];
  labs: LabProblem[];
  selectStudent: number;
  selectLab: number;

  constructor(private gradeService: GradeService, private router: Router) {
  }
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  ngOnInit(): void {
    this.getGrades();
    this.dataSource.sort = this.sort;
    this.pageSize = 5;

  }

  getGrades() {
    this.gradeService.getGrades()
      .subscribe(
        gr => {this.grades = gr; this.dataSource.data = this.grades;},
        error => this.errorMessage = <any>error
      );
    this.gradeService.getIdStudent().subscribe(
      st => {this.students = st; },
      error => this.errorMessage = <any>error
    );
    this.gradeService.getIdLab().subscribe(
      lb => {this.labs = lb; },
      error => this.errorMessage = <any>error
    );
  }



  onSelect(gr: Grade): void {
    this.selectedGrade = gr;
  }

  goToPage(pageNumber) {
    this.totalPages = Math.trunc(this.grades.length / this.pageSize);
    if (this.grades.length % this.pageSize !== 0) {
      this.totalPages += 1;
    }
    this.gradeService.getGradesPage(this.pageSize, pageNumber).subscribe(
      grades => {this.dataSource.data = grades; },
      error => this.errorMessage = <any>error
    );
  }

  applyFilter($event: KeyboardEvent) {
    const filterValue = (event.target as HTMLInputElement).value;
    console.log(this.grades);
    this.dataSource.data = this.grades;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.totalPages = 0;
    if((event.target as HTMLInputElement).value === ""){
      this.goToPage(1);
    }
  }

  arrayOne(n: number): any[] {
    return Array(n);
  }
  setPageSize(event) {
    // tslint:disable-next-line:radix
    if(parseInt(event.target.value) !== NaN) {
      this.pageSize = event.target.value;
      this.totalPages = Math.trunc(this.grades.length / this.pageSize);
      if (this.grades.length % this.pageSize !== 0) {
        this.totalPages += 1;
      }
      this.goToPage(1);
    } else {
      alert('invalid number!');
    }
  }
  add() : void{
    let grade;
    grade = new Grade();
    grade.idStudent = this.selectStudent;
    grade.idLabProblem = this.selectLab;
    grade.grade = 0;
    this.gradeService.add(grade).subscribe(data=> {console.log(data); this.getGrades();}, error => this.errorMessage = <any>error);
  }

  goToDetail(): void {
    this.router.navigate(['/grades/detail', this.selectedGrade.id]);

  }

  delete(grade: Grade): void {
    this.gradeService.delete(grade.id).subscribe(data=> {console.log(data); this.getGrades();}, error => this.errorMessage = <any>error);
    window.location.reload();
  }
}

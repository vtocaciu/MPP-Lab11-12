import {Component, OnInit, ViewChild} from '@angular/core';
import {Student} from '../shared/student.model';
import {StudentService} from '../shared/student.service';
import {Router} from '@angular/router';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";


@Component({
  moduleId: module.id,
  selector: 'ubb-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  errorMessage: string;
  students: Student[];
  selectedStudent: Student;
  addStudent: Student;
  displayedColumns: string[] = ['id', 'studentName', 'studentGroup'];
  dataSource = new MatTableDataSource<Student>();
  totalPages: number;
  pageSize: number;

  constructor(private studentService: StudentService,
              private router: Router) {
  }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  ngOnInit(): void {
    this.getStudents();
    this.pageSize = 5;
    this.dataSource.sort = this.sort;
    this.addStudent = new Student();
  }

  getStudents() {
    this.studentService.getStudents()
      .subscribe(
        students => {this.students = students;
        this.totalPages = Math.trunc(this.students.length / this.pageSize);
        if (this.students.length % this.pageSize !== 0) {
          this.totalPages += 1;
        }
          console.log(this.totalPages);
          this.goToPage(1);

        },
        error => this.errorMessage = <any>error
      );
  }

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }

  arrayOne(n: number): any[] {
    return Array(n);
  }

  gotoDetail(): void {
    this.router.navigate(['/student/detail', this.selectedStudent.id]);
  }

  delete(student: Student): void {
    this.studentService.delete(student.id).subscribe(data=> {this.getStudents(); this.selectedStudent=undefined;}, error => this.errorMessage = <any>error);

    //window.location.reload();
  }

  add(): void {
    this.studentService.add(this.addStudent).subscribe(data => {this.getStudents(); this.selectedStudent=undefined;}, error => this.errorMessage = <any>error);
    //window.location.reload();
  }

  setStudentName(event): void {
    this.addStudent.studentName = event.target.value;
  }

  setStudentGroup(event): void {
    this.addStudent.studentGroup = event.target.value;
  }

  goToPage(pageNumber) {
    this.totalPages = Math.trunc(this.students.length / this.pageSize);
    if (this.students.length % this.pageSize !== 0) {
      this.totalPages += 1;
    }
    this.studentService.getStudentsPage(this.pageSize, pageNumber).subscribe(
      students => {this.dataSource.data = students; },
      error => this.errorMessage = <any>error
    );
  }

  applyFilter($event: KeyboardEvent) {
    const filterValue = (event.target as HTMLInputElement).value;
    console.log(this.students);
    this.dataSource.data = this.students;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.totalPages = 0;
    if((event.target as HTMLInputElement).value === ""){
      this.goToPage(1);
    }
  }

  setPageSize(event) {
    // tslint:disable-next-line:radix
    if(parseInt(event.target.value) !== NaN) {
      this.pageSize = event.target.value;
      this.totalPages = Math.trunc(this.students.length / this.pageSize);
      if (this.students.length % this.pageSize !== 0) {
        this.totalPages += 1;
      }
      this.goToPage(1);
    } else {
      alert('invalid number!');
    }
  }

}

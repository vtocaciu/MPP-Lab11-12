import {Component, OnInit} from '@angular/core';
import {Student} from '../shared/student.model';
import {StudentService} from '../shared/student.service';
import {Router} from '@angular/router';


@Component({
  moduleId: module.id,
  selector: 'ubb-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  errorMessage: string;
  students: Array<Student>;
  selectedStudent: Student;
  addStudent: Student;

  constructor(private studentService: StudentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getStudents();
    this.addStudent={};
  }

  getStudents() {
    this.studentService.getStudents()
      .subscribe(
        students => this.students = students,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }

  gotoDetail(): void {
    this.router.navigate(['/student/detail', this.selectedStudent.id]);
  }

  delete(student: Student): void {
    this.studentService.delete(student.id);
    window.location.reload();
  }

  add(): void {
    console.log(this.addStudent);
    this.studentService.add(this.addStudent);
    window.location.reload();
  }

  setStudentName(event): void {
    this.addStudent.studentName = event.target.value;
  }

  setStudentGroup(event): void {
    this.addStudent.studentGroup = event.target.value;
  }

}

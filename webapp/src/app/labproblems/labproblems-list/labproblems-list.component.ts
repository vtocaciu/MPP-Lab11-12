import {Component, OnInit, ViewChild} from '@angular/core';

import {Router} from '@angular/router';
import {LabproblemsService} from '../shared/labproblems.service';
import {LabProblem} from '../shared/labproblems.model';
import {MatTableDataSource} from "@angular/material/table";
import {Student} from "../../students/shared/student.model";
import {MatSort} from "@angular/material/sort";

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
  displayedColumns: string[] = ['id', 'problemStatement', 'labNumber'];
  dataSource = new MatTableDataSource<LabProblem>();
  totalPages: number;
  pageSize: number;

  constructor(private labProblemService: LabproblemsService,
              private router: Router) {
  }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  ngOnInit(): void {
    this.getLabs();
    this.pageSize = 5;
    this.dataSource.sort = this.sort;
    this.addLab = new LabProblem();
  }

  getLabs() {
    this.labProblemService.getLabs()
      .subscribe(
        lb => {this.labs = lb; this.dataSource.data = lb;},
        error => this.errorMessage = <any>error
      );
  }

  onSelect(lab): void {
    this.selectedLab = lab;
    console.log(lab);
  }

  goToDetail(): void {
    this.router.navigate(['/labproblems/detail', this.selectedLab.id]);

  }

  delete(lab: LabProblem): void {
    this.labProblemService.delete(lab.id);
    window.location.reload();
  }

  add(): void {
    this.labProblemService.add(this.addLab);
    window.location.reload();
  }

  setProblemStatement(event): void {
    this.addLab.problemStatement = event.target.value;
  }

  setLabNumber(event): void {
    this.addLab.labNumber = event.target.value;
  }


  goToPage(pageNumber) {
    this.totalPages = Math.trunc(this.labs.length / this.pageSize);
    if (this.labs.length % this.pageSize !== 0) {
      this.totalPages += 1;
    }
    this.labProblemService.getLabsPage(this.pageSize, pageNumber).subscribe(
      labs => {this.dataSource.data = labs; },
      error => this.errorMessage = <any>error
    );
  }

  applyFilter($event: KeyboardEvent) {
    const filterValue = (event.target as HTMLInputElement).value;
    console.log(this.labs);
    this.dataSource.data = this.labs;
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
      this.totalPages = Math.trunc(this.labs.length / this.pageSize);
      if (this.labs.length % this.pageSize !== 0) {
        this.totalPages += 1;
      }
      this.goToPage(1);
    } else {
      alert('invalid number!');
    }
  }
}

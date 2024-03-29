import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {StudentsComponent} from './students/students.component';
import {StudentListComponent} from './students/student-list/student-list.component';
import {StudentService} from './students/shared/student.service';
import { GradesComponent } from './grades/grades.component';
import { GradesDetailComponent } from './grades/grades-detail/grades-detail.component';
import { GradesListComponent } from './grades/grades-list/grades-list.component';
import { LabproblemsComponent } from './labproblems/labproblems.component';
import { LabproblemsDetailComponent } from './labproblems/labproblems-detail/labproblems-detail.component';
import { LabproblemsListComponent } from './labproblems/labproblems-list/labproblems-list.component';
import {LabproblemsService} from "./labproblems/shared/labproblems.service";
import {GradeService} from "./grades/shared/grade.service";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import { FilterComponent } from './filter/filter.component';
import { SortComponent } from './sort/sort.component';
import {FilterService} from "./filter/filter.service";
import {SortService} from "./sort/sort.service";

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    StudentDetailComponent,
    StudentsComponent,
    StudentListComponent,
    GradesComponent,
    GradesDetailComponent,
    GradesListComponent,
    LabproblemsComponent,
    LabproblemsDetailComponent,
    LabproblemsListComponent,
    FilterComponent,
    SortComponent




  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatTableModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  providers: [LabproblemsService, StudentService, GradeService, FilterService, SortService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

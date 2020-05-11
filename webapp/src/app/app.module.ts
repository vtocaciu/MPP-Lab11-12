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


@NgModule({
  declarations: [
    AppComponent,
    StudentDetailComponent,
    StudentsComponent,
    StudentListComponent,
    GradesComponent,
    GradesDetailComponent,
    GradesListComponent,



  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [StudentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

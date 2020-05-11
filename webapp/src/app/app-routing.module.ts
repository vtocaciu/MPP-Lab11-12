import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentsComponent} from './students/students.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {GradesComponent} from './grades/grades.component';
import {LabproblemsComponent} from './labproblems/labproblems.component';


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'students', component: StudentsComponent},
  {path: 'student/detail/:id', component: StudentDetailComponent},
  {path: 'grades', component: GradesComponent},
  {path: 'labproblems', component: LabproblemsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

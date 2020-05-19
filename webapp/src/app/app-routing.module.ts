import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentsComponent} from './students/students.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {GradesComponent} from './grades/grades.component';
import {LabproblemsComponent} from './labproblems/labproblems.component';
import {LabproblemsDetailComponent} from './labproblems/labproblems-detail/labproblems-detail.component';
import {GradesDetailComponent} from "./grades/grades-detail/grades-detail.component";
import {FilterComponent} from "./filter/filter.component";
import {SortComponent} from "./sort/sort.component";



const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'students', component: StudentsComponent},
  {path: 'student/detail/:id', component: StudentDetailComponent},
  {path: 'grades', component: GradesComponent},
  {path: 'labproblems', component: LabproblemsComponent},
  {path: 'labproblems/detail/:id', component: LabproblemsDetailComponent},
  {path: 'grades/detail/:id', component: GradesDetailComponent},
  {path: 'filter', component: FilterComponent},
  {path: 'sort', component: SortComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

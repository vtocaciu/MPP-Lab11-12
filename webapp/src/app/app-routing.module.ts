import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentsComponent} from './students/students.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'students', component: StudentsComponent},
  {path: 'student/detail/:id', component: StudentDetailComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

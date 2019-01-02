import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {StudentListComponent} from './student-list/student-list.component';
import {SubjectListComponent} from './subject-list/subject-list.component';
import {ExamCreateComponent} from './exam-create/exam-create.component';

const routes: Routes = [
  {
    path: 'students',
    component: StudentListComponent
  },
  {
    path: 'subjects',
    component: SubjectListComponent
  },
  {
    path: 'exam',
    component: ExamCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

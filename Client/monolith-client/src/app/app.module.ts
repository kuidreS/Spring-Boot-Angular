import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentListComponent } from './student-list/student-list.component';
import { HttpClientModule } from '@angular/common/http';
import { StudentCreateComponent } from './student-create/student-create.component';
import { StudentService } from './student.service';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { StudentSearchComponent } from './student-search/student-search.component';
import { SubjectCreateComponent } from './subject-create/subject-create.component';
import { SubjectService } from './subject.service';
import { SubjectSearchComponent } from './subject-search/subject-search.component';
import { ExamCreateComponent } from './exam-create/exam-create.component';
import { ExamService } from './exam.service';
import { StatisticsComponent } from './statistics/statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent,
    StudentCreateComponent,
    SubjectListComponent,
    StudentSearchComponent,
    SubjectCreateComponent,
    SubjectSearchComponent,
    ExamCreateComponent,
    StatisticsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [StudentService, SubjectService, ExamService],
  bootstrap: [AppComponent]
})
export class AppModule { }

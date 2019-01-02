import { Component, OnInit } from '@angular/core';
import { ExamService } from '../exam.service';
import { ECTS } from '../ects';
import {Student} from '../student';
import {StudentService} from '../student.service';
import {Subject} from '../subject';
import {SubjectService} from '../subject.service';
import {Exam} from '../exam';

@Component({
  selector: 'app-exam-create',
  templateUrl: './exam-create.component.html',
  styleUrls: ['./exam-create.component.css']
})
export class ExamCreateComponent implements OnInit {

  ectsArray: string[] = ECTS;
  selectedEcts: string;
  students: Student[];
  selectedStudent: Student;
  subjects: Subject[];
  selectedSubject: Subject;
  submitted = false;

  constructor(private examService: ExamService,
              private studentService: StudentService,
              private subjectService: SubjectService) { }

  ngOnInit() {
    this.getStudentList();
    this.getSubjectList();
  }

  getStudentList() {
    this.studentService.getAll().subscribe(students => this.students = students);
  }

  getSubjectList() {
    this.subjectService.getAll().subscribe(subjects => this.subjects = subjects);
  }

  create() {
    const exam: Exam = new Exam(this.selectedSubject, this.selectedStudent, this.selectedEcts);
    this.submitted = true;
    this.examService.create(exam).then(() => {console.log('Exam has been created'); });
  }

  continueCreating() {
    this.submitted = false;
    this.selectedStudent = null;
    this.selectedEcts = null;
    this.selectedSubject = null;
  }

}

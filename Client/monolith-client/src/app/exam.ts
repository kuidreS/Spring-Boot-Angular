import {Subject} from './subject';
import {Student} from './student';

export class Exam {
  id: number;
  subject: Subject;
  student: Student;
  ects: string;


  constructor(subject: Subject, student: Student, ects: string, id?: number) {
    this.subject = subject;
    this.student = student;
    this.ects = ects;
    if (id) {
      this.id = id;
    }
  }
}

import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Student} from '../student';
import {StudentService} from '../student.service';

@Component({
  selector: 'app-student-search',
  templateUrl: './student-search.component.html',
  styleUrls: ['./student-search.component.css']
})
export class StudentSearchComponent implements OnInit {

  @Output() eventEmitter = new EventEmitter();

  students: Student[];
  submitted = false;
  firstName: string;
  lastName: string;
  selectedStudent: any;
  studentId: number;
  updatedFirstName: string;
  updatedLastName: string;
  updateStudentMode: boolean;

  constructor(private studentService: StudentService) { }

  ngOnInit() {
  }

  search(firstName: string, lastName: string) {
    this.submitted = true;
    this.studentService.search(firstName, lastName).subscribe(students => this.students = students);
  }

  newSearch() {
    this.submitted = false;
  }

  onSelect(selectedStudent: any) {
    this.selectedStudent = selectedStudent;
    this.studentId = selectedStudent.id;
    this.updatedFirstName = selectedStudent.firstName;
    this.updatedLastName = selectedStudent.lastName;
  }

  updateStudent() {
    this.updateStudentMode = true;
  }

  update(updatedFirstName: string, updatedLastName: string) {
    const student = new Student(updatedFirstName, updatedLastName, this.studentId);
    this.studentService.update(student).then(() => {
      this.eventEmitter.emit();
      this.updateStudentMode = false;
      this.search(this.selectedStudent.firstName, this.selectedStudent.lastName);
    });
  }

  deleteStudent() {
    this.studentService.delete(this.selectedStudent.id).then(() => {
      this.eventEmitter.emit();
      this.search(this.selectedStudent.firstName, this.selectedStudent.lastName);
    });
  }

}

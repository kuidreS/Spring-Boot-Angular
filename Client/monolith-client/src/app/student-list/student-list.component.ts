import { Component, OnInit } from '@angular/core';

import { StudentService } from '../student.service';
import { Student } from '../student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[];
  selectedStudent: any;
  addStudentVisible: boolean;
  studentId: number;
  updateStudentMode: boolean;
  searchStudentVisible: boolean;
  firstName: string;
  lastName: string;

  constructor(private studentService: StudentService) { }

  ngOnInit() {
    this.getList();
  }

  getList() {
    this.studentService.getAll().subscribe(students => this.students = students);
  }

  deleteAllStudents() {
    this.studentService.deleteAll().then(() => {
      this.updateStudentList();

    });
  }

  deleteStudent() {
    this.studentService.delete(this.selectedStudent.id).then(() => {
      this.updateStudentList();
    });
  }

  updateStudentList() {
    this.getList();
  }

  onSelect(selectedStudent: any) {
    this.selectedStudent = selectedStudent;
    this.studentId = selectedStudent.id;
    this.firstName = selectedStudent.firstName;
    this.lastName = selectedStudent.lastName;
  }

  addStudent() {
    if (this.addStudentVisible === true) {
      this.addStudentVisible = false;
    } else {
      this.addStudentVisible = true;
    }
  }

  searchStudent() {
    if (this.searchStudentVisible === true) {
      this.searchStudentVisible = false;
    } else {
      this.searchStudentVisible = true;
    }
  }

  updateStudent() {
    this.updateStudentMode = true;
  }

  update(firstName: string, lastName: string) {
    const student = new Student(firstName, lastName, this.studentId);
    this.studentService.update(student).then(() => {
      this.updateStudentMode = false;
      this.updateStudentList();
    });
  }

  hideSearch() {
    this.searchStudentVisible = false;
  }

  hideCreate() {
    this.addStudentVisible = false;
  }

}

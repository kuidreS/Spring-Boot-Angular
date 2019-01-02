import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Student} from '../student';
import {StudentService} from '../student.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-student-create',
  templateUrl: './student-create.component.html',
  styleUrls: ['./student-create.component.css'],
  providers: [StudentService]
})
export class StudentCreateComponent implements OnInit {

  @Output() eventEmitter = new EventEmitter();

  submitted = false;

  constructor(private studentService: StudentService) {
  }

  ngOnInit() {
  }

  create(firstName: string, lastName: string) {
    const student = new Student(firstName, lastName);
    this.submitted = true;
    this.studentService.create(student).then(() => {
      this.eventEmitter.emit();
    }).catch((error) => {
      console.log(error);
    });
  }

  continueCreating() {
    this.submitted = false;
  }

}

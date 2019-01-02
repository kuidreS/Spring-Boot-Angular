import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SubjectService} from '../subject.service';
import {Subject} from '../subject';

@Component({
  selector: 'app-subject-create',
  templateUrl: './subject-create.component.html',
  styleUrls: ['./subject-create.component.css']
})
export class SubjectCreateComponent implements OnInit {

  @Output() eventEmitter = new EventEmitter();

  submitted = false;

  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
  }

  create(name: string) {
    const subject = new Subject(name);
    this.submitted = true;
    this.subjectService.create(subject).then(() => {
      this.eventEmitter.emit();
    }).catch((error) => {
      console.log(error);
    });
  }

  continueCreating() {
    this.submitted = false;
  }

}

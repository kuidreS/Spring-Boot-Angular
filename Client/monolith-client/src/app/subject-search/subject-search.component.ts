import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Subject} from '../subject';
import {SubjectService} from '../subject.service';
import {Student} from '../student';

@Component({
  selector: 'app-subject-search',
  templateUrl: './subject-search.component.html',
  styleUrls: ['./subject-search.component.css']
})
export class SubjectSearchComponent implements OnInit {

  @Output() eventEmitter = new EventEmitter();

  subject: Subject;
  searchSubjectName: string;
  submitted = false;
  updatedSubjectMode: boolean;
  updatedSubjectName: string;

  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
  }

  search(name: string) {
    this.submitted = true;
    this.subjectService.search(name).subscribe(subject => this.subject = subject);
  }

  newSearch() {
    this.submitted = false;
  }

  updateSubject() {
    const subject = new Subject(this.updatedSubjectName, this.subject.id);
    this.subjectService.update(subject).then(() => {
      this.eventEmitter.emit();
      this.updatedSubjectMode = false;
      this.search(this.searchSubjectName);
    });
  }

  deleteSubject() {
    this.subjectService.delete(this.subject.id).then(() => {
      this.eventEmitter.emit();
      this.search(this.searchSubjectName);
    });
  }

  updateSubjectMode() {
    this.updatedSubjectMode = true;
  }

}

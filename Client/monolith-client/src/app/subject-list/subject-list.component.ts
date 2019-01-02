import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import {Student} from '../student';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  addSubjectVisible: boolean;
  selectedSubject: any;
  subjectId: number;
  subjectName: string;
  updateSubjectMode: boolean;
  searchSubjectVisible: boolean;

  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
    this.getList();
  }

  getList() {
    this.subjectService.getAll().subscribe(subjects => this.subjects = subjects);
  }

  addSubject() {
    if (this.addSubjectVisible === true) {
      this.addSubjectVisible = false;
    } else {
      this.addSubjectVisible = true;
    }
  }

  hideCreate() {
    this.addSubjectVisible = false;
  }

  deleteAllSubjects() {
    this.subjectService.deleteAll().then(() => {
      this.getList();
    });
  }

  onSelect(selectedSubject: any) {
    this.selectedSubject = selectedSubject;
    this.subjectId = selectedSubject.id;
    this.subjectName = selectedSubject.name;
  }

  updateSubject() {
    this.updateSubjectMode = true;
  }

  update(subjectName: string) {
    const subject = new Subject(subjectName, this.subjectId);
    this.subjectService.update(subject).then(() => {
      this.updateSubjectMode = false;
      this.getList();
    });
  }

  deleteSubject() {
    this.subjectService.delete(this.selectedSubject.id).then(() => {
      this.getList();
    });
  }

  searchSubject() {
    if (this.searchSubjectVisible === true) {
      this.searchSubjectVisible = false;
    } else {
      this.searchSubjectVisible = true;
    }
  }

  hideSearch() {
    this.searchSubjectVisible = false;
  }

}

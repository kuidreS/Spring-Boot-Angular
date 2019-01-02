export class Student {
  id: number;
  firstName: string;
  lastName: string;

  constructor(firstName: string, lastName: string, id?: number) {
    this.firstName = firstName;
    this.lastName = lastName;
    if (id) {
      this.id = id;
    }
  }
}

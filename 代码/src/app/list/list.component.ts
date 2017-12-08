import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  private books: Array<Book>;
  private bookb: Array<Book>;
  constructor() {
  }
  deleteBook(book: Book) {
    if (confirm('确认删除吗') === true) {
      this.books = this.bookb;
      console.log(book.id);
    }
  }
  ngOnInit() {
    this.books = [
      new Book(1, '韩智丞相声集', 'ds', 2),
      new Book(2, '韩智丞相声集2', 'ds', 2),
      new Book(3, '韩智丞相声集3', 'ds', 1),
      new Book(4, '韩智丞相声集4', 'ds', 3),
      new Book(5, '韩智丞相声集5', 'ds', 4),
      new Book(6, '韩智丞相声集6', 'ds', 5),
      new Book(7, '韩智丞相声集7', 'ds', 6),
      new Book(8, '韩智丞回忆录', 'ds', 3),
      new Book(9, '韩智丞回忆录2', 'ds', 234),
      new Book(10, '韩智丞回忆录3', 'ds', 22)
    ];
    this.bookb = [
      new Book(1, '韩智丞相声集', 'ds', 2),
      new Book(2, '韩智丞相声集2', 'ds', 2),
      new Book(3, '韩智丞相声集3', 'ds', 1),
      new Book(4, '韩智丞相声集4', 'ds', 3)
    ];
  }

}

export class Book {
  constructor(
    public id: number,
    public name: string,
    public publisher: string,
    public num: number
) {
}
}


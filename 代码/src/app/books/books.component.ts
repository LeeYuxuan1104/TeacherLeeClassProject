import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  books: Array<Book>;
  constructor() { }

  ngOnInit() {
    this.books = [
      new Book(1, '1', 'helloworld', '这个是书目备注', '这个是作者', '这个是出版社',
        '这个是出版日期', '这个是出版总数', '这个是大类编号', 'http://placehold.it/160x180'),
      new Book(2, '1', 'helloworld2', '这个是书目备注2', '这个是作者2', '这个是出版社2',
        '这个是出版日期2', '这个是出版总数2', '这个是大类编号2', 'http://placehold.it/160x180'),
      new Book(3, '1', 'helloworld3', '这个是书目备注3', '这个是作者3', '这个是出版社3',
        '这个是出版日期3', '这个是出版总数3', '这个是大类编号3', 'http://placehold.it/160x180'),
      new Book(3, '1', 'helloworld3', '这个是书目备注3', '这个是作者3', '这个是出版社3',
        '这个是出版日期3', '这个是出版总数3', '这个是大类编号3', 'http://placehold.it/160x180')
    ];
  }

}
export class Book {
  constructor(
    public order: number,  // 编号
    public iid: string, // 书目编号
    public name: string, // 书目名称
    public note: string, // 书目备注
    public author: string, // 书目作者
    public press: string, // 出版社
    public ptime: string, // 出版日期
    public count: string, // 出版总数
    public kid: string, // 大类编号
    public img: string // 书目图片
  ) {
  }
}

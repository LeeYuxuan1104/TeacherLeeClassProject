import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  usr: string;
  // router: Router;
  constructor(private router: Router) {
  }
  ngOnInit() {
    this.usr = sessionStorage.getItem('usr');
  }
  logout() {
    sessionStorage.removeItem('usr');
    this.usr = '';
    setTimeout(() => {if (sessionStorage.getItem('usr') == null) {
      alert('请先登录');
      this.router.navigate(['/login']);
    }}, 100);
  }
}

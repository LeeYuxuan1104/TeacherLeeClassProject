import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
@Component({
  selector: 'app-userinfo',
  templateUrl: './userinfo.component.html',
  styleUrls: ['./userinfo.component.css']
})
export class UserinfoComponent implements OnInit {
  changeForm: FormGroup;
  user: User;
  estate: number;
  pstate: number;
  constructor(fb: FormBuilder, private router: Router) {
    this.user = new User(1, '1', '燕昭', '123456', '管理员', '无备注', 'http://placehold.it/160x180', '18622989563', '915794414@qq.com');
    this.changeForm = fb.group({
      note : [this.user.note],
      phone : [this.user.phone, [Validators.pattern('^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$')]],
      email : [this.user.email, [Validators.pattern('^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$')]]
    });
  }

  ngOnInit() {
  }
  logout() {
    sessionStorage.removeItem('usr');
    setTimeout(() => {if (sessionStorage.getItem('usr') == null) {
      alert('请先登录');
      this.router.navigate(['/login']);
    }}, 100);
  }
  onSubmit() {
    const isValid: boolean = this.changeForm.get('phone').valid && this.changeForm.get('email').valid;
   // console.log(JSON.stringify(this.changeForm.get('phone').errors));
    console.log(this.changeForm.value);
    if (isValid) {
    } else {
      if (!this.changeForm.get('phone').valid) {
        this.pstate = 1;
      }if (!this.changeForm.get('email').valid) {
        this.estate = 1;
      }
   //   console.log(this.pstate);
    }
  }
}
export class User {
  constructor(
    public order: number,
    public id: string,
    public name: string,
    public password: string,
    public right: string,
    public note: string,
    public img: string,
    public phone: string,
    public email: string
  ) {
  }
}

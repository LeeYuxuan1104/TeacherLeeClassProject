import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  state: number;
  constructor(fb: FormBuilder, private router: Router) {
    this.loginForm = fb.group({
      username : ['',  [Validators.required , Validators.pattern('[0-9]*') , Validators.minLength(10)]],
      psw : ['', [Validators.required , Validators.minLength(6)]]
    });
  }

  ngOnInit() {
    this.state = 0;
  }
  onSubmit() {
    const isValid: boolean = this.loginForm.get('username').valid;
    console.log(JSON.stringify(this.loginForm.get('username').errors));
    if (isValid) {
      sessionStorage.setItem('usr', this.loginForm.get('username').value);
      this.router.navigate(['/']);
    } else {
      const validinfo: string = JSON.stringify(this.loginForm.get('username').errors);
      if (validinfo.toString().includes('minlength')) {
        this.state = 2;
      }else if (validinfo.toString().includes('pattern')) {
        this.state = 3;
      }else {
        this.state = 1;
      }
      console.log(this.state);
    }
  }
}

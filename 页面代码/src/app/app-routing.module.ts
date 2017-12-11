import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {UserinfoComponent} from './userinfo/userinfo.component';
import {BooksComponent} from "./books/books.component";
import {ListComponent} from "./list/list.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: '', component: HomeComponent,
    children: [
      {
        path: '', component: BooksComponent
      },
      {
        path: 'usrinfo', component : UserinfoComponent
      },
      {
        path: 'mybooks', component : ListComponent
      }
    ]
  },
  {path: 'usrinfo', component: UserinfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

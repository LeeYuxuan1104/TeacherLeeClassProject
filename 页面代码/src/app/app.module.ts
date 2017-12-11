import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { MainComponent } from './main/main.component';
import { SearchComponent } from './search/search.component';
import { CarouselComponent } from './carousel/carousel.component';
import { ListComponent } from './list/list.component';
import { UserinfoComponent } from './userinfo/userinfo.component';
import { BooksComponent } from './books/books.component';
import { BorrowlistComponent } from './borrowlist/borrowlist.component';
import { UserlistComponent } from './userlist/userlist.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    MainComponent,
    SearchComponent,
    CarouselComponent,
    ListComponent,
    UserinfoComponent,
    BooksComponent,
    BorrowlistComponent,
    UserlistComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

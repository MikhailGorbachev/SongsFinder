import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CdkTableModule} from '@angular/cdk';
import { DropDownsModule } from '@progress/kendo-angular-dropdowns';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { GridModule } from '@progress/kendo-angular-grid';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CdkTableModule,
    DropDownsModule,
    JsonpModule,
    ButtonsModule,
    GridModule
  ],
  providers: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

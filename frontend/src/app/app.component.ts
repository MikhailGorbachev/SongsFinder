import { Component } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/startWith';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  /*private baseUrl = 'http://localhost:8080/search';*/
  private baseUrl = '/search';

  searchTypes = [
       {id: 1, name: "Search songs by artist"},
       {id: 2, name: "Search songs by composer"},
       {id: 3, name: "Search artists by song name"}
     ];

  searchTypeValue = '';
  queryText = '';
  data = [];

  autocompleteCtrl: FormControl;
  filteredRows: any;

  constructor(private http: Http) {
    this.autocompleteCtrl = new FormControl();
    this.filteredRows = this.autocompleteCtrl.valueChanges
        .startWith(null)
        .map(name => {
            console.log('Changed Name = ' + name);
            return this.getUpdatedValues(name, this.searchTypeValue);
          });
  }

  find(event) {
    if (this.searchTypeValue != ''){
      let url = '';
      switch (this.searchTypeValue) {
        case "1":
          url = this.baseUrl + '/songs/artist/' + this.queryText;
          break;
        case "2":
          url = this.baseUrl + '/songs/composer/' + this.queryText;
          break;
      }
      this.data = this.getData(url);
      return this.data;
    }
  }



  getData(url) {
    let jsonData = [];
    this.http.get(url).map((res: Response)=> res.json()).subscribe(data=> {
      console.log(data);
      jsonData = data;
    })
    console.log("JSON data" + jsonData);
    return jsonData;
  }

  getUpdatedValues(name, searchTypeValue) {
      let url ='';
      if (searchTypeValue != ''){
        switch (this.searchTypeValue) {
          case "1":
            url = this.baseUrl + '/songs/artist/' + name;
            break;
          case "2":
            url = this.baseUrl + '/songs/composer/' + name;
            break;
          case "3":
            url = this.baseUrl + '/songs/' + name;
            break;
        }
        let autocompleteData = this.getData(url).map(song => song.name);

        console.log("autocompleteData = ");
        console.log(autocompleteData);

        return autocompleteData;
      } else {
        return [];
      }
  }


}

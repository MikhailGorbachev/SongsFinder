import { Component } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

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
       {id: 2, name: "Search songs by composer"}
     ];
  searchTypeValue = '';
  queryText = '';

  data = [];

  constructor(private http: Http) {
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
      Array.of(this.getData(url));
    }
  }

  getData(url) {
    return this.http.get(url).map((res: Response)=> res.json()).subscribe(data=> {
      console.log(data);
      this.data = data;
    }
    )
  }

}

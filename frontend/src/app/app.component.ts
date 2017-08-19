import {Component, ViewEncapsulation} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/startWith';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['../../node_modules/@progress/kendo-theme-default/dist/all.css'],

  encapsulation: ViewEncapsulation.None

})
export class AppComponent {

  private baseUrl = '/search';

  searchTypes = [
    {id: "1", name: "Search songs by artist"},
    {id: "2", name: "Search songs by composer"},
    {id: "3", name: "Search artists by song name"},
    {id: "4", name: "Search groups by artist name"},
    {id: "5", name: "Search albums by song name"},
    {id: "6", name: "Search songs by author"},
  ];

  searchTypeValue = this.searchTypes[0];
  queryText = '';
  data = [];

  gridData = [];

  constructor(private http: Http) {
    // this.autocompleteCtrl = new FormControl();
    // this.filteredRows = this.autocompleteCtrl.valueChanges
    //     .startWith(null)
    //     .map(name => {
    //         console.log('Changed Name = ' + name);
    //         return this.getUpdatedValues(name, this.searchTypeValue);
    //       });
  }

  find() {
    if (this.searchTypeValue.id != '') {
      let url = '';
      switch (this.searchTypeValue.id) {
        case "1":
          url = this.baseUrl + '/songs/artist/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data.map(song => {
              song.authors = song.authors.map(author => author.fullName).join(',');
              song.composers = song.composers.map(composer => composer.fullName).join(',');
              song.artists = song.artists.map(artist => artist.name).join(',');
              song.albums = song.albums.map(album => album.name).join(',');
              return song;
            })
          })
          break;
        case "2":
          url = this.baseUrl + '/songs/composer/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data.map(song => {
              song.authors = song.authors.map(author => author.fullName).join(',');
              song.composers = song.composers.map(composer => composer.fullName).join(',');
              song.artists = song.artists.map(artist => artist.name).join(',');
              song.albums = song.albums.map(album => album.name).join(',');
              return song;
            })
          })
          break;
        case "3":
          url = this.baseUrl + '/artists/song/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data;
          })
          break;
        case "4":
          url = this.baseUrl + '/groups/artist/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data;
          })
          break;
        case "5":
          url = this.baseUrl + '/albums/song/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data
          })
          break;
        case "6":
          url = this.baseUrl + '/songs/author/' + this.queryText;
          this.http.get(url).map((res: Response) => res.json()).subscribe(data => {
            this.gridData = data.map(song => {
              song.authors = song.authors.map(author => author.fullName).join(',');
              song.composers = song.composers.map(composer => composer.fullName).join(',');
              song.artists = song.artists.map(artist => artist.name).join(',');
              song.albums = song.albums.map(album => album.name).join(',');
              return song;
            })
          })
          break;
      }


      //this.getData(url, (data)=> this.gridData = data);

    }
  }

  public valueChange(changedValue: string): void {
    if (this.searchTypeValue.id == "3") {
      this.getUpdatedValues(changedValue, this.searchTypeValue)
    }
  }

  public filterChange(changedValue: string): void {
    console.log(this.searchTypeValue)
    if (this.searchTypeValue.id == "3") {
      this.getUpdatedValues(changedValue, this.searchTypeValue);
    }

  }


  getData(url) {
    let jsonData = [];
    this.http.get(url).map((res: Response) => res.json()).subscribe(data => {

      console.log("Inside getData lambda" + data)
      this.data = data.map(row => row.name);
    })
  }


  getUpdatedValues(name, searchTypeValue) {
    let url = '';
    if (searchTypeValue != '') {
      switch (this.searchTypeValue.id) {
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
      console.log("Before invoke getData");
      this.getData(url);

      // console.log("autocompleteData = ");
      // console.log(autocompleteData);

    }
  }

  onValueChange(event) {
    this.queryText = '';
    this.gridData = [];
  }

}

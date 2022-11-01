import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-project-name';
  name: string = 'Ahleem Akinwande';
  itemList: string[] = ['item 1', 'item 2', 'item 3', 'item 4'];
  rectangle = {
    length: 5,
    width: 6,
    area: function() {
       return this.length * this.width;
    }
 }
}

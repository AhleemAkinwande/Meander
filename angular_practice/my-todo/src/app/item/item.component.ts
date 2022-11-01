import { Component, Input, Output, EventEmitter,
  OnInit } from '@angular/core';
import { Item } from '../src/app/item'

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent{
  editable = false;
  @Input() item: Item;
  @Input() newItem: string;
  @Output() remove = new EventEmitter<Item>();
  constructor() { }

  ngOnInit(): void {
  }

}

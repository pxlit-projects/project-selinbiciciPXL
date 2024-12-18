import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Filter } from '../../shared/models/filter.model';


@Component({
  selector: 'app-filter-posts',
  standalone: true,
 imports:[FormsModule],
  templateUrl: './filter-posts.component.html',
  styleUrl: './filter-posts.component.css'
})
export class FilterPostsComponent {

  filter: Filter = { content: '', author: '', createdDate: '' };

  // EventEmitter om filterwaarden door te geven
  @Output() filterChanged = new EventEmitter<Filter>();

  onSubmit(form: any) {
    if (form.valid) {
      this.filterChanged.emit(this.filter);
    
    }
     
  }
}


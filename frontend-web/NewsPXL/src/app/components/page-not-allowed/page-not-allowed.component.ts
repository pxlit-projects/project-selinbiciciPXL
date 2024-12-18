import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-page-not-allowed',
  imports: [RouterLink],
  templateUrl: './page-not-allowed.component.html',
  styleUrl: './page-not-allowed.component.css'
})
export class PageNotAllowedComponent {
  message: string = 'You do not have permission to access this page.';

}

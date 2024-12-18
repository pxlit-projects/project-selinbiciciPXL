import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNotAllowedComponent } from './page-not-allowed.component';

describe('PageNotAllowedComponent', () => {
  let component: PageNotAllowedComponent;
  let fixture: ComponentFixture<PageNotAllowedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageNotAllowedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PageNotAllowedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

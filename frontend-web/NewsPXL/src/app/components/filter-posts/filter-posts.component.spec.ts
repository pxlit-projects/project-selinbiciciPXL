import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterPostsComponent } from './filter-posts.component';

describe('FilterPostsComponent', () => {
  let component: FilterPostsComponent;
  let fixture: ComponentFixture<FilterPostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilterPostsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilterPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

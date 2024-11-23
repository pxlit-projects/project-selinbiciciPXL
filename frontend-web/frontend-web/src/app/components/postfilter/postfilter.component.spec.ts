import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostfilterComponent } from './postfilter.component';

describe('PostfilterComponent', () => {
  let component: PostfilterComponent;
  let fixture: ComponentFixture<PostfilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostfilterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostfilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

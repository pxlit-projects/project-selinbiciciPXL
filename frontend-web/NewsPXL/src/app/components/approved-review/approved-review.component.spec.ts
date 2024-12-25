import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovedReviewComponent } from './approved-review.component';

describe('ApprovedReviewComponent', () => {
  let component: ApprovedReviewComponent;
  let fixture: ComponentFixture<ApprovedReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApprovedReviewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovedReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

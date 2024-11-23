import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosteditorComponent } from './posteditor.component';

describe('PosteditorComponent', () => {
  let component: PosteditorComponent;
  let fixture: ComponentFixture<PosteditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PosteditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosteditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradesListComponent } from './grades-list.component';

describe('GradesListComponent', () => {
  let component: GradesListComponent;
  let fixture: ComponentFixture<GradesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LabproblemsDetailComponent } from './labproblems-detail.component';

describe('LabproblemsDetailComponent', () => {
  let component: LabproblemsDetailComponent;
  let fixture: ComponentFixture<LabproblemsDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LabproblemsDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LabproblemsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

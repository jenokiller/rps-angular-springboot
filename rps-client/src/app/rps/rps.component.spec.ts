import {async, ComponentFixture, TestBed} from '@angular/core/testing'

import {RpsComponent} from './rps.component'
import {FormsModule} from '@angular/forms'
import {of} from 'rxjs/internal/observable/of';
import {GameResult} from './game-result';
import {RpsService} from './rps.service';
import {GamePlayer} from './game-player';

describe('RpsComponent', () => {
  let component;
  let fixture: ComponentFixture<RpsComponent>;
  let rpsServiceSpyStub;

  beforeEach(async(() => {
    rpsServiceSpyStub = jasmine.createSpyObj('RpsService', ['getResult']);
    rpsServiceSpyStub.getResult.and.returnValue(of(new GameResult('武藤wins')));
    TestBed.configureTestingModule({
      declarations: [RpsComponent],
      imports: [FormsModule],
      providers: [
        {provide: RpsService, useValue: rpsServiceSpyStub}
      ]
    })
    .compileComponents()
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RpsComponent);
    component = fixture.nativeElement;
    fixture.detectChanges();
  });

  describe('初期表示', () => {

    it('タイトルを表示する', () => {
      expect(component.querySelector('h1').textContent).toEqual('じゃんけん');
    });

    it('p1の入力フィールド', () => {
      expect(component.innerText).toContain('p1');
      expect(component.querySelector('label[for=p1-name]').textContent).toEqual('名前');
      expect(component.querySelector('input[type=text]#p1-name')).toBeTruthy();
      expect(component.querySelector('label[for=p1-throw-hand]').textContent).toEqual('出し手');
      expect(component.querySelector('select#p1-throw-hand')).toBeTruthy();
      expect(component.querySelector('select#p1-throw-hand option').textContent).toEqual('');
    });

    it('p2の入力フィールド', () => {
      expect(component.innerText).toContain('p2');
      expect(component.querySelector('label[for=p2-name]').textContent).toEqual('名前');
      expect(component.querySelector('input[type=text]#p2-name')).toBeTruthy();
      expect(component.querySelector('label[for=p2-throw-hand]').textContent).toEqual('出し手');
      expect(component.querySelector('select#p2-throw-hand')).toBeTruthy();
      expect(component.querySelector('select#p2-throw-hand option').textContent).toEqual('');
    });

    it('playボタン', () => {
      expect(component.querySelector('button').textContent).toEqual('play');
    });

    it('勝負結果が表示されない', () => {
      expect(component.querySelector('p').textContent).toEqual('');
    })
  })

  describe('playボタンを押下する', () => {
    beforeEach(() => {
      component.querySelector('input[type=text]#p1-name').value = 'さとう';
      component.querySelector('input[type=text]#p1-name').dispatchEvent(new Event('input'));
      component.querySelector('select#p1-throw-hand option[value=SCISSORS]').selected = true;
      component.querySelector('select#p1-throw-hand').dispatchEvent(new Event('change'));

      component.querySelector('input[type=text]#p2-name').value = '武藤';
      component.querySelector('input[type=text]#p2-name').dispatchEvent(new Event('input'));
      component.querySelector('select#p2-throw-hand option[value=PAPER]').selected = true;
      component.querySelector('select#p2-throw-hand').dispatchEvent(new Event('change'));


      component.querySelector('button').click();
      fixture.detectChanges();
    });

    it('サービスが呼ばれる', () => {
      const gamePlayer1 = new GamePlayer('さとう', 'SCISSORS');
      const gamePlayer2 = new GamePlayer('武藤', 'PAPER');
      expect(rpsServiceSpyStub.getResult).toHaveBeenCalledWith(gamePlayer1, gamePlayer2);
    });

    it('勝負結果を表示する', () => {
      expect(component.querySelector('p').textContent).toEqual('武藤wins');
    });
  });
});

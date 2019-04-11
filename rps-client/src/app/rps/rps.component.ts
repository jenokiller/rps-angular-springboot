import {Component, OnInit} from '@angular/core'
import {RpsService} from './rps.service'
import {GamePlayer} from './game-player'
import {GameResult} from './game-result';

@Component({
  selector: 'app-rps',
  templateUrl: './rps.component.html',
  styleUrls: ['./rps.component.css']
})
export class RpsComponent implements OnInit {
  gamePlayer1 = new GamePlayer('', '');
  gamePlayer2 = new GamePlayer('', '');
  gameResult = new GameResult('');

  constructor(private rpsService: RpsService) {
  }

  ngOnInit() {
  }

  onClick() {
    this.rpsService.getResult(this.gamePlayer1, this.gamePlayer2).subscribe(res => {
      this.gameResult = res;
    });
  }
}

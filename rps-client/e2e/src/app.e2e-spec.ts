import {AppPage} from './app.po'
import {browser, logging} from 'protractor'

describe('workspace-project App', () => {
  let page: AppPage;
  let player1: string;
  let player2: string;

  beforeEach(() => {
    page = new AppPage();
    player1 = 'さとう';
    player2 = '武藤';
  });

  it('画面が表示される', () => {
    page.navigateTo();
    expect(page.getTitleText()).toContain('じゃんけん');
  });

  it('P1が勝つ', () => {
    page.navigateTo();
    page.playerNameInput(1, player1);
    page.playerThrowHandInput(1, 'ROCK');
    page.playerNameInput(2, player2);
    page.playerThrowHandInput(2, 'SCISSORS');
    page.clickButton();
    expect(page.getResultText()).toContain(player1);
    expect(page.getResultText()).toContain('wins!');
  });

  it('引き分けになる', () => {
    page.navigateTo();
    page.playerNameInput(1, player1);
    page.playerThrowHandInput(1, 'ROCK');
    page.playerNameInput(2, player2);
    page.playerThrowHandInput(2, 'ROCK');
    page.clickButton();
    expect(page.getResultText()).toContain('tie!');
  });

  it('P2が勝つ', () => {
    page.navigateTo();
    page.playerNameInput(1, player1);
    page.playerThrowHandInput(1, 'ROCK');
    page.playerNameInput(2, player2);
    page.playerThrowHandInput(2, 'PAPER');
    page.clickButton();
    expect(page.getResultText()).toContain(player2);
    expect(page.getResultText()).toContain('wins!');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER)
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry))
  })
});

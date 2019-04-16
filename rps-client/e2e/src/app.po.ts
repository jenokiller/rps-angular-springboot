import {browser, by, element} from 'protractor'

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl)
  }

  getTitleText() {
    return element(by.css('app-rps h1')).getText()
  }

  playerNameInput(playerNumber: number, name: string) {
    const nameInput = element(by.css('app-rps input[type=text]#p' + playerNumber + '-name'))
    nameInput.sendKeys(name)
  }

  playerThrowHandInput(playerNumber: number, throwHand: string) {
    const throwHandInput = element(by.css('app-rps select#p' + playerNumber + '-throw-hand option[value=' + throwHand + ']'))
    throwHandInput.click()
  }

  clickButton() {
    element(by.css('button')).click();
  }

  getResultText() {
    return element(by.css('app-rps p')).getText()
  }
}

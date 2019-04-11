import {browser, by, element} from 'protractor'

export class AppPage {

  static playerThrowHandInput(playerNumber: number, throwHand: string) {
    const throwHandInput = element(by.css('app-rps input[type=text]#p' + playerNumber + '-throw-hand'))
    throwHandInput.sendKeys(throwHand)
  }
  navigateTo() {
    return browser.get(browser.baseUrl)
  }

  getTitleText() {
    return element(by.css('app-rps h1')).getText()
  }
}

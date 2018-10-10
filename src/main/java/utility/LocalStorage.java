package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorage {
  private JavascriptExecutor js;

  public LocalStorage(WebDriver webDriver) {
    this.js = (JavascriptExecutor) webDriver;
  }

  public void RemoveItemFromLocalStorage(String item) {
    js.executeScript(String.format(
        "window.localStorage.removeItem('%s');", item));
  }

  public boolean IsItemPresentInLocalStorage(String item) {
    return !(js.executeScript(String.format(
        "return window.localStorage.getItem('%s');", item)) == null);
  }

  public String GetItemFromLocalStorage(String key) {
    return (String) js.executeScript(String.format(
        "return window.localStorage.getItem('%s');", key));
  }

  public String GetKeyFromLocalStorage(int key) {
    return (String) js.executeScript(String.format(
        "return window.localStorage.key('%s');", key));
  }

  public Long GetLocalStorageLength() {
    return (Long) js.executeScript("return window.localStorage.length;");
  }

  public void SetItemInLocalStorage(String item, String value) {
    js.executeScript(String.format(
        "window.localStorage.setItem('%s','%s');", item, value));
  }

  public void ClearLocalStorage() {
    js.executeScript(String.format("window.localStorage.clear();"));
  }
}
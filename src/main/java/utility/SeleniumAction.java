package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAction {

	public static void refreshPage(WebDriver driver) {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getAlertText(WebDriver driver) {
		try {
			Alert alertDialog = driver.switchTo().alert();
			return alertDialog.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAIL";
	}

	public static void uploadFile(WebDriver driver, String xpathOfButton, String pathOfFile)
			throws InterruptedException {
		try {
			File f = new File(pathOfFile);
			if (f.isFile()) {
				click(driver, xpathOfButton);
				Thread.sleep(500);

				StringSelection filePath = new StringSelection(f.getAbsolutePath());
				Log.info("File Selection: " + f.getAbsolutePath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);

				r.keyRelease(KeyEvent.VK_A);
				r.keyRelease(KeyEvent.VK_CONTROL);

				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);

				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);

				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			Thread.sleep(5000);
		} catch (AWTException e) {
			System.console().writer().write("Unable to upload file");
		}
	}

	public static void uploadFileWithoutLocator(String pathOfFile) throws InterruptedException {
		try {
			File f = new File(pathOfFile);
			if (f.isFile()) {

				StringSelection filePath = new StringSelection(f.getAbsolutePath());
				Log.info("File Selection: " + f.getAbsolutePath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);

				r.keyRelease(KeyEvent.VK_A);
				r.keyRelease(KeyEvent.VK_CONTROL);

				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);

				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);

				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);

			}
			Thread.sleep(5000);
		} catch (AWTException e) {
			System.console().writer().write("Unable to upload file");
		}
	}

	public static void sendKeyByKeyboard(WebDriver driver, String xpathOfElement, String value)
			throws InterruptedException {
		try {
			if (value != null) {
				click(driver, xpathOfElement);
				clear(driver, xpathOfElement);
				Thread.sleep(500);

				StringSelection inputValue = new StringSelection(value);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(inputValue, null);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);

				r.keyRelease(KeyEvent.VK_A);
				r.keyRelease(KeyEvent.VK_CONTROL);

				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);

				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);

				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			Thread.sleep(1000);
		} catch (AWTException e) {
			System.console().writer().write("Unable to upload file");
		}
	}

	/**
	 * Get URL to load to browser
	 *
	 * @param driver
	 *            WebDriver
	 * @param url
	 *            URL of web site needs to load
	 */
	public static void getURL(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * Quit the browser
	 *
	 * @param driver
	 *            WebDriver
	 */

	public static void quit(WebDriver driver) {
		driver.quit();
	}

	/**
	 * Close the browser
	 *
	 * @param driver
	 *            WebDriver
	 */

	public static void close(WebDriver driver) {
		driver.close();
	}

	public static void implicitlyWaitTimeOut(WebDriver driver, TimeUnit unit, long time) {
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

	public static void maximumWindowSize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * Click element at locator
	 *
	 * @param driver
	 *            WebDriver needs to execute
	 * @param elementLocator
	 *            Locator of element to click
	 */

	public static void click(WebDriver driver, String xpathOfElement) {
		try {
			driver.findElement(By.xpath(xpathOfElement)).click();
			Log.info("Click Xpath at: " + xpathOfElement.toString());
			Thread.sleep(500);
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Xpath:" + xpathOfElement.toString() + " NOT FOUND");
			System.console().writer().write("Xpath is NOT FOUND");
		}
	}

	public static void clear(WebDriver driver, String xpathOfElement) {
		try {
			driver.findElement(By.xpath(xpathOfElement)).clear();
			Log.info("Clear text of Xpath at: " + xpathOfElement.toString());
			Thread.sleep(500);
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Xpath: " + xpathOfElement.toString() + " NOT FOUND");
			System.console().writer().write("Xpath is NOT FOUND");
		}
	}

	public static void click(WebElement element, By elementLocator) {
		try {
			element.findElement(elementLocator).click();
			Log.info("Click element at: " + elementLocator.toString());
			Thread.sleep(500);
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Element at" + elementLocator.toString() + " NOT FOUND");
			System.console().writer().write("Element is NOT FOUND");
		}
	}

	public static void clickByJavaScript(WebDriver driver, String xpathOfElement) {
		WebElement element = driver.findElement(By.xpath(xpathOfElement));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Log.info("Clicked Xpath By JavaScript: " + xpathOfElement.toString());
	}

//	public static void doubleClickByJavaScript(WebDriver driver, String xpathOfElement){
//		WebElement element = driver.findElement(By.xpath(xpathOfElement));
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].ondblclick();", element);
//		Log.info("Double click Xpath By JavaScript: " + xpathOfElement.toString());
//	}

	public static void doubleClick(WebDriver driver, String xpathOfElement) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(xpathOfElement))).doubleClick().build().perform();
	}

	/**
	 * get text of element at locator
	 *
	 * @param driver
	 *            WebDriver to get text
	 * @param elementLocator
	 *            Locator of element to get text
	 * @return value of element
	 */

	public static String getText(WebDriver driver, String xpathOfElement) {
		try {
			String text = driver.findElement(By.xpath(xpathOfElement)).getText();
			Log.info("Get text of Xpath at " + xpathOfElement.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get text from xpath: " + xpathOfElement.toString());
			System.console().writer().write("Xpath is NOT FOUND");
			return null;
		}
	}

	public static String getText(WebElement element, By elementLocator) {
		try {
			String text = element.findElement(elementLocator).getText();
			Log.info("Get text of element at " + elementLocator.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get text from element: " + elementLocator.toString());
			System.console().writer().write("Element is NOT FOUND");
			return null;
		}
	}

	public static String getText(WebElement element, String xpathOfElement) {
		try {
			String text = element.findElement(By.xpath(xpathOfElement)).getText();
			Log.info("Get text of element at " + xpathOfElement.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get text from xpath: " + xpathOfElement.toString());
			System.console().writer().write("Element is NOT FOUND");
			return null;
		}
	}

	public static String getText(WebElement element) {
		try {
			String text = element.getText();
			Log.info("Get text of element at " + element.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get text from element: " + element.toString());
			System.console().writer().write("Element is NOT FOUND");
			return null;
		}
	}

	/**
	 * get value of attribute in DOM
	 *
	 * @param driver
	 *            WebDriver to get attribute
	 * @param elementLocator
	 *            Locator of element to attribute
	 * @param attribute
	 *            Attribute name
	 * @return Value of attribute
	 */

	public static String getAttribute(WebDriver driver, String xpathOfElement, String attribute) {
		try {
			String text = driver.findElement(By.xpath(xpathOfElement)).getAttribute(attribute);
			Log.info("Get attribute " + attribute + " at Xpath: " + xpathOfElement.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get value from attribute " + attribute + " from xpath: " + xpathOfElement.toString());
			System.console().writer().write("Xpath is NOT FOUND");
			return null;
		}
	}

	public static String getAttribute(WebElement element, String attribute) {
		try {
			String text = element.getAttribute(attribute);
			Log.info("Get attribute " + attribute + " of element at " + element.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get value from attribute " + attribute + " from element: " + element.toString());
			System.console().writer().write("Element is NOT FOUND");
			return null;
		}
	}

	public static String getAttribute(WebElement element, String xpathOfElement, String attribute) {
		try {
			String text = element.findElement(By.xpath(xpathOfElement)).getAttribute(attribute);
			Log.info("Get attribute " + attribute + " of Xpath at " + xpathOfElement.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get value from attribute " + attribute + " from xpath: " + xpathOfElement.toString());
			System.console().writer().write("Xpath is NOT FOUND");
			return null;
		}
	}

	public static String getCssValue(WebDriver driver, String xpathOfElement, String attribute) {
		try {
			String text = driver.findElement(By.xpath(xpathOfElement)).getCssValue(attribute);
			Log.info("Get CSS value " + attribute + " of Xpath at " + xpathOfElement.toString());
			Thread.sleep(500);
			return text;
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Unable to get CSS value from attribute " + attribute + " from xpath: "
					+ xpathOfElement.toString());
			System.console().writer().write("Xpath is NOT FOUND");
			return null;
		}
	}

	/**
	 * check element is displayed or not
	 *
	 * @param driver
	 *            WebDriver to check
	 * @param locator
	 *            Locator of element
	 * @return True if element is displayed. False if not
	 */

	public static boolean isDisplayed(WebDriver driver, String xpathOfElement) {
		try {
			boolean display = driver.findElement(By.xpath(xpathOfElement)).isDisplayed();
			Thread.sleep(500);
			Log.info("Xpath: " + xpathOfElement.toString() + " AVAILABLE On UI");
			return display;
		} catch (Exception e) {
			Log.debug("Xpath " + xpathOfElement.toString() + " NOT AVAILABLE On UI");
			// System.console().writer().write("Element is NOT FOUND");
			return false;
		}
	}

	public static boolean isDisplayed(WebElement element, String xpathOfElement) {
		try {
			boolean display = element.findElement(By.xpath(xpathOfElement)).isDisplayed();
			Thread.sleep(500);
			Log.info("Xpath: " + xpathOfElement.toString() + " AVAILABLE On UI");
			return display;
		} catch (Exception e) {
			Log.debug("Xpath " + xpathOfElement.toString() + " NOT AVAILABLE On UI");
			// System.console().writer().write("Element is NOT FOUND");
			return false;
		}
	}

	/**
	 * Send key to locator
	 *
	 * @param driver
	 *            WebDriver to execute
	 * @param locator
	 *            Locator of element
	 * @param value
	 *            key value to send to element
	 */
	public static void sendKeys(WebDriver driver, String xpathOfElement, String value) {
		try {
			if (value != null) {
				WebElement element = driver.findElement(By.xpath(xpathOfElement));
				element.click();
				element.clear();
				element.sendKeys(value);
				Log.info("Send " + value + " to Xpath at " + xpathOfElement.toString());
				Thread.sleep(500);
			}
		} catch (ElementNotVisibleException | InterruptedException e) {
			Log.debug("Xpath at " + xpathOfElement.toString() + " NOT FOUND");
			System.console().writer().write("Xpath is NOT found");
		}
	}

	/**
	 * Wait for element visible
	 *
	 * @param driver
	 *            WebDriver to check
	 * @param locator
	 *            Locator of element needs to wait
	 * @param timeWait
	 *            Time to wait in second
	 * @return True if element existed on web driver after timeWait, false if it
	 *         is not appeared after timeWait
	 */

	public static Boolean waitElementVisible(WebDriver driver, String xpathOfElement, int timeWait) {
		try {
			(new WebDriverWait(driver, timeWait))
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathOfElement))));
			Log.info("Xpath: " + xpathOfElement.toString() + " VISIBLE");
			return true;
		} catch (Exception e) {
			Log.debug("Xpath: " + xpathOfElement.toString() + " INVISIBLE");
			return false;
		}
	}

	/**
	 * Wait for element invisible
	 *
	 * @param driver
	 *            WebDriver to check
	 * @param locator
	 *            Locator needs to wait
	 * @param timeWait
	 *            Time to wait in Second
	 * @return True if element is invisible on web driver after timeWait, false
	 *         if it still appears after timeWait
	 */
	public static Boolean waitElementLocatorInvisible(WebDriver driver, String xpathOfElement, int timeWait) {
		try {
			(new WebDriverWait(driver, timeWait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathOfElement)));
			Log.info("Xpath " + xpathOfElement.toString() + " INVISIBLE");
			return true;
		} catch (Exception e) {
			Log.debug("Xpath " + xpathOfElement.toString() + " NOT INVISIBLE");
			return false;
		}
	}

	public static List<WebElement> findElements(WebDriver driver, String xpathOfElement) {
		try {
			Log.info("get elements at " + xpathOfElement);
			return driver.findElements(By.xpath(xpathOfElement));
		} catch (Exception e) {
			Log.debug("Xpath " + xpathOfElement.toString() + " NOT FOUND");
			Log.debug(e.getMessage());
			System.console().writer().write("Xpath is NOT found");
			return null;
		}
	}

	public static List<WebElement> findElements(WebElement element, String xpathOfElement) {
		try {
			Log.info("get elements at " + xpathOfElement);
			return element.findElements(By.xpath(xpathOfElement));
		} catch (Exception e) {
			Log.debug("Xpath:" + xpathOfElement.toString() + " NOT FOUND");
			Log.debug(e.getMessage());
			System.console().writer().write("Xpath is NOT found");
			return null;
		}
	}

	public static List<WebElement> findElements(WebElement element, By locator) {
		try {
			Log.info("get elements at " + locator);
			return element.findElements(locator);
		} catch (Exception e) {
			Log.debug("Element at " + locator.toString() + " NOT FOUND");
			Log.debug(e.getMessage());
			System.console().writer().write("Element is NOT found");
			return null;
		}
	}

	public static void printScreen(WebDriver driver, String path) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path));
	}

	public static WebElement findElement(WebDriver driver, String xpathOfElement) {
		try {
			Log.info("find Xpath at " + xpathOfElement);
			return driver.findElement(By.xpath(xpathOfElement));
		} catch (Exception e) {
			Log.debug("Xpath " + xpathOfElement.toString() + " NOT FOUND");
			Log.debug(e.getMessage());
			System.console().writer().write("Xpath is NOT found");
			return null;
		}
	}

	public static void scrollToElement(WebDriver driver, String xpathOfElement) {
		WebElement element = SeleniumAction.findElement(driver, xpathOfElement);
		Coordinates coordinate = ((Locatable) element).getCoordinates();
		coordinate.inViewPort();
	}

}


package Test.Baris.Steps;

import Test.Baris.Locators.HomePageLocator;
import ReuseableClass.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Map;

import static ReuseableClass._Conditions.*;

public class LoginFunc extends BaseClass implements HomePageLocator {


    @Given("user on login Page")
    public void userOnLoginPage() {
        $(By.xpath("//div[text()=\"GİRİŞ Yap\"]")).click().waitFor(urlContains, "login");
    }

    @Then("user logs in with {string} and  {string}, {string} user should see {string} message")
    public void userLogsInWithAndUserShouldSeeMessage(String username, String password, String status, String message) {

        if (!status.equalsIgnoreCase("true")) {
            if (!username.isEmpty()) {
                $(xpath(loginInput, "username")).sendKeys(username);
            }
            if (!password.isEmpty()) {
                $(xpath(loginInput, "password")).sendKeys(password);
            }
            $(xpath(ALL_BUTTON, "Giriş Yap")).click();

            if (!message.isEmpty()) {
                String[] messageParts = message.split(" and ");
                for (String part : messageParts) {
                    $(xpath(ALL_Locator, part.trim())).waitFor(visibilty, null);
                }
            }
        } else {
            $(xpath(loginInput, "username")).sendKeys(username);
            $(xpath(loginInput, "password")).sendKeys(password);
            $(xpath(ALL_BUTTON, "Giriş Yap")).click();
            $(xpath(ALL_Locator, message)).waitFor(visibilty, null);
            $(xpath(ALL_Locator, message)).click();
            $(xpath(ALL_A, "")).click();
        }

    }


    @Given("should see two separate fields for username and password.")
    public void shouldSeeTwoSeparateFieldsForUsernameAndPassword() {
        $(xpath(loginInput, "username")).waitFor(visibilty, null);
        $(xpath(loginInput, "password")).waitFor(visibilty, null);
    }


    @Then("The text {string} it must be visible")
    public void theTextItMustBeVisible(String text) {
        $(xpath(ALL_Locator, text)).waitFor(visibilty, null);
    }

    @Then("Movita logo above it must be visible")
    public void movitaLogoAboveItMustBeVisible() {
        $(movitaLogo).waitFor(visibilty, null);
    }


    @Then("{string} link should be available.")
    public void şifreniziMiUnuttunuzLinkShouldBeAvailable(String text) {
        $(xpath(ALL_A, text)).waitFor(visibilty, null);
    }

    @Then("There should be a {string} button in blue colour in the form of a long bar.")
    public void thereShouldBeALoginButtonInBlueColourInTheFormOfALongBar(String text) {
        String expectedResult = "rgb(0, 173, 238)";
        String style = $(xpath(ALL_A, "Şifrenizi mi unuttunuz?")).getCurrentElement().getAttribute("style");
        if (!style.contains(expectedResult)) {
            Assert.fail();
        }
    }

    @Then("and when hover over, the colour of the text should change from blue to green and it should be clickable")
    public void andWhenHoverOverTheColourOfTheTextShouldChangeFromBlueToGreenAndItShouldBeClickable() {
        String expectedResult = "rgba(0, 255, 0, 1)";
        String style = $(xpath(ALL_A, "Şifrenizi mi unuttunuz?")).hower().getCurrentElement().getAttribute("style");
        if (!style.contains(expectedResult)) {
            Assert.fail();
        }
    }

    @Then("There should be a {string} link in the top right corner and when clicked it should navigate to the Home page")
    public void thereShouldBeAAnaSayfayaDönLinkInTheTopRightCornerAndWhenClickedItShouldNavigateToTheHomePage(String text) {
        $(xpath(ALL_A, text)).click().waitFor(urlToBe, url);
    }


    @Then("{string} should be written in the footer section.")
    public void shouldBeWrittenInTheFooterSection(String text) {
        $(xpath(ALL_Locator, text)).waitFor(visibilty, null);

    }


    @And("user successfully logs in with followind data")
    public void userSuccessfullyLogsInWithFollowindData(DataTable table) {
        Map<String, String> userInfo = table.asMap();

        $(xpath(loginInput, "username")).sendKeys(userInfo.get("username")).
                $(xpath(loginInput, "password")).sendKeys(userInfo.get("password")).
                $(xpath(ALL_BUTTON, "Giriş Yap")).click();
        $(xpath(ALL_Locator, userInfo.get("verification text"))).waitFor(visibilty, null);


    }

    public void successfulyLogin() {
        $(xpath(loginInput, "username")).sendKeys("demomovita").
                $(xpath(loginInput, "password")).sendKeys("1192movita").
                $(xpath(ALL_BUTTON, "Giriş Yap")).click().
                $(xpath(ALL_Locator, "Demo Filo")).waitFor(visibilty, null);
    }

    @And("user on accaunt Page")
    public void userSuccessfullyLogin() {
        successfulyLogin();

    }
}

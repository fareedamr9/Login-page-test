import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SaucedemoLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver=new EdgeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
@AfterMethod
public void quit()
{
    driver.quit();
}

@Test(priority = 1)
    public void testFieldsPresence()
    {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed");
    }
    @Test(priority = 2)
    public void testValidCredentials()
    {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement swagLabsLogo = driver.findElement(By.className("app_logo"));
        Assert.assertTrue(swagLabsLogo.isDisplayed(), "Swag Labs div is not visible after a successful login");
    }


@Test(priority = 3)
    public void testInvalidCredentials()
    {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("test");
        password.sendKeys("test");
        loginButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]"));
        Assert.assertTrue(errorMsg.isDisplayed(),"Error Message doesn't appear when invalid crendentials are entered");
        Assert.assertTrue(errorMsg.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }
@Test(priority = 4)
    public void testEmptyUsername()
    {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("");
        password.sendKeys("test");
        loginButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]"));
        Assert.assertTrue(errorMsg.isDisplayed(),"Error Message doesn't appear when empty username is submitted");
        Assert.assertTrue(errorMsg.getText().contains("Epic sadface: Username is required"));

    }
    @Test(priority = 5)
    public void testEmptyPassword()
    {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("test");
        password.sendKeys("");
        loginButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]"));
        Assert.assertTrue(errorMsg.isDisplayed(),"Error Message doesn't appear when empty password is submitted");
        Assert.assertTrue(errorMsg.getText().contains("Epic sadface: Password is required"));

    }

}

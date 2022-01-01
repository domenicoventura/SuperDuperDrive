package com.domenicoventura.jwdnd.course1.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Tests for User Signup, Login, and Unauthorized Access Restrictions.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new FirefoxDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    /**
     * Write a test that verifies that an unauthorized user can only access the login and signup pages.
     */
    @Test
    public void testPageAccess() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /**
     * Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies
     * that the home page is no longer accessible.
     */
    @Test
    public void testSignUpLoginLogout() {
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        SignupPage signupPage = new SignupPage(driver);
        signupPage.setFirstName("Pippo");
        signupPage.setLastName("Inzaghi");
        signupPage.setUserName("pippo");
        signupPage.setPassword("inzaghi");
        signupPage.signUp();

        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("pippo");
        loginPage.setPassword("inzaghi");
        loginPage.login();

        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        homePage.logout();

        driver.get("http://localhost:" + this.port + "/home");
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        Assertions.assertEquals("Login", driver.getTitle());
    }
}


package selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchUseriTest {

    @Test
    @DisplayName("User List is not empty")
    public void testUserList_UsersPresent_UsersListNotEmpty() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/GestionBibliotheque_war_exploded/users.jsp");

            WebElement userList = driver.findElement(By.className("userdiv"));

            List<WebElement> users = userList.findElements(By.tagName("li"));

            assert users.size() > 0;

        } finally {
            driver.quit();
        }
    }
}


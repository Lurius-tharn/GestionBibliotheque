import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchBookITest {

    @Test
    public void testSearchBook() {
        WebDriver driver = new ChromeDriver();
        int time = 10;
        Duration duration = Duration.ofSeconds(time);
        WebDriverWait wait = new WebDriverWait(driver, duration);

        try {
            driver.get("http://localhost:8080/GestionBibliotheque_war_exploded/"); // replace with your actual URL

            WebElement titleInput = driver.findElement(By.id("title"));
            titleInput.sendKeys("Fondation");


            titleInput.submit();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            WebElement table = driver.findElement(By.tagName("table"));
            String tableText = table.getText();

            assert tableText.contains("Fondation");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void testSearchByAuthor() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost:8080/GestionBibliotheque_war_exploded/");
            WebElement authorInput = driver.findElement(By.id("author"));
            authorInput.sendKeys("Isaac Asimov");

            authorInput.submit();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            WebElement table = driver.findElement(By.tagName("table"));
            String tableText = table.getText();

            assert tableText.contains("Isaac Asimov");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void testSearchByIsbn() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost:8080/GestionBibliotheque_war_exploded/");
            WebElement isbnInput = driver.findElement(By.id("isbn"));
            isbnInput.sendKeys("978-2-226-10701-6");

            isbnInput.submit();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            WebElement table = driver.findElement(By.tagName("table"));
            String tableText = table.getText();

            assert tableText.contains("978-2-226-10701-6");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void testSearchByAll() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://localhost:8080/GestionBibliotheque_war_exploded/");
            WebElement titleInput = driver.findElement(By.id("title"));
            WebElement authorInput = driver.findElement(By.id("author"));
            WebElement isbnInput = driver.findElement(By.id("isbn"));

            titleInput.sendKeys("Fondation");
            authorInput.sendKeys("Isaac Asimov");
            isbnInput.sendKeys("978-2-226-10701-6");

            titleInput.submit();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

            WebElement table = driver.findElement(By.tagName("table"));
            String tableText = table.getText();

            assert tableText.contains("Fondation");
            assert tableText.contains("Isaac Asimov");
            assert tableText.contains("978-2-226-10701-6");

        } finally {
            driver.quit();
        }
    }
}


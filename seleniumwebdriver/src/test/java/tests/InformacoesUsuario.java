package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InformacoesUsuario {

	private WebDriver navegador;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rodne\\Downloads\\drivers\\chromedriver.exe");
		// abrindo o navegador
		navegador = new ChromeDriver();
		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// navegando até a página
		navegador.get("http://www.juliodelima.com.br/taskit");
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {

		// clicando no botão Sign in
		navegador.findElement(By.linkText("Sign in")).click();
		navegador.findElement(By.id("login-sign-in")).sendKeys("rodneydeoliveira");
		navegador.findElement(By.id("password-sign-in")).sendKeys("Sharkman17!");
		navegador.findElement(By.id("btn-submit-sign-in")).click();
		navegador.findElement(By.linkText("Logout")).isEnabled();

		// clicar no Oi, Rodney
		navegador.findElement(By.cssSelector("a[class='me']")).click();
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		navegador.findElement(By.cssSelector("[id='moredata'] button")).click();

		WebElement popUpAddMoreData = navegador.findElement(By.id("addmoredata"));
		WebElement campoType = popUpAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByValue("phone");
		popUpAddMoreData.findElement(By.name("contact")).sendKeys("+5511999999999");

		navegador.findElement(By.linkText("SAVE")).click();
		String texto = "Your contact has been added!";
		assertEquals(texto, navegador.findElement(By.cssSelector("div[class='toast rounded']")).getText());

	}

	@Test
	public void excluirInformacaoUsuario() {

		navegador.findElement(By.linkText("Sign in")).click();
		navegador.findElement(By.id("login-sign-in")).sendKeys("rodneydeoliveira");
		navegador.findElement(By.id("password-sign-in")).sendKeys("Sharkman17!");
		navegador.findElement(By.id("btn-submit-sign-in")).click();
		navegador.findElement(By.linkText("Logout")).isEnabled();

		navegador.findElement(By.cssSelector("a[class='me']")).click();
		navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		navegador.findElement(By.xpath("//span[text()='+5511999999999']/following-sibling::a")).click();

		Alert alert = navegador.switchTo().alert();
		alert.accept();
		String texto = "Rest in peace, dear phone!";
		assertEquals(texto, navegador.findElement(By.cssSelector("div[class='toast rounded']")).getText());

	}

	@After
	public void tearDown() {
		navegador.quit();

	}

}
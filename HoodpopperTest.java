///////////////////////////////////////////////////////////
///////// CS 1632 DELIVERABLE 3 BY PETER STAMOS ///////////
///////////////////////////////////////////////////////////

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HoodpopperTest {

	static WebDriver driver = new HtmlUnitDriver();

	// Start at Hoodpopper page for each test 

  @Before
  public void setUp() throws Exception {

    driver.get("http://lit-bayou-7912.herokuapp.com/");
  }

  /********************************************************
  * As a user
  * I would like to see an interactive and informative
  * ruby compiler simulator webpage
  * So that I can know what is happening to my code when
  * is parsed, tokenized, and compiled
  *******************************************************/

  // Given that I am on the main page
  // When I view the title
  // Then I see that it exists

  @Test
  public void testTitleExists() {

    // Check that the title exists and is not null

    assertNotNull(driver.getTitle());
  }

  ///////////////////////////////////////////////////////////

  // Given that I am on the main page
  // When I view the title
  // Then I see that it contains the word "Hoodpopper"

  @Test
  public void testShowsCorrectTitle() {

    // Check that the title contains the word "Hoodpopper"

    String title = driver.getTitle();
    assertTrue(title.contains("Hoodpopper"));
  }

  /********************************************************
  * As a user
  * I would like buttons that allow me to tokenize, parse,
  * or compile my code
  * So that I can easily see how my code is interpretted
  *******************************************************/

  // Given that I am on the main page
  // When I try to tokenize my code
  // Then I see that the option exists

  @Test
  public void testTokenizeButtonExists() {

    // Check that the tokenize option button exists

    driver.getPageSource().contains("Tokenize");
  }

  ///////////////////////////////////////////////////////////

  // Given that I am on the main page
  // When I try to parse my code
  // Then I see that the option exists

  @Test
  public void testParseButtonExists() {

    // Check that the parse option button exists

    driver.getPageSource().contains("Parse");
  }

  ///////////////////////////////////////////////////////////

  // Given that I am on the main page
  // When I try to compile my code
  // Then I see that the option exists

  @Test
  public void testCompileButtonExists() {

    // Check that the compile option button exists

    driver.getPageSource().contains("Compile");
  }

  /********************************************************
  * As a user
  * I would like to know that String expressions are
  * being properly tokenized
  * So that I can know that the symbols that comprise my
  * input String are being correctly interpretted and
  * displayed by the compiler
  *******************************************************/

  // Given that I input a String
  // When I tokenize the String
  // Then I see that elements of the String are properly tokenized

  @Test
  public void testTokenizationString() {

    // Check that the String "Hello" is properly tokenized

    try {

      driver.findElement(By.id("code_code")).sendKeys("Hello");
      driver.findElement(By.xpath("//input[@value='Tokenize' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":on_const, \"Hello\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a String
  // When I tokenize the String
  // Then I see that elements of the String are properly tokenized

  @Test
  public void testTokenizationFunction() {

    // Check that the String "Hello" is properly tokenized

    try {

      driver.findElement(By.id("code_code")).sendKeys("puts 'Hello, world!'");
      driver.findElement(By.xpath("//input[@value='Tokenize' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":on_tstring_content, \"Hello, world!\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a String containing a new line character
  // When I tokenize the String
  // Then I see that the new line character is properly tokenized

  @Test
  public void testTokenizationNewLine() {

    // Check that the String "Hello" is properly tokenized

    try {

      driver.findElement(By.id("code_code")).sendKeys("Hello \r\n World!");
      driver.findElement(By.xpath("//input[@value='Tokenize' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":on_nl, \"\\r\\n\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  /********************************************************
  * As a user
  * I would like to know that expressions are
  * being properly parsed
  * So that I can know that the symbols that comprise my
  * input are being correctly interpretted by the compiler
  *******************************************************/

  // Given that I input a String
  // When I parse the String
  // Then I see that elements of the String are properly parsed

  @Test
  public void testParseString() {

    // Check that the String "Hello" is properly parsed

    try {

      driver.findElement(By.id("code_code")).sendKeys("\"Hello\"");
      driver.findElement(By.xpath("//input[@value='Parse' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":@tstring_content, \"Hello\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input an int
  // When I parse the int
  // Then I see that the int was properly parsed

  @Test
  public void testParseInt() {

    // Check that the int 1 is properly parsed

    try {

      driver.findElement(By.id("code_code")).sendKeys("1");
      driver.findElement(By.xpath("//input[@value='Parse' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":@int, \"1\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a mathematical expression containing an operator
  // When I parse the Sexpression
  // Then I see that the operator is properly parsed

  @Test
  public void testParseIdentifier() {

    // Check that the String "Hello" is properly tokenized

    try {

      driver.findElement(By.id("code_code")).sendKeys("a");
      driver.findElement(By.xpath("//input[@value='Parse' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains(":@ident, \"a\""));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  /********************************************************
  * As a user
  * I would like to know that mathematical expressions are
  * being properly interpretted by the compiler
  * So that I can know that the correct calculations
  * are being performed within my code
  *******************************************************/

  // Given that I input a mathematical expression
  // When I compile the expression
  // Then I see that the operands are properly interpreted by the compiler

  @Test
  public void testAdditionInterpretation() {

    // Check that the addition symbol "+" is properly interpreted

    try {

      driver.findElement(By.id("code_code")).sendKeys("1632+1530");
      driver.findElement(By.xpath("//input[@value='Compile' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains("opt_plus"));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a mathematical expression
  // When I compile the expression
  // Then I see that the operands are properly interpreted by the compiler

  @Test
  public void testSubtractionInterpretation() {

    // Check that the subtraction symbol "-" is properly interpreted

    try {

      driver.findElement(By.id("code_code")).sendKeys("1632-1530");
      driver.findElement(By.xpath("//input[@value='Compile' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains("opt_minus"));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a mathematical expression
  // When I compile the expression
  // Then I see that the operands are properly interpreted by the compiler

  @Test
  public void testMultiplicationInterpretation() {

    // Check that the multiplication symbol "*" is properly interpreted

    try {

      driver.findElement(By.id("code_code")).sendKeys("1632*1530");
      driver.findElement(By.xpath("//input[@value='Compile' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains("opt_mult"));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }

  ///////////////////////////////////////////////////////////

  // Given that I input a mathematical expression
  // When I compile the expression
  // Then I see that the operands are properly interpreted by the compiler

  @Test
  public void testDivisionInterpretation() {

    // Check that the division symbol "/" is properly interpreted

    try {

      driver.findElement(By.id("code_code")).sendKeys("1632/1530");
      driver.findElement(By.xpath("//input[@value='Compile' and @type='submit']")).click();

      String code = driver.findElement(By.xpath("//code")).getText();

      assertTrue(code.contains("opt_div"));

    } catch (NoSuchElementException nseex) {

      fail();
    }
  }
}

///////////////////////////////////////////////////////////
///////// CS 1632 DELIVERABLE 3 BY PETER STAMOS ///////////
///////////////////////////////////////////////////////////

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

  @BeforeAll
  static void configure() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "765x768";
    Configuration.baseUrl = "https://demoqa.com";
  }

  @Test
  void fillFormTest() {
    open("/automation-practice-form");

    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    $("#firstName").setValue("Irina");
    $("#lastName").setValue("Petrova");
    $("#userEmail").setValue("ira@ya.ru");
    $("#genterWrapper").$(byText("Female")).click();
    $("#userNumber").setValue("1234567889");
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption("April");
    $(".react-datepicker__year-select").selectOption("1995");
    $(".react-datepicker__day--0" + "15" + ":not(.react-datepicker__day--outside-month)").click();
    $("#subjectsInput").setValue("Economics").pressEnter();
    $("#hobbiesWrapper").$(byText("Music")).click();
    $("#uploadPicture").uploadFromClasspath("test.pdf");
    $("#currentAddress").setValue("Sochi");
    $("#state").click();
    $("#stateCity-wrapper").$(byText("Haryana")).click();
    $("#city").click();
    $("#stateCity-wrapper").$(byText("Karnal")).click();

    $("#submit").click();

    $(".modal-dialog").should(appear);
    $(".table-responsive").shouldHave(text("Irina Petrova"));
    $(".table-responsive").shouldHave(text("ira@ya.ru"));
    $(".table-responsive").shouldHave(text("Female"));
    $(".table-responsive").shouldHave(text("1234567889"));
    $(".table-responsive").shouldHave(text("15 April,1995"));
    $(".table-responsive").shouldHave(text("Economics"));
    $(".table-responsive").shouldHave(text("Music"));
    $(".table-responsive").shouldHave(text("test.pdf"));
    $(".table-responsive").shouldHave(text("Sochi"));
    $(".table-responsive").shouldHave(text("Haryana Karnal"));
  }
}

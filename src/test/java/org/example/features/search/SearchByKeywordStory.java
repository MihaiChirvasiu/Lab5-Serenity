package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.example.steps.serenity.EndUserSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/WikiTestData.csv")
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps anna;

    String username, password;

    @Issue("#Login Valid")
    @Test
    public void login_valid() {
        anna.is_the_login_page();
        anna.logins("a", "a");
        anna.should_see_main_page("Matches");

    }

    @Issue("#Login Invalid")
    @Test
    public void login_invalid() {
        anna.is_the_login_page();
        anna.logins("dafaf", "daafr");
        anna.should_see_login_page("Login");
    }

} 
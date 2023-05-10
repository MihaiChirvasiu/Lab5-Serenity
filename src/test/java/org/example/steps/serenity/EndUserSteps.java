package org.example.steps.serenity;

import org.example.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters_username(String username) {
        dictionaryPage.enter_username(username);
    }

    @Step
    public void enters_password(String password){
        dictionaryPage.enter_password(password);
    }

    @Step
    public void starts_login() {
        dictionaryPage.login();
    }

    @Step
    public void should_see_main_page(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_login_page() {
        dictionaryPage.open();
    }

    @Step
    public void logins(String username, String password) {
        enters_username(username);
        enters_password(password);
        starts_login();
    }
    @Step
    public void should_see_login_page(String definition) {
        assertThat(dictionaryPage.getDefinitionsForInvalid(), hasItem(containsString(definition)));
    }
}
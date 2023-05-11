package org.example.steps.serenity;

import org.example.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.joda.time.DateTime;

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
    public void starts_logout(){dictionaryPage.logout();}

    @Step
    public void should_see_main_page(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }
    
    @Step
    public void should_see_page(String definition){
        assertThat(dictionaryPage.getCurrentUrl(), containsString(definition));
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
    
    public void logout(){
        starts_logout();
    }
    
    @Step
    public void should_see_login_page(String definition) {
        assertThat(dictionaryPage.getDefinitionsForInvalid(), hasItem(containsString(definition)));
    }
    private String backupName = "Rona";
    @Step
    public void update_profile(String test){
        dictionaryPage.profile();
        dictionaryPage.enter_profile_name(test);
        dictionaryPage.clickUpdate(test);
        assertThat(dictionaryPage.getPetName(),containsString(test));
    }
    
    public void restore_profile(){
        if(backupName!=null) {
            dictionaryPage.enter_profile_name(backupName);
            dictionaryPage.clickUpdate(backupName);
        }
    }
    
    public void send_message(){
        long time = DateTime.now().getMillis();
        dictionaryPage.matches();
        should_see_page("matches");
        dictionaryPage.redirectToChat();
        dictionaryPage.send_message(String.valueOf(time));
        assertThat(dictionaryPage.getMessages(String.valueOf(time)),containsString(String.valueOf(time)));
    }
}
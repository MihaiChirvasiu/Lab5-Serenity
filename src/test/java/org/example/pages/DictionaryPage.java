package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://localhost:4200/login")
public class DictionaryPage extends PageObject {

    @FindBy(id="username")
    private WebElementFacade usernameInput;

    @FindBy(id="password")
    private WebElementFacade passwordInput;

    @FindBy(id="login")
    private WebElementFacade loginButton;

    public void enter_username(String username) {
        usernameInput.type(username);
    }

    public void enter_password(String password){
        passwordInput.type(password);
    }

    public void login() {
        loginButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("div"));
        return definitionList.findElements(By.tagName("a")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }

    public List<String> getDefinitionsForInvalid(){
        WebElementFacade definitionList = find(By.tagName("div"));
        return definitionList.findElements(By.id("login")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }

}
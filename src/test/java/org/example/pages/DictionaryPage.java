package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@DefaultUrl("http://localhost:4200/login")
public class DictionaryPage extends PageObject {

    @FindBy(id="username")
    private WebElementFacade usernameInput;

    @FindBy(id="password")
    private WebElementFacade passwordInput;

    @FindBy(id="login")
    private WebElementFacade loginButton;
    
    @FindBy(id="logout")
    private WebElementFacade logoutButton;
    
    @FindBy(id="profile-link")
    private WebElementFacade profileButton;
    
    @FindBy(id="name-profile")
    private WebElementFacade profileNameInput;
    
    @FindBy(id="update-button")
    private WebElementFacade updateButton;
    
    @FindBy(id="pet-name")
    private WebElementFacade petName;
    
    @FindBy(id="matches-link")
    private WebElementFacade matchButton;

    @FindBy(id="textbox")
    private WebElementFacade textbox;
    
    @FindBy(id="send")
    private WebElementFacade send;
    
    public void enter_username(String username) {
        usernameInput.type(username);
    }

    public void enter_password(String password){
        passwordInput.type(password);
    }

    public void enter_profile_name(String name){profileNameInput.type(name);}
    
    public void login() {
        loginButton.click();
    }
    
    public void logout(){
        //logoutButton.click();
        WebElement element = find(By.id("logout"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click()", element);
    }
    
    public void profile(){profileButton.click();}
    
    public void matches(){matchButton.click();}
    
    public void clickUpdate(String test){
        //updateButton.click();
        WebElement element = find(By.id("update-button"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click()", element);
        WebDriverWait wait = new WebDriverWait(getDriver(),1);
        wait.until(ExpectedConditions.textToBePresentInElement(petName,test));
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
    
    public String getMessages(String id){
        return find(By.id(id)).getText();
    }
    
    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }
    
    public String getPetName(){
        return petName.getText();
    }
    
    public void redirectToChat(){
        find(By.id("chat-redirect")).click();
    }
    
    public void send_message(String text){
        textbox.type(text);
        WebElement element = find(By.id("send"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click()", element);
    }

}
package com.example.selenium.tests;

import com.example.pages.HazelCastPage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.selenium.tests.base.SeleniumBase;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ClearCache extends SeleniumBase {

    @Test
    public void clearCache() {
        HazelCastPage hazelCastPage = new HazelCastPage().open();
        try {
            hazelCastPage.login("", "");
            hazelCastPage.clickMaps();
            hazelCastPage.clearSearchMap();

            List<String> maps = hazelCastPage.getAllMaps();
            hazelCastPage.clickConsole();

            maps.forEach(one -> {
                hazelCastPage.typeConsole("ns " + one);
                hazelCastPage.typeConsole("m.clear");
            });
            hazelCastPage.logOut();
        } catch (Throwable e) {
            hazelCastPage.logOut();
        }
    }
}
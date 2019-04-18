package com.yourcompany.qe.util;

import org.testng.annotations.BeforeTest;

public class TestBase {

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test Running " + this.getClass().toString());
    }

}
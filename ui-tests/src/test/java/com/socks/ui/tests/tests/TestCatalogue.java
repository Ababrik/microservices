package com.socks.ui.tests.tests;


import com.socks.ui.Page.CataloguePage;
import com.socks.ui.Page.MainPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestCatalogue extends BaseUiTest {

    @Test
    void canOpenCataloguePage() {
        MainPage.open().openCatalogue();
        assertThat(at(CataloguePage.class).getUrl(), is("http://51.15.240.40/category.html"));
    }

}

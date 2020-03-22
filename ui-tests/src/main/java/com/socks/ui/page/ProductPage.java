package com.socks.ui.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    public static ProductPage open(String productId){
        Selenide.open("/detail.html?id=" + productId);
        return new ProductPage();
    }

    public String getUrl(){
        return WebDriverRunner.url();
    }

    public SelenideElement getBreadcrumb(){
        return $("#breadcrumb");
    }

    public CataloguePage returnToCatalogPageViaBreadcrumb(){
        $(".breadcrumb a[href='category.html']").click();
        return new CataloguePage();
    }

}

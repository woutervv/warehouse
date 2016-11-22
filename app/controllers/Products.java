package controllers;

import com.google.inject.Inject;
import models.Product;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import views.html.products.list;
import views.html.products.details;

/**
 * Created by wouter on 15-9-16.
 */
public class Products extends Controller {
    @Inject FormFactory formFactory;

    public Result list() {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public Result newProduct() {
        return ok(details.render(formFactory.form(Product.class)));
    }

    public Result details(String ean) {
        return TODO;
    }

    public Result save() {
        return TODO;
    }
}

package controllers;

import models.Product;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import views.html.products.list;

/**
 * Created by wouter on 15-9-16.
 */
public class Products extends Controller {

    public Result list() {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public Result newProduct() {
        return TODO;
    }

    public Result details(String ean) {
        return TODO;
    }

    public Result save() {
        return TODO;
    }
}

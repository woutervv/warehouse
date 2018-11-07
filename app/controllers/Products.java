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

    public Result index() {
        return redirect(routes.Products.list(0));
    }

    public Result list(Integer page) {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public Result newProduct() {
        return ok(details.render(formFactory.form(Product.class)));
    }

    public Result details(String ean) {
        final Product product = Product.findByEan(ean);
        if(product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }

        Form<Product> filledForm = formFactory.form(Product.class).fill(product);
        return ok(details.render(filledForm));
    }

    public Result save() {
        Form<Product> boundForm = formFactory.form(Product.class).bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        product.save();
        flash("success", String.format("Successfully added product %s", product));
        return redirect(routes.Products.list(1));
    }

    public Result delete(String ean) {
        final Product product = Product.findByEan(ean);
        if(product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
        }
        Product.remove(product);
        return redirect(routes.Products.list(1));
    }
}

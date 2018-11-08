package controllers;

import annotations.Catch;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import utils.ExceptionMailer;

import java.util.concurrent.CompletionStage;

public class CatchAction extends Action<Catch> {
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        try {
            return delegate.call(ctx);
        } catch (Throwable e) {
            if(configuration.send()) {
                ExceptionMailer.send(e);
                throw new RuntimeException(e);
            } else {
                throw new RuntimeException(e);
            }
        }
    }

}

package controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Person;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    
//	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    private final List<Person> repository = new LinkedList<Person>();
    
    private String resultFuture;

    
    private HttpExecutionContext httpExecutionContext;

    @Inject
    public HomeController(HttpExecutionContext ec) {
    	this.httpExecutionContext = ec;
    }
    
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return redirect(controllers.routes.HomeController.hello("User"));
    }
    
    public Result hello(String name) {
        return ok(views.html.hello.render(name));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public Result sayHello() {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
        	return addPerson(json);
        }
    }
    
    public Result addPerson(JsonNode json) {
    	String name = json.findPath("name").textValue();
        int age = json.findPath("age").intValue();
        repository.add(new Person(name, age));
        if(name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("Hola " + name);
        }
    }
    
    public Result getHello() {
    	List<Person> personas = Arrays.asList(new Person("Daniel", 20), new Person("Carolina", 30));
    	return ok(Json.toJson(personas));
    }
    
	public Result allHello() {
		ArrayNode result = Json.newArray();
		repository.forEach(p -> {
			ObjectNode node = Json.newObject();
			node.put("name", p.getName());
			node.put("age", p.getAge());
			result.add(node);
		});
		return ok(result);
    }

	public List<Person> getRepository() {
		return repository;
	}
	
	public static class VerboseAction extends play.mvc.Action.Simple {
	    public CompletionStage<Result> call(Context ctx) {
//	        log.info("Calling action for {}", ctx);
	    	System.out.println("Calling action for { " + ctx.toString() + " }");
	        return delegate.call(ctx);
	    }
	}

	@With(VerboseAction.class)
	public Result verboseIndex() {
	    return ok("It works VerboseAction!");
	}
	
	public CompletionStage<Result> concurrenTwoFutures() throws InterruptedException {
		final int seconds = 3;

    	CompletionStage<Result> future2 = completedFutureTwo(seconds * 10).thenApplyAsync(answer -> {
        	System.out.println("call return future2");
    		return ok(answer);
    	}, httpExecutionContext.current());
    	
    	hacerOtraCosa("seguir despues del futuro2");
    	
        // Use a different task with explicit EC
		CompletionStage<Result> future1 = completedFutureOne(seconds).thenApplyAsync(answer -> {
            // uses Http.Context
            ctx().flash().put("info", "Response updated!");
        	System.out.println("call return future1");
            return ok(answer);
        }, httpExecutionContext.current());
		
		hacerOtraCosa("seguir despues del futuro1");
		
		return getFutureResult(future2, future1);
    }
	
	private CompletionStage<Result> getFutureResult(CompletionStage<Result> future2, CompletionStage<Result> future1) {
		if(future1.toCompletableFuture().isDone()) {
	    	System.out.println("future1 ok");
			return future1;
		} else {
	    	System.out.println("future2 ok");
			return future2;
		}
	}
	
	private void hacerOtraCosa(String mensaje) {
    	System.out.println(mensaje);
	}
	
	private static CompletionStage<String> completedFutureOne(int seconds) throws InterruptedException {
    	System.out.println("init completedFutureOne");
    	TimeUnit.SECONDS.sleep(seconds);
    	System.out.println("end completedFutureOne");
		return CompletableFuture.completedFuture("answer was completedFutureOne");
	}
	
	private static CompletionStage<String> completedFutureTwo(int seconds) throws InterruptedException {
    	System.out.println("init completedFutureTwo");
    	TimeUnit.SECONDS.sleep(seconds);
    	System.out.println("end completedFutureTwo");
        return CompletableFuture.completedFuture("completed FutureTwo");
    }
	
	public CompletionStage<Result> concurrent(Integer seconds) throws InterruptedException {
		if(seconds < 0) {
			flash("not-success", "cleaned");
			resultFuture = null;
			return CompletableFuture.completedFuture(ok(views.html.concurrent.render("clean")));
		} else {
			if(resultFuture == null) {
				int futureSecond = seconds < 5 ? seconds * 2 : 0;
		    	CompletionStage<Result> future2 = completedFutureTwo(futureSecond).thenApplyAsync(answer -> {
		        	System.out.println("call return future2");
		        	resultFuture = answer;
		    		return ok(views.html.concurrent.render(resultFuture));
		    	}, httpExecutionContext.current());
		    	
		    	hacerOtraCosa("seguir despues del futuro2");
		    	if(seconds >= 5) {
		        	TimeUnit.SECONDS.sleep(seconds);
		    	}

				if(future2.toCompletableFuture().isDone()) {
					flash("success", "future showed");
					return future2;
				} else {
					flash("success", "sin futuro");
					return CompletableFuture.completedFuture(ok(views.html.concurrent.render("no finish future")));
				}
				
			} else {
				flash("success", "Oops");
				return CompletableFuture.completedFuture(ok(views.html.concurrent.render(resultFuture + " reload")));
			}
		}
    }
	
}

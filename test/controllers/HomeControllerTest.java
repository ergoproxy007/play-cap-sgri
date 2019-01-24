package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.AssertFalse;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.util.ByteString;
import models.Person;
import play.Application;

import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import play.twirl.api.Content;

import play.libs.Json;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    private Result getResult(String metodo, String URI) {
        Http.RequestBuilder request = new Http.RequestBuilder().method(metodo).uri(URI);
        return route(app, request);
    }
    
    private void assertTrueContains(Result result, String texto) {
        String contenido = contentAsString(result);
//        System.out.println("contenido = " + contenido);
		assertTrue(contenido.contains(texto));
    }
    
    @Test
    public void testIndex() {
        Result result = getResult(GET, "/");
        assertEquals(SEE_OTHER, result.status());
        assertEquals("", contentAsString(result));
    }
    
    @Test
    public void testHello() {
        Result result = getResult(GET, "/hello");
        assertEquals(OK, result.status());
		assertTrueContains(result, "<h2>Hello, World</h2>");
    }
    
    @Test
    public void testHelloDaniel() {
        Result result = getResult(GET, "/hello/Daniel");
        assertEquals(OK, result.status());
		assertTrueContains(result, "<h2>Hello, Daniel</h2>");
    }

    @Test
    public void testRenderHello() {
        Content html = views.html.hello.render("welcome Karen");
        assertEquals("text/html", html.contentType());
        assertTrue(contentAsString(html).contains("Hello, welcome Karen"));
    }
    
    @Test
    public void testGetHello() {
    	Result result = new HomeController(null).getHello();
        assertEquals(OK, result.status());
        assertTrue("contentType", result.contentType().get().contains("application/json"));
        
        ByteString bs = play.test.Helpers.contentAsBytes(result);
        JsonNode jsonPerson = Json.parse(bs.decodeString("US-ASCII"));
        List<Person> lsPerson = covertJsonToList(jsonPerson, Person.class);
        
        assertTrue(lsPerson.stream().filter(p -> "Daniel".equals(p.getName())).findAny().isPresent());
        assertTrue(lsPerson.stream().filter(p -> "Carolina".equals(p.getName())).findAny().isPresent());
        assertFalse(lsPerson.stream().filter(p -> "Francisco".equals(p.getName())).findAny().isPresent());
    }
    
    private <T> List<T> covertJsonToList(JsonNode jsonPerson, Class<T> klazz) {
        List<T> list = new ArrayList<>();       
    	for (JsonNode nodePeson : jsonPerson) {
    		T t = klazz.cast(Json.fromJson(nodePeson, klazz));
    		list.add(t);
        }
        return list;
    }
    
    private ObjectNode getObjectNode(String field, String value) {
    	ObjectNode node = Json.newObject();
    	node.put(field, value);
    	return node;
    }
    
    @Test
    public void testAddPerson() {
    	HomeController home = new HomeController(null);
    	home.addPerson(getObjectNode("name", "Francisco"));
    	home.addPerson(getObjectNode("name", "Michael"));
    	home.addPerson(getObjectNode("name", "Brayan"));
    	
        assertTrue(home.getRepository().stream().filter(p -> "Michael".equals(p.getName())).findAny().isPresent());
        assertFalse(home.getRepository().stream().filter(p -> "Daniel".equals(p.getName())).findAny().isPresent());
    }
    
    @Test
    public void testAllHello() {
    	HomeController home = new HomeController(null);
    	home.addPerson(getObjectNode("name", "Francisca"));
    	home.addPerson(getObjectNode("name", "Ana"));
    	home.addPerson(getObjectNode("name", "Nicole"));
    	
    	Result result = home.allHello();
    	ByteString bs = play.test.Helpers.contentAsBytes(result);
        JsonNode jsonPerson = Json.parse(bs.decodeString("US-ASCII"));
        List<Person> lsPerson = covertJsonToList(jsonPerson, Person.class);
        
        assertTrue(lsPerson.stream().filter(p -> "Francisca".equals(p.getName())).findAny().isPresent());
        assertFalse(home.getRepository().stream().filter(p -> "Maria".equals(p.getName())).findAny().isPresent());
    }

    private Integer getValue() {
    	try {
			TimeUnit.SECONDS.sleep(20);
        	System.out.println("end getValue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return 10;
    }
    
    @Test
    public void testCompletionStage() {
    	CompletionStage<Integer> task = CompletableFuture.supplyAsync( () -> {
        	System.out.println("call future task");
    		return getValue();
    	});
    	CompletionStage<Integer> squareTask = task.thenApplyAsync( v -> {
        	System.out.println("call future squareTask");
    		return v * v;
    	});
    	
    	System.out.println("before toCompletableFuture");
    	
    	Integer valor = 0;
		try {
			valor = squareTask.toCompletableFuture().get();
	    	System.out.println(valor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	System.out.println("Fin hilo");
		
    }
}

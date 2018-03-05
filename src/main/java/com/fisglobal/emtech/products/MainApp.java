package com.fisglobal.emtech.products;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.fisglobal.emtech.utils.FISException;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

public class MainApp {

	final static Logger logger = Logger.getLogger(MainApp.class);
	private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

	static {
		corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		corsHeaders.put("Access-Control-Allow-Origin", "*");
		corsHeaders.put("Access-Control-Allow-Headers",
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
		corsHeaders.put("Access-Control-Allow-Credentials", "true");
	}

	public final static void applyCorsHeaders() {
		Filter filter = new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				corsHeaders.forEach((key, value) -> {
					response.header(key, value);
				});
			}
		};
		Spark.after(filter);
	}

	public static void main(String[] args) throws FISException {
		port(9091);
		Spark.staticFileLocation("/public");
		MainApp.applyCorsHeaders(); // Call this before mapping the routes
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();

		logger.debug("In MainApp...");

		// map the URLs for the APIs
		// get("/product/id/:id",
		// (rq, rs) -> new
		// ProductList().getInstance().getProductsById(rq.params(":id")), new
		// GsonBuilder().setPrettyPrinting().create()::toJson);

		get("/", (req, res) -> "<h1>Sample REST APIs for Solution Central</h1>" + "<h2>Examples:</h2>"
				+ "<p>/product/id/{id} &nbsp; e.g. /product/id/1998 or /product/id/2466,2353,1888,1843,1998,10455</p> "
				+ "<p>/product/name/{name} e.g. /product/name/profile or /product/name/positive pay</p>");

		get("/product/id/:id", (rq, rs) -> new ProductList().getInstance().getProductsById(rq.params(":id")));

		get("/product/name/:name", (rq, rs) -> new ProductList().getInstance().getProductByName(rq.params(":name")));

	}
}
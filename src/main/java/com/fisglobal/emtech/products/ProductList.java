package com.fisglobal.emtech.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.fisglobal.emtech.utils.Config;
import com.fisglobal.emtech.utils.FISException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProductList {
	private static ProductList instance = null;
	final static Logger logger = Logger.getLogger(ProductList.class);

	protected ProductList() {
		// Exists only to prevent instantiation.
	}

	public static ProductList getInstance() {
		if (instance == null) {
			instance = new ProductList();
		}
		return instance;
	}

	public String getProductsById(final String id) throws SQLException, FISException {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String productJson = null;

		try {
			final List<Product> productList = new ArrayList<Product>();

			final Config config = Config.getInstance();
			final String connString = config.jdbc + config.ip + "/" + config.database + "?" + "user=" + config.user
					+ "&password=" + config.pass;

			Class.forName(config.driverName);
			connect = DriverManager.getConnection(connString);
			// Since the data is only for display, we are using READ_ONLY and
			// FORWARD_ONLY for better performance.
			statement = connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			final StringTokenizer st = new StringTokenizer(id, ",");
			String in_clause = "";
			while (st.hasMoreTokens()) {
				final String value = st.nextToken();
				// if token value is not null, add to the in clause
				if (value != null && !value.isEmpty()) {
					in_clause = (in_clause != null && !in_clause.isEmpty()) ? in_clause + ",'" + value + "'"
							: "'" + value + "'";
				}
			}

			final String where_cond = ((id != null && !id.isEmpty())
					? " where `Solution Status` = 'Active' AND `Asset ID` in (" + in_clause + ")" : "");
			final String sql_stmt = "SELECT * FROM report.epmv_prod_att_dpndt " + where_cond;
			logger.debug(sql_stmt);

			resultSet = statement.executeQuery(sql_stmt);
			while (resultSet.next()) {
				final Product product = new Product();

				product.assetId = resultSet.getInt("Asset ID");
				product.solutionName = resultSet.getString("Solution Name");
				product.solutionDescription = resultSet.getString("Solution Description");
				product.solutionOwner = resultSet.getString("Solution Owner");
				product.domain = resultSet.getString("Domain");
				product.category = resultSet.getString("Category");
				product.solutionStatus = resultSet.getString("Solution Status");
				// product.type =
				// StringEscapeUtils.unescapeJava(resultSet.getString("Category"));
				product.riskBusinessLine = resultSet.getString("Risk Business Line");
				product.financeLevel5 = resultSet.getString("Finance Level 5");
				product.financeLevel10 = resultSet.getString("Finance Level 10");
				product.buOwner = resultSet.getString("BU Owner");
				product.productOwner = resultSet.getString("Product Owner");
				product.implementationMgr = resultSet.getString("Implementation Manager");
				product.developmentMgr = resultSet.getString("Development Manager");
				product.deliveryMethod = resultSet.getString("Delivery Method");
				product.processingLocation = resultSet.getString("Processing Location");
				product.ancilliaryProducts = resultSet.getString("Dependent Ancillary Products");

				product.sanitize();
				productList.add(product);
			}

			// Pretty JSON
			// Gson gson = new Gson();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//productJson = "<pre>" + gson.toJson(productList) + "</pre>";
			productJson = gson.toJson(productList);
			System.out.println(productJson);

		} catch (final Exception ex) {
			logger.error(ex.getMessage());
			throw new FISException(ex);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		}
		return productJson;
	}

	public String getProductByName(final String name) throws SQLException, FISException {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String productJson = null;

		try {
			final Product product = new Product();

			final Config config = Config.getInstance();
			final String connString = config.jdbc + config.ip + "/" + config.database + "?" + "user=" + config.user
					+ "&password=" + config.pass;

			Class.forName(config.driverName);
			connect = DriverManager.getConnection(connString);
			// Since the data is only for display, we are using READ_ONLY and
			// FORWARD_ONLY for better performance.
			statement = connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			final String where_cond = ((name != null && !name.isEmpty())
					? " where `Solution Status` = 'Active' AND `Solution Name` like (\'" + name + "\') " : "");

			final String sql_stmt = "SELECT * FROM report.epmv_prod_att_dpndt " + where_cond;
			logger.debug(sql_stmt);

			resultSet = statement.executeQuery(sql_stmt);
			if (resultSet.next()) {
				product.assetId = resultSet.getInt("Asset ID");
				product.solutionName = resultSet.getString("Solution Name");
				product.solutionDescription = resultSet.getString("Solution Description");
				product.solutionOwner = resultSet.getString("Solution Owner");
				product.domain = resultSet.getString("Domain");
				product.category = resultSet.getString("Category");
				product.solutionStatus = resultSet.getString("Solution Status");
				// product.type =
				// StringEscapeUtils.unescapeJava(resultSet.getString("Category"));
				product.riskBusinessLine = resultSet.getString("Risk Business Line");
				product.financeLevel5 = resultSet.getString("Finance Level 5");
				product.financeLevel10 = resultSet.getString("Finance Level 10");
				product.buOwner = resultSet.getString("BU Owner");
				product.productOwner = resultSet.getString("Product Owner");
				product.implementationMgr = resultSet.getString("Implementation Manager");
				product.developmentMgr = resultSet.getString("Development Manager");
				product.deliveryMethod = resultSet.getString("Delivery Method");
				product.processingLocation = resultSet.getString("Processing Location");
				product.ancilliaryProducts = resultSet.getString("Dependent Ancillary Products");

				product.sanitize();
			}

			// Pretty JSON
			// Gson gson = new Gson();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//productJson = "<pre>" + gson.toJson(product) + "</pre>";
			productJson = gson.toJson(product);
			System.out.println(productJson);

		} catch (final Exception ex) {
			logger.error(ex.getMessage());
			throw new FISException(ex);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		}
		return productJson;
	}

}

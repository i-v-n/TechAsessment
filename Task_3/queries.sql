/* All‌‌ customers‌‌ from‌‌ United‌‌ States */
/* I get the ID and names of customer filtered by country */
SELECT "id", "First_name", "Last_name" FROM customers WHERE "Country" = 'United States';

/* All‌‌ customers‌‌ whose‌‌ details‌‌ were‌‌ not‌‌ updated‌‌ from‌‌ their‌ creation‌‌ on */
SELECT customers."First_name", customers."Last_name"
FROM customers
JOIN customer_details 
ON customer_details."Updated_at" = customers."Created_at";
/* The asme can be achieved with an IN: I get the custemer id of people that have never updated their details and then I use the result to select their names from the customers table */
SELECT "First_name", "Last_name" FROM customers WHERE "id" IN (
	SELECT "Customer_id" 
	FROM customer_details 
	WHERE "Updated_at" = "Created_at"
);

/* The average customer age per country */
/* I use the truncate function to leave 1 significant decimal place */
SELECT customers."Country", TRUNC(AVG(customer_details."Age"), 1) AS "Average Age"
FROM customers
JOIN customer_details 
ON customer_details."Customer_id" = customers."id"
GROUP BY customers."Country";
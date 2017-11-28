#Overview
- This project reads an excel file (Apache POI) and then imports the information into MySQL db (Refer strategies section below)
- The information is synchronized in an Elasticsearch cluster as well.

#Setup
- Install MySQL server and create the schema "import_process"
- Install Elasticsearch 2.4.0
- Open ${ELASTICSEARCH_HOME}\config\elasticsearch.yml and add the following configuration
	-cluster.name: import-cluster
- Launch elasticsearch service ${ELASTICSEARCH_HOME}\config\bin\elasticsearch

#Skip MySQL configuration?
- Comment the database configuration properties in application.properties file(url, user, pass) and the project will use H2 out of the box (h2 dependency already included in pom.xm)


#Process
- Execute the App.java file as Java Application or execute "mvn install" and launch the jar file from console
- The application will parse the file using apache poi and then will load the information in the model (RowRecord.java)
- The repository (JPA) will persist the information into the database
- The Elasticsearch repository will sync the data into the cluster

#Strategies Implemented (listed by best to lowest performance)
1. Batch
2. JPA
3. JDBC + PreparedStatement

#Output
- Open your MySQL client and execute the next query: SELECT * FROM assignment.row_record
- Use your browser to see the output from elasticsearch service
	-http://localhost:9200/records/_search
 	-http://localhost:9200/records/_search?size=10&from=5
-JSON Raw
	-http://localhost:8080/records
-UI (DataTable JQuery)
	-http://localhost:8080/


#Verify if Elasticsearch is running
 - http://localhost:9200/
 
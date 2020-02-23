# ServletsStockMarkets

## Statement
Make a servlet that receives JSON lines the following fields: Date, Time, Opening, Maximum, Minimum, and Closing.
These records will be sorted by date and time and will be received every 10 minutes with the corresponding records 
for the last 6 hours.
It will insert these records into a database. And from these records, and without using the grouping SQL sentences,
it will generate new ones -that it will keep in another table- with the date, the first opening price,
the last closing price, the highest maximum and the lowest minimum.
Make a second Servlet that serves the data stored in this second table with the possible GET parameters that are
considered interesting.

## Technologies used
- JAVA 11
- Maven
- Tomcat7 (tomcat7-maven-plugin)
- Gson
- PostgreSQL 11.6
- Postman

## Solution
### Endpoints
1. (Post) http://localhost:9090/registry
2. (GET) http://localhost:9090/allMedias
3. (GET) http://localhost:9090/standardDeviation
4. (GET) http://localhost:9090/variance
5. (GET) http://localhost:9090/covariance
6. (GET) http://localhost:9090/pearson
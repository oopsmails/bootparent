
==> springbootoauth2 auth & resource: with hsqldb, because auth-server and resource-server need to share.

http://www.baeldung.com/rest-api-spring-oauth2-angularjs

run hsqldb-runServer

--> springbootoauth2-auth-server
run AuthorizationServerApplication

curl -X POST --user 'fooClientIdPassword:secret' -d 'grant_type=password&username=tom&password=111' http://localhost:8080/spring-security-oauth-server/oauth/token

--> springbootoauth2-resource-server
run ResourceServerApplication

http://localhost:8082/springbootoauth2-resource/foos/1 
--> cannot in browser because no Bearer f7a54755-79b7-464a-bc89-fa678e4ca5ae

--> so curl, or Postman
curl -i -H "Accept: application/json" -X GET http://localhost:8080/spring-security-oauth-server/tokens/fooClientIdPassword

curl -i -H "Accept: application/json" -H "Authorization: Bearer f7a54755-79b7-464a-bc89-fa678e4ca5ae" -X GET http://localhost:8082/springbootoauth2-resource/foos/1


------------------------------------------------------------------------------------




==> springbootoauth2 sso: without hsqldb

http://www.baeldung.com/sso-spring-security-oauth2

--> springbootoauth2-sso-auth-server
run AuthorizationServerApplication
actually running on port: 18081, see application.properties

--> springbootoauth2-sso-ui1
run UiApplication
actually running on port: 18082, see application.yml

--> springbootoauth2-sso-ui2
run UiApplication
actually running on port: 18083, see application.yml



http://localhost:18082/ui1
--> see login page
http://localhost:18083/ui2
--> see login page

http://localhost:18082/ui1/securedPage
--> redirect to login page

http://localhost:18083/ui2/securedPage
--> redirect to login page

ui1: login
john
123

--> see http://localhost:18082/ui1/securedPage

--> see http://localhost:18083/ui2/securedPage, without login again

http://localhost:18081/auth/login



# Spring Boot OAuth2 Resource Server Demo

## to run

```
$ mvn spring-boot:run
```

see `application.yml` for configuration details

## Testing

### run Authn server

```
$ springboot-authn-server-demo/mvn spring-boot:run
```

#### generate a token request


```
$ curl -X POST test-web-clientid:@localhost:8080/oauth/token -d grant_type=password -d username=john.doe -d password=demopass
```

get a response:


```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdC1vYXV0aDItcmVzb3VyY2VpZCJdLCJ1c2VyX25hbWUiOiJqb2huLmRvZSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImV4cCI6MTUxMTUyMjQyMiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiJkMDU0ODI0NC1iY2U3LTRjYzgtYjVjYS1iNWIwNTlmN2UzNWYiLCJjbGllbnRfaWQiOiJ0ZXN0LXdlYi1jbGllbnRpZCJ9.vJBxvQZvZJJ1SGF0D6jKpZSSlS_ZntygjH7ZhqAoMW0",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdC1vYXV0aDItcmVzb3VyY2VpZCJdLCJ1c2VyX25hbWUiOiJqb2huLmRvZSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il0sImF0aSI6ImQwNTQ4MjQ0LWJjZTctNGNjOC1iNWNhLWI1YjA1OWY3ZTM1ZiIsImV4cCI6MTUxNDA3MTIyMiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiJhOTZlYjY5ZS03Nzc2LTRjZTItODI5Mi01YTczYmNiMDYzMjIiLCJjbGllbnRfaWQiOiJ0ZXN0LXdlYi1jbGllbnRpZCJ9.wyWU4Cf4BCDM6pMSB821278R-7minXHiyEhHbE_zkSY",
    "expires_in": 43199,
    "scope": "read write trust",
    "jti": "d0548244-bce7-4cc8-b5ca-b5b059f7e35f"
}
```


### Run Resource Server

#### use the generated token to request a resource

```
$ curl http://localhost:8081/api/hello -H "Authorization: Bearer eyJhbGciOiJ . . . UzI1NiIsInR5c"
```

resposne:

Content-Type →text/plain;charset=utf-8
Body: hello world



## Access Token Request

[source](https://tools.ietf.org/html/rfc6749)

The client makes a request to the token endpoint by adding the
   following parameters using the "application/x-www-form-urlencoded"
   format per Appendix B with a character encoding of UTF-8 in the HTTP
   request entity-body:

```
   grant_type
         REQUIRED.  Value MUST be set to "password".

   username
         REQUIRED.  The resource owner username.

   password
         REQUIRED.  The resource owner password.

   scope
         OPTIONAL.  The scope of the access request as described by
         Section 3.3.
```

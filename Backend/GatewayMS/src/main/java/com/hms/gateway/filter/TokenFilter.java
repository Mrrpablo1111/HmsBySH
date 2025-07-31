package com.hms.gateway.filter;



import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {
	private static final String SECRET = "d83a8181663e425571342aa6c02ecfd8196a126a59f5d83200b9840742927cc4e4ce333d773d2f684a7af654d6b70d4f44a0c5408e25fc0e724184a6b8f3e7ee";
	 public TokenFilter()
		 {
			 super(Config.class);
		 }

		 @Override
		 public GatewayFilter apply(Config config) {
			 return (exchange,chain)-> {
				 String path=exchange.getRequest().getPath().toString();
				 if(path.equals("/user/login")||path.equals("/user/register")) {
					return chain.filter(exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET")).build());
				 }
				 HttpHeaders header =exchange.getRequest().getHeaders();
				 if(!header.containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Authorization header's missing");
				 }
				 String authHeader = header.getFirst(HttpHeaders.AUTHORIZATION);
				 if(authHeader==null||!authHeader.startsWith("Bearer ")) {
					 throw new RuntimeException("Authorization header's missing");
				 }
				 String token = authHeader.substring(7);
				 try {
					 Claims claims = Jwts.parser().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
					 exchange = exchange.mutate().request(r-> r.header("X-Secret-Key", "SECRET ")).build();
				 }catch (Exception e){
					 throw new RuntimeException("Token is invalid");
				 }
				 return chain.filter(exchange);

			 };
		 }
		 public static class Config{

		 }
	 }



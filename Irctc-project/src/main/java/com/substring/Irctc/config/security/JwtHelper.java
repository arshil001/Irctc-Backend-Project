package com.substring.Irctc.config.security;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.security.Keys;
    import jakarta.annotation.PostConstruct;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Component;

    import java.security.Key;
    import java.util.Date;

    @Component
    public class JwtHelper {

        //  Defines how long the token can be used before expiration
        private static final long JWT_VALIDITY=5*60*1000;// 5 MINUTES

        // “The secret key is used for signing and validating JWT tokens.”
        private final String SECRET= "jdhjhaidiuawssdhudedheuhdsidkjkjyrhiuhwiueieeuhihvjgkgfghgvhgvhvgvhnshiue";

        //Yes, the key variable stores the SECRET value, but in a cryptographic Key object form.
       // The SECRET string is converted into bytes and then into an HMAC key, which is used to sign and validate JWT tokens

        private Key key;

        @PostConstruct //@PostConstruct is used to run initialization code after the Spring bean is created.
       // In this case, it prepares the cryptographic key before JWT operations.”
        public void init(){
            this.key= Keys.hmacShaKeyFor(SECRET.getBytes());

        }

    // generate token

        public String generateToken(UserDetails userDetails)
        {
            return Jwts.builder() //“Start building a JWT token”
                    .setSubject(userDetails.getUsername()) //Subject identifies the user for whom the token is issued.
                    .setIssuedAt(new Date())//Sets when the token was created
                    .setExpiration(new Date(System.currentTimeMillis()+JWT_VALIDITY))// set token expiry
                    .signWith(key, SignatureAlgorithm.HS512)// “JWT is signed using HS512 to ensure integrity and security.”
                    .compact();//“Finish building the JWT and convert it into a single String.”
        }

        // get all claims from  token
        // This method extracts all claims (payload data) from a JWT after verifying its signature.
        public Claims getClaims(String token){
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        }

    // get username from token

    public String getUsernameFromToken(String token)
    {
        return getClaims(token).getSubject();

    }

    //validate token

    public boolean isTokenValid(String token, UserDetails userDetails)
    {

        String username= getUsernameFromToken(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    //  check token expiration
    private boolean isTokenExpired(String token) {

        return getClaims(token).getExpiration().before(new Date());
    }










}

    package com.substring.Irctc.config.security;

    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;

    @Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private UserDetailsService userDetailsService;
        private JwtHelper jwtHelper;

        public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtHelper jwtHelper) {
            this.userDetailsService = userDetailsService;
            this.jwtHelper = jwtHelper;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String authorizationHeader = request.getHeader("Authorization");
            String username =null;
            String token=null;

            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ") ){
                try{
                    token=authorizationHeader.substring(7);
                    username=jwtHelper.getUsernameFromToken(token);

                    if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){

                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        if(jwtHelper.isTokenValid(token,userDetails)){
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }

                catch(Exception e){

                    System.out.println(" there is some problem");
                }
            }
            else{
                System.out.println(" Invalid Authorization Header");
            }
            filterChain.doFilter(request,response);

        }
    }

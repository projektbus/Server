package ProjektBus.Server.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader("Authorization");

        UsernamePasswordAuthenticationToken usernameResult = getAuthenticationByToken(header);

        SecurityContextHolder.getContext().setAuthentication(usernameResult);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {

        Jws<Claims> claims =  Jwts.parser().setSigningKey("TbUL55^O|T<;UyT".getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""));

        String role = claims.getBody().get("role").toString();
        String username = claims.getBody().get("name").toString();

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

        return usernamePasswordAuthenticationToken;
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//
//        String httpHeader = httpServletRequest.getHeader("authorization");
//
//        if(httpHeader == null || !httpHeader.startsWith("Bearer ")){
//            throw new ServletException("Wrong or empty header");
//        }
//        else{
//            try {
//                String token = httpHeader.substring(7);
////                wyciągnięcie hasła
////                sprawdzić czy hasło poprawne!, jak nie to wyjątek
//                Claims claims = Jwts.parser().setSigningKey("testPass").parseClaimsJws(token).getBody();
//                request.setAttribute("claims", claims);
//            } catch(Exception ex){
//                throw new ServletException("Session expired");
//            }
//        }
//        chain.doFilter(request, response);
//    }
}

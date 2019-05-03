package ProjektBus.Server.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String httpHeader = httpServletRequest.getHeader("authorization");

        if(httpHeader == null || !httpHeader.startsWith("Bearer ")){
            throw new ServletException("Wrong or empty header");
        }
        else{
            try {
                String token = httpHeader.substring(7);
                //wyciągnięcie hasła
                //sprawdzić czy hasło poprawne!, jak nie to wyjątek
                Claims claims = Jwts.parser().setSigningKey("testPass").parseClaimsJws(token).getBody();
                request.setAttribute("claims", claims);
            } catch(Exception ex){
                throw new ServletException("Session expired");
            }
        }
        chain.doFilter(request, response);
    }
}

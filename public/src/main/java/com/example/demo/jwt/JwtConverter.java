package com.example.demo.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private final JwtConverterProperties properties;

    /**
     *
     * @param jwt
     * Тут нажо создать JwtAuthenticationToken
     * у него три конструктора. Используем конструктор с тремя параметрами:
     *      jwt - the JWT
     *      authorities - the authorities assigned to the JWT
     *      name - the principal name
     * Объединяем два списка ролей в один
     * @return
     */
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extreactResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt,authorities, getPrincipalClaimName(jwt));
    }

    /**
     * Забираем из токена principal name
     * 
     * @param jwt
     * @return
     */
    private String getPrincipalClaimName(Jwt jwt) {

    }

    private Collection<? extends GrantedAuthority> extreactResourceRoles(Jwt jwt) {

        return null;
    }
}

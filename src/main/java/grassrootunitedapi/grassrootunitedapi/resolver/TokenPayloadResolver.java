package grassrootunitedapi.grassrootunitedapi.resolver;

import grassrootunitedapi.grassrootunitedapi.helper.JwtProvider;
import grassrootunitedapi.grassrootunitedapi.constant.GlobalConstant;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TokenPayloadResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return TokenPayload.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorization.substring("Bearer ".length());
        String servletPath = request.getServletPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        if (antPathMatcher.match(GlobalConstant.BACK_OFFICE_PATH_PATTERN, servletPath)) {
            return jwtProvider.validateBackOfficeToken(token);
        } else {
            return jwtProvider.validateFrontOfficeToken(token);
        }
    }
}

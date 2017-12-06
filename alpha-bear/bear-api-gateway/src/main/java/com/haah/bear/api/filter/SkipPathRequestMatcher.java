package com.haah.bear.api.filter;

import org.junit.Assert;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SkipPathRequestMatcher implements RequestMatcher {

    private final OrRequestMatcher matcher;

    private final RequestMatcher requestMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip) {
        Assert.assertNotNull(pathsToSkip);
        List<RequestMatcher> matcherList = new ArrayList<>();
        for (String path : pathsToSkip) {
            matcherList.add(new AntPathRequestMatcher(path));
        }
        matcher = new OrRequestMatcher(matcherList);
        requestMatcher = new AntPathRequestMatcher("/**");
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !matcher.matches(request) && requestMatcher.matches(request);
    }
}

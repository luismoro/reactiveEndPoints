package com.br.itau.internet;

import org.springframework.http.HttpMethod;
import org.springframework.http.codec.BodyExtractor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.ServerRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by luismoro on 05/12/16.
 */
public class ServerRequestImpl implements ServerRequest{

    HttpServletRequest request;

    public ServerRequestImpl (HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.resolve(request.getMethod());
    }

    @Override
    public URI uri() {
        return URI.create(request.getRequestURI());
    }

    @Override
    public Headers headers() {
        return (Headers) getHeadersInfo(request);
    }

    @Override
    public <T> T body(BodyExtractor<T, ? super ServerHttpRequest> bodyExtractor) {
        return null;
    }

    @Override
    public <T> Mono<T> bodyToMono(Class<? extends T> aClass) {
        return null;
    }

    @Override
    public <T> Flux<T> bodyToFlux(Class<? extends T> aClass) {
        return null;
    }

    @Override
    public <T> Optional<T> attribute(String s) {
        return null;
    }

    @Override
    public List<String> queryParams(String s) {
        return  Arrays.asList(request.getQueryString().split("&"));
    }

    @Override
    public Map<String, String> pathVariables() {
        Map map = new HashMap();
        List<String> strings = Arrays.asList(request.getServletPath().substring(1).split("/"));
        for (int i = 0; i < strings.size(); i++) {
            map.put(strings.get(i),strings.get(i+1));
            i++;
        }
        return map;
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}

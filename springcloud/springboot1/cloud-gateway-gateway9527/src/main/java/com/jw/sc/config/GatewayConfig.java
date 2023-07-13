package com.jw.sc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//第2中方式：通过写注册类实现网关路由 转发到百度 测试ok
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //当访问http://localhost:9527/guonei?uname=s 转发到  https://news.baidu.com/guonei
        routes.route("route1",r->r.path("/guonei").uri("https://news.baidu.com/guonei")).build();

        return routes.build();
    }

    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //当访问http://localhost:9527/guoji?uname=s 转发到  https://news.baidu.com/guoji
        routes.route("route2",r->r.path("/guoji").uri("https://news.baidu.com/guoji")).build();

        return routes.build();
    }
}

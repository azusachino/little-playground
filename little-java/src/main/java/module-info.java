/**
 * @author az
 * @since 2020-03-31
 */
module little.java {
    requires java.base;
    requires com.google.common;
    requires hutool.all;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires java.management;
    requires org.apache.commons.codec;
    requires spring.context;
    requires org.apache.tomcat.embed.core;
    requires org.apache.commons.lang3;
    requires com.google.gson;
    requires spring.core;
    requires spring.beans;
    requires spring.boot.starter.data.redis;
    requires spring.data.redis;
    requires jedis;
    requires okhttp3;
    requires flowable.spring.common;
    requires com.fasterxml.jackson.core;

    opens cn.az.code to spring.core, spring.beans, spring.context;
    opens cn.az.code.servlet to spring.beans;
    opens cn.az.code.config to spring.core, spring.beans, spring.context;

    exports cn.az.code.stream;
}

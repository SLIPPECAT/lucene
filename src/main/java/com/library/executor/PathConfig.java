package com.library.executor;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("path")
@Getter
@Setter
public class PathConfig {

    String indexPath;
}

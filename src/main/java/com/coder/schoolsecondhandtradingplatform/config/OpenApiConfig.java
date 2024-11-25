package com.coder.schoolsecondhandtradingplatform.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "校园二手教材交易平台",
                version = "1.0",
                description = "此 API 提供校园二手教材交易平台的所有功能"
        )
)
public class OpenApiConfig {
    // 这个类的作用是提供 API 文档的全局配置
}


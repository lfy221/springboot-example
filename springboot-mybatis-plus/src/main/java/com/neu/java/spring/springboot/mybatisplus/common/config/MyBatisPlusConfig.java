package com.neu.java.spring.springboot.mybatisplus.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
       MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
       mapperScannerConfigurer.setBasePackage("com.neu.java.spring.springboot.mybatisplus.module.*.mapper");
       return mapperScannerConfigurer;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        /*注意:
            使用多个功能需要注意顺序关系,建议使用如下顺序
            1.多租户,动态表名
            2.分页,乐观锁
            3.sql性能规范,防止全表更新与删除
            总结: 对sql进行单次改造的优先放入,不对sql进行改造的最后放入
        */
        //对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        //该属性将会随着 com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor 插件的移除而移除，但是为了避免缓存出现问题还需要这样设置
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}

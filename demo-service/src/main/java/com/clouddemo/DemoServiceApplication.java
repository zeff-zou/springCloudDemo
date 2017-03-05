package com.clouddemo;

import com.clouddemo.common.base.repository.support.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EntityScan(basePackages = {"com.clouddemo.common.**.entity"})
@ComponentScans({
		@ComponentScan(basePackages ={"com.clouddemo.demoservice.**"})
})
public class DemoServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoServiceApplication.class).web(true).run(args);
	}
}

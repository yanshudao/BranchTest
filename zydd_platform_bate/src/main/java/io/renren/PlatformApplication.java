package io.renren;

import io.renren.datasources.DynamicDataSourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
//@MapperScan("io.renren.modules.*.dao")
public class PlatformApplication extends SpringBootServletInitializer {

	public static class CustomGenerator implements BeanNameGenerator {

		@Override
		public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
			return definition.getBeanClassName();
		}
	}
	public static void main(String[] args) {
		new SpringApplicationBuilder(PlatformApplication.class)
				.beanNameGenerator(new CustomGenerator())
				.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlatformApplication.class);
	}
	/*@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
			*//*String root =  patternResolver.getResource("classpath:templates").getFile().toString();
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(root);*//*
			ClasspathResourceLoader cploder = new ClasspathResourceLoader("/templates");
			beetlGroupUtilConfiguration.setResourceLoader(cploder);

			beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
			return beetlGroupUtilConfiguration;


	}*/
}

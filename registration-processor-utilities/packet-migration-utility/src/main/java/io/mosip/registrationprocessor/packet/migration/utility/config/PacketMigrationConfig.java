package io.mosip.registrationprocessor.packet.migration.utility.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.mosip.kernel.dataaccess.hibernate.config.HibernateDaoConfig;
import io.mosip.kernel.dataaccess.hibernate.repository.impl.HibernateRepositoryImpl;
import io.mosip.registration.processor.core.spi.restclient.RegistrationProcessorRestClientService;
import io.mosip.registration.processor.rest.client.service.impl.RegistrationProcessorRestClientServiceImpl;
import io.mosip.registrationprocessor.packet.migration.utility.builder.AuditLogRequestBuilder;
import io.mosip.registrationprocessor.packet.migration.utility.service.PacketMigrationService;
import io.mosip.registrationprocessor.packet.migration.utility.utils.DecryptorImpl;
import io.mosip.registrationprocessor.packet.migration.utility.utils.RestApiClient;


@Configuration
@PropertySource("classpath:bootstrap.properties")
@Import({ HibernateDaoConfig.class })
@EnableJpaRepositories(basePackages = "io.mosip.registration.processor", repositoryBaseClass = HibernateRepositoryImpl.class)
public class PacketMigrationConfig {

	
	/*@Bean
	public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer(Environment env)
			throws IOException {

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		List<String> applicationNames = getAppNames(env);
		Resource[] appResources = new Resource[applicationNames.size()];

		for (int i = 0; i < applicationNames.size(); i++) {
			String loc = env.getProperty("spring.cloud.config.uri") + "/registration-processor/"
					+ env.getProperty("spring.profiles.active") + "/" + env.getProperty("spring.cloud.config.label")
					+ "/" + applicationNames.get(i) + "-" + env.getProperty("spring.profiles.active") + ".properties";
			appResources[i] = resolver.getResources(loc)[0];
			((AbstractEnvironment) env).getPropertySources()
					.addLast(new ResourcePropertySource(applicationNames.get(i), loc));
		}
		pspc.setLocations(appResources);
		return pspc;

	}
	
	public List<String> getAppNames(Environment env) {
		String names = env.getProperty("spring.application.name");
		return Stream.of(names.split(",")).collect(Collectors.toList());
	}
*/

		
	@Bean
	public AuditLogRequestBuilder getAuditLogRequestBuilder() {
		return new AuditLogRequestBuilder();
	}

	

	@Bean
	public PacketMigrationService getPacketMigrationService() {
		return new PacketMigrationService();
	}
	@Bean
	public RegistrationProcessorRestClientService<Object> getRegistrationProcessorRestService(){
		return new RegistrationProcessorRestClientServiceImpl();
	}
	@Bean
	@Primary
	public RestApiClient getRestApiClient() {
		return new RestApiClient();
	}
	@Bean
	public io.mosip.registration.processor.rest.client.utils.RestApiClient getRegistrationProcessorRestApiClient() {
		return new io.mosip.registration.processor.rest.client.utils.RestApiClient();
	}
}

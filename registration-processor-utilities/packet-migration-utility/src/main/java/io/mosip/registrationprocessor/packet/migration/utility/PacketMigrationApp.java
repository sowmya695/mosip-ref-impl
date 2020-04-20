package io.mosip.registrationprocessor.packet.migration.utility;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import io.mosip.registrationprocessor.packet.migration.utility.service.PacketMigrationService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {
"io.mosip.registrationprocessor.packet.migration.utility.*","io.mosip.registration.processor.core.*"})
@PropertySource("classpath:bootstrap.properties")
public class PacketMigrationApp {

@Autowired
PacketMigrationService packetMigrationService;


    public static void main( String[] args )
    {
    	  SpringApplication.run(PacketMigrationApp.class, args);
    	 
          }


    @PostConstruct
    public void run() {
    	packetMigrationService.processPackets();
    }
}

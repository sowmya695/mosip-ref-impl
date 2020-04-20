package io.mosip.registrationprocessor.packet.migration.utility.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.mosip.kernel.core.exception.IOException;
import io.mosip.kernel.core.util.FileUtils;
import io.mosip.registration.processor.core.exception.ApisResourceAccessException;
import io.mosip.registration.processor.core.exception.PacketDecryptionFailureException;
import io.mosip.registrationprocessor.packet.migration.utility.utils.DecryptorImpl;

@Component
public class PacketMigrationService {
	@Autowired
	private Environment env;
	
	@Autowired
	private DecryptorImpl decryptor;
	
	private static final String ENCRYPTEDPACKETSTORE = "mosip.registration.processor.encryptedpacketstore";

	public void processPackets() {
		
		File f = new File("C:/encrypted/");
		for (File each : f.listFiles()) {
			
			try {
				File file = new File("C:/encrypted/"+ each.getName());
				FileInputStream fis = new FileInputStream(file);
				InputStream output = decryptor.decrypt(fis, file.getName().substring(0, file.getName().length() - 4));
				FileUtils.copyInputStreamToFile(output,
						new File("C:/decrypted/" + each.getName()));
			} catch (PacketDecryptionFailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApisResourceAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package com.myapp.azurekeyreactor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

@RestController
public class AzureKeyReactorController {

	@GetMapping("/data")
	public String getSecret() {
		SecretClient secretClient = new SecretClientBuilder().vaultUrl("https://mykey7.vault.azure.net/")
				.credential(new DefaultAzureCredentialBuilder().build()).buildClient();

		KeyVaultSecret secretMono = secretClient.getSecret("mysecret");
		String secretValue = secretMono.getValue();
		System.out.println(secretValue);

		return "My Stored Secret is : " + secretValue;
	}

}

package com.accountingservices.lisa.configuration;

import com.accountingservices.lisa.models.OrganizationType;
import com.accountingservices.lisa.repository.OrganizationTypeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ApplicationRunner dataLoader(OrganizationTypeRepository repo) {
        return args -> {
            repo.save(new OrganizationType(1, "ИП"));
            repo.save(new OrganizationType(2, "ООО"));
            repo.save(new OrganizationType(3, "Другое"));
        };
    }
}

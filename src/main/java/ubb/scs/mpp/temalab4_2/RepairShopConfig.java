package ubb.scs.mpp.temalab4_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ubb.scs.mpp.temalab4_2.repository.ComputerRepairRequestRepository;
import ubb.scs.mpp.temalab4_2.repository.ComputerRepairedFormRepository;
import ubb.scs.mpp.temalab4_2.repository.jdbc.ComputerRepairRequestJdbcRepository;
import ubb.scs.mpp.temalab4_2.repository.jdbc.ComputerRepairedFormJdbcRepository;
import ubb.scs.mpp.temalab4_2.services.ComputerRepairServices;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {
        Properties props = new Properties();
        try{
            System.out.println("Searching bd.config in directory" + ((new File(".")).getAbsolutePath()));
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.err.println("Configuration file bd.config not found" + e.getMessage());
        }
        return props;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        return new ComputerRepairRequestJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
        return new ComputerRepairedFormJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairServices services(){
        return new ComputerRepairServices(requestsRepo(), formsRepo());
    }

}

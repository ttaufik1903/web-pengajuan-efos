package com.brk.servicepencairan;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.brk.servicepencairan.controllers.PencairanController;

@SpringBootApplication
@EnableEurekaClient
public class ServicePencairanApplication {
	private static Logger logger = Logger.getLogger(PencairanController.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(ServicePencairanApplication.class, args);
		
		logger.info("   ___  _____   ___   _____         _         _   	              ");
		logger.info("  /  _]|     | /   \\ / ___/    \\\\  | |__ _ _ | |__   //            ");
		logger.info(" /  [_ |   __||     (   \\_      )) | '_ \\ '_|| / /   ((             ");
		logger.info("|    _]|  |_  |  O  |\\__  |    //  |_.__/ _| |_\\_\\   \\\\            ");
		logger.info("|   [_ |   _] |     |/  \\ |    PT Bank Riau Kepri                  ");	
		logger.info("|_____||__|    \\___/ \\____| 							              ");
		logger.info("#########################################################################");	
		logger.info("Electronic Financing Origination System (v1.0.0.RELEASE DATE: 2022-06-02)");	
	}

}

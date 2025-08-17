package com.vanillacare.medibot2;

import com.vanillacare.medibot2.controller.PatientController;
import com.vanillacare.medibot2.dto.PatientCreateRequest;
import com.vanillacare.medibot2.dto.PatientDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Medibot2Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Medibot2Application.class, args);

        var patientController = context.getBean(PatientController.class);

        patientController.createPatient(
                new PatientCreateRequest(
                        "vanillasky",
                        "12345",
                        "1989/12/13",
                        "0999999991",
                        "test"
                )
        );



    }

}

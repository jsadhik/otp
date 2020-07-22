package com.apl.otps.lara.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.apl.otps.lara.entities.BatchDetails;

@Component
public class EmailSender {
	Logger logger = Logger.getLogger(EmailSender.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${recipientAddress}")
	private String recipientAddress;
	private String subject;
	private String message;

	public void sendEmail(BatchDetails batchDetails) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);

		if (batchDetails.getBatchRespCode() == 0) {
			message = "Batch number " + batchDetails.getBatchNo() + " Succesfuly completed";
			subject = "LARA to OTPS SYNC Success";
			
		} else if (batchDetails.getBatchRespCode() == -1) {
			message = "Batch number " + batchDetails.getBatchNo() + " Partialy  completed";
			subject = "LARA to OTPS SYNC Success";
			
		} else if (batchDetails.getBatchRespCode() == -2) {
			message = "Batch number " + batchDetails.getBatchNo() + " Failed at Oracle end";
			subject = "LARA to OTPS SYNC Failure";
			
		} else if (batchDetails.getBatchRespCode() == -5) {
			message = "Batch number : " + batchDetails.getBatchNo() + " Unknown error occured";
			subject = "LARA to OTPS SYNC Failure";
			
		} else if (batchDetails.getBatchRespCode() == -6) {
			message = "Batch number : " + batchDetails.getBatchNo() + " No data fetched from Lara Mirror";
			subject = "LARA to OTPS SYNC Success";
			
		} else if (batchDetails.getBatchRespCode() == -8) {
			message = "Batch number : " + batchDetails.getBatchNo() + " Error occured at dotnet end";
			subject = "LARA to OTPS SYNC Failure";
			
		}
		
		email.setSubject(subject);
		email.setText(message);
		mailSender.send(email);

	}
}

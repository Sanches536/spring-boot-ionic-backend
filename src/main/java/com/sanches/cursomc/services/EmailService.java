package com.sanches.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.sanches.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}

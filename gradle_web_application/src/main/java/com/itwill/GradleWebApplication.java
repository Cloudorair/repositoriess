package com.itwill;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ServletComponentScan
public class GradleWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleWebApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void launchBrowser() {
		System.setProperty("java.awt.headless", "false");
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("http://localhost:80/index.jsp"));
		} catch (Exception e) {

		}
	}
}

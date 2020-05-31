package com.computacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.computacion.model.TsscAdmin;
import com.computacion.model.TsscGame;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscGameException;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.service.TsscAdminService;
import com.computacion.service.TsscGameService;
import com.computacion.service.TsscTopicService;



@SpringBootApplication
public class CiCarvajalHernandezTaller1Application {

	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static void main(String[] args) throws TsscTopicException, TsscTopicNotFoundException, TsscGameException {
		ConfigurableApplicationContext c = SpringApplication.run(CiCarvajalHernandezTaller1Application.class, args);
		TsscAdminService adminService=c.getBean(TsscAdminService.class);
		TsscAdmin superadmin=new TsscAdmin();
		superadmin.setUser("root");
		superadmin.setPassword("{noop}root");
		superadmin.setSuperAdmin("SA");
		adminService.create(superadmin);
		TsscAdmin admin=new TsscAdmin();
		admin.setUser("admin");
		admin.setPassword("{noop}admin");
		admin.setSuperAdmin("A");
		adminService.create(admin);
		
		
		TsscTopicService topicService=c.getBean(TsscTopicService.class);
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setName("Topic 1");
		topicService.createTopic(topic);
		
		topic=new TsscTopic();
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setName("Topic 2");
		topicService.createTopic(topic);
		
		
		
		TsscGameService gameService=c.getBean(TsscGameService.class);
		TsscGame game=new TsscGame();
		game.setName("Juego 1");
		game.setNGroups(4);
		game.setNSprints(4);		
		game.setScheduledDate(LocalDate.now());
		game.setScheduledTime(LocalTime.now());
		game.setUserPassword("gagaegaewgae");
		game.setAdminPassword("gegegegeg");
		game.setGuestPassword("gagaegwaeg");
		gameService.createGame(game, topic.getId());

		
	}

}

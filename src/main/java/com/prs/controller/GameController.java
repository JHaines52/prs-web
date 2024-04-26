package com.prs.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;



@RestController
@RequestMapping("/api/play")
public class GameController {
	
	   private final Random random = new Random();
	    private enum Choice {
	        GIANTS, WIZARDS, ELVES;
	    }

	    @PostMapping("")
	    public String playGame(@RequestParam  Choice userChoice) {
	        Choice computerChoice = Choice.values()[random.nextInt(Choice.values().length)];
	        String result = determineWinner(userChoice, computerChoice);
	        return result;
	    }

	    private String determineWinner(Choice user, Choice computer) {
	        if (user == computer) {
	            return "It's a tie! Both chose " + user;
	        }

	        switch (user) {
	            case GIANTS:
	                return (computer == Choice.ELVES) ? "You win! Giants crush wizards." : "You lose! Elves beat giants.";
	            case WIZARDS:
	                return (computer == Choice.GIANTS) ? "You win! Wizards zap elves." : "You lose! Giants beat wizards.";
	            case ELVES:
	                return (computer == Choice.WIZARDS) ? "You win! Elves trip giants." : "You lose! Wizards beat elves.";
	            default:
	                return "Invalid choice.";
	        }
	    }
//	    @Configuration
//	    public class WebConfig implements WebMvcConfigurer {
//	        @Override
//	        public void addCorsMappings(CorsRegistry registry) {
//	            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//	        }
//	    }
	}


	


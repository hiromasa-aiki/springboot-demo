package com.example.demo.controller;

import static org.mockito.Mockito.*;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.demo.Service.GreetingService;
import com.example.demo.dto.EmployeeRequest;

public class GreetingControllerTest {
	@Test
	void directValidationTest() {

	    EmployeeRequest request = new EmployeeRequest();

	    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    validator.afterPropertiesSet();

	    Set<ConstraintViolation<EmployeeRequest>> violations =
	            validator.validate(request);

	    System.out.println("違反件数 = " + violations.size());

	    for (ConstraintViolation<EmployeeRequest> violation : violations) {
	        System.out.println(
	                violation.getPropertyPath() + " : " + violation.getMessage()
	        );
	    }
	}
	
    @Test
    void helloTest() throws Exception {

    	GreetingService service = Mockito.mock(GreetingService.class);

    	GreetingController controller = new GreetingController(service);


        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/Greeting/hello")
                        .param("name", "Tanaka")
        )
        .andExpect(
                MockMvcResultMatchers.status().isOk()
        )
        .andExpect(
                MockMvcResultMatchers.content().string("Hello Tanaka!")
        );
        
    }
    
    
    @Test
    void createEmployeeTest() throws Exception {

    	GreetingService service = Mockito.mock(GreetingService.class);

    	GreetingController controller = new GreetingController(service);

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/Greeting/employees")
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "Tanaka",
                                    "email": "tanaka@example.com"
                                }
                                """)
        )
        .andExpect(
                MockMvcResultMatchers.status().isOk()
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Tanaka")
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("tanaka@example.com")
        );
    }
    
    @Test
    void createEmployeeValidationErrorTest() throws Exception {

    	GreetingService service = Mockito.mock(GreetingService.class);

    	GreetingController controller = new GreetingController(service);

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/Greeting/employees")
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "",
                                    "email": ""
                                }
                                """)
        )
        .andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("名前は必須です")
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value("メールアドレスは必須です")
        );
//        .andDo(result -> {
//            System.out.println("★★★ HTTPステータス = "
//                    + result.getResponse().getStatus());
//            System.out.println("★★★ レスポンス = "
//                    + result.getResponse().getContentAsString());
//        });
    }
    
    @Test
    void greetingMockTest() throws Exception {
    	
    	GreetingService service = Mockito.mock(GreetingService.class);
    	when(service.getGreeting("Tanaka")).thenReturn("Mock Hello Tanaka!");
    	
//    	GreetingController controller = new GreetingController();
//    	
//    	java.lang.reflect.Field field = GreetingController.class.getDeclaredField("greetingService");
//    	
//    	field.setAccessible(true);
//    	field.set(controller, service);
    	
    	GreetingController controller = new GreetingController(service);
    	
    	MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    	mockMvc.perform(
    			MockMvcRequestBuilders.get("/Greeting/greeting")
    				.param("name","Tanaka")
    	)
    	.andExpect(
    			MockMvcResultMatchers.status().isOk()
    	)
    	.andExpect(
    			MockMvcResultMatchers.content().string("Mock Hello Tanaka!")
    	);
    }
    	

}

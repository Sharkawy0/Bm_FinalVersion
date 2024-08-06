//package com.example.Bm.controller;
//
//import com.example.Bm.dto.UpdateCustomerDTO;
//import com.example.Bm.model.Customer;
//import com.example.Bm.service.ICustomer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.RequestEntity.put;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ICustomer customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    void testGetAllCustomers() throws Exception {
//        Customer customer1 = new Customer();
//        customer1.setEmail("customer1@gmail.com");
//        Customer customer2 = new Customer();
//        customer2.setEmail("customer2@gmail.com");
//
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        when(customerService.getCustomerList()).thenReturn(customers);
//
//        mockMvc.perform(get("/api/all"))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$[0].email").value("customer1@gmail.com"))
//                .andExpect((ResultMatcher) jsonPath("$[1].email").value("customer2@gmail.com"));
//    }
//
//    @Test
//    void testUpdateCustomer() throws Exception {
//        UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO();
//        updateCustomerDTO.setLastName("NewLastName");
//        updateCustomerDTO.setPhoneNumber("1234567890");
//
//        when(customerService.updateCustomer(any(String.class), any(UpdateCustomerDTO.class)))
//                .thenReturn("Update successful");
//
//        mockMvc.perform((RequestBuilder) put("/api/update/testuser@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.valueOf("{\"lastName\": \"NewLastName\", \"phoneNumber\": \"1234567890\"}")))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.message").value("Update successful"));
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}

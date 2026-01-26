package com.jonathan.Gestao_Vagas.modules.company.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathan.Gestao_Vagas.modules.company.dto.CreateJobDTO;
import com.jonathan.Gestao_Vagas.modules.company.entities.CompanyEntity;
import com.jonathan.Gestao_Vagas.modules.company.repositories.CompanyRepository;
import com.jonathan.Gestao_Vagas.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)//simula um servidor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Garante que rode numa porta randomica
@ActiveProfiles("test") //Irá pegar o application-test.properties
public class CreateJobControllerTest {

    private MockMvc mvc; //Simula um ambiente/servidor

    @Autowired
    private WebApplicationContext context; //Cria um contexto

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).
        apply(SecurityMockMvcConfigurers.springSecurity()). //Vai Mockar um springSecurity
        build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var company = CompanyEntity.builder().description("Description_test").email("email@company.com")
                .password("1234567890").username("Company_Username_Test").name("Company_name_test").build();

        var companySave = companyRepository.saveAndFlush(company); //Salva IMEDIATAMENTE (Sem esperar as requisições) a company no repositorio de memoria (h2)

        var createdJobDTO = CreateJobDTO.builder().benefits("Benefits_test").descriptions("description_test").level("level_Test").build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/").contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJSON(createdJobDTO)).
                        header("Authorization",TestUtils.generateToken(companySave.getId(),"JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);

    }



}

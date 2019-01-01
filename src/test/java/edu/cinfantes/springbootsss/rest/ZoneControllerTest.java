package edu.cinfantes.springbootsss.rest;

import com.google.gson.Gson;
import edu.cinfantes.springbootsss.domain.Priority;
import edu.cinfantes.springbootsss.usecase.AddPolygonToZone;
import edu.cinfantes.springbootsss.usecase.CreateZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ZoneController.class)
public class ZoneControllerTest {
  @MockBean
  private CreateZone createZone;
  @MockBean
  private AddPolygonToZone addPolygonToZone;

  private Gson gson = new Gson();

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void should_return_ok_when_zone_is_created() throws Exception {
    CreateZoneRequest zone = CreateZoneRequest.builder()
      .name("prueba 1")
      .priority(Priority.LOW.getValue())
      .build();

    mockMvc.perform(post("/zone")
      .contentType(APPLICATION_JSON)
      .content(gson.toJson(zone)))
      .andDo(print())
      .andExpect(status().isOk());
  }
}
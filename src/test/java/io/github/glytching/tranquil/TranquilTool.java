package io.github.glytching.tranquil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.glytching.tranquil.mapping.JacksonMappingProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Disabled
public class TranquilTool {

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    this.objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @Test
  public void jsonWithSingleArrayField() throws IOException {
    File file =
        new File(getClass().getClassLoader().getResource("nobel-prize-dataset.json").getFile());

    String select =
        "firstname, surname, bornCountry as countryOfBirth, prizes.category[0] as prize, "
            + "prizes.year[0] as year, prizes.affiliations.country as countriesOfAffiliation";

    String where = "laureates.prizes[*].category='medicine' and laureates.gender='female'";

    System.out.println(
        Tranquil.using(new JacksonMappingProvider(objectMapper)).parse(file).read(select, where));
  }

  @Test
  public void jsonArray() throws IOException {
    File file = new File(getClass().getClassLoader().getResource("github-events.json").getFile());

    String select = "type, actor.login as who, repo.name as repo";

    String where = "type in ('PushEvent', 'DeleteEvent') and payload.ref is not null";

    System.out.println(
        Tranquil.using(new JacksonMappingProvider(objectMapper)).parse(file).read(select, where));
  }

  @Test
  public void matches() throws IOException {
    File file = new File(getClass().getClassLoader().getResource("nyc-citybike.json").getFile());

    String where =
        "stationBeanList[*].id = 281 or stationBeanList[*].stationName = 'Thompson St & Bleecker St'";

    System.out.println(Tranquil.parse(file).exists(where));
  }
}

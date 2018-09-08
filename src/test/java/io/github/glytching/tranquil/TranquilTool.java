package io.github.glytching.tranquil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.glytching.tranquil.mapping.JacksonMappingProvider;
import io.github.glytching.tranquil.mapping.XmlMappingProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Disabled
public class TranquilTool {

  @Test
  public void a() throws IOException {
    File file =
        new File(
            getClass().getClassLoader().getResource("nobel-prize-dataset-mini.json").getFile());

    System.out.println(Tranquil.parse(file).read("", "laureates.prizes[*].year='1901'"));
  }

  @Test
  public void b() throws IOException {
    File file =
        new File(
            getClass().getClassLoader().getResource("nobel-prize-dataset-mini.json").getFile());

    System.out.println(
        Tranquil.parse(file).read("firstname as name", "laureates.prizes[*].year='1901'"));
  }

  @Test
  public void c() throws IOException {
    File file =
        new File(
            getClass().getClassLoader().getResource("nobel-prize-dataset-mini.json").getFile());

    System.out.println(Tranquil.parse(file).read("firstname as name", ""));
  }

  @Test
  public void d() throws IOException {
    File file =
        new File(
            getClass().getClassLoader().getResource("nobel-prize-dataset-mini.json").getFile());

    System.out.println(Tranquil.parse(file).read("laureates.prizes.year", ""));
    System.out.println(Tranquil.parse(file).read("laureates.prizes.year as years", ""));
    System.out.println(Tranquil.parse(file).read("laureates.prizes.year", ""));
    System.out.println(Tranquil.parse(file).read("laureates.prizes[0].year as firstYear", ""));
    System.out.println(Tranquil.parse(file).read("laureates.prizes[*].year", ""));
    System.out.println(Tranquil.parse(file).read("1+1 as two", ""));
    System.out.println(Tranquil.parse(file).read("'me' as name", ""));
    System.out.println(Tranquil.parse(file).read("laureates.prizes[0].year + 50 as later", ""));
  }

  @Test
  public void e() throws IOException {
    File file = new File(getClass().getClassLoader().getResource("nyc-citybike.json").getFile());

    // numeric - literals (add, substract, multiply and divide) for literal pairs
    //     System.out.println(Tranquil.parse(file).read("1+1 as two", ""));
    //     System.out.println(Tranquil.parse(file).read("10-1 as nine", ""));
    //     System.out.println(Tranquil.parse(file).read("10 * 2 as twenty", ""));
    //     System.out.println(Tranquil.parse(file).read("10 / 2 as five", ""));

    // numeric - literals (add, substract, multiply and divide) for literal with accessor
    //    System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey, 5
    // + stationBeanList[2].statusKey as fivePlusKey", ""));
    //    System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey, 5
    // - stationBeanList[2].statusKey as fiveMinuskey", ""));
    //    System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey, 2
    // * stationBeanList[2].statusKey as fiveTimekey", ""));
    //    System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey,
    // 10 / stationBeanList[2].statusKey as fiveOverkey", ""));

    //      System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey,
    // 'Not ' + stationBeanList[2].statusValue as notStatusValue", "stationBeanList.id=281"));

    // TODO string literal concatenation
    //      System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey,
    //  'Mr ' + 'Bean' as stringLiteralPair", ""));
    //      System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey,
    //  stationBeanList[2].statusKey + '5' as keyPlusFiveLiteralAfter", ""));
    //      System.out.println(Tranquil.parse(file).read("stationBeanList[2].statusKey as statusKey,
    //  '5' + stationBeanList[2].statusKey as keyPlusFiveLiteralBefore", ""));
  }

  @Test
  public void f() {
    System.out.println(
        Tranquil.using(new XmlMappingProvider())
            .parse(
                "<laureates><laureate><id>1</id><firstname>Marie</firstname></laureate><laureate><id>2</id><firstname>Helmut</firstname></laureate></laureates>")
            //                "<laureate><id>1</id><firstname>Marie</firstname></laureate>")
            //            .parse(
            //
            // "<laureates><laureate><id>1</id><firstname>Marie</firstname></laureate><laureate><id>2</id><firstname>Helmut</firstname></laureate></laureates>")
            //            .read("", ""));
            .read("laureates.laureate.id as id", ""));
    //            .read("laureate.id", "laureate.firstname!='Marie'"));
  }

  @Test
  public void g() throws IOException {
    // "2018-09-03 01:05:03 PM"
    File file = new File(getClass().getClassLoader().getResource("nyc-citybike.json").getFile());

    System.out.println(Tranquil.parse(file).read("*", "stationBeanList[*].id=281"));
  }

  @Test
  public void h() throws IOException {
    File file = new File(getClass().getClassLoader().getResource("github-events.json").getFile());

    System.out.println(Tranquil.parse(file).read("*", "id='8214085055'"));
  }

  @Test
  public void i() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    System.out.println(
        Tranquil.using(new JacksonMappingProvider(objectMapper))
            .parse(
                "[\n"
                    + "  {\n"
                    + "    \"id\": \"8214085055\",\n"
                    + "    \"type\": \"CreateEvent\",\n"
                    + "    \"actor\": {\n"
                    + "      \"id\": 22006877,\n"
                    + "      \"login\": \"luis-ordering\",\n"
                    + "      \"display_login\": \"luis-ordering\",\n"
                    + "      \"gravatar_id\": \"\",\n"
                    + "      \"url\": \"https://api.github.com/users/luis-ordering\",\n"
                    + "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/22006877?\"\n"
                    + "    },\n"
                    + "    \"repo\": {\n"
                    + "      \"id\": 147423523,\n"
                    + "      \"name\": \"luis-ordering/app_1638\",\n"
                    + "      \"url\": \"https://api.github.com/repos/luis-ordering/app_1638\"\n"
                    + "    },\n"
                    + "    \"payload\": {\n"
                    + "      \"ref\": \"master\",\n"
                    + "      \"ref_type\": \"branch\",\n"
                    + "      \"master_branch\": \"master\",\n"
                    + "      \"description\": \"Use for phonegap build\",\n"
                    + "      \"pusher_type\": \"user\"\n"
                    + "    },\n"
                    + "    \"public\": true,\n"
                    + "    \"created_at\": \"2018-09-04T21:44:36Z\"\n"
                    + "  },\n"
                    + "  {\n"
                    + "    \"id\": \"8214085054\",\n"
                    + "    \"type\": \"CreateEvent\",\n"
                    + "    \"actor\": {\n"
                    + "      \"id\": 31450899,\n"
                    + "      \"login\": \"NSBENCAT\",\n"
                    + "      \"display_login\": \"NSBENCAT\",\n"
                    + "      \"gravatar_id\": \"\",\n"
                    + "      \"url\": \"https://api.github.com/users/NSBENCAT\",\n"
                    + "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/31450899?\"\n"
                    + "    },\n"
                    + "    \"repo\": {\n"
                    + "      \"id\": 147423538,\n"
                    + "      \"name\": \"NSBENCAT/NCATNSBEWebSite\",\n"
                    + "      \"url\": \"https://api.github.com/repos/NSBENCAT/NCATNSBEWebSite\"\n"
                    + "    },\n"
                    + "    \"payload\": {\n"
                    + "      \"ref\": null,\n"
                    + "      \"ref_type\": \"repository\",\n"
                    + "      \"master_branch\": \"master\",\n"
                    + "      \"description\": \"This is the website created for North Carolina A&T's chapter of NSBE. Here is a set of pages where students can learn about our chapter, learn about NSBE and ultimately figure out how to become a member.\",\n"
                    + "      \"pusher_type\": \"user\"\n"
                    + "    },\n"
                    + "    \"public\": true,\n"
                    + "    \"created_at\": \"2018-09-04T21:44:36Z\"\n"
                    + "  }]")
            .read("*", "id='8214085055'"));
  }
}

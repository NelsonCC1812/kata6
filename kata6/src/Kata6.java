import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import dto.ExchangeRate.ExchangeRate;
import dto.ExchangeRate.imp.JsonExchangeRate;
import dto.ExchangeRate.imp.XmlExchangeRate;

public class Kata6 {
    public static void main(String[] args) throws IOException, JAXBException {
        String json = read("https://api.exchangerate.host/latest");
        Gson gson = new Gson();

        ExchangeRate jsonER = gson.fromJson(json, JsonExchangeRate.class);
        ExchangeRate xmlER = gson.fromJson(json, XmlExchangeRate.class);

        System.out.println(jsonER);

        Marshaller marshaller = JAXBContext.newInstance(XmlExchangeRate.class).createMarshaller();
        marshaller.setProperty((Marshaller.JAXB_FORMATTED_OUTPUT), Boolean.TRUE);
        marshaller.marshal(xmlER, System.out);
    }

    private static String read(String url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            return reader.lines().collect(Collectors.joining(" "));
        }
    }
}
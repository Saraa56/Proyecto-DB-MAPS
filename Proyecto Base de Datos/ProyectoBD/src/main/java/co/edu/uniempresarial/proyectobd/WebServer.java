package co.edu.uniempresarial.proyectobd;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class WebServer {
    private static Map<String, Double> coordenadas = new HashMap<>();

    public static void main(String[] args) {
        port(4567);
        
        
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

       before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        post("/coordenadas", (req, res) -> {
            res.type("application/json");

            CoordenadasData data = new Gson().fromJson(req.body(), CoordenadasData.class);
            coordenadas.put("latitud", data.getLatitud());
            coordenadas.put("longitud", data.getLongitud());

            System.out.println("Coordenadas recibidas: " + coordenadas);

            return new Gson().toJson(coordenadas);
        });
    } 

    public static Map<String, Double> getCoordenadas() {
        return coordenadas;
    }
}

class CoordenadasData {
    private double latitud;
    private double longitud;

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}


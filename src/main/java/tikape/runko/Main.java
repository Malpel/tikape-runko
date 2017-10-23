package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothiet.db");

        AnnosDao annokset = new AnnosDao(database);
        RaakaAineDao raakaAineet = new RaakaAineDao(database);
        AnnosRaakaAineDao annosRaakaAine = new AnnosRaakaAineDao(database);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annokset.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAineet", raakaAineet.findAll());

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        Spark.post("/ainekset", (req, res) -> {
            RaakaAine raakaAine = new RaakaAine(-1, req.queryParams("aine"));
            raakaAineet.saveOrUpdate(raakaAine);

            res.redirect("/ainekset");
            return "";
        });

        Spark.get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothiet", annokset.findAll());
            map.put("raakaAineet", raakaAineet.findAll());

            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());

        Spark.post("/smoothiet", (req, res) -> {
            System.out.println(req.queryParams());
            if (req.queryParams().contains("smoothieNimi")) {
                System.out.println("YEAH");
                Annos annos = new Annos(-1, req.queryParams("smoothieNimi"));
                System.out.println(req.params());
                annokset.saveOrUpdate(annos);
            } else {
                System.out.println(req.queryParams("annos"));
                System.out.println(req.queryParams("raakaAine"));
                AnnosRaakaAine ara = new AnnosRaakaAine(
                        Integer.parseInt(req.queryParams("annos")),
                        Integer.parseInt(req.queryParams("raakaAine")),
                        Integer.parseInt(req.queryParams("jarjestys")),
                        req.queryParams("maara"),
                        req.queryParams("ohje"));
                annosRaakaAine.saveOrUpdate(ara);
            }

            res.redirect("/smoothiet");
            return "";

        });

        Spark.get("/smoothiet/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("smoothie", annokset.findOne(Integer.parseInt(req.params("id"))));

            AnnosRaakaAine kysytty = annosRaakaAine.findOne(Integer.parseInt(req.params("id")));
            raakaAineet.findAllById(kysytty.getAnnosId());

            map.put("raakaAineet", raakaAineet.findAllById(kysytty.getAnnosId()));

            return new ModelAndView(map, "smoothie");
        }, new ThymeleafTemplateEngine());

        /*
        

        get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());
         */
    }
}

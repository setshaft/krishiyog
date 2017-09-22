package models;

import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

import com.krishiyog.enums.RecordState;

public class Farmer extends AbstractKrishiyogModel {

    public String                                      name;

    private static JacksonDBCollection<Farmer, String> farmer = MongoDB.getCollection("farmers",
            Farmer.class, String.class);

    public Farmer() {
        super();
    }

    public Farmer(String name) {
        super();
        this.name = name;
        this.recordState = RecordState.ACTIVE;
    }

    public static List<Farmer> all() {
        return Farmer.farmer.find().toArray();
    }

    public static void create(Farmer farmer) {
        Farmer.farmer.save(farmer);
    }

    public static void create(String name) {
        create(new Farmer(name));
    }

    public static void delete(String id) {
        Farmer farmer = Farmer.farmer.findOneById(id);
        if (farmer != null) {
            farmer.recordState = RecordState.DELETED;
            Farmer.farmer.save(farmer);
        }
    }

    public static void removeAll() {
        Farmer.farmer.drop();
    }

    @Override
    public String toString() {
        return "Farmer [id=" + id + ", name=" + name + "]";
    }
}

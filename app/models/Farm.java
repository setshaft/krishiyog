package models;

import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

import com.krishiyog.enums.RecordState;
import com.krishiyog.pojos.CartesianPoints;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Farm extends AbstractKrishiyogModel {
    public String                                    farmerId;
    public List<CartesianPoints>                     boundaryPoints;

    private static JacksonDBCollection<Farm, String> farm = MongoDB.getCollection("farm",
                                                                  Farm.class, String.class);

    public Farm() {
        super();
    }

    public Farm(String farmerId, List<CartesianPoints> boundaryPoints) {
        super();
        this.farmerId = farmerId;
        this.boundaryPoints = boundaryPoints;
    }

    public static Farm getFarmById(String id) {
        Farm farm = Farm.getFarm().findOneById(id);
        return farm;
    }

    public static List<Farm> all() {
        DBObject query = new BasicDBObject();
        query.put("recordState", RecordState.ACTIVE);
        return Farm.getFarm().find(query).toArray();
    }

    public static Farm create(Farm farm) {
        Farm.getFarm().save(farm);
        return farm;
    }

    public static Farm create(String farmerId, List<CartesianPoints> boundaryPoints) {
        Farm farm = create(new Farm(farmerId, boundaryPoints));
        return farm;
    }

    public static void delete(String id) {
        Farm farm = Farm.getFarm().findOneById(id);
        if (farm != null) {
            farm.recordState = RecordState.DELETED;
            Farm.getFarm().save(farm);
        }
    }

    public static void removeAll() {
        Farm.getFarm().drop();
    }

    public static JacksonDBCollection<Farm, String> getFarm() {
        return farm;
    }

    public static void setFarm(JacksonDBCollection<Farm, String> farm) {
        Farm.farm = farm;
    }

    @Override
    public String toString() {
        return "Farm [farmerId=" + farmerId + ", boundaryPoints=" + boundaryPoints + "]";
    }
}

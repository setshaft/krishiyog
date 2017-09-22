package models;

import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

import com.krishiyog.enums.RecordState;
import com.krishiyog.enums.Seasons;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Crop extends AbstractKrishiyogModel {
    public Seasons                                   season;
    public String                                    cropName;
    public String                                    year;
    public String                                    fieldId;

    private static JacksonDBCollection<Crop, String> crop = MongoDB.getCollection("crop",
            Crop.class, String.class);

    public Crop() {
        super();
    }

    public Crop(Seasons season, String cropName, String year, String fieldId) {
        super();
        this.season = season;
        this.cropName = cropName;
        this.year = year;
        this.fieldId = fieldId;
    }

    public static List<Crop> all() {
        return Crop.getCrop().find().toArray();
    }

    public static Crop create(Crop crop) {
        Crop.getCrop().save(crop);
        return crop;
    }

    public static Crop create(Seasons season, String cropName, String year, String fieldId) {
        DBObject query = new BasicDBObject();
        query.put("season", season);
        query.put("year", year);
        query.put("fieldId", fieldId);
        Crop crop = Crop.getCrop().findOne(query);
        if (crop == null) {
            Crop crop1 = create(new Crop(season, cropName, year, fieldId));
            return crop1;
        }
        return null;
    }

    public static void delete(String id) {
        Crop crop = Crop.getCrop().findOneById(id);
        if (crop != null) {
            crop.recordState = RecordState.DELETED;
            Crop.getCrop().save(crop);
        }
    }

    public static void removeAll() {
        Crop.getCrop().drop();
    }

    public static Crop getCropById(String id) {
        Crop crop = Crop.getCrop().findOneById(id);
        return crop;
    }

    public static JacksonDBCollection<Crop, String> getCrop() {
        return crop;
    }

    public static void setCrop(JacksonDBCollection<Crop, String> crop) {
        Crop.crop = crop;
    }
}

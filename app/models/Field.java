package models;

import java.util.List;

import net.vz.mongodb.jackson.JacksonDBCollection;
import play.modules.mongodb.jackson.MongoDB;

import com.krishiyog.enums.RecordState;
import com.krishiyog.pojos.CartesianPoints;

public class Field extends AbstractKrishiyogModel {

    public String                                     farmerId;
    public String                                     farmId;
    public List<CartesianPoints>                      boundaryPoints;

    private static JacksonDBCollection<Field, String> field = MongoDB.getCollection("field",
            Field.class, String.class);

    public Field() {
        super();
    }

    public Field(String farmerId, String farmId, List<CartesianPoints> boundaryPoints) {
        super();
        this.farmerId = farmerId;
        this.farmId = farmId;
        this.boundaryPoints = boundaryPoints;
    }

    public static List<Field> all() {
        return Field.getField().find().toArray();
    }

    public static Field create(Field field) {
        Field.getField().save(field);
        return field;
    }

    public static Field create(String farmerId, String farmId, List<CartesianPoints> boundaryPoints) {
        Field field = create(new Field(farmerId, farmId, boundaryPoints));
        return field;
    }

    public static void delete(String id) {
        Field field = Field.getField().findOneById(id);
        if (field != null) {
            field.recordState = RecordState.DELETED;
            Field.getField().save(field);
        }
    }

    public static void removeAll() {
        Field.getField().drop();
    }

    public static Field getFieldById(String id) {
        Field field = Field.getField().findOneById(id);
        return field;
    }

    public static JacksonDBCollection<Field, String> getField() {
        return field;
    }

    public static void setField(JacksonDBCollection<Field, String> field) {
        Field.field = field;
    }
}

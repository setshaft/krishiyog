package models;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import com.krishiyog.enums.RecordState;

public class AbstractKrishiyogModel {

    @Id
    @ObjectId
    public String      id;
    public RecordState recordState;

    public AbstractKrishiyogModel() {
        super();
    }

    public AbstractKrishiyogModel(String id) {
        super();
        this.id = id;
        this.recordState = RecordState.ACTIVE;
    }
}

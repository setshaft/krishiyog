package controllers;

import models.Field;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.krishiyog.managers.FieldManager;
import com.krishiyog.pojos.requests.CreateFieldReq;
import com.krishiyog.pojos.requests.EditFieldReq;
import com.krishiyog.pojos.requests.GetFieldsReq;
import com.krishiyog.pojos.responses.CreateFieldRes;
import com.krishiyog.pojos.responses.EditFieldRes;
import com.krishiyog.pojos.responses.GetFieldsRes;

public class Fields extends Controller {

    public static Result createField() {

        Form<CreateFieldReq> createFieldForm = Form.form(CreateFieldReq.class).bindFromRequest();
        if (createFieldForm.hasErrors()) {
            return ok("missing parameters");
        }
        CreateFieldReq createFieldReq = createFieldForm.get();
        CreateFieldRes createFieldRes = FieldManager.createField(createFieldReq);
        return ok(createFieldRes.toString());
    }

    public static Result editField() {
        Form<EditFieldReq> editFieldForm = Form.form(EditFieldReq.class).bindFromRequest();
        if (editFieldForm.hasErrors()) {
            return ok("missing parameters");
        }
        EditFieldReq editFieldReq = editFieldForm.get();
        EditFieldRes editFieldRes = FieldManager.editField(editFieldReq);
        return ok(editFieldRes.toString());
    }

    public static Result deleteField(String id) {
        Field.delete(id);
        return ok("Successfully deleted Field");
    }

    public static Result getFields() {
        Form<GetFieldsReq> getFieldsForm = Form.form(GetFieldsReq.class).bindFromRequest();
        if (getFieldsForm.hasErrors()) {
            return ok("missing parameters");
        }
        GetFieldsReq getFieldsReq = getFieldsForm.get();
        GetFieldsRes getFieldsRes = FieldManager.getFields(getFieldsReq);
        return ok(getFieldsRes.toString());
    }
}

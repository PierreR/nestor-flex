package srv;

import com.google.inject.Inject;
import dao.Picker;
import entity.*;
import enu.Language;

import java.util.Set;

/**
 * Date: Nov 30, 2009
 */
public class PickerService extends Service<PickerEntity> {


    @Inject
    Picker dao;

    public Set<Recipient> listAllRecipients() {
        return dao.listAllRecipient();
    }

    public Set<Bureau> listAllBureaus() {
        return dao.listAllBureaus();
    }

    public Recipient findRecipientByName(String name, String languageToken) {

        switch (Language.valueOf(languageToken.toUpperCase())) {
            case FR:
                return dao.findFRRecipientByName(name);
            case NL:
                return dao.findNLRecipientByName(name);
            default:
                throw new EnumConstantNotPresentException(Language.class, languageToken);
        }

    }


}

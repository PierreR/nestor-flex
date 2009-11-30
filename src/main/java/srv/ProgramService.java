package srv;


import com.google.inject.Inject;
import entity.Program;
import enu.Language;

import java.util.Locale;
import java.util.Set;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class ProgramService extends Service<Program> {

    @Inject
    dao.Program dao;

    public Set<entity.Program> listAll() {
        return dao.listAll();
    }

    public entity.Program findByName(String name) {
        return dao.findByName(name);
    }

    public entity.Program findByRecipient(String recipientName, String languageToken) {
        switch(Language.valueOf(languageToken.toUpperCase())) {
            case FR:
                return dao.findByFRRecipient(recipientName);
            case NL:
                return  dao.findByNLRecipient(recipientName);
            default:
                throw new EnumConstantNotPresentException(Language.class, languageToken);
        }
    }



}

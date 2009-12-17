package nestor.srv;


import com.google.inject.Inject;
import nestor.entity.Program;
import nestor.enu.Language;

import javax.persistence.NoResultException;
import java.util.*;

/**
 * The service layer : should roughly match user stories
 * Date: Nov 18, 2009
 */
public class ProgramService extends Service<Program> {

    @Inject
    nestor.dao.Program dao;

    public Set<nestor.entity.Program> listAll() {
        return dao.listAll();
    }

    public nestor.entity.Program findByName(String name) {

        try {
            return dao.findByName(name);
        } catch (NoResultException e) {
            //TODO i18n
            // we need to decide where it is best to handle internationalization, on the client or the server side.
            // does it make sense for the server to always know about the locale of the user that makes the request ?
            // if so we need to know about the user here
            throw new RuntimeException("No program with name \"" + name + "\" has been found in the database");

        }
    }


    public nestor.entity.Program findByRecipient(String recipientName, String languageToken) {
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

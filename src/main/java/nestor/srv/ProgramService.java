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

    @Override
    /**
     * Here comes a huge flex dependency ! We have to manually deserialize avoiding the pain ...
     */
    public Program update(Program program) {
        Set plannings = new HashSet(program.getPlannings());
        program.setPlannings(new HashSet<Program.Planning>());
        for (Object p : plannings) {
            HashMap _planning = (HashMap) p;
            Program.Planning planning = new Program.Planning();
            planning.setId((Integer) _planning.get("id"));
            planning.setDate((Date) _planning.get("date"));
            planning.setName((String) _planning.get("name"));
            program.addPlanning(planning);
        }
        return super.update(program);
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

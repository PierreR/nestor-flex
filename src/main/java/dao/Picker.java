package dao;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;
import entity.Bureau;
import entity.Recipient;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: Nov 28, 2009
 */
public interface Picker {

    @Finder(query = "from Bureau", returnAs = HashSet.class)
    public Set<Bureau> listAllBureaus();

    @Finder(query = "from Recipient", returnAs = HashSet.class)
    public Set<Recipient> listAllRecipient();

    @Finder(query = "from Recipient r where r.name_nl = :name")
    Recipient findNLRecipientByName(@Named("name") String name);

    @Finder(query = "from Recipient r where r.name_fr = :name")
    Recipient findFRRecipientByName(@Named("name") String name);
}

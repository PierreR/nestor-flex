package nestor.dao;

import com.google.inject.name.Named;
import com.wideplay.warp.persist.dao.Finder;
import nestor.entity.Bureau;
import nestor.entity.ContractType;
import nestor.entity.Recipient;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: Nov 28, 2009
 */
public interface Picker {

    @Finder(query = "from Bureau", returnAs = HashSet.class)
    Set<Bureau> listAllBureaus();

    @Finder(query = "from Recipient", returnAs = HashSet.class)
    Set<Recipient> listAllRecipient();

    @Finder(query = "from ContractType", returnAs = HashSet.class)
    Set<ContractType> listAllContractTypes();

    @Finder(query = "from Recipient r where r.name_nl = :name")
    Recipient findNLRecipientByName(@Named("name") String name);

    @Finder(query = "from Recipient r where r.name_fr = :name")
    Recipient findFRRecipientByName(@Named("name") String name);


}

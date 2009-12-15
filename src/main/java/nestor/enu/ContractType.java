package nestor.enu;

/**
 * Not used !!
 *
 * It is really not a good idea to implement this as enums.
 * First it is less flexible because completely static
 * Second there is no way to serialize an enum JAVA into an enum AS
 *   This means you have to duplicate the static info on the ria side and send strings across
 *
 * Date: Nov 24, 2009
 */
public enum ContractType {
    AREA_CONTRACT {
        @Override
        public String toString() {
            return "Contrat de quartier";
        }
    },

    REGION_CONTRACT {
        @Override
        public String toString() {
            return "Contrat de quartier";
        }
    }
}

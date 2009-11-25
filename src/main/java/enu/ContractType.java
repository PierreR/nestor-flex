package enu;

/**
 * Is this a translatable enum ?
 * Date: Nov 24, 2009
 */
public enum ContractType {
    AREA_CONTRACT {
        @Override
        public String toString() {
            return "Contrat de quartier";
        }
    }

    
}

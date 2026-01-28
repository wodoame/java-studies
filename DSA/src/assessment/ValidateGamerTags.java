package assessment;

public class ValidateGamerTags {
    static void main() {
       validateTag("TSM_ninja99");
        validateTag("pro");
        validateTag("FaZe1!");
        validateTag("PLAYER1");
        validateTag("cool_guy99");
    }
    static void validateTag(String tag){
        int criteriaMet = 0;
        boolean[] criteria = {false, false, false, false, false};
        String[] criteriaNames = {"Length", "Clan", "Name", "Rank", "Icon"};
        // criteria 1: Length
        if(tag.length() >= 6){
            criteriaMet++;
            criteria[0] = true;
        }
        // criteria 2: Clan
        if(tag.matches(".*[A-Z].*")){
            criteriaMet++;
            criteria[1] = true;
        }

        // criteria 3: Name
        if(tag.matches(".*[a-z].*")){
            criteriaMet++;
            criteria[2] = true;
        }

        // criteria 4: Rank
        if(tag.matches(".*[0-9].*")){
            criteriaMet++;
            criteria[3] = true;
        }

        // criteria 5: Icon
        if(tag.matches(".*[\\[!@#$%^&*()_+=\\]{}|;:,.<>?-].*")){
            criteriaMet++;
            criteria[4] = true;
        }
        if(criteriaMet == 5) System.out.println("GOLD");
        else if(criteriaMet >= 3 && criteria[0])printMissing("SILVER", criteria, criteriaNames);
        else printMissing("BRONZE", criteria, criteriaNames);
    }

    static void printMissing(String tier, boolean[] criteria, String[] criteriaNames){
        System.out.println(tier);
        System.out.print("Missing: ");
        for (int i = 0; i < criteria.length; i++) {
            if(!criteria[i]) System.out.print(criteriaNames[i] + " ");
        }
        System.out.println();
    }
}

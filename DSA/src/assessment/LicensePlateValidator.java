package assessment;

public class LicensePlateValidator {
    // GH-ABC123
    // abc123
    // GH ABC123
    // GH_ABC123
    private void validatePlate(String plate){
        // Check for character length
        if(plate.length() < 6){
            System.out.println(plate + ": Wrong character count");
            return;
        }
        plate = plate.replaceAll("[\\s-_]", "");

        // Check for invalid character
        if(!plate.matches("[A-Za-z0-9]+")){
           return;
        }

        if(plate.length() == 8){
            String region = plate.substring(0, 2);
            String main = plate.substring(2);
            if(validateRegion(region) && validateMain(main)){
                System.out.println(region + '-' + main);
                return;
            }
            System.out.println("Invalid Region");
        }
        else if(plate.length() == 6 && validateMain(plate)){
            System.out.println();
        }
    }

    static boolean validateRegion(String plate){
        return true;
    }
    static boolean validateMain(String plate){
        return true;
    }

}

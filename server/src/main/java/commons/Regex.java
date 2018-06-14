package commons;

import java.util.regex.Pattern;

public abstract class Regex {
    public static Boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("^(0|\\+33)[1-9]([-. ]?[0-9]{2}){4}$");
        return pattern.matcher(phone).find();
    }

    public static Boolean isMailValid(String mail) {
        Pattern pattern = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
        return pattern.matcher(mail).find();
    }

    public static Boolean isSiretValid(String siret) {
        Pattern pattern = Pattern.compile("^((RCS)?([0-9]{3}){3}$|^([0-9]{3}){3}[0-9]{5})|\\s*$");
        return pattern.matcher(siret).find();
    }

    public static Boolean isWebsiteUrlValid(String websiteUrl) {
        Pattern pattern = Pattern.compile("^((http://|https://)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?)|\\s*$");
        return pattern.matcher(websiteUrl).find();
    }

    public static Boolean isFrenchPostalCodeValid(String postalCode) {
        Pattern pattern = Pattern.compile("^([0-9]){5}$");
        return pattern.matcher(postalCode).find();
    }
}
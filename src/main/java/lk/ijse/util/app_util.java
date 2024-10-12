package lk.ijse.util;

import java.util.Base64;
import java.util.UUID;

public class app_util {
    public static String generateItemCode(){
        return "Item"+ UUID.randomUUID();
    }

    public static String generateCustomerId(){
        return "Customer"+ UUID.randomUUID();
    }

    public static String generateOrderId(){
        return "Customer"+ UUID.randomUUID();
    }
    public static String generateOrderDetailsId(){
        return "OD"+ UUID.randomUUID();
    }

    public static String profilePicToBase64(byte[] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}

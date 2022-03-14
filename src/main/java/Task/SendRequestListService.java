package Task;

import java.util.Random;
import java.util.Set;

public class SendRequestListService {
    public String generateReference(){
        var rand = new Random().nextInt(1000);
        return String.valueOf(rand);
    }

    public boolean sendAll (Set<Message> messageSet){
        if (messageSet != null){
            return true;
        } else return false;
    }

}

package A;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Tedikova O.
 * @version 1.0
 */
public class Friends {

    private static HashMap<String, Map<String, Message>> chat;
    private static int d;

    public static void main(String[] args) {
        chat = new HashMap<String, Map<String, Message>>();
        Scanner scanner = new Scanner(System.in);
        String[] firstString = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstString[0]);
        d = Integer.parseInt(firstString[1]);
        for (int i = 0; i < n; i++) {
            String[] event = scanner.nextLine().split(" ");
            String[] users = new String[]{event[0], event[1]};
            Message message = new Message(Long.parseLong(event[2]));
            processNewMessage(users, message);
        }


    }

    private static class Message {
        private long time;
        private byte relation;

        public Message(long time) {
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public byte getRelation() {
            return relation;
        }

        public void setRelation(byte relation) {
            this.relation = relation;
        }
    }

    private static Message lookUp(String user1, String user2) {
        if (chat.get(user1) != null) {
            return chat.get(user1).get(user2);
        } else {
            return null;
        }
    }

    private static String[] orderUsers(String[] users, Message message) {
        String[] sortedUsers = new String[2];
        if (users[0].compareTo(users[1]) <= 0) {
            message.setRelation((byte) 1);
            sortedUsers[0] = users[0];
            sortedUsers[1] = users[1];
        } else {
            message.setRelation((byte) 2);
            sortedUsers[0] = users[1];
            sortedUsers[1] = users[0];
        }
        return sortedUsers;
    }

    private static void processNewMessage(String[] users, Message message) {
        String[] sortedUsers = orderUsers(users, message);
        String user1 = sortedUsers[0];
        String user2 = sortedUsers[1];
        Message savedMessage = lookUp(user1, user2);
        if (savedMessage != null) {
            byte resultMask = (byte) (savedMessage.getRelation() + message.getRelation());
            if (message.getTime() - savedMessage.getTime() <= d && resultMask == (byte) 3) {
                savedMessage.setRelation(resultMask);
                System.out.println(user1 + " " + user2);
            } else {
                savedMessage.setRelation(message.getRelation());
            }
            savedMessage.setTime(message.getTime());
        } else {
            putNewMessage(user1, user2, message);
        }
    }

    private static void putNewMessage(String user1, String user2, Message message) {
        Map<String, Message> childMap = new HashMap<String, Message>();
        childMap.put(user2, message);
        chat.put(user1, childMap);
    }
}

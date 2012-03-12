package A;

import java.util.*;

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
            processNewMessage(users, Long.parseLong(event[2]));
        }
    }

    private static class Message {
        private long time;
        private byte relation;

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
        if (chat.get(user1) == null) {
            Map<String, Message> childMap = new HashMap<String, Message>();
            childMap.put(user2, new Message());
            chat.put(user1, childMap);
        }
        return chat.get(user1).get(user2);
    }

    private static void processNewMessage(String[] users, long time) {
        byte mask = users[0].compareTo(users[1]) <= 0 ? (byte) 1 : (byte) 2;
        Arrays.sort(users, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String user1 = users[0];
        String user2 = users[1];
        Message savedMessage = lookUp(user1, user2);
        boolean delta = (time - savedMessage.getTime()) <= d;

        savedMessage.setRelation(delta ? (byte) (savedMessage.getRelation() | mask) : mask);
        savedMessage.setTime(time);

        if (savedMessage.getRelation() == 0x3) {
            savedMessage.setRelation((byte) 0xFF);
            System.out.println(user1 + " " + user2);
        }
    }

}

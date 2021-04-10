package ufs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并查集试水：统计独立用户
 */
public class Code02_CountDistinctUser {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User u1 = new User("1234", "0068", "a@qq.com");
        User u2 = new User("1234", "1234", "b@qq.com");
        User u3 = new User("1122", "0068", "c@qq.com");
        User u4 = new User("9527", "0000", "d@qq.com");
        User u5 = new User("1111", "1111", "1111@qq.com");
        User u6 = new User("4321", "2222", "c@qq.com");

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);

        int num = process(users);
        System.out.println("num = " + num);
    }

    private static int process(List<User> users) {
        if (users == null) {
            return 0;
        }

        Code01_UnionFindSet.UnionFindSet<User> unionFindSet = new Code01_UnionFindSet.UnionFindSet<>();


        Map<String, User> mapA = new HashMap<>();
        Map<String, User> mapB = new HashMap<>();
        Map<String, User> mapC = new HashMap<>();

        for (User user : users) {
            unionFindSet.add(user);

            String id = user.id;
            User userById = mapA.get(id);
            if (userById != null) {
                unionFindSet.union(user, userById);
                continue;
            }
            mapA.put(id, user);

            String phone = user.phone;
            User userByPhone = mapB.get(phone);
            if (userByPhone != null) {
                unionFindSet.union(user, userByPhone);
                continue;
            }
            mapB.put(phone, user);

            String email = user.email;
            User userByEmail = mapC.get(email);
            if (userByEmail != null) {
                unionFindSet.union(user, userByEmail);
                continue;
            }
            mapC.put(email, user);
        }

        return unionFindSet.groupNum();
    }

    private static class User {
        public String id;
        public String phone;
        public String email;

        public User(String id, String phone, String email) {
            this.id = id;
            this.phone = phone;
            this.email = email;
        }
    }
}

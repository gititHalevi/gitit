package com.example.user.exercise_5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Users {

    private static Users users;
    private Map<String, User> userMap;

    private Users(){
        userMap = new HashMap<>();
    }

    public static Users getUsers(){
        if (users == null)
            users = new Users();
        return users;
    }

    public boolean signUp(User user){
        if (!userMap.containsKey(user.getUserName())){
            userMap.put(user.getUserName(), user);
            return true;
        }

        return false;
    }

    public boolean login(User user){
        if (userMap.containsKey(user.getUserName()))
            return user.getPassword().equals(userMap.get(user.getUserName()).getPassword());
        return false;
    }

    public void loadUsers(Set<String> usersAsString){
        for (String userAsString: usersAsString){
            User user = new User(userAsString);
            userMap.put(user.getUserName(),user);
        }
    }
    public Set<String> getUsersAsString(){
        Set<String> set = new HashSet<>();
        for (User user: userMap.values()){
            set.add(user.toString());
        }
        return set;
    }
}

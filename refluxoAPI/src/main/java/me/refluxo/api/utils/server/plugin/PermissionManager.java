package me.refluxo.api.utils.server.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PermissionManager {
    private static HashMap<String, List<String>> perms = new HashMap<>();
    private static String project;
    private static List<String> permList;

    public PermissionManager(String project) {
        this.project = project;
        if(!perms.containsKey(project)){
            permList = new ArrayList<>();
        } else {
            permList = perms.get(project);
        }
    }

    public void addPermission(String permission) {
        permList.add(permission);
    }

    public void build() {
        perms.put(project, permList);
    }

    public static List<String> getPermissions(String project) {
        if(perms.containsKey(project)) {
            return perms.get(project);
        } else {
            List<String> list = new ArrayList<>();
            list.add("no_permissions");
            return list;
        }
    }

    public static List<String> getProjects() {
        Set<String> set = perms.keySet();
        List<String> list = new ArrayList<>();
        for(String s : set) {
            list.add(s);
        }
        return list;
    }

}

package me.refluxo.api.bungeecord.utils.files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileBuilder {

    private final File file;
    private Configuration yml;

    public FileBuilder(String path) {
        String s = path;
        if(!s.contains("plugins/")){
            s = "plugins/API/"+path;
        }
        file = new File(s);
        try {
            yml = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getYaml() {
        return yml;
    }

    public File getFile() {
        return file;
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(yml, file);
        } catch (IOException ignored) {
        }
    }

}

package me.refluxo.api.spigot.utils.pets.pet;

import me.refluxo.api.spigot.utils.PlayerHead;
import me.refluxo.api.spigot.utils.mysql.MySQLService;
import me.refluxo.api.spigot.utils.pets.PetManager;
import me.refluxo.api.spigot.utils.pets.PetTypes;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet {

    private final Player p;
    private final MySQLService mySQLService = new MySQLService();
    private final Connection con = mySQLService.getConnection();
    private Entity pet;
    private ArmorStand petArmorStand;

    public Pet(Player p) {
        this.p = p;
    }

    public PetTypes getPet() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT pet FROM pets WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                return PetTypes.valueOf(rs.getString("pet"));
            } else {
                PetManager.check(p);
                return getPet();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PetManager.check(p);
        return getPet();
    }

    public Types getPetType() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT petType FROM pets WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                return Types.valueOf(rs.getString("petType"));
            } else {
                PetManager.check(p);
                return getPetType();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PetManager.check(p);
        return getPetType();
    }

    public void spawnPet() {
        if(petIsActive()) {
            if(pet != null) {
                return;
            }
        }
        if(!petIsActive()) {
            setPetActive(ActiveTypes.TRUE);
        }
        if(PetDB.getPetEntityID(p) != -1 || PetDB.getPetEntity(p) != null) {
            return;
        }
        Entity en = p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        ArmorStand armorStand = (ArmorStand) en;
        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        en.getLocation().setDirection(p.getLocation().subtract(en.getLocation()).toVector());
        armorStand.setGravity(false);
        armorStand.setAI(false);
        armorStand.setVisible(false);
        armorStand.setCollidable(false);
        armorStand.setInvulnerable(true);
        String pt = "";
        switch (getPet()) {
            case CAT:
                switch (getPetType()) {
                    case IVORY:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjUxYjE3ZDdkZWQ2YzdlOGYzYjJkYWMxMjM3OGE2ZmM0ZTkyMjhiOTExOTg2ZjY0YzhhZjQ1ODM3YWU2ZDllMSJ9fX0=";
                        break;
                    case ONYX:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU5MjQxMTVkM2E4YmJhY2ZkNGZhZmI2Y2M3MGY5OWEyZjc1ODBlNDU4M2E1MGZhOWI5YzI4NWE5OGFjMGM1NiJ9fX0=";
                        break;
                }
                break;
            case STONE:
                pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2IyYjVkNDhlNTc1Nzc1NjNhY2EzMTczNTUxOWNiNjIyMjE5YmMwNThiMWYzNDY0OGI2N2I4ZTcxYmMwZmEifX19";
                break;
            case AXOLOTL:
                switch (getPetType()) {
                    case GOLD:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIwZTgxNzU3MThlYWMzOTY3MmU5ZGM2YzRlZWNjMGEzMmI0YzYyOTcwZDQ2YWJmNTIxN2FjNjUyYzU2ZjMxNyJ9fX0=";
                        break;
                    case BLUE:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQwMDU2NjIyMzU5NzRjZmRhZjAzZmY1MTg0ZDM0NWM0MDlmN2YyNWEwYTBkMGZjN2FiODdjNTk3MWIwZjQ0OCJ9fX0=";
                        break;
                    case CYAN:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWE4NTAyZjgwODBmYWUwNTE3ZWUxZDU4NDc5ZDE1OWEwMzdhYjljNDZhZjlkNzExZTJhNWU4NjBlZTdiNjM3NSJ9fX0=";
                        break;
                    case GREEN:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQ0NWNkNjNjNjQ1NGI1YTg0Yjg1NmUzZjJiNzAwMjE3ZDQ2ODhiYjM3NWRkZTE0MjdjZTVlNTNmOTA1MDBhMSJ9fX0=";
                        break;
                    case LUCY:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjRhZDExYWNkMjkzYTNmMzk0NGQ4ZGUwM2YwNWU3MzFiOGYwMmQ5OWNlMGMyYTY4NWJlNzA1YzU2MjY0NzYxYSJ9fX0=";
                        break;
                    case WILD:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWExMGJjNGYzNjhlMTJjY2JkZWE0NjA2ZDJkM2ZiMjE5ZTkyNjM0MTA1ZDE5NzRkZTcxMGQxZmRiOWIwMjlhYyJ9fX0=";
                        break;
                    case BLACK:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWY1NjFmNDAzYTljMjdhNWU3MmE0Njg0MzY5MGViNDM3ODg1MjI5YTlkZTZjMjVlYmMyMWI5M2U2ZmRjZDliZCJ9fX0=";
                        break;
                }
                break;
            case TURTLE:
                pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg0ZjRjNWJjYzUxNTVhNjNkNTViM2MwZGYwZGYxMDBlZTU4M2E0NGZkY2EyM2Q3ZWE1MzU0YzdiYThlZTA0MiJ9fX0=";
                break;
            case GORILLA:
                pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNlYjNlMzdlOTg3M2JmYzE3NmI5ZWQ4ZWY0ZmJlZjgzM2RlMTQ0NTQ2YmZhZWZkZjI0ODYzYzNlYjg3YmI4NiJ9fX0=";
                break;
            case BABY_YODA:
                pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ0MTBkNDAzMjEwM2IwNzU1ZmIwMTY3OTEwOWYxMjk5MTE4MjJiNDdiYmU0YjUzM2IyNTYxYzc3ZmNmZDBhZiJ9fX0=";
                break;
            case ELEPHANT:
                switch (getPetType()) {
                    case MONOCHROME:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJkZjBmNjI4YzA1ZTg2Y2FiZGVlMmY1ODU4ZGQ1ZGVmN2Y4YjhkOTQwY2JmMjVmOTkzN2UyZmZiNTM0MzJmNCJ9fX0=";
                        break;
                    case GREEN:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYwYzEyMmFkZTViMmZlZGNhMTRhYTc4YzgzNGE3YjBhYzljYjVkYTJhMGM5MzExMjE2MzA4NmY5MGMxM2I2OCJ9fX0=";
                        break;
                    case PURPLE:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZmOWRmMjkwYjZjNWE0OTg0ZmM2ZTUxNjYwNWY5ODE2Yjk4ODJmN2JmMDRkYjA4ZDNmN2VlMzJkMTk2OWE0NCJ9fX0=";
                        break;
                    case RED:
                        pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE1YzY2ZWM2NmNiNmI0YjU1NTAwODVmNTgzYjRlNWMxY2VlNTI0N2JlYzVmYmNjNWMzMThjMzBjNjZjYWI0MiJ9fX0=";
                        break;
                }
                break;
            case NONE:
                pt = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWYxMzE5N2M2YTdjZjUyNTcwZmU1NjRhYWI5OTQ0YTkwNDNiZGY3ZDk1NzQ3OTMxMWVjZTAyMjVlMWQzOGRmNCJ9fX0=";
                break;
        }
        armorStand.setHelmet(PlayerHead.getItemStackWithTexture(pt));
        pet = en;
        petArmorStand = armorStand;
        PetDB.addPet(p, pet);
    }

    public Entity getPetEntity() {
        return pet;
    }

    public ArmorStand getPetArmorStand() {
        return petArmorStand;
    }

    public void loadPet() {
        pet = PetDB.getPetEntity(p);
        petArmorStand = PetDB.getPetArmorStand(p);
    }

    public void savePet() {
        PetDB.addPet(p, pet);
    }

    public void deSpawnPet() {
        if(pet != null) {
            pet.remove();
        }
        PetDB.removePet(p);
        if(petIsActive()) {
            setPetActive(ActiveTypes.FALSE);
        }
    }

    public int getPetEntityID() {
        return pet.getEntityId();
    }

    public boolean petIsActive() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT active FROM pets WHERE uuid = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = mySQLService.getResult(ps);
            if(rs.next()) {
                switch (rs.getString("active")) {
                    case "TRUE":
                        return true;
                    case "FALSE":
                        return false;
                }
            } else {
                PetManager.check(p);
                return petIsActive();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PetManager.check(p);
        return petIsActive();
    }

    public void setPetActive(ActiveTypes enabled) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE pets SET active = ? WHERE uuid = ?");
            ps.setString(1, enabled.name());
            ps.setString(2, p.getUniqueId().toString());
            mySQLService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (enabled) {
            case TRUE:
                checkFirmity();
                if(pet.isDead() || pet == null) {
                    spawnPet();
                }
                break;
            case FALSE:
                deSpawnPet();
                break;
        }
    }

    public void setPet(PetTypes pet) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE pets SET pet = ? WHERE uuid = ?");
            ps.setString(1, pet.name());
            ps.setString(2, p.getUniqueId().toString());
            mySQLService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        checkFirmity();
    }

    public void setPetType(Types type) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE pets SET petType = ? WHERE uuid = ?");
            ps.setString(1, type.name());
            ps.setString(2, p.getUniqueId().toString());
            mySQLService.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deSpawnPet();
        checkFirmity();
        spawnPet();
    }

    private void checkFirmity() {
        if(petIsActive()) {
            if(PetDB.getPetEntity(p) == null) {
                deSpawnPet();
            }
            switch (getPet()) {
                case CAT:
                    switch (getPetType()) {
                        case IVORY:
                        case ONYX:
                            break;
                        case WILD:
                        case RED:
                        case BLUE:
                        case CYAN:
                        case GOLD:
                        case LUCY:
                        case NONE:
                        case BLACK:
                        case GREEN:
                        case PURPLE:
                        case MONOCHROME:
                            deSpawnPet();
                            setPetType(Types.IVORY);
                            spawnPet();
                            break;
                    }
                    break;
                case BABY_YODA:
                    setPetType(Types.NONE);
                    break;
                case GORILLA:
                    setPetType(Types.NONE);
                    break;
                case TURTLE:
                    setPetType(Types.NONE);
                    break;
                case AXOLOTL:
                    switch (getPetType()) {
                        case GOLD:
                        case BLUE:
                        case CYAN:
                        case GREEN:
                        case LUCY:
                        case WILD:
                        case BLACK:
                            break;
                        case MONOCHROME:
                        case PURPLE:
                        case RED:
                        case ONYX:
                        case NONE:
                        case IVORY:
                            deSpawnPet();
                            setPetType(Types.GOLD);
                            spawnPet();
                            break;
                    }
                    break;
                case ELEPHANT:
                    switch (getPetType()) {
                        case MONOCHROME:
                        case GREEN:
                        case PURPLE:
                        case RED:
                            break;
                        case IVORY:
                        case BLACK:
                        case NONE:
                        case ONYX:
                        case LUCY:
                        case GOLD:
                        case CYAN:
                        case BLUE:
                        case WILD:
                            deSpawnPet();
                            setPetType(Types.MONOCHROME);
                            spawnPet();
                            break;
                    }
                    break;
                case STONE:
                    setPetType(Types.NONE);
                    break;
                case NONE:
                    deSpawnPet();
                    setPetActive(ActiveTypes.FALSE);
                    break;
            }
        }
    }

}

package ersha.qq1508444061.youyi.Util;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public final class FlansModUtils {

        public static Player getShooter(Entity entity) {
                if(entity == null) {
                        return null;
                } else {

                        net.minecraft.server.v1_7_R4.Entity bullet = ((CraftEntity)entity).getHandle();
                        net.minecraft.server.v1_7_R4.Entity shooter = null;
                        if(isBullet(entity)) {
                                try {
                                        shooter = (net.minecraft.server.v1_7_R4.Entity)bullet.getClass().getDeclaredField("owner").get(bullet);
                                } catch (Exception var5) {
                                        return null;
                                }
                        }

                        if(isGrenade(entity)) {
                                try {
                                        shooter = (net.minecraft.server.v1_7_R4.Entity)bullet.getClass().getDeclaredField("thrower").get(bullet);
                                } catch (Exception var4) {
                                        return null;
                                }
                        }

                        return shooter != null?(Player)shooter.getBukkitEntity():null;
                }
        }

        public static boolean isGrenade(Entity entity) {
                net.minecraft.server.v1_7_R4.Entity bullet = ((CraftEntity)entity).getHandle();
                return bullet.getClass().getSimpleName().equals("EntityGrenade");
        }

        public static boolean isBullet(Entity entity) {
                net.minecraft.server.v1_7_R4.Entity bullet = ((CraftEntity)entity).getHandle();
                return bullet.getClass().getSimpleName().equals("EntityBullet");
        }
}

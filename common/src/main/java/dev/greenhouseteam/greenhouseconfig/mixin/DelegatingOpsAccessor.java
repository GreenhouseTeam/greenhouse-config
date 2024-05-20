package dev.greenhouseteam.greenhouseconfig.mixin;

import com.mojang.serialization.DynamicOps;
import net.minecraft.resources.DelegatingOps;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DelegatingOps.class)
public interface DelegatingOpsAccessor<T> {
    @Accessor("delegate") @Final
    DynamicOps<T> greenhouseconfig$getDelegate();
}
